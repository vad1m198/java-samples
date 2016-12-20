package ru.vmerkotan;

import org.junit.Test;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
* Tests for Calculate class.
*
* @author Vadim Merkotans
* @since  1.0
* @version $Id$
*/
public class CalculateTest {

	/**
	* Test verify print to console 'Hello World!'.
	*/
	@Test
	public void whenExecuteMainThenPrintToConsole() {
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		System.setOut(new PrintStream(out));
		Calculate.main(null);
		assertThat(out.toString(), is("Hello World!\r\n"));
	}
}