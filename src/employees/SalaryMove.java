package employees;

/**
 * Nacteny pohyb platu ze souboru
 * @author user
 *
 */
public class SalaryMove {

	/**
	 * Jmeno zamestnance, u ktereho budeme hybat s platem
	 */
	private String name;
	
	/**
	 * Typ operace - je to pozadavek na zvyseni platu, jinak na snizeni
	 */
	private boolean isIncreaseMove;
	
	/**
	 * Hodnota zvyseni/snizeni platu
	 */
	private int value;
	
	public SalaryMove(String name, boolean isIncreaseMove, int value) {
		this.name = name;
		this.isIncreaseMove = isIncreaseMove;
		this.value = value;
	}

	public String getName() {
		return name;
	}

	public int getValue() {
		return value;
	}

	public boolean isIncreaseMove() {
		return isIncreaseMove;
	}

	@Override
	public String toString() {
		return "jmeno: " + name + ", pohyb zvyseni: " + isIncreaseMove + ", hodnota: " + value;
	}

}
