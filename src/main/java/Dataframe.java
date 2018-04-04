package main.java;

import java.util.ArrayList;

import main.java.exception.TooMuchTypeInOneColumnException;
import main.java.exception.UnequalArraySizeException;
import main.java.exception.UnknownTypeException;

public class Dataframe {
	
	private ArrayList<Datacolumn> columns;
	
	public Dataframe(Object[][] contenu, String[] labels) throws TooMuchTypeInOneColumnException, UnknownTypeException, UnequalArraySizeException {
		if(contenu.length != labels.length){
			throw new UnequalArraySizeException();
		}
		this.columns = new ArrayList<Datacolumn>();
		for(int index = 0; index < contenu.length; index++){
			switch (contenu[index][0].getClass().getName()) {
				case "java.lang.Integer":
					this.columns.add(new Datacolumn<Integer>(contenu[index], labels[index]));
					break;
				case "java.lang.String":
					this.columns.add(new Datacolumn<String>(contenu[index], labels[index]));
					break;
				case "java.lang.Double":
					this.columns.add(new Datacolumn<Double>(contenu[index], labels[index]));
					break;
				case "java.lang.Boolean":
					this.columns.add(new Datacolumn<Boolean>(contenu[index], labels[index]));
					break;
				default:
					throw new UnknownTypeException("Type non reconnu lors de la création du dataframe.\nTypes supportés : Integer, String, Float, Boolean");
			}
		}
	}

	public void displayAll() {
		for(Datacolumn colonne : this.columns){
			colonne.afficherColonne();
		}
		
	}

	public void displayFirstLines(int i) {
		if(i > this.columns.size() || i < 0){
			throw new IndexOutOfBoundsException("Nombre d'éléments à afficher trop grand (maximum = " + this.columns.size() + ").");
		}
		for(int index = 0; index < i; index++){
			this.columns.get(index).afficherColonne();
		}
	}

	public void displayLastLines(int i) {
		if(i > this.columns.size() || i < 0){
			throw new IndexOutOfBoundsException("Nombre d'éléments à afficher trop grand (maximum = " + this.columns.size() + ").");
		}
		for(int index = 0; index < i; index++){
			this.columns.get(this.columns.size() -1 - index).afficherColonne();
		}
		
	}
	
	public Dataframe selectFirstLines(int i) {
		if(i > this.columns.size() || i < 0){
			throw new ArrayIndexOutOfBoundsException();
		}
		return null;
	}
	
	public Dataframe selectLastLines(int i) {
		return null;
	}
	
	public Dataframe selectLines(int start, int end) {
		return null;
	}
}
