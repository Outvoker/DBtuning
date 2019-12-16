package HashJoin;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class MyTool {

    public static void writeToFile(String filePath, List<String> data, boolean isNewFile) {
        //isNewFile判断是否为新的join
        try {
            File file = new File(filePath);
            FileWriter fw = null;
            // if file doesnt exists, then create it
            if (!file.exists()) {
                file.createNewFile();
            }
            if(isNewFile) {
                fw = new FileWriter(file.getAbsoluteFile());
            }
            else {
                fw = new FileWriter(file.getAbsoluteFile(), true);   //不是新的join则追加
            }

            BufferedWriter bw = new BufferedWriter(fw);
            for (String line: data) {
                bw.write(line+'\n');
            }

            bw.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //读取文件内容
    public static ArrayList<String> getFileContent(String filePath) throws IOException {

        FileInputStream inputStream = new FileInputStream(filePath);
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));

        ArrayList<String> arr = new ArrayList<>();
        String line = null;

        while((line = bufferedReader.readLine())!=null) {
            arr.add(line);
        }
        bufferedReader.close();
        inputStream.close();

        return arr;
    }

}
