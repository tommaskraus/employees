package employees;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import employees.Human;
import employees.Manager;

public class HumanTest {

	@Test
	public void testEqualsObject() {
		Human human = Manager.createBuilder("Test User").build();
		Human humanSame = Manager.createBuilder("Test User").build();
		Human humanAnother = Manager.createBuilder("Testttt").build();
		
		// stejne jmeno, jina instance
		assertTrue(human.equals(humanSame));

		// jine jmeno
		assertFalse(human.equals(humanAnother));

		List<Human> list = new ArrayList<Human>();
		list.add(human);

		assertTrue(list.contains(humanSame));
		assertFalse(list.contains(humanAnother));

		// srovnani s null
		assertFalse(human.equals(null));

		// stejna instance
		assertTrue(human.equals(human));
		
		Human humanNull = Manager.createBuilder(null).build();
		assertFalse(humanNull.equals(human));
	}

}
