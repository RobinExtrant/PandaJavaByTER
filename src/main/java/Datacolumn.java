package main.java;

import java.util.ArrayList;

import main.java.exception.TooMuchTypeInOneColumnException;

public class Datacolumn<E>{
	
	private ArrayList<E> datalines;
	private String label;
	
	public Datacolumn(Object[] colonne, String label) throws TooMuchTypeInOneColumnException{
		this.datalines = new ArrayList<E>();
		this.label = label;
		for(Object cellule : colonne){
			if(cellule.getClass().getName().equals(colonne[0].getClass().getName())){
				datalines.add((E) cellule);
			}else{
				throw new TooMuchTypeInOneColumnException("Erreur : Types différents dans une colonne");
			}
		}
	}

	public void afficherColonne() {
		System.out.print(this.label + ": ");
		for(E cellule : this.datalines){
			System.out.print(cellule + " ");
		}
		System.out.println();
		
	}
	
}
