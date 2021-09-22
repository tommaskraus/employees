package employees;

import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.StringReader;
import java.util.List;
import java.util.Map;

import org.junit.Test;

public class CsvReaderHumansTest {

	String head = "jméno;plat;zařazení (D - developer, M - manager)\n";
	
	/**
	 * Prazdny soubor
	 */
	@Test
	public void testParseEmptyFile() {
		
		BufferedReader bufferedReader = new BufferedReader(new StringReader(""));
		
		CsvReaderHumans reader = new CsvReaderHumans();
		boolean resultParse = reader.parse(bufferedReader);
		assertFalse(resultParse);
		
		Map<HumanKey, Human> mapHuman = reader.getListHuman();
		assertTrue(mapHuman.isEmpty());
		
		List<String> listHumanError = reader.getListError();
		assertFalse(listHumanError.isEmpty());
	}

	/**
	 * Spravne nacteni
	 */
	@Test
	public void testParseOk() {
		
		String csv = head;
		csv += "Petr;12000;M\n";
		csv += "Jan;12000;D";
		
		BufferedReader bufferedReader = new BufferedReader(new StringReader(csv));
		
		CsvReaderHumans reader = new CsvReaderHumans();
		boolean resultParse = reader.parse(bufferedReader);
		assertTrue(resultParse);
		
		Map<HumanKey, Human> mapHuman = reader.getListHuman();
		
		assertEquals(2, mapHuman.size());
		
		List<String> listHumanError = reader.getListError();
		assertTrue(listHumanError.isEmpty());

		assertTrue(mapHuman.get(new HumanKey("Petr")) instanceof Manager);
		assertTrue(mapHuman.get(new HumanKey("Jan")) instanceof Developer);
	}

	/**
	 * Vstup null
	 */
	@Test
	public void testParseNull() {
		
		CsvReaderHumans reader = new CsvReaderHumans();
		boolean resultParse = reader.parse(null);
		assertFalse(resultParse);
		
		Map<HumanKey, Human> mapHuman = reader.getListHuman();
		assertEquals(0, mapHuman.size());
		
		List<String> listHumanError = reader.getListError();
		assertEquals(1, listHumanError.size());
	}

	/**
	 * Spatny pocet sloupcu
	 */
	@Test
	public void testParseBadColumns() {
		
		String csv = head;
		csv += "Petr;12000;S;12000;";
		
		BufferedReader bufferedReader = new BufferedReader(new StringReader(csv));
		
		CsvReaderHumans reader = new CsvReaderHumans();
		boolean resultParse = reader.parse(bufferedReader);
		assertFalse(resultParse);
		
		Map<HumanKey, Human> mapHuman = reader.getListHuman();
		assertTrue(mapHuman.isEmpty());
	}

	/**
	 * Neni zadana pozice
	 */
	@Test
	public void testParseNoPosition() {
		
		String csv = head;
		csv += "Petr;12000;";
		
		BufferedReader bufferedReader = new BufferedReader(new StringReader(csv));
		
		CsvReaderHumans reader = new CsvReaderHumans();
		boolean resultParse = reader.parse(bufferedReader);
		assertFalse(resultParse);
	}

	/**
	 * Spatna pozice
	 */
	@Test
	public void testParseBadPosition() {
		
		String csv = head;
		csv += "Petr;12000;K";
		
		BufferedReader bufferedReader = new BufferedReader(new StringReader(csv));
		
		CsvReaderHumans reader = new CsvReaderHumans();
		boolean resultParse = reader.parse(bufferedReader);
		assertFalse(resultParse);
	}

	/**
	 * Spatny plat
	 */
	@Test
	public void testParseBadSalary() {
		
		String csv = head;
		csv += "Petr;plat;M";
		
		BufferedReader bufferedReader = new BufferedReader(new StringReader(csv));
		
		CsvReaderHumans reader = new CsvReaderHumans();
		boolean resultParse = reader.parse(bufferedReader);
		assertFalse(resultParse);
	}

	/**
	 * Max plat
	 */
	@Test
	public void testParseMaxSalary() {
		
		String csv = head;
		
		csv += "Petr;1000001;M";
		
		BufferedReader bufferedReader = new BufferedReader(new StringReader(csv));
		
		CsvReaderHumans reader = new CsvReaderHumans();
		boolean resultParse = reader.parse(bufferedReader);
		assertFalse(resultParse);
	}

	/**
	 * Zaporny plat
	 */
	@Test
	public void testParseNegativeSalary() {
		
		String csv = head;
		
		csv += "Petr;-10;M";
		
		BufferedReader bufferedReader = new BufferedReader(new StringReader(csv));
		
		CsvReaderHumans reader = new CsvReaderHumans();
		boolean resultParse = reader.parse(bufferedReader);
		assertFalse(resultParse);
		
	}

	/**
	 * Duplicitni zamestnanec
	 */
	@Test
	public void testParseDuplicate() {
		
		String csv = head;

		csv += "Duplicit;10;M\n";
		csv += "Duplicit;100;M";
		
		BufferedReader bufferedReader = new BufferedReader(new StringReader(csv));
		
		CsvReaderHumans reader = new CsvReaderHumans();
		boolean resultParse = reader.parse(bufferedReader);
		assertFalse(resultParse);
		
	}

}
