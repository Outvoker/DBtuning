package Merge;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import Base.MyTools;
import Base.WriteToFile;

public class MergeJoin_Query4_2 {

    public static void MergeJoin(String pathNameofInput1, String pathNameofInput2, String outPath) {
        try {

            ArrayList<String> ans = new ArrayList<String>();//用来存放结果
            String[] Tuples1 = MyTools.getFileContent(pathNameofInput1);//读出Score中的内容
            String[] Tuples2 = MyTools.getFileContent(pathNameofInput2);//读出Student中的内容
            int size1 = Tuples1.length;
            int size2 = Tuples2.length;


            Score[] Scores = new Score[size1]; //存放Score对象
            Student_Competition[] Students = new Student_Competition[size2+1];//存放Student对象

            for (int i = 0; i < size1; i++) {
                String[] temp = Tuples1[i].split("\\s+");
                Scores[i] = new Score();
                Scores[i].setSco_id(Integer.parseInt(temp[0]));
                Scores[i].setU_id(Integer.parseInt(temp[1]));
                Scores[i].setSco_year(temp[2]);
                Scores[i].setSco_name(temp[3]);
                Scores[i].setSco_property(temp[4]);
                Scores[i].setSco_period(Integer.parseInt(temp[5]));
                Scores[i].setSco_credit(Integer.parseInt(temp[6]));
                Scores[i].setSco_point(Integer.parseInt(temp[7]));

            }
            SortedByUserIdinScore compare = new SortedByUserIdinScore();
            Arrays.sort(Scores, compare);  //对Score排序

            for (int i = 0; i < size2; i++) {
                String[] temp = Tuples2[i].split("\\s+");
                Students[i] = new Student_Competition();
                Students[i].setU_id(Integer.parseInt(temp[0]));
                Students[i].setU_name(temp[1]);
                Students[i].setU_class(Integer.parseInt(temp[2]));
                Students[i].setU_attend_year(temp[3]);
                Students[i].setU_native_place(temp[4]);
                Students[i].setU_birthday(temp[5]);
                Students[i].setU_major(temp[6]);
                Students[i].setU_gender(temp[7]);
                Students[i].setU_id2(Integer.parseInt(temp[8]));
                Students[i].setComp_id(Integer.parseInt(temp[9]));
                Students[i].setComp_title(temp[10]);
                Students[i].setComp_class(temp[11]);
                Students[i].setComp_type(temp[12]);
                Students[i].setComp_date(temp[13]);
                Students[i].setU_scid(i+1);
            }
//            SortedByUserIdinStuComp compare2 = new SortedByUserIdinStuComp();
//            Arrays.sort(Students, compare2);  //对Student排序


            int point1 = 0, point2 = 0;//point1代表Score的移标，point2代表Student的移标
            while (point1 < size1 && point2 < size2) {//Merge Join算法的实现
                if (Students[point2].getU_id() == Scores[point1].getU_id()) {//判断当前两条记录主键是否相同
                    String[] StudentTemp = Tuples2[Students[point2].getU_scid() - 1].split("\\s+");
                    String[] orderTemp = Tuples1[Scores[point1].getSco_id() - 1].split("\\s+");
                    String str = MyTools.joinWithStrings(StudentTemp, orderTemp);
                    ans.add(str);
                    if (ans.size() >= 1000000) {
                        WriteToFile.writeBufferToFile(outPath, ans);
                        ans.clear();
                    }
                    if (point1 + 1 < size1 && point2 + 1 < size2) {
                        if (Students[point2 + 1].getU_id() == Scores[point1 + 1].getU_id()) {//当前两条记录的下一条，主键是否相同，如不进行判断，会产生遗漏
                            int tpoint = point1;
                            while (Students[point2].getU_id() == Scores[point1 + 1].getU_id()) { //固定一条记录，匹配完另一张表所有可匹配的记录后再移动
                                point1++;
                                String[] StudentTemp2 = Tuples2[Students[point2].getU_scid() - 1].split("\\s+");
                                String[] orderTemp2 = Tuples1[Scores[point1].getSco_id() - 1].split("\\s+");
                                String str2 = MyTools.joinWithStrings(StudentTemp2, orderTemp2);
                                ans.add(str2);
                                if (ans.size() >= 1000000) {
                                    WriteToFile.writeBufferToFile(outPath, ans);
                                    ans.clear();
                                }
                                if (point1 == size1 - 1) break; //移动到表尾时退出
                            }
                            point2++;
                            point1 = tpoint;
                        } else if (Students[point2 + 1].getU_id() == Scores[point1].getU_id()) { //下一条记录仍匹配的表向下遍历
                            point2++;
                        } else if (Students[point2].getU_id() == Scores[point1 + 1].getU_id()) {  //下一条记录仍匹配的表向下遍历
                            point1++;
                        } else {
                            point1++;
                        }
                    } else if (point1 + 1 < size1) { //当前记录匹配，没到表尾那张表的向下遍历
                        point1++;
                    } else {  //当前记录匹配，没到表尾那张表的向下遍历
                        point2++;
                    }
                } else if (Students[point2].getU_id() < Scores[point1].getU_id()) {  //若当前记录不相等，编号小的记录向下遍历
                    point2++;
                } else {  //若当前记录不相等，编号小的记录向下遍历
                    point1++;
                }
            }
            if (ans.size() != 0) {
                WriteToFile.writeBufferToFile(outPath, ans);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}