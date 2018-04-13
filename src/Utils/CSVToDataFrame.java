package Utils;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

import com.opencsv.CSVReader;

import main.java.exception.UnknownTypeException;

public class CSVToDataFrame {

	public String[][] readCSV() throws IOException{
		//lire fichier CSV
		final String RESOURCES_PATH = "/home/z/zhaot/git/PandaJavaByTER/";
	    final String FILE_NAME = "fichier.csv";
	    final char SEPARATOR = ';';
		File file = new File(RESOURCES_PATH + FILE_NAME );
		FileReader fr = new FileReader(file);
		CSVReader csvreader = new CSVReader(fr,SEPARATOR);
		
		
//		[0][0] : Prenom
//		[1][0] --->> [0][1] : manu  
//		[2][0] --->> [0][2] : Robin
//		
//		[0][1] --->> [1][0] : Age
// 		[1][1] --->> [1][1]   21 
		// recuperer chaque colonne a la main
		List<String[]> contenu = csvreader.readAll(); 
		//String [][] columns = new String[contenu.size()][contenu.get(0).length];
		String [][] columns = new String[contenu.get(0).length][contenu.size()];
		for(int i=0; i<contenu.get(0).length;i++){
			//String[] ligne = contenu.get(i);
			for(int j=0; j<contenu.size();j++){	
				columns[i][j] = contenu.get(j)[i];
				//System.out.println(contenu.get(j)[i]);
			}
		}
		// test columns
		System.out.println("**************");
		for(int i=0; i<columns.length;i++){
			for(int j=0; j<columns[0].length;j++){	
				System.out.println(columns[i][j]);
			}
		}
	
		return columns;
		
	}
	
	public String[] getLabels(String[][] columns){
		System.out.println("**************");
		String[] labels = new String[columns.length];
		for(int i=0; i<columns.length;i++){	
			labels[i] = columns[i][0];
		}
		
		for(int i=0;i<labels.length;i++){
		System.out.println(labels[i]);
		}
		return labels;
	}
	
	public Object[] getColumnWithRightType(String[] column) throws UnknownTypeException{
		//enlever le label 
		//String[] tables = new String[column.length-1];
		
		switch(column[1].getClass().getName()){
		case "java.lang.Integer":
			Integer[] result1 = new Integer[column.length-1];
			//Remplir result
			for (int j = 1; j < column.length; j++) {
				// récupérer la valeur à partir de l'index 0
				result1[j - 1] = Integer.parseInt(column[j]);
			}
			return result1;
		case "java.lang.String":
			String[] result2 = new String[column.length-1];
			//Remplir result
			for (int j = 1; j < column.length; j++) {
				// récupérer la valeur à partir de l'index 0
				result2[j - 1] = column[j];
			}
			return result2;
		case "java.lang.Double":
			Double[] result3 = new Double[column.length-1];
			//Remplir result
			for (int j = 1; j < column.length; j++) {
				// récupérer la valeur à partir de l'index 0
				result3[j - 1] = Double.parseDouble(column[j]);
			}
			return result3;
		case "java.lang.Boolean":
			Boolean[] result4 = new Boolean[column.length-1];
			//Remplir result
			for (int j = 1; j < column.length; j++) {
				// récupérer la valeur à partir de l'index 0
				result4[j - 1] = Boolean.parseBoolean(column[j]);
			}
			return result4;
		default:
			throw new UnknownTypeException("Type non reconnu lors de la création du dataframe.\nTypes supportés : Integer, String, Float, Boolean");
		}
	}	
}