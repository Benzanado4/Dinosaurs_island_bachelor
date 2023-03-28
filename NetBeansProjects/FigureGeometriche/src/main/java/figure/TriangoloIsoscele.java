package figure;

public final class TriangoloIsoscele extends Triangolo {
	public TriangoloIsoscele(double base, double altezza) {
		super(base, altezza);
	}
	public void setBase(double base) {
		this.base = base;
	}
	public void setAltezza(double altezza) {
		this.altezza = altezza;
	}
	@Override
	public void printDescrizione() {
		System.out.println("Triangolo isoscele di base = " + base + ", lato = " + getLato() + ", perimetro = " + getPerimetro() + " e area = " + getArea());
	}
	@Override
	public double getPerimetro() {
		return base + 2*getLato();
	}
	public double getLato() {
		return Math.sqrt(base*base/4 + altezza*altezza);
	}
}

