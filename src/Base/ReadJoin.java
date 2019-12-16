package Base;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class ReadJoin {

    public static int nowLocation;
    public static int currentSize;
    public static int flag;
    /*
     *每次从Join结果中读取一百万条记录
     */

    public String[] readBufferFile(String pathName) throws IOException {

        FileInputStream inputStream = new FileInputStream(pathName);
        BufferedReader bufferReader = new BufferedReader(new InputStreamReader(inputStream));

        int i = 0;
        int bfSize = 1000000;
        while (i < nowLocation) {
            bufferReader.readLine();
            i++;
        }

        int k = 0;
        String[] tempStrings = new String[bfSize];
        String str = null;
        while ((str = bufferReader.readLine()) != null && k < bfSize) {
            tempStrings[k] = str;
            k++;
        }
        if (str == null) flag = 1;
        if (k == bfSize) nowLocation = nowLocation + bfSize;
        else nowLocation = nowLocation + k;

        currentSize = k;
        return tempStrings;

    }


}
