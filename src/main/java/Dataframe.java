package main.java;

import java.util.ArrayList;

public class Dataframe {
	
	public enum Types {INT, STRING, DOUBLE}
	
	private ArrayList<Datacolumn> columns;
	
	public Dataframe(Types[] types) {
		this.columns = new ArrayList<Datacolumn>();
		for (Types type : types) {
			switch (type) {
				case INT:
					this.columns.add(new Datacolumn<Integer>());
					break;
				case STRING:
					this.columns.add(new Datacolumn<String>());
					break;
				case DOUBLE:
					this.columns.add(new Datacolumn<Double>());
					break;
				default:
			}
		}
	}
}
