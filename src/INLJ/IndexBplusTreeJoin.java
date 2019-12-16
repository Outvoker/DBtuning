package INLJ;

import Base.MyTools;
import Base.WriteToFile;
import Base.ReadTable;

import java.io.IOException;
import java.util.ArrayList;

public class IndexBplusTreeJoin {

    public static void IndexBplusTreeJoin(String file1, int index1, String file2, int index2, String outputfile) throws IOException {

        try {
            //设定B+树的阶数M
            BplusTree tree = new BplusTree(15);
            String[] tuple1 = MyTools.getFileContent(file1);
            for (String tuple : tuple1) {
                //以空格符分割字符串
                String[] temp = tuple.split("\\s+");
                //将一条记录存入B+树中，用来join查询的字段作为key，整条记录作为value
                tree.insertOrUpdate(temp[index1], tuple);
            }

            ArrayList<String> join = new ArrayList<String>();


            ReadTable.nowLocation = 0;
            ReadTable.flag = 0;
            String[] tuple2 = new String[100000];
            ReadTable rtable = new ReadTable();
            int count = 0;
            while (true) {
                //文件file2的所有记录全部读取完，跳出循环
                if (ReadTable.flag == 1)
                    break;
                //将文件中的十万条数据读入tuple2
                tuple2 = rtable.readBufferFile(file2);
                for (String tuple : tuple2) {
                    if (tuple == null)
                        break;

                    String[] temp = tuple.split("\\s+");
                    //在B+树中寻找符合条件的记录
                    String data = String.valueOf(tree.get(temp[index2]));
                    String[] compData = data.split("\\s+");

                    //如果查找到该条数据
                    if (!data.equals("null")) {
                        String str = MyTools.joinWithStrings(compData, temp);
//                        String str = data + tuple;
                        join.add(str);
                    }
                }
            }

//            System.out.println("IndexJoinSize=" + join.size());
            if (join.size() != 0) {
                //将查找的所有符合条件的记录写入join表中
                WriteToFile.writeBufferToFile(outputfile, join);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}

