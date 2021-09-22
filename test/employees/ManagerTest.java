package employees;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import employees.Human;
import employees.Manager;

public class ManagerTest {
	
	private final String name = "Adam Jehlicka";
	private final int salary = 50001;
	private final String mobilePhone = "Samsung";
	
	/**
	 * Manager se vsemi parametry
	 */
	Human human;
	
	/**
	 * Human jen se jmenem
	 */
	Human humanEmptyParameters;
	
	@Before
	public void init() {
		Manager.Builder builder = Manager.createBuilder(name);
		human = builder
				.setSalary(salary)
				.setMobilePhone(mobilePhone)
				.build();
		
		humanEmptyParameters = Manager.createBuilder(name).build();
	}

	@Test
	public void testToString() {
		assertEquals(name + ", pozice: Manager, plat: " + salary + ", mobil: " + mobilePhone, human.toString());
		
		Human humanNullName = Manager.createBuilder(null).build();

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
	public void testManager() {
		assertTrue(human instanceof Manager);
	}

	@Test
	public void testGetMobilePhone() {
		Manager manager = (Manager) human;
		assertEquals(mobilePhone, manager.getMobilePhone());
		
		Manager managerNull = (Manager) humanEmptyParameters;
		assertEquals("", managerNull.getMobilePhone());
	}

	@Test
	public void testSetSalary() {
		human.setSalary(salary + 1);
		assertEquals(salary + 1, human.getSalary());
	}

}
