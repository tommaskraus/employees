package employees;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import employees.Developer;
import employees.Human;

public class DeveloperTest {
	
	private final String name = "Adam Jehlicka";
	private final int salary = 50002;
	private final int priority = 20;
	private final String notebook = "Acer";
	
	/**
	 * Developer se vsemi parametry
	 */
	Human human;
	
	/**
	 * Human jen se jmenem
	 */
	Human humanEmptyParameters;
	
	@Before
	public void init() {
		Developer.Builder builder = Developer.createBuilder(name);
		human = builder
				.setSalary(salary)
				.setNotebook(notebook)
				.setPriority(priority)
				.build();
		
		humanEmptyParameters = Developer.createBuilder(name).build();
	}

	@Test
	public void testToString() {
		assertEquals("Nesouhlasi hodnoty v toString()", name + ", pozice: Developer, plat: " + salary + ", priorita: " + priority + ", notebook: " + notebook, human.toString());
		
		Human humanNullName = Developer.createBuilder(null).build();

		assertNotNull(humanNullName.toString());
	}

	@Test
	public void testGetName() {
		assertEquals(name, human.getName());
		assertEquals(name, humanEmptyParameters.getName());
	}

	@Test
	public void testGetSalary() {
		assertEquals(salary, human.getSalary());
		assertEquals(0, humanEmptyParameters.getSalary());
	}

	@Test
	public void testDeveloper() {
		assertTrue(human instanceof Developer);
	}

	@Test
	public void testGetMobilePhone() {
		Developer developer = (Developer) human;
		assertEquals(notebook, developer.getNotebook());
		
		Developer developerNull = (Developer) humanEmptyParameters;
		
		assertEquals("", developerNull.getNotebook());
	}

	@Test
	public void testGetPriority() {
		Developer developer = (Developer) human;
		assertEquals(priority, developer.getPriority());
		
		Developer developerNull = (Developer) humanEmptyParameters;
		assertEquals(0, developerNull.getPriority());
	}

	@Test
	public void testSet() {
		human.setSalary(salary + 1);
		assertEquals(salary + 1, human.getSalary());
	}

}
