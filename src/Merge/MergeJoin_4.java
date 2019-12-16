package Merge;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import Base.MyTools;
import Base.WriteToFile;

public class MergeJoin_4 {

    public static void MergeJoin(String pathNameofInput1,String pathNameofInput2,String outPath){
        try{

            ArrayList<String> ans = new ArrayList<String>();//用来存放结果
            String[] Tuples1 = MyTools.getFileContent(pathNameofInput1);//读出Orders中的内容
            String[] Tuples2 = MyTools.getFileContent(pathNameofInput2);//读出Evaluation中的内容
            int size1 = Tuples1.length;
            int size2 = Tuples2.length;


            Orders[] orders = new Orders[size1]; //存放order对象
            Evaluation[] evaluations = new Evaluation[size1];//存放Evaluation对象

            for(int i=0;i<size1;i++){
                String[] temp=Tuples1[i].split("\\s+");
                orders[i]=new Orders();
                orders[i].setOrder_id(Integer.parseInt(temp[0]));
                orders[i].setMeal_id(Integer.parseInt(temp[1]));
                orders[i].setUser_id(Integer.parseInt(temp[2]));
                orders[i].setOrder_date(temp[3]);
                orders[i].setOrder_number(Integer.parseInt(temp[4]));


            }
            SortedByOrderId_order compare =new SortedByOrderId_order();
            Arrays.sort(orders, compare);  //对order排序

            for(int i=0;i<size2;i++){
                String[] temp=Tuples2[i].split("\\s+");
                evaluations[i]=new Evaluation();
                evaluations[i].setEvaluation_id(Integer.parseInt(temp[0]));
                evaluations[i].setOrder_id(Integer.parseInt(temp[1]));
                evaluations[i].setEmployee_id(Integer.parseInt(temp[2]));
                evaluations[i].setOrder_grade(Integer.parseInt(temp[3]));
                evaluations[i].setEmployee_grade(Integer.parseInt(temp[4]));
            }
            SortedByOrderId_eva compare2 =new SortedByOrderId_eva();
            Arrays.sort(evaluations, compare2);  //对evaluations排序


            int point1 = 0, point2 = 0;//point1代表order的移标，point2代表user的移标
            while(point1 < size1 && point2 < size2){//Merge Join算法的实现
                if(evaluations[point2].getOrder_id()==orders[point1].getOrder_id()){//判断当前两条记录主键是否相同
                    String[] evaTemp = Tuples2[evaluations[point2].getEvaluation_id()-1].split("\\s+");
                    String[] orderTemp = Tuples1[orders[point1].getOrder_id()-1].split("\\s+");
                    String str = MyTools.joinWithStrings(evaTemp, orderTemp);
                    ans.add(str);
                    if(ans.size()>=1000000){
                        WriteToFile.writeBufferToFile(outPath, ans);
                        ans.clear();
                    }
                    if(point1+1<size1 && point2+1<size2){
                        if(evaluations[point2+1].getOrder_id()==orders[point1+1].getOrder_id()){//当前两条记录的下一条，主键是否相同，如不进行判断，会产生遗漏
                            int tpoint=point1;
                            while(evaluations[point2].getOrder_id()==orders[point1+1].getOrder_id()){ //固定一条记录，匹配完另一张表所有可匹配的记录后再移动
                                point1++;
                                String[] evaTemp2 = Tuples2[evaluations[point2].getEvaluation_id()-1].split("\\s+");
                                String[] orderTemp2 = Tuples1[orders[point1].getOrder_id()-1].split("\\s+");
                                String str2 = MyTools.joinWithStrings(evaTemp2, orderTemp2);
                                ans.add(str2);
                                if(ans.size()>=1000000){
                                    WriteToFile.writeBufferToFile(outPath, ans);
                                    ans.clear();
                                }
                                if(point1==size1-1) break; //移动到表尾时退出
                            }
                            point2++;
                            point1=tpoint;
                        }else if(evaluations[point2+1].getOrder_id()==orders[point1].getOrder_id()){ //下一条记录仍匹配的表向下遍历
                            point2++;
                        }else if(evaluations[point2].getOrder_id()==orders[point1+1].getOrder_id()){  //下一条记录仍匹配的表向下遍历
                            point1++;
                        }else{
                            point1++;
                        }
                    }else if(point1+1<size1){ //当前记录匹配，没到表尾那张表的向下遍历
                        point1++;
                    }else{  //当前记录匹配，没到表尾那张表的向下遍历
                        point2++;
                    }
                }else if(evaluations[point2].getOrder_id()<orders[point1].getOrder_id()){  //若当前记录不相等，编号小的记录向下遍历
                    point2++;
                }else{  //若当前记录不相等，编号小的记录向下遍历
                    point1++;
                }
            }
            if(ans.size()!=0){
                WriteToFile.writeBufferToFile(outPath, ans);
            }
        } catch (IOException e){
            e.printStackTrace();
        }
    }
}
