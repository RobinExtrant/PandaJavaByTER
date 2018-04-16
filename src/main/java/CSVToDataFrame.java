package main.java;

import java.io.IOException;
import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

import main.java.exception.UnknownTypeException;

public class CSVToDataFrame {

	public static String[][] readCSV(String path, String file) throws IOException{
	    final String FILE_NAME = path+file;
		File f = new File(FILE_NAME);
        Scanner scannerFile = new Scanner(f);
		ArrayList<String[]> contenu = new ArrayList<String[]>();
		while(scannerFile.hasNextLine()){
			String line = scannerFile.nextLine();
			Scanner scannerLine = new Scanner(line);
			scannerLine.useDelimiter(";");
			ArrayList<String> lineArray = new ArrayList<String>();
			while(scannerLine.hasNext()){
				lineArray.add(scannerLine.next());
			}
			contenu.add(lineArray.toArray(new String[lineArray.size()]));
			scannerLine.close();
		}
		String [][] columns = new String[contenu.get(0).length][contenu.size()];
		for(int i=0; i<contenu.get(0).length;i++){
			for(int j=0; j<contenu.size();j++){	
				columns[i][j] = contenu.get(j)[i];
			}
		}
		scannerFile.close();
		return columns;
		
	}
	
	public static String[] getLabels(String[][] columns){
		String[] labels = new String[columns.length];
		for(int i=0; i<columns.length;i++){	
			labels[i] = columns[i][0];
		}
		
		return labels;
	}
	
	public static Object[] getColumnWithRightType(String[] column) throws UnknownTypeException{

		Object[] result = new Object[column.length-1];
		
		for (int j = 1; j < column.length; j++) {	
			if(column[j].equals("true") || column[j].equals("false")){
				result[j - 1] = Boolean.parseBoolean(column[j]);			
			}else{		
				try{
					result[j - 1] = Integer.parseInt(column[j]);
				}catch(Exception e2){
					try{
						result[j - 1] = Double.parseDouble(column[j]);
					}catch(Exception e3){
						result[j - 1] = column[j];
					}
				}
			}
			
		}
		return result;
		/*
		if(column[1].equals("true") || column[1].equals("false")){
			Boolean[] result1 = new Boolean[column.length-1];
			for (int j = 1; j < column.length; j++) {
				result1[j - 1] = Boolean.parseBoolean(column[j]);
			}
			return result1;
			
		}else{
			
			try{
				Integer[] result2 = new Integer[column.length-1];
				for (int j = 1; j < column.length; j++) {
					result2[j - 1] = Integer.parseInt(column[j]);
				}
				return result2;
			}catch(Exception e2){
				try{
					Double[] result3 = new Double[column.length-1];
					for (int j = 1; j < column.length; j++) {
						result3[j - 1] = Double.parseDouble(column[j]);
					}
					return result3;
				}catch(Exception e3){
					String[] result4 = new String[column.length-1];
					for (int j = 1; j < column.length; j++) {
						result4[j - 1] = column[j];
					}
					return result4;
					}
			}
		}*/
	}
	
}