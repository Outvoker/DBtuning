package BNL;

import java.io.*;
import java.util.*;

import Base.MyTools;
import Base.WriteToFile;

public class BlockNL {


    public static void BNLJoin(String pathNameofInput1, int index1, String pathNameofInput2, int index2, String outPath, int bufferSize) throws IOException {

        try {
            BlockTool bufferFromOuter = new BlockTool();
            String[] buffer1 = new String[bufferSize];

            String[] innerTuples = MyTools.getFileContent(pathNameofInput2);

            ArrayList<String> ans = new ArrayList<String>();

            Map<String, String> mapBuffer = new HashMap<>();

            while (true) {

                buffer1 = bufferFromOuter.readBufferFile(pathNameofInput1, bufferSize);  //每次从表1中读取bufferSize大小的元组（字符串）


                for (String tuple : buffer1) {
                    if (tuple == null)
                        continue;
                    String[] temp = tuple.split("\\s+");
                    String record = mapBuffer.get(temp[index1]);
                    if (record == null) {  //如果map中没有对应记录，直接插入
                        mapBuffer.put(temp[index1], tuple);
                    } else {  //如果map中有相应记录，将原记录取出，与新记录用逗号分隔后插入
                        String combine = MyTools.joinTwoString(record, tuple);
                        mapBuffer.put(temp[index1], combine);
                    }
                }

                // 对内表的每一个记录来说
                for (String str2 : innerTuples) {

                    String[] temp2 = str2.split("\\s+");

                    String lst = mapBuffer.get(temp2[index2]);
                    // 如果找到了
                    if (lst != null) {
                        String[] tempLst = lst.split(",");
                        for (String tuple2 : tempLst) {  //将小表中对应的所有记录与当前记录匹配
                            String[] temp3 = tuple2.split("\\s+");
                            String str = MyTools.joinWithStrings(temp3, temp2);
                            ans.add(str);
                        }
                    }

                }
                if (BlockTool.currentSize < bufferSize) break;
                mapBuffer.clear();
            }


//            System.out.println("BNLJoinSize = " + ans.size());
            if (ans.size() != 0) {
                WriteToFile.writeBufferToFile(outPath, ans);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
	
	


