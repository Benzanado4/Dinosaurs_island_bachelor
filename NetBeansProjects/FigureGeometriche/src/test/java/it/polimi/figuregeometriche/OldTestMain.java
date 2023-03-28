package it.polimi.figuregeometriche;

import junit.framework.JUnit4TestAdapter;
import junit.framework.TestSuite;
import junit.textui.TestRunner;

public class OldTestMain extends TestSuite {
	public static TestSuite suite(){
		TestSuite suite = new TestSuite();
		suite.addTest(new JUnit4TestAdapter(CerchioTest.class));
		suite.addTest(new JUnit4TestAdapter(TriangoloIsosceleTest.class));
		return suite;
	}
	/**
	 * @param args
	 */
	public static void main(String[] args){
		TestRunner.run(suite());
	}
}