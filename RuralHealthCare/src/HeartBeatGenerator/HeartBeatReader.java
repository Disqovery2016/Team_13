package com.tcs.HeartBeatGenerator;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class HeartBeatReader {
public static void main(String[] args) {
boolean HeartRateIncresed=false;
	try {
		FileInputStream fileInputStream=new FileInputStream("C:/Users/st_utsav/Desktop/HeartBeat.docx");
		double inputBytes=0;
		FileOutputStream fileOutputStream=new FileOutputStream("C:/Users/st_utsav/Desktop/Copying.docx");
		while((inputBytes=fileInputStream.read())!= -1){
			double HBPM=(double) inputBytes;
			if(HBPM>100){
			HeartRateIncresed=true;
			System.out.println("The Heart Beat is increased "+HBPM);
			}
			else
				System.out.println(HBPM);
			
		}
	} catch (FileNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
}
}
