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

	public String afficherColonne() {
		String result = new String();
		result += this.label + ": ";
		for(E cellule : this.datalines){
			result += cellule + " ";
		}
		result+= "\n";
		return result;
	}
	
	public String afficherColonne(int start, int end) {
		String result = new String();
		result += this.label + ": ";
		for (int indexLine = start; indexLine < end; indexLine++) {
			result += this.datalines.get(indexLine) + " ";
		}
		result+= "\n";
		return result;
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
	
	public Double getMaximum() {
		switch (this.datalines.get(0).getClass().getName()){
			case "java.lang.Integer":
				Integer maxI = (Integer) this.datalines.get(0);
				for(int index = 1; index < this.datalines.size(); index++){
					if((Integer) maxI < (Integer) this.datalines.get(index)){
						maxI = (Integer) this.datalines.get(index);
					}
				}
				return maxI * 1.0;
			case "java.lang.Double":
				Double maxD = (Double) this.datalines.get(0);
				for(int index = 1; index < this.datalines.size(); index++){
					if((Double) maxD < (Double) this.datalines.get(index)){
						maxD = (Double) this.datalines.get(index);
					}
				}
				return maxD;
			default:
				throw new IllegalArgumentException("Impossible de chercher un maximum sur ce type de données.");
		}
	}
	
	public Double getMinimum() {
		switch (this.datalines.get(0).getClass().getName()){
		case "java.lang.Integer":
			Integer minI = (Integer) this.datalines.get(0);
			for(int index = 1; index < this.datalines.size(); index++){
				if((Integer) minI > (Integer) this.datalines.get(index)){
					minI = (Integer) this.datalines.get(index);
				}
			}
			return minI * 1.0;
		case "java.lang.Double":
			Double minD = (Double) this.datalines.get(0);
			for(int index = 1; index < this.datalines.size(); index++){
				if((Double) minD > (Double) this.datalines.get(index)){
					minD = (Double) this.datalines.get(index);
				}
			}
			return minD;
		default:
			throw new IllegalArgumentException("Impossible de chercher un minimum sur ce type de données.");
		}
	}
	
}
