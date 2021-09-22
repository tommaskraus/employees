package employees;


/**
 * Konstanty pouzivane v programu
 * @author user
 *
 */
public interface Constants {
	public final String FILE_HUMANS = "zamestnanci.csv";
	public final String FILE_SALARY_MOVES = "pohyby.csv";
	
	/**
	 * Parametr z CSV, pozice zamestnance - Developer
	 */
	public final char CSV_TYPE_DEVELOPER = 'D';
	
	/**
	 * Parametr z CSV, pozice zamestnance - Manager
	 */
	public final char CSV_TYPE_MANAGER = 'M';

	/**
	 * Parametr z CSV, zvyseni platu
	 */
	public final char CSV_TYPE_INCREASE = 'Z';
	
	/**
	 * Parametr z CSV, znizeni platu
	 */
	public final char CSV_TYPE_DECREASE = 'S';

	/**
	 * Vsichni zamenstnanci maji tento mobil
	 */
	public final String DEFAULT_MOBILE_PHONE = "Xiomi Mi 9";
	
	/**
	 * Nekteri zamestnanci maji sluzebni notebook, ostatni si ho musi koupit
	 */
	public final String DEFAULT_NOTEBOOK = "ASUS ZenBook 14";
	
	/**
	 * Jmeno zamestnance, ktery se bude vyhledavat
	 */
	public final String DEFAULT_SEARCH_HUMAN = "Alexandr Prochazka";
	
	/**
	 * Maximalni plat ve firme
	 */
	public final int MAX_SALARY = 1000000;
}
