package test.java;

import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.*;
import main.java.Dataframe;
import main.java.exception.*;

public class DataframeTest {

	//For constructors
	@Test
	public void test_Constructor_good() throws TooMuchTypeInOneColumnException, UnknownTypeException, UnequalArraySizeException {
		Object[][] contenu = new Object[][]{{4,5,8},{"Hello", "Goodbye", "Thank you"},{true, false, true},{4.6, 3.8, 2.6}};
		String [] labels = new String[]{"entiers", "string", "booleens", "doubles"};
		Dataframe datatest = new Dataframe(contenu, labels);
	}
	
	@Test(expected = TooMuchTypeInOneColumnException.class)
	public void test_Constructor_toomuch_type_in_one_column_wrong() throws TooMuchTypeInOneColumnException, UnknownTypeException, UnequalArraySizeException {
		Object[][] contenu = new Object[][]{{4.5, 5 ,8},{"Hello", "Goodbye", "Thank you"}};
		String [] labels = new String[]{"double", "string"};
		Dataframe datatest = new Dataframe(contenu, labels);
	}
	
	@Test(expected = UnknownTypeException.class)
	public void test_Constructor_unknown_type_wrong() throws TooMuchTypeInOneColumnException, UnknownTypeException, UnequalArraySizeException {
		long a = 4;
		long b = 6;
		long c = 8;
		Object[][] contenu = new Object[][]{{a,b,c},{"Hello", "Goodbye", "Thank you"}};
		String [] labels = new String[]{"long", "string"};
		Dataframe datatest = new Dataframe(contenu, labels);
	}
	
	@Test(expected = UnequalArraySizeException.class)
	public void test_Constructor_wrong_size_array_label() throws TooMuchTypeInOneColumnException, UnknownTypeException, UnequalArraySizeException {
		Object[][] contenu = new Object[][]{{2,4,7},{"Hello", "Goodbye", "Thank you"}, {4.6,3.8}};
		String [] labels = new String[]{"entiers", "string"};
		Dataframe datatest = new Dataframe(contenu, labels);
	}
	
	//For display all
	public Dataframe createGoodDataset() throws TooMuchTypeInOneColumnException, UnknownTypeException, UnequalArraySizeException {
		Object[][] contenu = new Object[][]{{40,50,60},{"Hello", "Goodbye", "Thank you"}};
		String [] labels = new String[]{"entiers", "string"};
		Dataframe datatest = new Dataframe(contenu, labels);
		return datatest;
	}
	@Test
	public void test_Display_all() throws TooMuchTypeInOneColumnException, UnknownTypeException, UnequalArraySizeException {
		Dataframe datatest = createGoodDataset();
		datatest.displayAll();
		assertEquals("entiers: 40 50 60 \nstring: Hello Goodbye Thank you \n", datatest.getAllLines());
	}
	
	//For read from csv
	@Test
	public void test_reader_csv_good() throws TooMuchTypeInOneColumnException, UnknownTypeException, UnequalArraySizeException, IOException {
		Dataframe datatest = Dataframe.readFromFile("./data/", "dataset_good.csv");
		assertEquals("entiers: 40 50 60 \nstring: Hello Goodbye Thank you \ndoubles: 35.5 40.0 44.5 \n", datatest.getAllLines());
		assertEquals("Problème dans le calcul d'une moyenne sur des entiers provenant d'un fichier csv", 50, datatest.getAverage(0), 0.0001);
		assertEquals("Problème dans le calcul d'une moyenne sur des doubles provenant d'un fichier csv", 40.0, datatest.getAverage(2), 0.0001);
	}
	
	@Test(expected = TooMuchTypeInOneColumnException.class)
	public void test_reader_toomuch_type_in_one_column_wrong() throws TooMuchTypeInOneColumnException, UnknownTypeException, UnequalArraySizeException, IOException {
		Dataframe datatest = Dataframe.readFromFile("./data/", "toomuchtype_dataset.csv");
	}
	
	//For display first line
	@Test
	public void test_Display_first_lines_good() throws TooMuchTypeInOneColumnException, UnknownTypeException, UnequalArraySizeException {
		Dataframe datatest = createGoodDataset();
		datatest.displayFirstLines(2);
		assertEquals("entiers: 40 50 \nstring: Hello Goodbye \n", datatest.getFirstLines(2));
	}
	
	@Test(expected = IndexOutOfBoundsException.class)
	public void test_Display_first_lines_wrong_with_index_too_big() throws TooMuchTypeInOneColumnException, UnknownTypeException, UnequalArraySizeException {
		Dataframe datatest = createGoodDataset();
		datatest.displayFirstLines(4);
	}
	
	@Test(expected = IndexOutOfBoundsException.class)
	public void test_Display_first_lines_wrong_with_negative_index() throws TooMuchTypeInOneColumnException, UnknownTypeException, UnequalArraySizeException {
		Dataframe datatest = createGoodDataset();
		datatest.displayFirstLines(-3);
	}
	//For display last line
	@Test
	public void test_Display_last_lines_good() throws TooMuchTypeInOneColumnException, UnknownTypeException, UnequalArraySizeException {
		Dataframe datatest = createGoodDataset();
		datatest.displayLastLines(2);
		assertEquals("entiers: 50 60 \nstring: Goodbye Thank you \n", datatest.getLastLines(2));
	}
	
	@Test(expected = IndexOutOfBoundsException.class)
	public void test_Display_last_lines_wrong_with_index_too_big() throws TooMuchTypeInOneColumnException, UnknownTypeException, UnequalArraySizeException {
		Dataframe datatest = createGoodDataset();
		datatest.displayLastLines(4);
	}
	
	@Test(expected = IndexOutOfBoundsException.class)
	public void test_Display_last_lines_wrong_with_negative_index() throws TooMuchTypeInOneColumnException, UnknownTypeException, UnequalArraySizeException {
		Dataframe datatest = createGoodDataset();
		datatest.displayLastLines(-3);
	}
	
	//For select firstline
	@Test
	public void test_Select_first_lines_good() throws TooMuchTypeInOneColumnException, UnknownTypeException, UnequalArraySizeException, UnequalColumnSizeException, UncorrectParameterOrderException {
		Dataframe datatest = createGoodDataset();
		datatest.selectFirstLines(2);
	}
	
	@Test(expected = IndexOutOfBoundsException.class)
	public void test_Select_first_lines_wrong_with_index_too_big() throws TooMuchTypeInOneColumnException, UnknownTypeException, UnequalArraySizeException, UnequalColumnSizeException, UncorrectParameterOrderException {
		Dataframe datatest = createGoodDataset();
		datatest.selectFirstLines(4);
	}
	
	@Test(expected = IndexOutOfBoundsException.class)
	public void test_Select_first_lines_wrong_with_negative_index() throws TooMuchTypeInOneColumnException, UnknownTypeException, UnequalArraySizeException, UnequalColumnSizeException, UncorrectParameterOrderException {
		Dataframe datatest = createGoodDataset();
		datatest.selectFirstLines(-3);
	}
	
	//For select lastline
	@Test
	public void test_Select_last_lines_good() throws TooMuchTypeInOneColumnException, UnknownTypeException, UnequalArraySizeException, UnequalColumnSizeException, UncorrectParameterOrderException {
		Dataframe datatest = createGoodDataset();
		datatest.selectLastLines(2);
	}
	
	@Test(expected = IndexOutOfBoundsException.class)
	public void test_Select_last_lines_wrong_with_index_too_big() throws TooMuchTypeInOneColumnException, UnknownTypeException, UnequalArraySizeException, UnequalColumnSizeException, UncorrectParameterOrderException {
		Dataframe datatest = createGoodDataset();
		datatest.selectLastLines(4);
	}
	
	@Test(expected = IndexOutOfBoundsException.class)
	public void test_Select_last_lines_wrong_with_negative_index() throws TooMuchTypeInOneColumnException, UnknownTypeException, UnequalArraySizeException, UnequalColumnSizeException, UncorrectParameterOrderException {
		Dataframe datatest = createGoodDataset();
		datatest.selectLastLines(-3);
	}
	
	//For select lines
	@Test
	public void test_Select_lines_good() throws TooMuchTypeInOneColumnException, UnknownTypeException, UnequalArraySizeException, UnequalColumnSizeException, UncorrectParameterOrderException {
		Dataframe datatest = createGoodDataset();
		datatest.selectLines(1,2);
	}
	
	@Test(expected = IndexOutOfBoundsException.class)
	public void test_Select_lines_wrong_with_firstIndex_too_big() throws TooMuchTypeInOneColumnException, UnknownTypeException, UnequalArraySizeException, UnequalColumnSizeException, UncorrectParameterOrderException {
		Dataframe datatest = createGoodDataset();
		datatest.selectLines(4,2);
	}
	
	@Test(expected = IndexOutOfBoundsException.class)
	public void test_Select_lines_wrong_with_negative_firstIndex() throws TooMuchTypeInOneColumnException, UnknownTypeException, UnequalArraySizeException, UnequalColumnSizeException, UncorrectParameterOrderException {
		Dataframe datatest = createGoodDataset();
		datatest.selectLines(-1,2);
	}
	
	@Test(expected = IndexOutOfBoundsException.class)
	public void test_Select_lines_wrong_with_secondIndex_too_big() throws TooMuchTypeInOneColumnException, UnknownTypeException, UnequalArraySizeException, UnequalColumnSizeException, UncorrectParameterOrderException {
		Dataframe datatest = createGoodDataset();
		datatest.selectLines(1,4);
	}
	
	@Test(expected = IndexOutOfBoundsException.class)
	public void test_Select_lines_wrong_with_negative_secondIndex() throws TooMuchTypeInOneColumnException, UnknownTypeException, UnequalArraySizeException, UnequalColumnSizeException, UncorrectParameterOrderException {
		Dataframe datatest = createGoodDataset();
		datatest.selectLines(1,-1);
	}
	
	@Test(expected = UncorrectParameterOrderException.class)
	public void test_Select_lines_wrong_with_firstIndex_smaller_than_second_index() throws TooMuchTypeInOneColumnException, UnknownTypeException, UnequalArraySizeException, UnequalColumnSizeException, UncorrectParameterOrderException {
		Dataframe datatest = createGoodDataset();
		datatest.selectLines(2,1);
	}
	
	//For statistics
	public Dataframe createGoodDatasetForStat() throws TooMuchTypeInOneColumnException, UnknownTypeException, UnequalArraySizeException {
		Object[][] contenu = new Object[][]{{40,50,60},{"Hello", "Goodbye", "Thank you"}, {35.5,40.0,44.5}};
		String [] labels = new String[]{"entiers", "string", "double"};
		Dataframe datatest = new Dataframe(contenu, labels);
		return datatest;
	}
	//For get average
	@Test
	public void test_get_average_good() throws TooMuchTypeInOneColumnException, UnknownTypeException, UnequalArraySizeException, UnequalColumnSizeException, UncorrectParameterOrderException {
		Dataframe datatest = createGoodDatasetForStat();
		datatest.showAverage(2);
		assertEquals("Calcul de la moyenne sur des entiers mauvais", 50, datatest.getAverage(0),0.0001);
		assertEquals("Calcul de la moyenne sur des doubles mauvais", 40.0, datatest.getAverage(2),0.0001);
	}
	
	@Test(expected = IndexOutOfBoundsException.class)
	public void test_get_average_wrong_with_index_too_big() throws TooMuchTypeInOneColumnException, UnknownTypeException, UnequalArraySizeException, UnequalColumnSizeException {
		Dataframe datatest = createGoodDatasetForStat();
		datatest.getAverage(4);
	}
	
	@Test(expected = IndexOutOfBoundsException.class)
	public void test_get_average_wrong_with_negative_index() throws TooMuchTypeInOneColumnException, UnknownTypeException, UnequalArraySizeException, UnequalColumnSizeException {
		Dataframe datatest = createGoodDatasetForStat();
		datatest.getAverage(-3);
	}
	//For get min
	@Test
	public void test_get_min_good() throws TooMuchTypeInOneColumnException, UnknownTypeException, UnequalArraySizeException, UnequalColumnSizeException {
		Dataframe datatest = createGoodDatasetForStat();
		datatest.showMin(2);
		assertEquals("Calcul du minimum sur des entiers mauvais", 40, datatest.getMin(0),0.0001);
		assertEquals("Calcul du minimum sur des doubles mauvais", 35.5, datatest.getMin(2),0.0001);
	}
	
	@Test(expected = IndexOutOfBoundsException.class)
	public void test_get_min_wrong_with_index_too_big() throws TooMuchTypeInOneColumnException, UnknownTypeException, UnequalArraySizeException, UnequalColumnSizeException {
		Dataframe datatest = createGoodDatasetForStat();
		datatest.getMin(4);
	}
	
	@Test(expected = IndexOutOfBoundsException.class)
	public void test__get_min_wrong_with_negative_index() throws TooMuchTypeInOneColumnException, UnknownTypeException, UnequalArraySizeException, UnequalColumnSizeException {
		Dataframe datatest = createGoodDatasetForStat();
		datatest.getMin(-3);
	}
	//For get max
	@Test
	public void test_get_max_good() throws TooMuchTypeInOneColumnException, UnknownTypeException, UnequalArraySizeException, UnequalColumnSizeException {
		Dataframe datatest = createGoodDatasetForStat();
		datatest.showMax(2);
		assertEquals("Calcul du maximum sur des entiers mauvais", 60, datatest.getMax(0),0.0001);
		assertEquals("Calcul du maximum sur des doubles mauvais", 44.5, datatest.getMax(2),0.0001);
	}
	
	@Test(expected = IndexOutOfBoundsException.class)
	public void test_get_max_wrong_with_index_too_big() throws TooMuchTypeInOneColumnException, UnknownTypeException, UnequalArraySizeException, UnequalColumnSizeException {
		Dataframe datatest = createGoodDatasetForStat();
		datatest.getMax(4);
	}
	
	@Test(expected = IndexOutOfBoundsException.class)
	public void test__get_max_wrong_with_negative_index() throws TooMuchTypeInOneColumnException, UnknownTypeException, UnequalArraySizeException, UnequalColumnSizeException {
		Dataframe datatest = createGoodDatasetForStat();
		datatest.getMax(-3);
	}
}