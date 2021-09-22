package employees;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import employees.SalaryMove;

public class SalaryMoveTest {
	
	SalaryMove salaryMove;
	
	String name = "Tomas";
	boolean isIncreaseMove = true;
	int value = 1000;

	@Before
	public void init() {
		salaryMove = new SalaryMove(name, isIncreaseMove, value);
	}
	
	@Test
	public void testGetName() {
		assertEquals(name, salaryMove.getName());
	}

	@Test
	public void testGetValue() {
		assertEquals(value, salaryMove.getValue());
	}

	@Test
	public void testIsIncreaseMove() {
		assertEquals(isIncreaseMove, salaryMove.isIncreaseMove());
	}
	
	@Test
	public void testToString() {
		assertEquals("jmeno: " + name + ", pohyb zvyseni: " + isIncreaseMove + ", hodnota: " + value, salaryMove.toString());
	}

}
