package employees;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import employees.HumanKey;

public class HumanKeyTest {

	@Test
	public void testEqualsObject() {
		
		//uzivatel se stejnym jmenem
		HumanKey humanKey = new HumanKey("Test User");
		
		//uzivatel se stejnym jmenem
		HumanKey humanKeySame = new HumanKey("Test User");
		
		//uplne jine jmeno
		HumanKey humanKeyAnother = new HumanKey("Testtttt");

		//stejne jmeno, jina instance
		assertTrue(humanKey.equals(humanKeySame));
		
		//jine jmeno
		assertFalse(humanKey.equals(humanKeyAnother));
		
		List<HumanKey> list = new ArrayList<HumanKey>();
		list.add(humanKey);
		
		assertTrue(list.contains(humanKeySame));
		assertFalse(list.contains(humanKeyAnother));
		
		//srovnani s null
		assertFalse(humanKey.equals(null));

		//stejna instance
		assertTrue(humanKey.equals(humanKey));
		
		HumanKey humanKeyNull = new HumanKey(null);
		assertFalse(humanKeyNull.equals(humanKey));
	}

}
