package it.polimi.figuregeometriche;

java org.junit.tests.AllTests;
import figure.Cerchio;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.Assert;

public class CerchioTest  {
	private double raggio = 2;
	private Cerchio cerchio = new Cerchio(raggio);
	@Test
	public void testGetCirconferenza(){
		double circonferenzaAttesa = 2*Math.PI*raggio;
		Assert.assertEquals(circonferenzaAttesa, cerchio.getCirconferenza());
	}
	@Test
	public void testGetArea(){
		double areaAttesa = Math.PI*raggio*raggio;
		Assert.assertEquals(areaAttesa,cerchio.getArea());
	}
}
