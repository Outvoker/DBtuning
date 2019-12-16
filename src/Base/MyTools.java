package Base;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class MyTools {

	
	/*
	 * 该方法的作用是实现把两个Sting数组中的字符串用空格连接起来，并返回
	 * 比如 temp1=["ab","cd","ef"]; temp2=["gh","ij","x"];
	 * 返回一个String="ab cd ef gh ij x";
	 */
	public static int flag;
	
	public static String joinWithStrings(String []temp1,String []temp2){
				
		StringBuilder ans = new StringBuilder();
		for(int i=0;i<temp1.length;i++){
			ans.append(temp1[i]+" ");
		}
		
		for(int i=0;i<temp2.length;i++){
			ans.append(temp2[i]+" ");
		}
		//System.out.println(ans.toString());
		
		return ans.toString();
		
	}
	
	public static String joinTwoString(String temp1,String temp2){
		StringBuilder ans = new StringBuilder();
		ans.append(temp1+",");
		ans.append(temp2);		
		return ans.toString();
	}

	/*
	 * 该方法输入一个文件路径，把每一行作为一个字符串，最后得到一个字符串数组返回。
	 */
	public static String [] getFileContent(String pathName) throws IOException{
		
		 FileInputStream inputStream = new FileInputStream(pathName);
		 BufferedReader input = new BufferedReader(new InputStreamReader(inputStream));
         
         String str = null;
         ArrayList<String> tempStrs = new ArrayList<String>();
         while((str = input.readLine())!=null){
        	 tempStrs.add(str);
         }
         String [] tuples = new String[tempStrs.size()];
         
         return  tempStrs.toArray(tuples);
        		 
	}

	/*
	 * 该方法输入一个文件路径，把每一行作为一个字符串，每次读一百万行记录到内存，最后得到一个字符串数组返回。
	 */
	public static String [] getFileContent2(String pathName) throws IOException{
		
		 FileInputStream inputStream = new FileInputStream(pathName);
		 BufferedReader input = new BufferedReader(new InputStreamReader(inputStream));
         
         String str = null;
         ArrayList<String> tempStrs = new ArrayList<String>();
         int count=0;
         while((str = input.readLine())!=null && count<1000000){
        	 tempStrs.add(str);
        	 count++;
         }
         if(str==null) flag=1;
         String [] tuples = new String[tempStrs.size()];
         
         return  tempStrs.toArray(tuples);
        		 
	}
}
