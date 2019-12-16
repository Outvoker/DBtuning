package HashJoin;

import java.io.*;
import java.util.ArrayList;

public class ReadTable {

    /* 每次从表中读取bgSize大小的数据*/

    public static int currentSize;
    public static int bfSize;
    public static int flag;


    public ArrayList<String> getbfSizeFileContent(String filePath) throws IOException {

        FileInputStream inputStream = new FileInputStream(filePath);
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));

        ArrayList<String> arr = new ArrayList<>();
        String line = null;

        for (int i = 0; i < currentSize; i++) {
            bufferedReader.readLine();
        }

        while ((line = bufferedReader.readLine()) != null && arr.size() < bfSize) {
            arr.add(line);
            currentSize++;
        }

        if (line == null) flag = 1;

        return arr;

    }
}
