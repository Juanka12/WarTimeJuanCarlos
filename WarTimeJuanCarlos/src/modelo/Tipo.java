package modelo;

public enum Tipo {
	infanteria(1,1,1), caballeria(2,1,2), arqueria(1,2,2);
	private int step;
	private Rango rango;
	Tipo(int step, int rangoMin, int rangoMax) {
		this.step = step;
		rango = new Rango(rangoMin, rangoMax);
	}
	public Rango getRango() {
		return rango;
	}
	public int getStep() {
		return step;
	}
	@Override
	public String toString() {
		return super.toString();
	}
}
