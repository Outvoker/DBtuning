package Merge;


import Base.MyTools;
import Base.WriteToFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class MergeJoin_2 {

    public static void MergeJoin(String pathNameofInput1, String pathNameofInput2, String outPath) {
        try {

            ArrayList<String> ans = new ArrayList<String>();//用来存放结果
            String[] Tuples1 = MyTools.getFileContent(pathNameofInput1);//读出Evaluation中的内容
            String[] Tuples2 = MyTools.getFileContent(pathNameofInput2);//读出Delivery中的内容
            int size1 = Tuples1.length;
            int size2 = Tuples2.length;


            Evaluation[] evaluations = new Evaluation[size1];
            Delivery[] deliveries = new Delivery[size2];

            for (int i = 0; i < size1; i++) {
                String[] temp = Tuples1[i].split("\\s+");
                evaluations[i] = new Evaluation();
                evaluations[i].setEvaluation_id(Integer.parseInt(temp[0]));
                evaluations[i].setOrder_id(Integer.parseInt(temp[1]));
                evaluations[i].setEmployee_id(Integer.parseInt(temp[2]));
                evaluations[i].setOrder_grade(Integer.parseInt(temp[3]));
                evaluations[i].setEmployee_grade(Integer.parseInt(temp[4]));


            }
            SortedByEmployeeId_Eva compare = new SortedByEmployeeId_Eva();
            Arrays.sort(evaluations, compare);  //对order排序

            for (int i = 0; i < size2; i++) {
                String[] temp = Tuples2[i].split("\\s+");
                deliveries[i] = new Delivery();
                deliveries[i].setEmployee_id(Integer.parseInt(temp[0]));
                deliveries[i].setEmployee_name(temp[1]);
                deliveries[i].setEmployee_gender(temp[2]);
                deliveries[i].setEmployee_age(Integer.parseInt(temp[3]));
                deliveries[i].setEmployee_tel(temp[4]);
                deliveries[i].setEmployee_wechat(temp[5]);
                deliveries[i].setEmployee_salary(Integer.parseInt(temp[6]));
            }
            SortedByEmployeeId_Deli compare2 = new SortedByEmployeeId_Deli();
            Arrays.sort(deliveries, compare2);  //对user排序


            int point1 = 0, point2 = 0;//point1代表evaluations的移标，point2代表deliveries的移标
            while (point1 < size1 && point2 < size2) {//Merge Join算法的实现
                if (deliveries[point2].getEmployee_id() == evaluations[point1].getEmployee_id()) {//判断当前两条记录主键是否相同
                    String[] deliTemp = Tuples2[deliveries[point2].getEmployee_id() - 1].split("\\s+");
                    String[] evaTemp = Tuples1[evaluations[point1].getEvaluation_id() - 1].split("\\s+");
                    String str = MyTools.joinWithStrings(deliTemp, evaTemp);
                    ans.add(str);
                    if (ans.size() >= 1000000) {
                        WriteToFile.writeBufferToFile(outPath, ans);
                        ans.clear();
                    }
                    if (point1 + 1 < size1 && point2 + 1 < size2) {
                        if (deliveries[point2 + 1].getEmployee_id() == evaluations[point1 + 1].getEmployee_id()) {//当前两条记录的下一条，主键是否相同，如不进行判断，会产生遗漏
                            int tpoint = point1;
                            while (deliveries[point2].getEmployee_id() == evaluations[point1 + 1].getEmployee_id()) { //固定一条记录，匹配完另一张表所有可匹配的记录后再移动
                                point1++;
                                String[] deliTemp2 = Tuples2[deliveries[point2].getEmployee_id() - 1].split("\\s+");
                                String[] evaTemp2 = Tuples1[evaluations[point1].getEvaluation_id() - 1].split("\\s+");
                                String str2 = MyTools.joinWithStrings(deliTemp2, evaTemp2);

                                ans.add(str2);
                                if (ans.size() >= 1000000) {
                                    WriteToFile.writeBufferToFile(outPath, ans);
                                    ans.clear();
                                }
                                if (point1 == size1 - 1) break; //移动到表尾时退出
                            }
                            point2++;
                            point1 = tpoint;
                        } else if (deliveries[point2 + 1].getEmployee_id() == evaluations[point1].getEmployee_id()) { //下一条记录仍匹配的表向下遍历
                            point2++;
                        } else if (deliveries[point2].getEmployee_id() == evaluations[point1 + 1].getEmployee_id()) {  //下一条记录仍匹配的表向下遍历
                            point1++;
                        } else {
                            point1++;
                        }
                    } else if (point1 + 1 < size1) { //当前记录匹配，没到表尾那张表的向下遍历
                        point1++;
                    } else {  //当前记录匹配，没到表尾那张表的向下遍历
                        point2++;
                    }
                } else if (deliveries[point2].getEmployee_id() < evaluations[point1].getEmployee_id()) {  //若当前记录不相等，编号小的记录向下遍历
                    point2++;
                } else {  //若当前记录不相等，编号小的记录向下遍历
                    point1++;
                }
            }

//            System.out.println("MergeJoinSize = " + ans.size());
            if (ans.size() != 0) {
                WriteToFile.writeBufferToFile(outPath, ans);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
