package BNL;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.LineNumberReader;

import javax.xml.transform.Templates;

public class BlockTool {
	
	public static int nowLocation;
	public static int currentSize;
	
	
	/*
	 * 这个方法每次执行，可以从指定的文件中读取bfSize大小的行，并且是从上次读取完的地方开始读，
	 * 主要是利用上面的两个静态变量实现的，nowLocation，currentSize
	 */

	String [] readBufferFile(String pathName,int bfSize) throws IOException{
		
		FileInputStream inputStream = new FileInputStream(pathName);
		BufferedReader bufferReader = new BufferedReader(new InputStreamReader(inputStream));
		
		int i=0;
		while(i<nowLocation){
			bufferReader.readLine();
			i++;
		}
		
		int k=0;
		String []tempStrings = new String[bfSize];
		String str = null;
		while((str=bufferReader.readLine())!=null&&k<bfSize){
			tempStrings[k] = str;		
			k++;
		}
		
		if(k==bfSize) nowLocation = nowLocation + bfSize;
		else nowLocation = nowLocation + k;
		
		currentSize = k;
		return tempStrings;
		
	}
	
}
