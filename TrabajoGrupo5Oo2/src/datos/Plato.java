package datos;

import java.util.List;

public class Plato {

	
	//ATRIBUTOS
	private int id;
	private String nombre;
	private double precioVenta;
	private double costoProduccion;
	private List<DetallePedido> detalles;
	
	//CONSTRUCTORES
	public Plato() {
		super();
	}
	public Plato(String nombre, double precioVenta, double costoProduccion) {
		super();
		this.nombre = nombre;
		this.precioVenta = precioVenta;
		this.costoProduccion = costoProduccion;
	}
	
	//GETTERS Y SETTERS
	public int getId() {
		return id;
	}
	protected void setId(int id) {
		this.id = id;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public double getPrecioVenta() {
		return precioVenta;
	}
	public void setPrecioVenta(double precioVenta) {
		this.precioVenta = precioVenta;
	}
	public double getCostoProduccion() {
		return costoProduccion;
	}
	public void setCostoProduccion(double costoProduccion) {
		this.costoProduccion = costoProduccion;
	}
	public List<DetallePedido> getDetalles() {
		return detalles;
	}
	public void setDetalles(List<DetallePedido> detalles) {
		this.detalles = detalles;
	}
	
	//TO STRING
	@Override
	public String toString() {
		return "Plato [id=" + id + ", nombre=" + nombre + ", precioVenta=" + precioVenta + ", costoProduccion="
				+ costoProduccion + ", detalles=" + detalles + "]";
	}	
}
