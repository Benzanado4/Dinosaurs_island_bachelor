package figure;
public abstract class Triangolo implements Poligono {
	/**
	 * @uml.property  name="base"
	 */
	protected double base;
	/**
	 * @uml.property  name="altezza"
	 */
	protected double altezza;
	
	protected Triangolo(double base, double altezza){
		this.base = base;
		this.altezza = altezza;
	}
	/**
	 * @return
	 * @uml.property  name="base"
	 */
	public double getBase(){
		return base;
	}
	/**
	 * @return
	 * @uml.property  name="altezza"
	 */
	public double getAltezza(){
		return altezza;
	}
	@Override
	public abstract double getPerimetro();
	@Override
	public double getArea(){
		return base*altezza/2;
	}
}
