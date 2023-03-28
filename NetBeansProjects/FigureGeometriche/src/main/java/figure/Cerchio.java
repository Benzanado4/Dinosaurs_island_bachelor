package figure;
public final class Cerchio implements FiguraGeometrica {
	/**
	 * @uml.property  name="raggio"
	 */
	private double raggio;
	
	public Cerchio(double raggio){
		this.raggio = raggio;
	}
	/**
	 * @return
	 * @uml.property  name="raggio"
	 */
	public double getRaggio(){
		return raggio;
	}
	/**
	 * @param  raggio
	 * @uml.property  name="raggio"
	 */
	public void setRaggio(double raggio){
		this.raggio = raggio;
	}
	public double getCirconferenza(){
		return 2*Math.PI*raggio;
	}
	
	public double getArea(){
		return Math.PI*Math.pow(raggio, 2);
	}
	@Override
	public void printDescrizione(){
		System.out.println("Figura: cerchio\n - raggio = " + raggio + "\n - circonferenza = " + getCirconferenza() + "\n - area = " + getArea());
	}
}

