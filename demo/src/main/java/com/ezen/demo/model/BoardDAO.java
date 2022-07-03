package com.ezen.demo.model;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import org.springframework.stereotype.Repository;

@Repository
public class BoardDAO {

	private static final String path = "D:/board.txt";
	
	public boolean save(Board board) {
	try {
		PrintWriter pw =new PrintWriter(new FileWriter(path,true));
		pw.printf("%s|%s \r\n",board.getTitle(),board.getContents());
		pw.close();
		return true;
	} catch (IOException e) {
	
		e.printStackTrace();
	}	
		
		return false;
	}


}
