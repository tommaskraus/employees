package employees;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Nacitani pohybu uzivatelu ze souboru
 *
 */
public class CsvReaderSalaryMoves extends CsvReader {

	private List<SalaryMove> listSalaryMove = new ArrayList<SalaryMove>();

	/**
	 * Nacte csv soubor, vytvori objekty a vraci List
	 * @param bufferedReader
	 * @return vraci true, pokud se behem parsovani nevyskytla zadna chyba
	 */
	public boolean parse(BufferedReader bufferedReader) {
		
		listSalaryMove.clear();
		listError.clear();
		
		try {

			// nacteni hlavicky
			String line = bufferedReader.readLine();

			if (line == null) {
				listError.add("Zadany soubor je prazdny");
			} else {

				while ((line = bufferedReader.readLine()) != null) {
					parseLine(line, listSalaryMove);
				}
			}

		} catch (IOException e) {
			listError.add("Chyba pri cteni souboru");
		} catch (NullPointerException e) {
			listError.add("Neni zadany vstupni soubor");
		}
		
		return listError.size() == 0;

	}

	/**
	 * Precteni radky souboru, vytvoreni Human, ulozeni do listu
	 * @param row
	 * @param listSalaryMove
	 */
	private void parseLine(String line, List<SalaryMove> listSalaryMove) {

		String [] splitLine = line.split(csvDelimiter);
		
		if (splitLine.length != 3) {
			listError.add("Radka neobsahuje 3 sloupce - " + line);
			return;
		}

		String name = splitLine[0].trim();
		int value;
		
		try {
			value = Integer.parseInt(splitLine[2]);
		} catch (NumberFormatException e) {
			listError.add("Spatny format cisla ve sloupci zvyseni/snizeni - " + line);
			return;
		}
		
		if (value < 0) {
			listError.add("Cislo ve sloupci zvyseni/snizeni musi byt >= 0 - " + line);
			return;
		}
		
		String moveType = splitLine[1];
		
		if (moveType.length() <= 0) {
			listError.add("Nevyplneny typ pohybu - " + line);
			return;
		}
		
		boolean isIncreaseMove;
		if (moveType.charAt(0) == Constants.CSV_TYPE_INCREASE) {
			isIncreaseMove = true;
		} else if (moveType.charAt(0) == Constants.CSV_TYPE_DECREASE) {
			isIncreaseMove = false;
		} else {
			listError.add("Neplatny typ pohybu - " + line);
			return;
		}
		
		SalaryMove salaryMove = new SalaryMove(name, isIncreaseMove, value);

		listSalaryMove.add(salaryMove);
	}
	
	public List<SalaryMove> getListSalaryMove() {
		return listSalaryMove;
	}
}
