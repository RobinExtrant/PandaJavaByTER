package main.java;

import java.util.ArrayList;

import main.java.exception.TooMuchTypeInOneColumnException;
import main.java.exception.UnknownTypeException;

public class Dataframe {
	
	private ArrayList<Datacolumn> columns;
	
	public Dataframe(Object[][] contenu) throws TooMuchTypeInOneColumnException, UnknownTypeException {
		this.columns = new ArrayList<Datacolumn>();
		for (Object[] colonne : contenu) {
			switch (colonne[0].getClass().getName()) {
				case "java.lang.Integer":
					this.columns.add(new Datacolumn<Integer>(colonne));
					break;
				case "java.lang.String":
					this.columns.add(new Datacolumn<String>(colonne));
					break;
				case "java.lang.Float":
					this.columns.add(new Datacolumn<Float>(colonne));
					break;
				case "java.lang.Boolean":
					this.columns.add(new Datacolumn<Boolean>(colonne));
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
		if(i > this.columns.size()){
			throw new IndexOutOfBoundsException("Nombre d'éléments à afficher trop grand (maximum = " + this.columns.size() + ").");
		}
		for(int index = 0; index < i; index++){
			this.columns.get(index).afficherColonne();
		}
	}

	public void displayLastLines(int i) {
		if(i > this.columns.size()){
			throw new IndexOutOfBoundsException("Nombre d'éléments à afficher trop grand (maximum = " + this.columns.size() + ").");
		}
		for(int index = 0; index < i; index++){
			this.columns.get(this.columns.size() - index).afficherColonne();
		}
		
	}
	
}
