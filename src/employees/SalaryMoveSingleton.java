package employees;

import java.util.concurrent.locks.ReentrantLock;

/**
 * Trida, ktera zajistuje pohyb platu na zamestnanci
 *
 */
public class SalaryMoveSingleton {

	/**
	 * Instance singletonu
	 */
	private static SalaryMoveSingleton instance = null;
	
	/**
	 * Promenna zamku
	 */
	private static ReentrantLock lock = new ReentrantLock();
	
	/**
	 * Aby neslo volat konstruktor
	 */
	private SalaryMoveSingleton() {
		
	}
	
	/**
	 * Ziskani objektu singletonu, threadsafe
	 * @return
	 */
	public static synchronized SalaryMoveSingleton getInstance() {
		if (instance == null) {
			instance = new SalaryMoveSingleton();
		}
		return instance;
	}
	
	/**
	 * Zvednuti platu konkretnimu uzivateli o zadanou hodnotu
	 * @param human
	 * @param value
	 */
	public boolean increase(Human human, int value) {
		
		if (human == null || value < 0) {
			return false;
		}
		
		lock.lock();
		int newValue = human.getSalary() + value;
		
		if (newValue > Constants.MAX_SALARY) {
			lock.unlock();
			return false;
		}
		human.setSalary(newValue);
		lock.unlock();
		
		return true;
	}

	/**
	 * Snizeni platu konkretnimu uzivateli o zadanou hodnotu
	 * @param human
	 * @param value
	 * @return true pokud se podarilo snizit
	 */
	public boolean decrease(Human human, int value) {
		
		if (human == null || value < 0) {
			return false;
		}
		
		lock.lock();
		int newValue = human.getSalary() - value;
		
		if (newValue < 0) {
			lock.unlock();
			return false;
		} 
		
		human.setSalary(newValue);
		lock.unlock();
		return true;
	}
}

