package test.java;

import static org.junit.Assert.*;
import org.junit.*;
import main.java.Dataframe;

public class test_test {

	@Test
	public void test() {
		Dataframe.Types[] types = {Dataframe.Types.INT, Dataframe.Types.STRING};
		Dataframe datatest = new Dataframe(types);
		assertEquals(3,3);
	}

}