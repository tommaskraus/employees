package employees;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import employees.Constants;
import employees.Developer;
import employees.Human;
import employees.SalaryMoveSingleton;

public class SalaryMoveSingletonTest {

	private final String name = "Adam Jehlicka";
	private final int salary = 3000;
	private final int salaryMove = 2000;
	private final int negativeSalaryMove = -5000;
	
	private SalaryMoveSingleton salaryMoveSingleton = SalaryMoveSingleton.getInstance();
	private Human human;
	
	@Before
	public void init() {
		salaryMoveSingleton = SalaryMoveSingleton.getInstance();
		human = Developer.createBuilder(name).setSalary(salary).build();
	}
	
	@Test
	public void testIncrease() {
		
		boolean result = salaryMoveSingleton.increase(human, salaryMove);
		assertTrue(result);
		
		assertEquals(salary + salaryMove, human.getSalary());
	}

	@Test
	public void testDecrease() {
		
		boolean result = salaryMoveSingleton.decrease(human, salaryMove);
		
		assertTrue(result);
		assertEquals(salary - salaryMove, human.getSalary());
	}
	
	/**
	 * Prekroceni max hodnoty platu ve firme
	 */
	@Test
	public void testIncreaseMax() {
		
		boolean result = salaryMoveSingleton.increase(human, Constants.MAX_SALARY);
		
		assertFalse(result);
		
		assertEquals(salary, human.getSalary());
	}

	/**
	 * Po odecteni platu bude plat < 0
	 */
	@Test
	public void testDecreaseMax() {
		
		boolean result = salaryMoveSingleton.decrease(human, Constants.MAX_SALARY);
		
		assertFalse(result);
		
		assertEquals(salary, human.getSalary());
	}
	
	/**
	 * Human bude null
	 */
	@Test
	public void testIncreaseNullHuman() {
		
		boolean result = salaryMoveSingleton.increase(null, Constants.MAX_SALARY);
		
		assertFalse(result);
		
		assertEquals(salary, human.getSalary());
	}

	/**
	 * Human bude null
	 */
	@Test
	public void testDecreaseNullHuman() {
		
		boolean result = salaryMoveSingleton.decrease(null, Constants.MAX_SALARY);
		
		assertFalse(result);
		
	}
	
	/**
	 * Pridani platu v zaporne hodnote
	 */
	@Test
	public void testIncreaseNegativeValue() {
		
		boolean result = salaryMoveSingleton.increase(human, negativeSalaryMove);
		
		assertFalse(result);
		
		assertEquals(salary, human.getSalary());
	}
	
	/**
	 * Snizeni platu v zaporne hodnote
	 */
	@Test
	public void testDecreaseNegativeValue() {
		
		boolean result = salaryMoveSingleton.decrease(human, negativeSalaryMove);
		
		assertFalse(result);
		
		assertEquals(salary, human.getSalary());
	}

}
