package employees;

import org.junit.internal.TextListener;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;

public class MainForTests {

	public static void main(String[] args) {
		JUnitCore junit = new JUnitCore();
		junit.addListener(new TextListener(System.out));

		Result result = junit.run(
		  CsvReaderHumansTest.class, 
		  CsvReaderSalaryMovesTest.class,
		  DeveloperTest.class,
		  HumanKeyTest.class,
		  HumanTest.class,
		  ManagerTest.class,
		  SalaryMoveSingletonTest.class,
		  SalaryMoveTest.class);

		resultReport(result);
	}
	
	public static void resultReport(Result result) {
	    System.out.println("Finished. Result: Failures: " +
	      result.getFailureCount() + ". Ignored: " +
	      result.getIgnoreCount() + ". Tests run: " +
	      result.getRunCount() + ". Time: " +
	      result.getRunTime() + "ms.");
	}

}
