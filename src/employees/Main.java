package employees;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;

public class Main {

	/**
	 * Pocet uspesnych snizeni/zvyseni platu
	 */
	private static int countSuccessSalaryMove = 0;
	
	/**
	 * Hashmapa se seznamem zamestnancu nactenych ze souboru
	 */
	private static Map<HumanKey, Human> mapHuman = new HashMap<>();
	private static List<SalaryMove> listSalaryMove = new ArrayList<SalaryMove>();
	
	/**
	 * Spustena vlakna, pro kontrolu, jestli uz dobehla
	 */
	private static List<Thread> listThread = new ArrayList<Thread>();

	public static void main(String[] args) {

		BufferedReader bufferedReaderInput = new BufferedReader(new InputStreamReader(System.in));

		/**
		 * Text, prikaz zadany uzivatelem
		 */
		String line = "";
		char command;
		boolean isPressedExit = false;
		
		try {
			while (!isPressedExit) {
				printMenu();

				line = bufferedReaderInput.readLine();

				if (line.length() <= 0) {
					continue;
				}

				command = line.charAt(0);

				switch (command) {
					case 'q':
						//exit
						isPressedExit = true;
						break;
						
					case 'r':
						//nacteni zamestnancu
						onPressedLoadFileHuman(line);
						break;
						
					case 'm':
						//nacteni pohybu
						onPressedLoadFileSalaryMoves(line);
						break;
						
					case 's':
						//sql
						onPressedSql();
						break;
					case 'v':
						//vyhledani
						onPressedSearchHuman(line);
						
						break;
						
					case 'd':
						//smazani uzivatelu
						onPressedDelete();
						break;
						
					case 'l' :
						//vypis uzivatelu
						onPressedList();
						break;
						
					default:
						System.out.println("!!!Neplatny parametr. Zvolte z menu spravny parametr.");
						break;
				}

			}
			bufferedReaderInput.close();

		} catch (IOException e) {
			System.out.println("Vyskytla se chyba pri nacitani z prikazove radky. Program bude ukoncen.");
		}

		System.out.println("Konec programu");
	}
	
	/**
	 * vypis sql
	 */
	private static void onPressedSql() {
		System.out.println("CREATE TABLE `user` (\r\n" + 
				"  `id` mediumint(9) unsigned NOT NULL AUTO_INCREMENT,\r\n" + 
				"  `name` varchar(50) NOT NULL,\r\n" + 
				"  `created_at` datetime DEFAULT NULL,\r\n" + 
				"  `updated_at` datetime DEFAULT NULL,\r\n" + 
				"  `deleted_at` datetime DEFAULT NULL,\r\n" + 
				"  PRIMARY KEY (`id`),\r\n" + 
				"  KEY `name` (`name`),\r\n" + 
				"  KEY `deleted_at` (`deleted_at`)\r\n" + 
				") ENGINE=InnoDB DEFAULT CHARSET=utf8;\r\n");
		System.out.println("ALTER TABLE `user`\r\n" + 
				"CHANGE `name` `name` varchar(255) COLLATE 'utf8_general_ci' NOT NULL AFTER `id`;\r\n");
		System.out.println("DROP TABLE `user`;");
	}

	/**
	 * Tim zajistime ze nam to nebudou dve vlakna prepisovat zaroven
	 */
	private static synchronized void addToCountSuccessSalaryMove() {
		countSuccessSalaryMove++;
	}
	
	/**
	 * Vyhledani zamestnance podle jmena, nebo defaultniho
	 * @param line zadany prikaz od uzivatele (v name)
	 */
	private static void onPressedSearchHuman(String line) {
		
		String[] splitLine = line.split(" ", 2);
		
		String name;
		//neni zadan soubor, bereme defaultni
		if (splitLine.length == 1) {
			name = Constants.DEFAULT_SEARCH_HUMAN;
		} else {
			name = splitLine[1].trim();
		}
		
		Human testDeveloper = Developer.createBuilder(name).build();
		System.out.println("Hledame zamestnance: " + testDeveloper.getName());
		
		HumanKey humanKey = new HumanKey(testDeveloper.getName());
		boolean isInMap = mapHuman.containsKey(humanKey);
		
		System.out.println("containsKey(): " + (isInMap ? "ano" : "ne"));
		
		Human foundHuman = mapHuman.get(humanKey);
		System.out.println("Map.get(): " + (foundHuman instanceof Human ? foundHuman.toString() : null));
		
		mapHuman.forEach((HumanKey key, Human h) -> {
			if (h.equals(testDeveloper)) {
				System.out.println("Human.equals(): " + h.toString());
			}
		});
		
	}

	/**
	 * Smazani vsech zamestnancu
	 */
	private static void onPressedDelete() {
		int pocetSmazanych = mapHuman.size();
		mapHuman.clear();
		System.out.println("Pocet smazanych zamestnancu: " + pocetSmazanych);
	}

	/**
	 * Vypis vsech zamestnancu
	 */
	private static void onPressedList() {
		if (mapHuman != null && mapHuman.size() > 0) {
			
			mapHuman.forEach((HumanKey humanKey, Human human) -> System.out.println(human));
		} else {
			System.out.println("!!!Seznam zamestnancu je prazdny.");
		}
	}

	/**
	 * Nacteni souboru human.csv nebo podle zadaneho uzivatelem
	 * @param line zadany prikaz od uzivatele (r filename.csv)
	 */
	private static void onPressedLoadFileHuman(String line) {
		
		mapHuman.clear();
		String[] splitLine = line.split(" ", 2);
		
		String filename;
		
		//neni zadan soubor, bereme defaultni
		if (splitLine.length == 1) {
			filename = Constants.FILE_HUMANS;
		} else {
			filename = splitLine[1].trim();
		}
		
		
		try {
			FileReader fileReader = new FileReader(filename);
			
			BufferedReader bufferedReader = new BufferedReader(fileReader);
			
			CsvReaderHumans reader = new CsvReaderHumans();
			boolean resultParse = reader.parse(bufferedReader);
			
			mapHuman = reader.getListHuman();
			
			System.out.println("Pocet nactenych zamestnancu: " + mapHuman.size());
			
			if (!resultParse) {
				
				List<String> listErrors = reader.getListError();
				
				System.out.println("Pocet chyb: " + listSalaryMove.size());
				listErrors.forEach((String error) -> System.out.println(error));
				
			}
			
			bufferedReader.close();
			fileReader.close();
			
		} catch (FileNotFoundException e) {
			System.out.println("Soubor se nepodarilo nacist");
		} catch (IOException e) {
			System.out.println("Soubor se nepodarilo nacist");
		}
		
	}

	/**
	 * Nacteni souboru s pohybama platu
	 * @param line zadany prikaz od uzivatele (m filename.csv)
	 */
	private static void onPressedLoadFileSalaryMoves(String line) {
		
		listSalaryMove.clear();
		
		String[] splitLine = line.split(" ", 2);
		
		String filename;
		
		//neni zadan soubor, bereme defaultni
		if (splitLine.length == 1) {
			filename = Constants.FILE_SALARY_MOVES;
		} else {
			filename = splitLine[1].trim();
		}
		
		try {
			FileReader fileReader = new FileReader(filename);
			BufferedReader bufferedReader = new BufferedReader(fileReader);
			
			CsvReaderSalaryMoves reader = new CsvReaderSalaryMoves();
			boolean resultParse = reader.parse(bufferedReader);
			
			listSalaryMove = reader.getListSalaryMove();
			
			System.out.println("Pocet nactenych pohybu: " + listSalaryMove.size());
			
			if (!resultParse) {
				
				List<String> listErrors = reader.getListError();
				
				System.out.println("Pocet chyb: " + listSalaryMove.size());
				listErrors.forEach((String error) -> System.out.println(error));
			}
			
			bufferedReader.close();
			fileReader.close();
			
		} catch (FileNotFoundException e) {
			System.out.println("Soubor se nepodarilo nacist");
		} catch (IOException e) {
			System.out.println("Soubor se nepodarilo nacist");
		}
		
		runSalaryMoves();
		
	}

	/**
	 * Spusti pohyby platu, pro kazdy pohyb vytvori vlakno a pres threadsafe singleton provede pohyb
	 */
	private static void runSalaryMoves() {
		
		countSuccessSalaryMove = 0;
		
		listSalaryMove.forEach((SalaryMove salaryMove) -> {
			Thread thread = new Thread(() -> {
				
				HumanKey humanKey = new HumanKey(salaryMove.getName());
				Human human = mapHuman.get(humanKey);
				SalaryMoveSingleton salaryMoveSingleton = SalaryMoveSingleton.getInstance();
				
				//vysledek operace
				boolean resultMove;
				if (salaryMove.isIncreaseMove()) {
					resultMove = salaryMoveSingleton.increase(human, salaryMove.getValue());
				} else {
					resultMove = salaryMoveSingleton.decrease(human, salaryMove.getValue());
				}
				
				if (!resultMove) {
					System.out.println("Chyba - " + salaryMove.toString());
				} else {
					addToCountSuccessSalaryMove();
				}
			});
			
			listThread.add(thread);
			thread.start();
		});
		
		while (true) {
			
			//atomicBoolean kvuli lambde a vnitrni promenny, kterou chceme nastavit
			AtomicBoolean isRunning = new AtomicBoolean(false);
			
			listThread.forEach((Thread thread) -> {
				if (thread.isAlive()) {
					isRunning.set(true);
				}
			});
			
			if (!isRunning.get()) {
				break;
			}
			
			try {
				Thread.sleep(300);
			} catch (InterruptedException e) {
				System.out.println("Neocekavana chyba programu, zkuste znovu.");
				break;
			}
		}
		
		System.out.println("Pocet uspesnych pohybu:  " + countSuccessSalaryMove);
		
	}

	private static void printMenu() {
		String menu = "\nTest ukol\n" + "Muzete pouzit nasledujici parametry k volani:\n"
				+ "r <nazevSouboru> - nacteni seznamu zamestnancu ze souboru. Pokud nebude zadan, bude nacten defaultni soubor zamestnanci.csv\n"
				+ "m <nazevSouboru> - nacteni pohybu pro mzdy. Pokud nebude zadan, bude nacten defaultni soubor pohyby.csv\n"
				+ "s - vypsani 3 sql prikazu dle zadani\n"
				+ "v <jmenoZamestnance> - vyhledani zamestnance podle jmena. Pokud nebude zadan, bude pouzito jmeno " + Constants.DEFAULT_SEARCH_HUMAN + "\n"
				+ "d - smazani seznamu zamestnancu\n"
				+ "l - vypsani vsech zamestrancu\n" + "q - konec programu\n";

		System.out.println(menu);
	}
}
