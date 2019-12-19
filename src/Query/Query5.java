package Query;

import BNL.BlockNL;
import BNL.BlockTool;
import Base.ReadJoin;
import Base.WriteToFile;
import HashJoin.MyTool;
import HashJoin.ReadTable;
import HashJoin.HashJoin;
import INLJ.IndexBplusTreeJoin;
import Merge.MergeJoin_2;
import Tools.Data_creation;

import java.io.IOException;
import java.util.ArrayList;

/*
 * 查询外卖员评分低于6分的外卖员姓名
 * Select emp.employee_name
 * from Employee emp, Evaluation eva
 * where emp.employee_id = eva.employee_id and eva.employee_grade < 6 ;
 */
public class Query5 {

    public static void main(String[] args) throws IOException {

        String inputPath1 = "C:\\Users\\admin\\Desktop\\Dave\\dbtuning\\DBtuning\\employee.txt";
        String inputPath2 = "C:\\Users\\admin\\Desktop\\Dave\\dbtuning\\DBtuning\\evaluation.txt";

        String indexOutPath1 = "C:\\Users\\admin\\Desktop\\Dave\\dbtuning\\DBtuning\\Query5\\Index\\join.txt";
        String indexOutPath2 = "C:\\Users\\admin\\Desktop\\Dave\\dbtuning\\DBtuning\\Query5\\Index\\result.txt";
        String MergeOutPath1 = "C:\\Users\\admin\\Desktop\\Dave\\dbtuning\\DBtuning\\Query5\\Merge\\join.txt";
        String MergeOutPath2 = "C:\\Users\\admin\\Desktop\\Dave\\dbtuning\\DBtuning\\Query5\\Merge\\result.txt";
        String BNLoutPath1 = "C:\\Users\\admin\\Desktop\\Dave\\dbtuning\\DBtuning\\Query5\\BNL\\join.txt";
        String BNLoutPath2 = "C:\\Users\\admin\\Desktop\\Dave\\dbtuning\\DBtuning\\Query5\\BNL\\result.txt";
        String HashOutPath1 = "C:\\Users\\admin\\Desktop\\Dave\\dbtuning\\DBtuning\\Query5\\Hash\\join.txt";
        String HashOutPath2 = "C:\\Users\\admin\\Desktop\\Dave\\dbtuning\\DBtuning\\Query5\\Hash\\result.txt";


        ArrayList<String> ans = new ArrayList<String>();
//        Data_creation.create_Employee(inputPath1);
//        Data_creation.create_Evaluation(inputPath2);


        //Block Nested Loops join
        BlockTool.nowLocation = 0;
        int bufferSize = 50;

        long startTime = System.currentTimeMillis();
        BlockNL.BNLJoin(inputPath1, 0, inputPath2, 2, BNLoutPath1, bufferSize);  //嵌套循环连接，结果输出到文件
        ReadJoin.nowLocation = 0;
        ReadJoin.flag = 0;
        ReadJoin readtool = new ReadJoin();
        String[] BNLTuples = new String[1000000];
        while (true) {
            if (ReadJoin.flag == 1) break;
            BNLTuples = readtool.readBufferFile(BNLoutPath1);
            for (String tuple : BNLTuples) {
                if (tuple == null) break;
                String[] temp = tuple.split("\\s+");
                if (Integer.parseInt(temp[11]) < 6) {
                    ans.add(tuple);
                }
            }
        }

        long endTime = System.currentTimeMillis();
        System.out.println("Query5 Block nest loop join 耗时=" + (endTime - startTime) + "ms");
        if (ans.size() != 0) {
            WriteToFile.writeBufferToFile(BNLoutPath2, ans);
            ans.clear();
        }


        //Index Nested Loops Join
        long startTime2 = System.currentTimeMillis();
        //B+index连接，结果输出到文件indexOutPath1
        IndexBplusTreeJoin.IndexBplusTreeJoin(inputPath1, 0, inputPath2, 2, indexOutPath1);

        ReadJoin.nowLocation = 0;
        ReadJoin.flag = 0;
        ReadJoin readtool2 = new ReadJoin();
        String[] indexBplusTreeTuples = new String[1000000];
        while (true) {
            if (ReadJoin.flag == 1) break;
            indexBplusTreeTuples = readtool2.readBufferFile(indexOutPath1);
            for (String tuple : indexBplusTreeTuples) {
                if (tuple == null)
                    break;
                String[] temp = tuple.split("\\s+");
                //从Join结果中筛选出外卖员评分低于6分的外卖员姓名
                if (Integer.parseInt(temp[11]) < 6) {
                    ans.add(tuple);
                }
            }
        }

        long endTime2 = System.currentTimeMillis();
        System.out.println("Query5 indexB+ join 耗时=" + (endTime2 - startTime2) + "ms");
        if (ans.size() != 0) {
            WriteToFile.writeBufferToFile(indexOutPath2, ans);
            ans.clear();
        }


        //Merge Join
        long startTime3 = System.currentTimeMillis();
        MergeJoin_2.MergeJoin(inputPath2, inputPath1, MergeOutPath1); //排序连接，结果写入文件
        ReadJoin.nowLocation = 0;
        ReadJoin.flag = 0;
        ReadJoin readtool3 = new ReadJoin();
        String[] MergeTuples = new String[1000000];
        while (true) {
            if (ReadJoin.flag == 1) break;
            MergeTuples = readtool3.readBufferFile(MergeOutPath1);
            for (String tuple : MergeTuples) {
                if (tuple == null) break;
                String[] temp = tuple.split("\\s+");
                if (Integer.parseInt(temp[11]) < 6) {
                    ans.add(tuple);
                }
            }
        }

        long endTime3 = System.currentTimeMillis();
        System.out.println("Query5 Merge join 耗时=" + (endTime3 - startTime3) + "ms");
        if (ans.size() != 0) {
            WriteToFile.writeBufferToFile(MergeOutPath2, ans);
            ans.clear();
        }


        //Hash Join
        long startTime4 = System.currentTimeMillis();
        HashJoin.hashJoin(inputPath1, 0, inputPath2, 2, HashOutPath1); //hash join连接两张表
        ReadTable.currentSize = 0;
        ReadTable.flag = 0;
        ReadTable.bfSize = 1000000; //每次从join表中读取1百万条数据
        ReadTable readtool4 = new ReadTable();
        ArrayList<String> HashTuples = new ArrayList<>();

        while (ReadTable.flag == 0) {
            HashTuples = readtool4.getbfSizeFileContent(HashOutPath1);
            for (String tuple : HashTuples) {
                if (tuple == null) break;
                String[] temp = tuple.split("\\s+");
                if (Integer.parseInt(temp[11]) < 6) {
                    ans.add(tuple);
                }
            }
        }

        long endTime4 = System.currentTimeMillis();
        System.out.println("Query5 Hash join 耗时 = " + (endTime4 - startTime4) + "ms");
        if (ans.size() != 0) {
            MyTool.writeToFile(HashOutPath2, ans, true);
            ans.clear();
        }

    }
}
