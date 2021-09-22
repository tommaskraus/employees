package employees;

import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.StringReader;
import java.util.List;

import org.junit.Test;

public class CsvReaderSalaryMovesTest {

	String head = "jméno;pohyb (Z - zvýšení, S - snížení);hodnota o kterou se má plat pohnout\n";
	
	/**
	 * Prazdny soubor
	 */
	@Test
	public void testParseEmptyFile() {
		
		BufferedReader bufferedReader = new BufferedReader(new StringReader(""));
		
		CsvReaderSalaryMoves reader = new CsvReaderSalaryMoves();
		boolean resultParse = reader.parse(bufferedReader);
		assertFalse(resultParse);
		
		List<SalaryMove> list = reader.getListSalaryMove();
		assertTrue(list.isEmpty());
		
		List<String> listError = reader.getListError();
		assertFalse(listError.isEmpty());
	}

	/**
	 * Spravne nacteni
	 */
	@Test
	public void testParseOk() {
		
		String csv = head;
		csv += "Adam;Z;1000\n";
		csv += "Adam;S;500\n";
		
		BufferedReader bufferedReader = new BufferedReader(new StringReader(csv));
		
		CsvReaderSalaryMoves reader = new CsvReaderSalaryMoves();
		boolean resultParse = reader.parse(bufferedReader);
		assertTrue(resultParse);
		
		List<SalaryMove> list = reader.getListSalaryMove();
		
		assertEquals(2, list.size());
		
		List<String> listError = reader.getListError();
		assertTrue(listError.isEmpty());
	}

	/**
	 * Vstup null
	 */
	@Test
	public void testParseNull() {
		
		CsvReaderSalaryMoves reader = new CsvReaderSalaryMoves();
		boolean resultParse = reader.parse(null);
		assertFalse(resultParse);
		
		List<SalaryMove> list = reader.getListSalaryMove();
		assertEquals(0, list.size());
		
		List<String> listError = reader.getListError();
		assertEquals(1, listError.size());
	}

	/**
	 * Spatny pocet sloupcu
	 */
	@Test
	public void testParseBadColumns() {
		
		String csv = head;
		csv += "Petr;S;12000;S;12000;";
		
		BufferedReader bufferedReader = new BufferedReader(new StringReader(csv));
		
		CsvReaderSalaryMoves reader = new CsvReaderSalaryMoves();
		boolean resultParse = reader.parse(bufferedReader);
		assertFalse(resultParse);
		
		List<SalaryMove> list = reader.getListSalaryMove();
		assertTrue(list.isEmpty());
	}

	/**
	 * Neni zadana pohyb
	 */
	@Test
	public void testParseNoSalaryMove() {
		
		String csv = head;
		csv += "Petr;;12000";
		
		BufferedReader bufferedReader = new BufferedReader(new StringReader(csv));
		
		CsvReaderSalaryMoves reader = new CsvReaderSalaryMoves();
		boolean resultParse = reader.parse(bufferedReader);
		assertFalse(resultParse);
	}

	/**
	 * Spatny typ pohybu
	 */
	@Test
	public void testParseBadTypeSalaryMove() {
		
		String csv = head;
		csv += "Petr;K;12000";
		
		BufferedReader bufferedReader = new BufferedReader(new StringReader(csv));
		
		CsvReaderSalaryMoves reader = new CsvReaderSalaryMoves();
		boolean resultParse = reader.parse(bufferedReader);
		assertFalse(resultParse);
	}

	/**
	 * Spatne zadane cislo
	 */
	@Test
	public void testParseBadValue() {
		
		String csv = head;
		csv += "Petr;S;hodnota";
		
		BufferedReader bufferedReader = new BufferedReader(new StringReader(csv));
		
		CsvReaderSalaryMoves reader = new CsvReaderSalaryMoves();
		boolean resultParse = reader.parse(bufferedReader);
		assertFalse(resultParse);
	}


	/**
	 * Zaporna hodnota
	 */
	@Test
	public void testParseNegativeValue() {
		
		String csv = head;
		
		csv += "Petr;S;-200";
		
		BufferedReader bufferedReader = new BufferedReader(new StringReader(csv));
		
		CsvReaderSalaryMoves reader = new CsvReaderSalaryMoves();
		boolean resultParse = reader.parse(bufferedReader);
		assertFalse(resultParse);
	}

}
