package datos;

public class PuestoDesarmable extends UnidadVenta {

	//ATRIBUTOS
	private int cantidadCarpas;
	private int tiempoMontaje;
	
	//CONSTRUCTORES
	public PuestoDesarmable() {
		super();
	}
	public PuestoDesarmable(int cantidadCarpas, int tiempoMontaje) {
		super();
		this.cantidadCarpas = cantidadCarpas;
		this.tiempoMontaje = tiempoMontaje;
	}
	
	//GETTERS Y SETTERS
	public int getCantidadCarpas() {
		return cantidadCarpas;
	}
	public void setCantidadCarpas(int cantidadCarpas) {
		this.cantidadCarpas = cantidadCarpas;
	}
	public int getTiempoMontaje() {
		return tiempoMontaje;
	}
	public void setTiempoMontaje(int tiempoMontaje) {
		this.tiempoMontaje = tiempoMontaje;
	}
	
	//TO STRING
	@Override
	public String toString() {
		return "PuestoDesarmable [cantidadCarpas=" + cantidadCarpas + ", tiempoMontaje=" + tiempoMontaje + "]";
	}
}
