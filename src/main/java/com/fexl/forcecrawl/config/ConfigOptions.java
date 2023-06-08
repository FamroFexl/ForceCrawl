package com.fexl.forcecrawl.config;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.loader.api.FabricLoader;

@Environment(EnvType.CLIENT)
public class ConfigOptions {
	public static File configFile = new File(FabricLoader.getInstance().getConfigDir() + "\\forcecrawl.cfg");
	
	public static void fileInit() {
		try {
			//Create a new config file if it doesn't exist
			configFile.createNewFile();
				
			//Write crawlType setting to file with default value
			PrintWriter configWrite = new PrintWriter(configFile);
			configWrite.println("crawlType=toggle");
			
			//Close the file once initialized
			configWrite.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	//Get a key from the file
	public static String get(String key) {
		try {
			Scanner configRead = new Scanner(configFile);
			
			while(configRead.hasNextLine()) {
				//Get the key-value pair as an array without whitespace
				String[] pair = configRead.nextLine().stripLeading().stripTrailing().split("=");
				
				//If the key in the file matches the argument key
				if(pair[0].equals(key)) {
					configRead.close();
					
					return pair[1];
				}
			}
			configRead.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return "";
	}
	
	public static void set(String key, String value) {
		ArrayList<String> lines = new ArrayList<String>();
		Boolean keyFound = false;
		String pair = key + "=" + value;
		
		try {
			Scanner configRead = new Scanner(configFile);
			
			//Store file for processing
			while(configRead.hasNextLine()) {
				lines.add(configRead.nextLine());
			}
			
			configRead.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		for(int i=0;i<lines.size();i++) {
			//Get the key-value pair as an array without whitespace
			if(lines.get(i).stripLeading().stripTrailing().split("=")[0].equals(key)) {
				lines.set(i, pair);
				keyFound = true;
			}
		}
		
		//Change the value if the key has been found
		if(!keyFound) {
			lines.add(pair);
		}
		
		try {
			PrintWriter configWrite = new PrintWriter(configFile);
			
			//Write the result to the file
			for(int i=0;i<lines.size();i++) {
				configWrite.println(lines.get(i));
			}
			
			configWrite.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
}
