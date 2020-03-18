package com.example;
/**
 * return metadata (JSON format),
 *  so a list of attributes and type
 *  
 * @author Pasquale Pio Troiano
 */
import java.io.*;
import java.lang.reflect.*;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
public class Metadata {
	//created and initialized a private String COMMA_DELIMITER to ';'
		private static final String COMMA_DELIMITER = ";";
		//created and initialized a private String JSON_FILE_NAME to 'getMetadata.json'
		private static final String JSON_FILE_NAME = "getMetadata.json";
		private List<String> firstLine;  //List of String called firstLine
		
		/**
		 * Constructor with a split of first line of csv
		 */
		public Metadata() {
			String line = "";  //initialized a String to to copy the csv file "dati.csv" into it
			try {
				line = new BufferedReader(new FileReader("dati.csv")).readLine(); //fills 'line' with the firstline of the file
			} catch (IOException e) {
				e.printStackTrace();
			}

			this.firstLine = Arrays.asList(line.split(COMMA_DELIMITER));

		}
		
		/**
		 * Method that create a json file with the metadata
		 * @throws ClassNotFoundException
		 */
		
		public void toJSonMetadata() throws ClassNotFoundException {
			try {
				Class<?> c = Class.forName("com.example.Scuolasecondaria");
				Field listaParam[] = c.getDeclaredFields();   //array of listaParam
				BufferedWriter w = new BufferedWriter(new FileWriter(JSON_FILE_NAME));//open BufferReadear
				                                                                       //to write
				                                                                       //JSON_FILE_NAME
				Iterator<String> it = firstLine.iterator(); //Create iterator for cycle the firstline
				int i = 0;
				String typeStr = "NULL";
				for(int j=0; j<firstLine.size();j++) {
					if(listaParam[i].getType()==typeStr.getClass()) { //if the paramaters of attributes is a string then return "String" 
						typeStr="String";
					}else if(listaParam[i].getType().equals(Integer.TYPE)) { //if the paramater is an int then return "Integer"
						typeStr="Integer";
					}
				}
				while (it.hasNext()) { //while there is something else keep going and print:
					w.write("{");
					w.newLine();
					w.write("\"alias\":" + listaParam[i].toString() + "\"\n"); //write on file the name of attribute
					w.write("\"sourceField:\"" + it.next() + "\n"); //write on file sourcefield of attribute
					w.write("\"type:\" \"" + typeStr + "\"\n"); //write on file the type of attributes used
					w.write("},");
					i++;
				}
				w.newLine();
				w.close();
				System.out.println("File json getMetadata creato");
				} catch (IOException e) {
				e.printStackTrace();
			}

		}
		/**
		 * Method that create a json file with the metadata
		 * @throws ClassNotFoundException
		 */
		public void toJsonMedataWithObject() {
			try {
				Class<?> c = Class.forName("com.example.ScuolaSecondaria");
				Field listaParam[] = c.getDeclaredFields();
				BufferedWriter w = new BufferedWriter(new FileWriter(JSON_FILE_NAME));
				String typeStr = "NULL";
				JSONArray metadataArray = new JSONArray();
				
				for(int i=0; i<firstLine.size();i++) { 
					JSONObject metadata = new JSONObject();
					metadata.put("alias", listaParam[i].getName()); //write on file the name of attribute
					metadata.put("sourceField", firstLine.get(i)); //write on file sourcefield of attribute
					if(listaParam[i].getType()==typeStr.getClass()) {
						typeStr="String";
					}else if(listaParam[i].getType().equals(Integer.TYPE)) {
						typeStr="Integer";
					}
					metadata.put("type:", typeStr); //write on file the type of attributes used
					metadataArray.add(metadata); //Create a file that return object json with the metadata of class
				}
				w.write(metadataArray.toJSONString());
				w.flush();
				w.close();
				System.out.println("File json getMetadata creato");
			}catch(IOException | ClassNotFoundException e) {
				e.printStackTrace();
				
			}
		}
}
