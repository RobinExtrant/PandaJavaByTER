package main.java;

import java.util.ArrayList;

import main.java.exception.TooMuchTypeInOneColumnException;

public class Datacolumn<E>{
	
	private ArrayList<E> datalines;
	
	public Datacolumn(Object[] colonne) throws TooMuchTypeInOneColumnException{
		this.datalines = new ArrayList<E>();
		for(Object cellule : colonne){
			if(cellule.getClass().getName().equals(colonne[0].getClass().getName())){
				datalines.add((E) cellule);
			}else{
				throw new TooMuchTypeInOneColumnException("Erreur : Types différents dans une colonne");
			}
		}
	}
	
}
