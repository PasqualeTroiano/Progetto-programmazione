package com.example;
/**
 * the Serialization class
 * starts from the "dati.csv" file already downloaded thanks
 *  to the DownloadFile class and takes care of executing the
 *   file serialization in a list and  then save the serialized file 
 *   in a file called "ScuolaSecondaria.ser".
 * 
 * @author Pasquale Pio Troiano
 */
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;
import java.io.*;
import java.nio.file.Files;

public class Serialization {
	List<ScuolaSecondaria> lista = new ArrayList<>(); // create list  "ScuolaSecondaria"

	/**
	 * Method for a correct creation ScuolaSecondaria with data of csv
	 * @return List<ScuolaSecondaria> that is the data of csv
	 */
	
	public List<ScuolaSecondaria> serialize(){
		String csvFile = "dati.csv"; //create and initialize  String "csvFile"
		String line = "";//create and initialize  String "line"
		String cvsSplitBy = ";";//create and initialize  String "cvsPlitBy"
	
		try {

			BufferedReader br = new BufferedReader(new FileReader(csvFile)); // open Buffer to read "csvFile"
			br.readLine();// skip the first line because there is no informations
			while ((line = br.readLine()) != null) { // While end of file, read line and put it in string 
				                                     //"line"
														

				List<String> school = Arrays.asList(line.split(cvsSplitBy, 7)); // split of line in an array
																				// of string

				for (int i = 0; i < school.size(); i++) {
					if (school.get(i).equals("") == true) { // if find in the cells of each position an empty cell
															// then puts -1
						school.set(i, "0");
					}

				}
				lista.add(new ScuolaSecondaria(school)); //create an obj "ScuolaSecondaria" to use it in the list

			}
			br.close();

		}catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}

		return lista;
	}
	/**
	 * Method that create a file ScuolaSecondaria.ser with serialization of the data on List<ScuolaSecondaria>
	 * @param lista that arrive with serialize
	 */
	public void outputfile(final List<ScuolaSecondaria> lista) {
		try {
			FileOutputStream fileOut = new FileOutputStream("ScuolaSecondaria.ser"); // Creation of a file
																					// appartamento.ser to put
																					// the serialization of the file
			ObjectOutputStream out = new ObjectOutputStream(fileOut);
			out.writeObject(lista); //Writing of the list object where all the data of the csv are saved
			out.close();
			fileOut.close();
			System.out.printf("Serialized data is saved in ScuolaSecondaria.ser\n");
		} catch (IOException i) {
			i.printStackTrace();
		}
		System.out.println("\nI dati serializzati sono: " + lista.size()); // Print the list size to see if
																			// everything has been saved

	}
}
