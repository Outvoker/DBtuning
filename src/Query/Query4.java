package Query;

import BNL.BlockNL;
import BNL.BlockTool;
import Base.ReadJoin;
import Base.WriteToFile;
import HashJoin.MyTool;
import HashJoin.ReadTable;
import HashJoin.HashJoin;
import INLJ.IndexBplusTreeJoin;
//import Merge.*;
import Merge.MergeJoin_Query4_1;
import Merge.MergeJoin_Query4_2;

import java.io.IOException;
import java.util.ArrayList;
import Tools.Data_creation;

/*
 * 查询获得过国家级奖项的2016级同学的课程成绩
 * SELECT sco.*,u.`u_name` FROM cc_score sco, cc_user u WHERE u.`u_id` =sco.`u_id` AND u.`u_id` in (
 *  SELECT  u2.`u_id` FROM cc_competition comp, cc_user u2 WHERE u2.`u_id` =comp.`u_id` AND comp.` type ` = ‘1’ AND u2.u_attend_year = 2016
 * )

 */
public class Query4 {

    public static void main(String[] args) throws IOException {

        String inputPath1 = "C:\\Users\\admin\\Desktop\\Dave\\dbtuning\\DBtuning\\student.txt";
        String inputPath2 = "C:\\Users\\admin\\Desktop\\Dave\\dbtuning\\DBtuning\\score.txt";
        String inputPath3 = "C:\\Users\\admin\\Desktop\\Dave\\dbtuning\\DBtuning\\competition.txt";

        String indexOutPath1 = "C:\\Users\\admin\\Desktop\\Dave\\dbtuning\\DBtuning\\Query4\\Index\\join1.txt";
        String indexOutPath2 = "C:\\Users\\admin\\Desktop\\Dave\\dbtuning\\DBtuning\\Query4\\Index\\result1.txt";
        String indexOutPath3 = "C:\\Users\\admin\\Desktop\\Dave\\dbtuning\\DBtuning\\Query4\\Index\\join2.txt";
        String indexOutPath4 = "C:\\Users\\admin\\Desktop\\Dave\\dbtuning\\DBtuning\\Query4\\Index\\result2.txt";

        String MergeOutPath1 = "C:\\Users\\admin\\Desktop\\Dave\\dbtuning\\DBtuning\\Query4\\Merge\\join1.txt";
        String MergeOutPath2 = "C:\\Users\\admin\\Desktop\\Dave\\dbtuning\\DBtuning\\Query4\\Merge\\result1.txt";
        String MergeOutPath3 = "C:\\Users\\admin\\Desktop\\Dave\\dbtuning\\DBtuning\\Query4\\Merge\\join2.txt";
        String MergeOutPath4 = "C:\\Users\\admin\\Desktop\\Dave\\dbtuning\\DBtuning\\Query4\\Merge\\result2.txt";

        String BNLoutPath1 = "C:\\Users\\admin\\Desktop\\Dave\\dbtuning\\DBtuning\\Query4\\BNL\\join1.txt";
        String BNLoutPath2 = "C:\\Users\\admin\\Desktop\\Dave\\dbtuning\\DBtuning\\Query4\\BNL\\result1.txt";
        String BNLoutPath3 = "C:\\Users\\admin\\Desktop\\Dave\\dbtuning\\DBtuning\\Query4\\BNL\\join2.txt";
        String BNLoutPath4 = "C:\\Users\\admin\\Desktop\\Dave\\dbtuning\\DBtuning\\Query4\\BNL\\result2.txt";

        String HashOutPath1 = "C:\\Users\\admin\\Desktop\\Dave\\dbtuning\\DBtuning\\Query4\\Hash\\join1.txt";
        String HashOutPath2 = "C:\\Users\\admin\\Desktop\\Dave\\dbtuning\\DBtuning\\Query4\\Hash\\result1.txt";
        String HashOutPath3 = "C:\\Users\\admin\\Desktop\\Dave\\dbtuning\\DBtuning\\Query4\\Hash\\join2.txt";
        String HashOutPath4 = "C:\\Users\\admin\\Desktop\\Dave\\dbtuning\\DBtuning\\Query4\\Hash\\result2.txt";

        ArrayList<String> ans = new ArrayList<String>();
        //分别生成学生表、成绩表、奖项表
        Data_creation.creat_student(inputPath1);
        Data_creation.creat_score(inputPath2);
        Data_creation.creat_competition(inputPath3);


        //Block Nested Loops join
        BlockTool.nowLocation = 0;
        int bufferSize = 1000000;

        long startTime = System.currentTimeMillis();//开始时间

        BlockNL.BNLJoin(inputPath1, 0, inputPath3, 0, BNLoutPath1, bufferSize);  //嵌套循环连接，结果输出到文件BNLoutPath1，学生表u_id与奖项表u_id链接
        ReadJoin.nowLocation = 0;
        ReadJoin.flag = 0;
        ReadJoin readtool = new ReadJoin();
        String[] BNLTuples;
        while (true) {
            if (ReadJoin.flag == 1) break;
            BNLTuples = readtool.readBufferFile(BNLoutPath1);
            for (String tuple : BNLTuples) {
                if (tuple == null) break;
                String[] temp = tuple.split("\\s+");

                //获得国家级奖项且2016届的同学
                if (temp[12].equals("国家级") && temp[3].equals("2016")) {
                    ans.add(tuple);
                }
            }
        }
        if (ans.size() != 0) {
            WriteToFile.writeBufferToFile(BNLoutPath2, ans);
            ans.clear();
        }

        BlockTool.nowLocation = 0;
        BlockNL.BNLJoin(inputPath2, 1, BNLoutPath2, 0, BNLoutPath3, bufferSize);  //嵌套循环连接，结果输出到文件BNLoutPath3，成绩表u_id与获国奖学生表u_id链接
        ReadJoin.nowLocation = 0;
        ReadJoin.flag = 0;
        ReadJoin readtool_ = new ReadJoin();
        String[] BNLTuples_;
        while (true) {
            if (ReadJoin.flag == 1) break;
            BNLTuples_ = readtool_.readBufferFile(BNLoutPath3);
            for (String tuple : BNLTuples_) {
                if (tuple == null) break;
                String[] temp = tuple.split("\\s+");
                ans.add(temp[9] + "\t" + temp[0] + "\t" + temp[1] + "\t" + temp[2] + "\t" + temp[3] + "\t" + temp[4] + "\t" + temp[5] + "\t" + temp[6]);
            }
        }
        if (ans.size() != 0) {
            WriteToFile.writeBufferToFile(BNLoutPath4, ans);
            ans.clear();
        }

        long endTime = System.currentTimeMillis();//结束时间
        System.out.println("Query4 Block nest loop join 耗时=" + (endTime - startTime) + "ms");


        //Index Nested Loops Join
        long startTime2 = System.currentTimeMillis();

        IndexBplusTreeJoin.IndexBplusTreeJoin(inputPath1, 0, inputPath3, 0, indexOutPath1);//B+index连接，结果输出到文件indexOutPath1，学生表u_id与奖项表u_id链接

        ReadJoin.nowLocation = 0;
        ReadJoin.flag = 0;
        ReadJoin readtool2 = new ReadJoin();
        String[] indexTuples;
        while (true) {
            if (ReadJoin.flag == 1) break;
            indexTuples = readtool2.readBufferFile(indexOutPath1);
            for (String tuple : indexTuples) {
                if (tuple == null)
                    break;
                String[] temp = tuple.split("\\s+");
                //获得国家级奖项且2016届的同学
                if (temp[12].equals("国家级") && temp[3].equals("2016")) {
                    ans.add(tuple);
                }
            }
        }
        if (ans.size() != 0) {
            WriteToFile.writeBufferToFile(indexOutPath2, ans);
            ans.clear();
        }

        IndexBplusTreeJoin.IndexBplusTreeJoin(indexOutPath2, 0, inputPath2, 1, indexOutPath3);//B+index连接，结果输出到文件indexOutPath3，成绩表u_id与获国奖表u_id链接

        ReadJoin.nowLocation = 0;
        ReadJoin.flag = 0;
        ReadJoin readtool2_ = new ReadJoin();
        String[] indexTuples_;
        while (true) {
            if (ReadJoin.flag == 1) break;
            indexTuples_ = readtool2_.readBufferFile(indexOutPath3);
            for (String tuple : indexTuples_) {
                if (tuple == null)
                    break;
                String[] temp = tuple.split("\\s+");
                ans.add(temp[1] + "\t" + temp[14] + "\t" + temp[15] + "\t" + temp[16] + "\t" + temp[17] + "\t" + temp[18] + "\t" + temp[19] + "\t" + temp[20]);

            }
        }
        if (ans.size() != 0) {
            WriteToFile.writeBufferToFile(indexOutPath4, ans);
            ans.clear();
        }
        long endTime2 = System.currentTimeMillis();
        System.out.println("Query4 indexB+ join 耗时=" + (endTime2 - startTime2) + "ms");



        //Merge Join
        long startTime3 = System.currentTimeMillis();
        MergeJoin_Query4_1.MergeJoin(inputPath3, inputPath1, MergeOutPath1); //排序连接，结果写入文件MergeOutPath1
        ReadJoin.nowLocation = 0;
        ReadJoin.flag = 0;
        ReadJoin readtool3 = new ReadJoin();
        String[] MergeTuples;
        while (true) {
            if (ReadJoin.flag == 1) break;
            MergeTuples = readtool3.readBufferFile(MergeOutPath1);
            for (String tuple : MergeTuples) {
                if (tuple == null) break;
                String[] temp = tuple.split("\\s+");
                if (temp[12].equals("国家级") && temp[3].equals("2016")) {
                    ans.add(tuple);
                }
            }
        }
        if (ans.size() != 0) {
            WriteToFile.writeBufferToFile(MergeOutPath2, ans);
            ans.clear();
        }

//        MergeJoin_Query4_2.MergeJoin(inputPath2, MergeOutPath2, MergeOutPath3); //排序连接，结果写入文件MergeOutPath3
//        ReadJoin.nowLocation = 0;
//        ReadJoin.flag = 0;
//        ReadJoin readtool3_ = new ReadJoin();
//        String[] MergeTuples_;
//        while (true) {
//            if (ReadJoin.flag == 1) break;
//            MergeTuples_ = readtool3_.readBufferFile(MergeOutPath3);
//            for (String tuple : MergeTuples_) {
//                if (tuple == null) break;
//                String[] temp = tuple.split("\\s+");
//                ans.add(temp[1] + "\t" + temp[15] + "\t" + temp[16] + "\t" + temp[17] + "\t" + temp[18] + "\t" + temp[19] + "\t" + temp[20] + "\t" + temp[21]);
//            }
//        }
//        if (ans.size() != 0) {
//            WriteToFile.writeBufferToFile(MergeOutPath4, ans);
//            ans.clear();
//        }
//
//        long endTime3 = System.currentTimeMillis();
//        System.out.println("Query4 Merge join 耗时=" + (endTime3 - startTime3) + "ms");



        //Hash Join
        long startTime4 = System.currentTimeMillis();
        HashJoin.hashJoin(inputPath1, 0, inputPath3, 0, HashOutPath1); //hash join,学生表u_id与奖项表u_id链接
        ReadTable.currentSize = 0;
        ReadTable.flag = 0;
        ReadTable.bfSize = 1000000; //每次从join表中读取1百万条数据
        ReadTable readtool4 = new ReadTable();
        ArrayList<String> HashTuples;

        while (ReadTable.flag == 0) {
            HashTuples = readtool4.getbfSizeFileContent(HashOutPath1);
            for (String tuple : HashTuples) {
                if (tuple == null) break;
                String[] temp = tuple.split("\\s+");
                if (temp[12].equals("国家级") && temp[3].equals("2016")) {  //获得国家级奖项且2016届的同学
                    ans.add(tuple);
                }
            }
        }
        if (ans.size() != 0) {
            MyTool.writeToFile(HashOutPath2, ans, true);
            ans.clear();
        }

        HashJoin.hashJoin(inputPath2, 1, HashOutPath2, 0, HashOutPath3); //hash join，成绩表u_id与获过奖学生表u_id链接
        ReadTable.currentSize = 0;
        ReadTable.flag = 0;
        ReadTable.bfSize = 1000000; //每次从join表中读取1百万条数据
        ReadTable readtool4_ = new ReadTable();
        ArrayList<String> HashTuples_;

        while (ReadTable.flag == 0) {
            HashTuples_ = readtool4_.getbfSizeFileContent(HashOutPath3);
            for (String tuple : HashTuples_) {
                if (tuple == null) break;
                String[] temp = tuple.split("\\s+");
                ans.add(temp[9] + "\t" + temp[0] + "\t" + temp[1] + "\t" + temp[2] + "\t" + temp[3] + "\t" + temp[4] + "\t" + temp[5] + "\t" + temp[6]);
            }
        }
        if (ans.size() != 0) {
            MyTool.writeToFile(HashOutPath4, ans, true);
            ans.clear();
        }

        long endTime4 = System.currentTimeMillis();
        System.out.println("Query4 Hash join 耗时 = " + (endTime4 - startTime4) + "ms");


    }
}
