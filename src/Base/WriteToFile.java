package Base;
import java.io.*;
import java.util.ArrayList;

//import static jdk.internal.org.objectweb.asm.Type.getType;

public class WriteToFile {
	
	
	/*
	 * 改方法输入一个文件路径（已存在就覆盖，没有就新建）和一个ArrayList类型的Datas,Datas中的每个作为一行写入文件
	 */
	public static void writeBufferToFile(String pathName,ArrayList<String> datas) throws IOException{

		try {
			File file = new File(pathName);
			FileWriter fw = new FileWriter(file);
			PrintWriter pw = new PrintWriter(fw);

			String[] strs = datas.get(0).split("\\s+");   //split("\\s+"),这个字符串方法是以一个或多个空格来分割一个字符串，最后返回一个字符串数组
			// 比如： "dsa dsad  d      k".split("\\s+")返回 字符串数组=["dsa","dsad","d","k"]

			String format = "";                              //format是作为控制格式化输出的
			for (int i = 0; i < strs.length; i++) {
				format += "%-" + (strs[i].length() + 15) + "S";
			}
			format += "\r\n";

			String str = null;


			for (int i = 0; i < datas.size(); i++) {
				str = String.valueOf(datas.get(i));
				pw.format(format, str.split("\\s+"));
			}
			fw.flush();
			pw.close();
			fw.close();
		}catch(Exception e){
			e.printStackTrace();
		}

//			System.out.println("成功写入"+datas.size()+"行数据!");
	}


//	public static void writeBufferToFile(String filePath, ArrayList<String> data) {
//		//isNewFile判断是否为新的join
//		try {
//			File file = new File(filePath);
//			FileWriter fw = null;
//			// if file doesnt exists, then create it
//			if (!file.exists()) {
//				file.createNewFile();
//			}
//			fw = new FileWriter(file.getAbsoluteFile());
//
//
//			BufferedWriter bw = new BufferedWriter(fw);
//			for (String line: data) {
//				bw.write(line+'\n');
//			}
//
//			bw.close();
//
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//	}


}
