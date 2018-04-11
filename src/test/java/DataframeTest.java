package test.java;

import static org.junit.Assert.*;
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
	
	private Dataframe createGoodDataset() throws TooMuchTypeInOneColumnException, UnknownTypeException, UnequalArraySizeException {
		Object[][] contenu = new Object[][]{{40,50,60},{"Hello", "Goodbye", "Thank you"}};
		String [] labels = new String[]{"entiers", "string"};
		Dataframe datatest = new Dataframe(contenu, labels);
		return datatest;
	}
	
	//For display all
	@Test
	public void test_Display_all() throws TooMuchTypeInOneColumnException, UnknownTypeException, UnequalArraySizeException {
		Dataframe datatest = createGoodDataset();
		datatest.displayAll();
	}
	
	//For display first line
	@Test
	public void test_Display_first_lines_good() throws TooMuchTypeInOneColumnException, UnknownTypeException, UnequalArraySizeException {
		Dataframe datatest = createGoodDataset();
		datatest.displayFirstLines(2);
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
	public void test_Select_first_lines_good() throws TooMuchTypeInOneColumnException, UnknownTypeException, UnequalArraySizeException, UnequalColumnSizeException {
		Dataframe datatest = createGoodDataset();
		datatest.selectFirstLines(2);
	}
	
	@Test(expected = IndexOutOfBoundsException.class)
	public void test_Select_first_lines_wrong_with_index_too_big() throws TooMuchTypeInOneColumnException, UnknownTypeException, UnequalArraySizeException, UnequalColumnSizeException {
		Dataframe datatest = createGoodDataset();
		datatest.selectFirstLines(4);
	}
	
	@Test(expected = IndexOutOfBoundsException.class)
	public void test_Select_first_lines_wrong_with_negative_index() throws TooMuchTypeInOneColumnException, UnknownTypeException, UnequalArraySizeException, UnequalColumnSizeException {
		Dataframe datatest = createGoodDataset();
		datatest.selectFirstLines(-3);
	}
	
	//For select lastline
	@Test
	public void test_Select_last_lines_good() throws TooMuchTypeInOneColumnException, UnknownTypeException, UnequalArraySizeException, UnequalColumnSizeException {
		Dataframe datatest = createGoodDataset();
		datatest.selectLastLines(2);
	}
	
	@Test(expected = IndexOutOfBoundsException.class)
	public void test_Select_last_lines_wrong_with_index_too_big() throws TooMuchTypeInOneColumnException, UnknownTypeException, UnequalArraySizeException, UnequalColumnSizeException {
		Dataframe datatest = createGoodDataset();
		datatest.selectLastLines(4);
	}
	
	@Test(expected = IndexOutOfBoundsException.class)
	public void test_Select_last_lines_wrong_with_negative_index() throws TooMuchTypeInOneColumnException, UnknownTypeException, UnequalArraySizeException, UnequalColumnSizeException {
		Dataframe datatest = createGoodDataset();
		datatest.selectLastLines(-3);
	}
}