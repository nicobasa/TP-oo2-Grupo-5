package datos;

public class FoodTruck extends UnidadVenta {
	
	//ATRIBUTOS
	private String patente;
	private boolean requiereElectricidad;
	
	//CONSTRUCTORES
	public FoodTruck() {
		super();
	}
	public FoodTruck(String patente, boolean requiereElectricidad) {
		super();
		this.patente = patente;
		this.requiereElectricidad = requiereElectricidad;
	}
	
	//GETTERS Y SETTERS
	public String getPatente() {
		return patente;
	}
	public void setPatente(String patente) {
		this.patente = patente;
	}
	public boolean isRequiereElectricidad() {
		return requiereElectricidad;
	}
	public void setRequiereElectricidad(boolean requiereElectricidad) {
		this.requiereElectricidad = requiereElectricidad;
	}
	
	//TO STRING
	@Override
	public String toString() {
		return "FoodTruck [patente=" + patente + ", requiereElectricidad=" + requiereElectricidad + "]";
	}
}
