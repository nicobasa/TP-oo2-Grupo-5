package datos;

public class Cocinero extends Empleado {
	
	//ATRIBUTOS
	private String especialidadCulinaria;
	private double plusCategoria;
	
	//CONSTRUCTORES
	public Cocinero() {
		super();
	}
	public Cocinero(String especialidadCulinaria, double plusCategoria) {
		super();
		this.especialidadCulinaria = especialidadCulinaria;
		this.plusCategoria = plusCategoria;
	}
	
	//GETTERS Y SETTERS
	public String getEspecialidadCulinaria() {
		return especialidadCulinaria;
	}
	public void setEspecialidadCulinaria(String especialidadCulinaria) {
		this.especialidadCulinaria = especialidadCulinaria;
	}
	public double getPlusCategoria() {
		return plusCategoria;
	}
	public void setPlusCategoria(double plusCategoria) {
		this.plusCategoria = plusCategoria;
	}
	
	//TO STRING
	@Override
	public String toString() {
		return "Cocinero [especialidadCulinaria=" + especialidadCulinaria + ", plusCategoria=" + plusCategoria + "]";
	}
	
}
