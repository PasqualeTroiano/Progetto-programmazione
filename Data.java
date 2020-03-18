package com.example;
/**
 * The Data class return data (JSON format)
 *  and show filters 
 * shows the data selection filters by customers who decide to 
 * filter the schools based on their characteristics
 * With a switch case the customer can name a file
 *  and choose whether to filter a string or an integer
 *  Then the customer can choose can choose from the keyboard to filter :Unita scolastica, Alunni f, Municipio, Classi
 * @author Pasquale Pio Troiano
 * 
 */
//some imports needed to read write and import files
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.OptionalDouble;
import java.util.OptionalInt;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Data {
	// created and initialized a private String JSON_FILE_NAME
	private static final String JSON_FILE_NAME = "getData.json";
	// created and initialized a private String JSON_FILE_NAME_FILTER
	private static final String JSON_FILE_NAME_FILTER = "getDataFilter";
	// created and initialized a String JSON_FILE_NAME_FILTER_SUMMINMAXAVG
	private static final String JSON_FILE_NAME_FILTER_SUMMINMAXAVG = "getDataFilterMAXMINAVGSUM";

	private Serialization s = new Serialization(); // inizialized a private Serialization called "s"
	List<ScuolaSecondaria> call = s.serialize(); // list of type ScuolaSecondaria called "call"

	/**
	 * Method void that print to a json file the get Data
	 * @author Pasquale Pio Troiano
	 */
	public void toJsonData() {

		try {
			// open a buffer writer of JSON_FILE_NAME
			BufferedWriter w = new BufferedWriter(new FileWriter(JSON_FILE_NAME));
			List<String> l = call.stream().map(a -> a.toString()).collect(Collectors.toList());
			Iterator<String> it = l.iterator();

			while (it.hasNext()) { // while there is other elements keep going
				w.write("{");
				w.newLine();
				w.write(it.next());
				w.write("},\n");

			}
			w.newLine();
			w.close(); // close the BuffreWriter

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	/**
	 * Method that return an int for the switch
	 * @param a is the data chosen by user
	 * @return a int for a correct selection
	 */
	public int ritornaDato(int a) {
		boolean flag1 = false;
		Scanner in = new Scanner(System.in);
		do {
			flag1 = true;
			try {
				a = in.nextInt();
			} catch (Exception e) {
				System.out.println("Inserire un numero intero");
				flag1 = false;
				in.nextLine();
			}
		} while (!flag1);
		return a;
	}
	/**
	 * Method that return an String for the switch
	 * @param app is the data chosen by user
	 * @return a String for a correct selection
	 */
	public String ritornaStringa(String app) {
		Scanner in = new Scanner(System.in);
		app = in.nextLine();
		return app;
	}
	
	/**
	 * Return a creation of a file with a name chosen by user
	 * @param a iterator for a correct cycle
	 * @param str string chosen by user
	 */
	  public void ritornaFile(Iterator<ScuolaSecondaria> a,String str) { 
		  try {
			  System.out.println("Dammi il nome che vuoi mettere al file");
			  String appoggio = null;
			  appoggio=this.ritornaStringa(str);
			  BufferedWriter w = new BufferedWriter(new FileWriter(JSON_FILE_NAME_FILTER+appoggio+".json"));
			  while (a.hasNext()) {// while there is other elements keep going
			    	w.write("{");
					w.newLine();
					w.write(a.next().toString()); //put the data on file
					w.write("},\n");
			 }
		 	w.newLine();
	 		w.close();	
     		} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
		}
	  }
	  
/**
 * Method that create a json file with a data filter
 */
	  public void jsonDataFilter() {
		boolean select = false;
		boolean select2 = false;
		boolean select3 = false;
		int choice = 0;
		int choice2 = 0;
		int choice3 = 0;
		int primo = 0;
		int secondo = 0;
		String str="NULL";
		do {
			System.out.println("Scegli che tipo di filtro vuoi fare" + "Si posssono utilizzare questi campi:"
					+ "\n1-Filtro Su Stringa\n2-Filtro su numero");
			choice = this.ritornaDato(choice);
			switch (choice) {
				case 1:
					do {
							System.out.println("Scegliere il campo di utilizzo del filrto\n"
									+ "Si posssono utilizzare questi campi:" + "\n1-anno scolastico\n2-gestione");
							choice2 = this.ritornaDato(choice2);
							switch (choice2) {
							case 1:
								String a;
								System.out.println("Dammi la stringa da ricercare per l'anno scolastico");
								a = this.ritornaStringa(str);
								List <ScuolaSecondaria> list = call.stream().filter(e -> e.getAnno_scol().equals(a)).collect(Collectors.toList());
								Iterator<ScuolaSecondaria> iter = list.iterator(); // Iterator creation to scroll the ScuolaSecondaria list
								System.out.println("\nGli elementi trovati per questo filtro sono: \t" + list.size()); // print the created items
								this.ritornaFile(iter,str);	
								select2 = true;
								break;
							case 2:
								String b;
								System.out.println("Dammi la stringa da ricercare per la gestione");
								b = this.ritornaStringa(str);
								List <ScuolaSecondaria> list2 = call.stream().filter(e -> e.getGestione().equals(b)).collect(Collectors.toList());
								Iterator<ScuolaSecondaria> iter2 = list2.iterator(); // Iterator creation to scroll the ScuolaSecondaria list
								System.out.println("\nGli elementi trovati per questo filtro sono: \t" + list2.size()); // print the  created items
								this.ritornaFile(iter2,str);							
								select2 = true;
								break;
							}
						} while (!select2);
						select=true;
						break;
				case 2:
					do {
						System.out.println("Scegliere il campo di utilizzo del filtro\n"
								+ "Si posssono utilizzare questi campi:" + "\n1-Unita scolastica\n2-Classi");
						choice3 = this.ritornaDato(choice3);
						switch(choice3) {
						case 1: 
							int dato;
							int dato2;
							System.out.println("\nDigitaree >=primonumero <=secondonumero (scelto) \nPrimo numero:\t");
							dato = this.ritornaDato(primo);
							System.out.println("Secondo numero:\t");
							dato2 = this.ritornaDato(secondo);
							List<ScuolaSecondaria> l = call.stream().filter(a -> a.getUnita_scol() >= dato && a.getUnita_scol()<=dato2).collect(Collectors.toList());// Use of stream and collections to create a filter of ScuolaSecondaria list
							Iterator<ScuolaSecondaria> it = l.iterator(); // Iterator creation to scroll the ScuolaSecondaria list
							System.out.println("\nGli elementi trovati per questo filtro sono: \t" + l.size()); // print the size of created items
							this.ritornaFile(it,str);
							select3=true;
							break;
						case 2:
							int dato3;
							int dato4;
							System.out.println("\nDigitaree >=primonumero <=secondonumero (scelto) \nPrimo numero:\t");
							dato3 = this.ritornaDato(primo);
							System.out.println("Secondo numero:\t");
							dato4 = this.ritornaDato(secondo);
							List<ScuolaSecondaria> l2 = call.stream().filter(a -> a.getClassi() >= dato3 && a.getClassi()<=dato4).collect(Collectors.toList());// Use of stream and collections to create a filter of ScuolaSecondaria list
							Iterator<ScuolaSecondaria> it2 = l2.iterator(); // Iterator creation to scroll the ScuolaSecondaria list
							System.out.println("\nGli elementi trovati per questo filtro sono: \t" + l2.size()); // print the size of created items
							this.ritornaFile(it2,str);
							select3=true;
							break;
						}
					}while(!select3);
				select=true;
			}
			} while (!select);
			System.out.println("E' stato creato il file json con il filtro ");
	}
	
/**
 * return a String due the choice of the user
 * @param a the choice of user
 * @return the choice of user
 */
	public String scelta(int a) {
		String str = "";
		if (a == 1) {
			str = "Classi";
		} else if (a == 2) {
			str = " Alunni F";
		} else if (a == 3) {
			str = " Municipio";
		} else if (a == 4) {
			str = " Unita scolastica";
		}
		return str;
	}
/**
 * Method that create a json file with a special statistic of the List<Appartamento>
 * @throws IOException
 */
	public void jsonDataSumAvgMinMaxCount() throws IOException {
		// initialized variables sum, count, choise to 0 //json file
		// initialized variables avg, max, min to null
		int sum = 0;
		OptionalDouble avg = null;
		OptionalInt max = null;
		OptionalInt min = null;
		long count = 0;
		int choice = 0;
		Scanner in = new Scanner(System.in);
		boolean validSelection = false;
		// let you choose between different filter fields
		do {
			System.out.println("Scegliere il campo di utilizzo del filto\n" + "Si posssono utilizzare questi campi:"
					+ "\n1-Classi\n2-Alunni F\n3-Municipio\n4-Unita scolastica");
			choice = in.nextInt(); // let write your choice
			// based on the choice switch case of 'choice' starts with the 4 possible cases
			switch (choice) {
			case 1:
				sum = call.stream().filter(p -> p.getClassi() != -1).mapToInt(ScuolaSecondaria::getClassi).sum();
				avg = call.stream().filter(p -> p.getClassi() != -1).mapToInt(ScuolaSecondaria::getClassi)
						.average();
				max = call.stream().filter(p -> p.getClassi() != -1).mapToInt(ScuolaSecondaria::getClassi).max();
				min = call.stream().filter(p -> p.getClassi() != -1).mapToInt(ScuolaSecondaria::getClassi).min();
				validSelection = true;
				break;
			case 2:
				sum = call.stream().filter(p -> p.getAlunni_f() != -1).mapToInt(ScuolaSecondaria::getAlunni_f).sum();
				avg = call.stream().filter(p -> p.getAlunni_f() != -1).mapToInt(ScuolaSecondaria::getAlunni_f)
						.average();
				max = call.stream().filter(p -> p.getAlunni_f() != -1).mapToInt(ScuolaSecondaria::getAlunni_f).max();
				min = call.stream().filter(p -> p.getAlunni_f() != -1).mapToInt(ScuolaSecondaria::getAlunni_f).min();
				count = call.stream().filter(p -> p.getAlunni_f() != -1).mapToInt(ScuolaSecondaria::getAlunni_f)
						.count();
				validSelection = true;
				break;
			case 3:
				sum = call.stream().filter(p -> p.getMunicipio() != -1).mapToInt(ScuolaSecondaria::getMunicipio).sum();
				avg = call.stream().filter(p -> p.getMunicipio() != -1).mapToInt(ScuolaSecondaria::getMunicipio).average();
				max = call.stream().filter(p -> p.getMunicipio() != -1).mapToInt(ScuolaSecondaria::getMunicipio).max();
				min = call.stream().filter(p -> p.getMunicipio() != -1).mapToInt(ScuolaSecondaria::getMunicipio).min();
				count = call.stream().filter(p -> p.getMunicipio() != -1).mapToInt(ScuolaSecondaria::getMunicipio).count();
				validSelection = true;
				break;
			case 4:
				sum = call.stream().filter(p -> p.getUnita_scol() != -1).mapToInt(ScuolaSecondaria::getUnita_scol).sum();
				avg = call.stream().filter(p -> p.getUnita_scol() != -1).mapToInt(ScuolaSecondaria::getUnita_scol).average();
				max = call.stream().filter(p -> p.getUnita_scol() != -1).mapToInt(ScuolaSecondaria::getUnita_scol).max();
				min = call.stream().filter(p -> p.getUnita_scol() != -1).mapToInt(ScuolaSecondaria::getUnita_scol).min();
				count = call.stream().filter(p -> p.getUnita_scol() != -1).mapToInt(ScuolaSecondaria::getUnita_scol).count();
				validSelection = true;
				break;
			}
		} while (validSelection != true);
		// in.close(); //close input stream
		// based on the choice prints the filtered data
		BufferedWriter w = new BufferedWriter(new FileWriter(JSON_FILE_NAME_FILTER_SUMMINMAXAVG+this.scelta(choice)+".json"));// open Buffer
		// to write on a
		w.write("{");
		w.newLine();
		w.write("\"Field\":" + this.scelta(choice));
		w.write("\n\"Sum\":" + sum);
		w.write("\n\"Avg\":" + avg);
		w.write("\n\"Max\":" + max);
		w.write("\n\"Min\":" + min);
		w.write("\n\"Count\":" + count);
		w.write("\n}");
		w.newLine();
		w.close();
		System.out.println("File " + JSON_FILE_NAME_FILTER_SUMMINMAXAVG+this.scelta(choice)+".json" + "creato");
	}

	// generate getter and setter of Serialization 's'
	public Serialization getS() {
		return s;
	}

	public void setS(Serialization s) {
		this.s = s;
	}

	public static String getJsonFileName() {
		return JSON_FILE_NAME;
	}


}