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
	
	public Dataframe selectFirstLines(int i) throws UnequalColumnSizeException {
		if(i > this.columns.size() || i < 0){
			throw new ArrayIndexOutOfBoundsException();
		}
		for(int index = 0; index < i; index++){
			if(this.columns.get(index).getDatalines().size() != this.columns.get(0).getDatalines().size()){
				throw new UnequalColumnSizeException();
			}
		}
		String[] labels = new String[i];
		Object[][] result = new Object[i][this.columns.get(0).getDatalines().size()];
		
		for(int index = 0; index < i; index++){
			labels[index] = this.columns.get(index).getLabel();
			for(int index2 = 0; index2 < this.columns.get(index).getDatalines().size(); index2++){
				result[index][index2] = this.columns.get(index).getDatalines().get(index2);
			}
		}
		try {
			return new Dataframe(result, labels);
		} catch (Exception e) {
			System.err.println("Impossible de recréer un Dataframe à partir des " + i + " premières lignes");
			return null;
		}
	}
	
	public Dataframe selectLastLines(int i) throws UnequalColumnSizeException {
		if(i > this.columns.size() || i < 0){
			throw new ArrayIndexOutOfBoundsException();
		}
		for(int index = i; index < this.columns.size(); index++){
			if(this.columns.get(index).getDatalines().size() != this.columns.get(0).getDatalines().size()){
				throw new UnequalColumnSizeException();
			}
		}
		String[] labels = new String[this.columns.size()-i];
		Object[][] result = new Object[this.columns.size()-i][this.columns.get(this.columns.size()-i).getDatalines().size()];
		
		for(int index = this.columns.size()-1; index >= this.columns.size()-i; index--){
			labels[index] = this.columns.get(index).getLabel();
			for(int index2 = 0; index2 < this.columns.get(index).getDatalines().size(); index2++){
				result[index][index2] = this.columns.get(index).getDatalines().get(index2);
			}
		}
		try {
			return new Dataframe(result, labels);
		} catch (Exception e) {
			System.err.println("Impossible de recréer un Dataframe à partir des " + i + " dernières lignes");
			return null;
		}
	}
	
	public Dataframe selectLines(int start, int end) throws UnequalColumnSizeException, UncorrectParameterOrderException {
		if(start > this.columns.size() || start < 0 || end > this.columns.size() || end < 0){
			throw new ArrayIndexOutOfBoundsException();
		}
		if(end < start){
			throw new UncorrectParameterOrderException();
		}
		for(int index = start; index < end; index++){
			if(this.columns.get(index).getDatalines().size() != this.columns.get(0).getDatalines().size()){
				throw new UnequalColumnSizeException();
			}
		}
		String[] labels = new String[end-start];
		Object[][] result = new Object[end-start][this.columns.get(start).getDatalines().size()];
		
		for(int index = start; index > end; index--){
			labels[index] = this.columns.get(index).getLabel();
			for(int index2 = 0; index2 < this.columns.get(index).getDatalines().size(); index2++){
				result[index][index2] = this.columns.get(index).getDatalines().get(index2);
			}
		}
		try {
			return new Dataframe(result, labels);
		} catch (Exception e) {
			System.err.println("Impossible de recréer un Dataframe à partir des " + start + " à " + end + " lignes");
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
			return (Double) this.columns.get(i).getMinimum();
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
