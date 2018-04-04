package test.java;

import static org.junit.Assert.*;
import org.junit.*;
import main.java.Dataframe;

public class Dataframe_constructor_test {

	@Test
	public void test() {
		Object[][] contenu = new Object[][]{{4,5,8},{"Hello", "Goodbye", "Thank you"}};
		Dataframe datatest = new Dataframe(types);
		assertEquals(3,3);
	}

}