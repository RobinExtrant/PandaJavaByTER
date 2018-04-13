package main.java;

import java.util.ArrayList;

import main.java.exception.*;

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
					throw new UnknownTypeException("Type non reconnu lors de la crÃ©ation du dataframe.\nTypes supportÃ©s : Integer, String, Float, Boolean");
			}
		}
	}

	public void displayAll() {
		System.out.println(this.getAllLines());
	}
	
	public String getAllLines() {
		String result = new String();
		for(Datacolumn colonne : this.columns){
			result += colonne.afficherColonne();
		}
		return result;
	}

	public void displayFirstLines(int i) {
		System.out.println(this.getFirstLines(i));
	}
	
	public String getFirstLines(int i) {
		if(i > this.columns.get(0).getDatalines().size() || i < 0){
			throw new IndexOutOfBoundsException("Nombre d'Ã©lÃ©ments Ã  afficher trop grand (maximum = " + this.columns.size() + ").");
		}
		String result = new String();
		for (Datacolumn column : this.columns) {
			result += column.afficherColonne(0, i);
		}
		return result;
	}
	
	public void displayLastLines(int i) {
		System.out.println(this.getLastLines(i));
	}
	
	public String getLastLines(int i) {
		if(i > this.columns.get(0).getDatalines().size() || i < 0){
			throw new IndexOutOfBoundsException("Nombre d'Ã©lÃ©ments Ã  afficher trop grand (maximum = " + this.columns.size() + ").");
		}
		String result = new String();
		for (Datacolumn column : this.columns) {
			result += column.afficherColonne(this.columns.get(0).getDatalines().size()-i, this.columns.get(0).getDatalines().size());
		}
		return result;
	}
	
	public Dataframe selectFirstLines(int i) throws UnequalColumnSizeException, UncorrectParameterOrderException {
		return selectLines(0,i);
	}
	
	public Dataframe selectLastLines(int i) throws UnequalColumnSizeException, UncorrectParameterOrderException {
		return selectLines(this.columns.get(0).getDatalines().size()-i,this.columns.get(0).getDatalines().size());
	}
	
	public Dataframe selectLines(int start, int end) throws UnequalColumnSizeException, UncorrectParameterOrderException {
		for (Datacolumn column : this.columns) {
			if(start > column.getDatalines().size() || start < 0 || end > column.getDatalines().size() || end < 0){
				throw new ArrayIndexOutOfBoundsException();
			}
		}
		if(end <= start){
			throw new UncorrectParameterOrderException();
		}
		/*
		for(int index = start; index < end; index++){
			if(this.columns.get(index).getDatalines().size() != this.columns.get(0).getDatalines().size()){
				throw new UnequalColumnSizeException();
			}
		}*/
		String[] labels = new String[this.columns.size()];
		Object[][] result = new Object[this.columns.size()][end-start];
		
		for(int index = 0; index < this.columns.size(); index++){
			labels[index] = this.columns.get(index).getLabel();
		}
		
		int indexLineInResult = 0;
		for(int index = start; index < end; index++){
			for(int index2 = 0; index2 < this.columns.size(); index2++){
				result[index2][indexLineInResult] = this.columns.get(index2).getDatalines().get(index);
			}
			indexLineInResult++;
		}
		try {
			return new Dataframe(result, labels);
		} catch (Exception e) {
			System.err.println("Impossible de recrÃ©er un Dataframe Ã  partir des " + start + " Ã  " + end + " lignes");
			return null;
		}
	}
	
	public void showAverage(int i){
		System.out.println(getAverage(i));
	}
	
	public float getAverage(int i){
		if(i > this.columns.size() || i < 0){
			throw new ArrayIndexOutOfBoundsException();
		}
		else{
			return this.columns.get(i).computeAverage();
		}
	}
	
	public void showMin(int i){
		System.out.println(getMin(i));
	}
	
	public double getMin(int i){
		if(i > this.columns.size() || i < 0){
			throw new ArrayIndexOutOfBoundsException();
		}
		else{
			return this.columns.get(i).getMinimum();
		}
	}
	
	public void showMax(int i){
		System.out.println(getMax(i));
	}
	
	public double getMax(int i){
		if(i > this.columns.size() || i < 0){
			throw new ArrayIndexOutOfBoundsException();
		}
		else{
			return (Double) this.columns.get(i).getMaximum();
		}
	}
}
