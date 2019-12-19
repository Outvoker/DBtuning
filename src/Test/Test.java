package Test;

import BNL.BlockNL;
import BNL.BlockTool;
import Base.ReadJoin;
import Base.WriteToFile;
import HashJoin.MyTool;
import HashJoin.ReadTable;
import HashJoin.HashJoin;
import INLJ.IndexBplusTreeJoin;
//import Merge.MergeJoin_4;
import Tools.Data_creation;

import java.io.IOException;
import java.util.ArrayList;

/*
 * 查询获得过国家级奖项的2016级同学的课程成绩
 * SELECT sco.*,u.`u_name` FROM cc_score sco, cc_user u WHERE u.`u_id` =sco.`u_id` AND u.`u_id` in (
 *  SELECT  u2.`u_id` FROM cc_competition comp, cc_user u2 WHERE u2.`u_id` =comp.`u_id` AND comp.` type ` = ‘1’ AND u2.u_attend_year = 2016
 * )

 */
public class Test {

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
//        Data_creation.creat_student(inputPath1);
//        Data_creation.creat_score(inputPath2);
//        Data_creation.creat_competition(inputPath3);


        long startTime2 = System.currentTimeMillis();
        IndexBplusTreeJoin.IndexBplusTreeJoin(inputPath2, 1, indexOutPath2, 0, indexOutPath3);//B+index连接，结果输出到文件indexOutPath3，成绩表u_id与获国奖表u_id链接

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
                ans.add(temp[8] + "\t" + temp[0] + "\t" + temp[1] + "\t" + temp[2] + "\t" + temp[3] + "\t" + temp[4] + "\t" + temp[5] + "\t" + temp[6]);

            }
        }
        if (ans.size() != 0) {
            WriteToFile.writeBufferToFile(indexOutPath4, ans);
            ans.clear();
        }
        long endTime2 = System.currentTimeMillis();
        System.out.println("Query4 indexB+ join 耗时=" + (endTime2 - startTime2) + "ms");
    }
}
