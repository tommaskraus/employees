package employees;

import java.io.BufferedReader;
import java.util.ArrayList;
import java.util.List;

public abstract class CsvReader {

	/**
	 * Seznam chyb pri parsovani
	 */
	protected List<String> listError = new ArrayList<String>();
	
	/**
	 * Oddelovac v CSV
	 */
	protected String csvDelimiter = ";";
	
	public abstract boolean parse(BufferedReader bufferedReader);
	
	public List<String> getListError() {
		return listError;
	}
}
