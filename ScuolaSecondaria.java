package com.example;
import java.io.*;
import java.util.List;
/**
 * the  ScuolaSecondaria class
 *  lists all the features of Milan secondary school
 *  I used strings and integers as needed
 *  Generated constructor and getters and setters
 * @author Pasquale Pio Troiano
 *
 */
// class "ScuolaSecondaria"

public class ScuolaSecondaria implements Serializable{
	int municipio;
	String anno_scol;
	String gestione;
	int unita_scol;
	int classi;
	int alunni_f;
	int alunni_mf;

    //Generate constructors of class "ScuolaSecondaria"
	public ScuolaSecondaria(int municipio, String anno_scol, String gestione, int unita_scol,
			int classi, int alunni_f, int alunni_mf) {
		this.municipio = municipio;
		this.anno_scol = anno_scol;
		this.gestione = gestione;
		this.unita_scol = unita_scol;
		this.classi = classi;
		this.alunni_f = alunni_f;
		this.alunni_mf = alunni_mf;
	}
	
	//created list "school" with features of class "ScuolaSecondaria"
	/**
	 * This is a constructor for a better serialization
	 * @param school
	 */
	public ScuolaSecondaria( final List<String> school)  {
		this(Integer.parseInt(school.get(0)),school.get(1),school.get(2),Integer.parseInt(school.get(3)),Integer.parseInt(school.get(4)),Integer.parseInt(school.get(5)),Integer.parseInt(school.get(6)));
		}
	
	public ScuolaSecondaria() {
		super();
	}
	
	@Override
	public String toString() {
		return "\"municipio\": \"" + municipio + "\"anno_scol\": \"" + "\"gestione\": \"" + gestione + "\"unita_scol\": \"" + unita_scol + 
				"\"classi\": \"" + classi + "\"alunni_f\": \"" + alunni_f + "\"alunni_mf\": \"" + alunni_mf + "\"\n";
	}
	
	//Generate getters and setters of class "ScuolaSecondaria"
	public int getMunicipio() {
		return municipio;
	}

	public void setMunicipio(int municipio) {
		this.municipio = municipio;
	}

	public String getAnno_scol() {
		return anno_scol;
	}

	public void setAnno_scol(String anno_scol) {
		this.anno_scol = anno_scol;
	}

	public String getGestione() {
		return gestione;
	}

	public void setGestione(String gestione) {
		this.gestione = gestione;
	}

	public int getUnita_scol() {
		return unita_scol;
	}

	public void setUnita_scol(int unita_scol) {
		this.unita_scol = unita_scol;
	}

	public int getClassi() {
		return classi;
	}

	public void setClassi(int classi) {
		this.classi = classi;
	}

	public int getAlunni_f() {
		return alunni_f;
	}

	public void setAlunni_f(int alunni_f) {
		this.alunni_f = alunni_f;
	}

	public int getAlunni_mf() {
		return alunni_mf;
	}

	public void setAlunni_mf(int alunni_mf) {
		this.alunni_mf = alunni_mf;
	}
	
}
	
	
