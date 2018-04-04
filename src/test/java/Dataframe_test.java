package test.java;

import static org.junit.Assert.*;
import org.junit.*;
import main.java.Dataframe;
import main.java.exception.*;

public class Dataframe_test {

	@Test
	public void test_Constructor_good() throws TooMuchTypeInOneColumnException, UnknownTypeException {
		Object[][] contenu = new Object[][]{{4,5,8},{"Hello", "Goodbye", "Thank you"},{true, false},{4.6, 3.8}};
		Dataframe datatest = new Dataframe(contenu);
	}
	
	@Test(expected = TooMuchTypeInOneColumnException.class)
	public void test_Constructor_toomuch_type_in_one_column_wrong() throws TooMuchTypeInOneColumnException, UnknownTypeException {
		Object[][] contenu = new Object[][]{{4.5,5,8},{"Hello", "Goodbye", "Thank you"}};
		Dataframe datatest = new Dataframe(contenu);
	}
	
	@Test(expected = UnknownTypeException.class)
	public void test_Constructor_unknown_type_wrong() throws TooMuchTypeInOneColumnException, UnknownTypeException {
		long a = 4;
		long b = 6;
		Object[][] contenu = new Object[][]{{a,b},{"Hello", "Goodbye", "Thank you"}};
		Dataframe datatest = new Dataframe(contenu);
	}
	
	@Test
	public void test_Display_all() throws TooMuchTypeInOneColumnException, UnknownTypeException {
		Object[][] contenu = new Object[][]{{40,50},{"Hello", "Goodbye", "Thank you"}};
		Dataframe datatest = new Dataframe(contenu);
		datatest.displayAll();
	}
	
	@Test
	public void test_Display_first_lines_good() throws TooMuchTypeInOneColumnException, UnknownTypeException {
		Object[][] contenu = new Object[][]{{40,50,46,38},{"Hello", "Goodbye", "Thank you"}};
		Dataframe datatest = new Dataframe(contenu);
		datatest.displayFirstLines(2);
	}
	
	@Test(expected = IndexOutOfBoundsException.class)
	public void test_Display_first_lines_wrong() throws TooMuchTypeInOneColumnException, UnknownTypeException {
		Object[][] contenu = new Object[][]{{40,50,46,38,26},{"Hello", "Goodbye", "Thank you"}};
		Dataframe datatest = new Dataframe(contenu);
		datatest.displayFirstLines(4);
	}

	@Test
	public void test_Display_last_lines_good() throws TooMuchTypeInOneColumnException, UnknownTypeException {
		Object[][] contenu = new Object[][]{{40,50,46,38},{"Hello", "Goodbye", "Thank you"}};
		Dataframe datatest = new Dataframe(contenu);
		datatest.displayLastLines(2);
	}
	
	@Test(expected = IndexOutOfBoundsException.class)
	public void test_Display_last_lines_wrong() throws TooMuchTypeInOneColumnException, UnknownTypeException {
		Object[][] contenu = new Object[][]{{40,50,46,38,26},{"Hello", "Goodbye", "Thank you"}};
		Dataframe datatest = new Dataframe(contenu);
		datatest.displayLastLines(4);
	}
}