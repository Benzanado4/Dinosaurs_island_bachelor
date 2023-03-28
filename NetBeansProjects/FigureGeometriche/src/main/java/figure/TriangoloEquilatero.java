package figure;

public final class TriangoloEquilatero extends Triangolo {
	public TriangoloEquilatero(double lato){
		super(lato, Math.sqrt(3)/2*lato);
	}
	public double getLato(){
		return base;
	}
	public void setLato(double lato){
		base = lato;
		altezza = Math.sqrt(3)/2*lato;
	}
	@Override
	public void printDescrizione(){
		System.out.println("Triangolo equilatero di lato = " + base + ", perimetro = " + getPerimetro() + " e area = " + getArea());
	}
	@Override
	public double getPerimetro(){
		return 3*base;
	}
}
