package datos;

public class DetallePedido {
	
	//ATRIBUTOS
	private int id;
	private int cantidad;
	private double precioUnitario;
	private Plato plato;
	private Pedido pedido;
	
	//CONSTRUCTORES
	public DetallePedido() {
		super();
	}
	public DetallePedido(int cantidad, double precioUnitario, Plato plato, Pedido pedido) {
		super();
		this.cantidad = cantidad;
		this.precioUnitario = precioUnitario;
		this.plato = plato;
		this.pedido = pedido;
	}
	
	//GETTERS Y SETTERS
	public int getId() {
		return id;
	}
	protected void setId(int id) {
		this.id = id;
	}
	public int getCantidad() {
		return cantidad;
	}
	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}
	public Plato getPlato() {
		return plato;
	}
	public void setPlato(Plato plato) {
		this.plato = plato;
	}
	public double getPrecioUnitario() {
		return precioUnitario;
	}
	public void setPrecioUnitario(double precioUnitario) {
		this.precioUnitario = precioUnitario;
	}
	public Pedido getPedido() {
		return pedido;
	}
	public void setPedido(Pedido pedido) {
		this.pedido = pedido;
	}
	
	//TO STRING
	@Override
	public String toString() {
		return "DetallePedido [id=" + id + ", cantidad=" + cantidad + ", precioUnitario=" + precioUnitario + ", plato="
				+ plato + ", pedido=" + pedido + "]";
	}
	
	
	
}
