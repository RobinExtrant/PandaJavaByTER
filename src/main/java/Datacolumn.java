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
	
	public ArrayList<E> getDatalines() {
		return datalines;
	}

	public String getLabel() {
		return label;
	}

	public float computeAverage() {
		float sum = 0;
		switch (this.datalines.get(0).getClass().getName()){
			case "java.lang.Integer":
				for(int index = 0; index < this.datalines.size(); index++){
					sum += (Integer) this.datalines.get(index);
				}
				break;
			case "java.lang.Double":
				for(int index = 0; index < this.datalines.size(); index++){
					sum += (Double) this.datalines.get(index);
				}
				break;
			default:
				throw new IllegalArgumentException("Impossible d'effectuer une moyenne sur ce type de données.");
		}
		return sum/this.datalines.size();
	}
	
	public Object getMaximum() {
		switch (this.datalines.get(0).getClass().getName()){
			case "java.lang.Integer":
				Object max = (Integer) this.datalines.get(0);
				for(int index = 1; index < this.datalines.size(); index++){
					if((Integer) max < (Integer) this.datalines.get(index)){
						max = (Integer) this.datalines.get(index);
					}
				}
				return (Integer) max;
			case "java.lang.Double":
				max = (Double) this.datalines.get(0);
				for(int index = 1; index < this.datalines.size(); index++){
					if((Double) max < (Double) this.datalines.get(index)){
						max = (Double) this.datalines.get(index);
					}
				}
				return (Double) max;
			default:
				throw new IllegalArgumentException("Impossible de chercher un maximum sur ce type de données.");
		}
	}
	
	public Object getMinimum() {
		switch (this.datalines.get(0).getClass().getName()){
		case "java.lang.Integer":
			Object min = (Integer) this.datalines.get(0);
			for(int index = 1; index < this.datalines.size(); index++){
				if((Integer) min > (Integer) this.datalines.get(index)){
					min = (Integer) this.datalines.get(index);
				}
			}
			return (Integer) min;
		case "java.lang.Double":
			min = (Double) this.datalines.get(0);
			for(int index = 1; index < this.datalines.size(); index++){
				if((Double) min > (Double) this.datalines.get(index)){
					min = (Double) this.datalines.get(index);
				}
			}
			return (Double) min;
		default:
			throw new IllegalArgumentException("Impossible de chercher un minimum sur ce type de données.");
		}
	}
	
}
