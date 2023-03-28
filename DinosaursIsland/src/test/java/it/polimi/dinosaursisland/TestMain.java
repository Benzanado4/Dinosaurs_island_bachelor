package it.polimi.dinosaursisland;

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.RunWith;

public class TestMain {
	/**
	 * @param args
	 */
	public static void main(String[] args){
		Result result = JUnitCore.runClasses(GameControllerTest.class);
		//Result result1 = JUnitCore.runClasses(DinosaursTest.class);
		//Result result2 = JUnitCore.runClasses(GameTest.class);
		System.out.println(result.getRunCount() + " test eseguiti in " + + result.getRunTime() + " ms");
		if(result.wasSuccessful()){
			System.out.println("OK!test eseguito con successo!");
		}
		else{
			System.out.println("Sono falliti " + result.getFailureCount() + " test");
		}
	}
}
