package com.ezen.demo.model;

import java.io.BufferedReader;

import java.io.FileReader;
import java.io.IOException;

import org.springframework.stereotype.Repository;

@Repository
public class UserDAO {
	
	private static final String path="D:/member.txt";
                        
	public boolean login(User user) {
		try {
			BufferedReader br = new BufferedReader(new FileReader(path));
			String line=null;
			while((line=br.readLine())!=null) {
				User usr = new User(line.split(" "));
				if(user.equals(usr)) return true;
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return false;
	}

}
