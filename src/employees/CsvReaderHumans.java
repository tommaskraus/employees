package employees;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Nacitani zamestnancu ze souboru
 * @author user
 *
 */
public class CsvReaderHumans extends CsvReader {
	private Map<HumanKey,Human> mapHuman = new HashMap<>();
	
	/**
	 * Nacte csv soubor, vytvori objekty a vraci List
	 * @param bufferedReader
	 * @return vraci true, pokud se behem parsovani nevyskytla zadna chyba
	 */
	public boolean parse(BufferedReader bufferedReader) {
		
		mapHuman.clear();
		listError.clear();
		
		int priority = 1;

		try {

			// nacteni hlavicky
			String line = bufferedReader.readLine();

			if (line == null) {
				listError.add("Zadany soubor je prazdny");
			} else {

				while ((line = bufferedReader.readLine()) != null) {
					parseLine(line, mapHuman, priority++);
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
	 * @param line
	 * @param mapHuman
	 * @param priority - cim vis v souboru, tim ma vetsi prioritu
	 */
	private void parseLine(String line, Map<HumanKey, Human> mapHuman, int priority) {

		String [] splitLine = line.split(csvDelimiter);
		
		if (splitLine.length != 3) {
			listError.add("Radka neobsahuje 3 sloupce - " + line);
			return;
		}

		Human human;

		String zarazeni = splitLine[2];

		if (zarazeni.length() <= 0) {
			listError.add("Nevyplnena pozice zamestnance - " + line);
			return;
		}
		
		String name = splitLine[0].trim();
		
		int salary;
		
		try {
			salary = Integer.parseInt(splitLine[1]);
		} catch (NumberFormatException e) {
			listError.add("Spatny format cisla ve sloupci plat - " + line);
			return;
		}
		
		if (salary < 0) {
			listError.add("Cislo ve sloupci plat musi byt >= 0 - " + line);
		}
		
		if (salary > Constants.MAX_SALARY) {
			listError.add("Cislo ve sloupci plat musi byt <= " + Constants.MAX_SALARY + " - " + line);
		}

		switch (zarazeni.charAt(0)) {
			case Constants.CSV_TYPE_DEVELOPER:
				Developer.Builder developerBuilder = Developer.createBuilder(name);
				developerBuilder
						.setSalary(salary)
						.setPriority(priority);
				
				//pokud je priorita delena tremi, tak dostane nb
				if (priority % 3 == 0) {
					developerBuilder.setNotebook(Constants.DEFAULT_NOTEBOOK);
				}
						
				human = developerBuilder.build();
				break;
				
			case Constants.CSV_TYPE_MANAGER:
				Manager.Builder managerBuilder = Manager.createBuilder(name);
				human = managerBuilder
						.setSalary(salary)
						.setMobilePhone(Constants.DEFAULT_MOBILE_PHONE)
						.build();
				break;
			
			default:
				listError.add("Neplatne pismeno pozice zamestnance - " + line);
				return;
		}

		HumanKey key = new HumanKey(human.name);
		
		if (mapHuman.containsKey(key)) {
			listError.add("Duplicitni zamestnanec - " + line);
		}
		mapHuman.put(key, human);
	}

	public Map<HumanKey, Human> getListHuman() {
		return mapHuman;
	}

}
