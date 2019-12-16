package HashJoin;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class HashJoin {


    public static void hashJoin(String smallTablePath, int index1, String bigTablePath, int index2, String outputPath) throws IOException {


        ArrayList<String> smallTable = MyTool.getFileContent(smallTablePath);

        //bulid hash table
        HashMap<String, List<String>> hashtable = new HashMap();    //相同key值可能存在多行数据，使用list来存储value

        for (String line : smallTable) {
            String[] arr = line.split("\\s+");
            String key = arr[index1];
            List<String> listLine = new ArrayList<>();
            if (hashtable.containsKey(key)) {   //key值如果已经存在，则在原来的list中加入新的数据
                listLine = hashtable.get(key);
            }
            listLine.add(line);
            hashtable.put(key, listLine);
        }

        // probe table
        ArrayList<String> bigTable = new ArrayList<>();
        int bfSize = 100000;    //每次从表中读取的数据大小
        ReadTable readTable = new ReadTable();
        ReadTable.currentSize = 0;
        ReadTable.flag = 0;
        ReadTable.bfSize = 100000;
        List<String> joinResult = new ArrayList<>();
        boolean isNewFile = true;   //是否是新的join

        while (ReadTable.flag == 0) {
            bigTable = readTable.getbfSizeFileContent(bigTablePath); //每次读取bfsize大小的数据
            for (String lineBig : bigTable) {
//                if(lineBig == null) break;
                String[] arrBigTable = lineBig.split("\\s+");
                String key = arrBigTable[index2];

                if (hashtable.containsKey(key)) {    //判断大表中的key在hashtable中是否有对应值
                    List<String> listLine = hashtable.get(key);
                    for (String lineSmall : listLine) {
                        String joinStr = lineSmall + lineBig;   //匹配后连接
                        joinResult.add(joinStr);
                    }
                }
            }

//            System.out.println("HashJoinSize = " + joinResult.size());
            MyTool.writeToFile(outputPath, joinResult, isNewFile); //每次最多写入10万行
            joinResult.clear();
            isNewFile = false;
        }

    }
}

