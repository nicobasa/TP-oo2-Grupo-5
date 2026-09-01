package datos;

import java.util.List;

public class UnidadVenta {

    // ATRIBUTOS
    private int id;
    private String nombreComercial;
    private double superficie;
    private String codigo;
    private Empleado responsable;
    private List<Empleado> empleados;
    private List<Pedido> pedidos;
    private List<Plato> platos;
    private List<Festival> festivales;

    // CONSTRUCTORES
    public UnidadVenta() {
        super();
    }

    public UnidadVenta(String nombreComercial, double superficie, String codigo, Empleado responsable) {
        super();
        this.nombreComercial = nombreComercial;
        this.superficie = superficie;
        setCodigo(codigo);
        this.responsable = responsable;
    }

    // GETTERS Y SETTERS
    public int getId() {
        return id;
    }

    protected void setId(int id) {
        this.id = id;
    }

    public String getNombreComercial() {
        return nombreComercial;
    }

    public void setNombreComercial(String nombreComercial) {
        this.nombreComercial = nombreComercial;
    }

    public double getSuperficie() {
        return superficie;
    }

    public void setSuperficie(double superficie) {
        this.superficie = superficie;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        if (!validarCodigo(codigo)) {
            throw new IllegalArgumentException(
                    "El codigo debe tener exactamente 10 caracteres: 2 letras mayusculas y 8 numeros");
        }

        this.codigo = codigo;
    }

    private boolean validarCodigo(String codigo) {
        return codigo != null && codigo.matches("[A-Z]{2}[0-9]{8}");
    }

    public Empleado getResponsable() {
        return responsable;
    }

    public void setResponsable(Empleado responsable) {
        this.responsable = responsable;
    }

    public List<Empleado> getEmpleados() {
        return empleados;
    }

    public void setEmpleados(List<Empleado> empleados) {
        this.empleados = empleados;
    }

    public List<Pedido> getPedidos() {
        return pedidos;
    }

    public void setPedidos(List<Pedido> pedidos) {
        this.pedidos = pedidos;
    }

    public List<Plato> getPlatos() {
        return platos;
    }

    public void setPlatos(List<Plato> platos) {
        this.platos = platos;
    }

    public List<Festival> getFestivales() {
        return festivales;
    }

    public void setFestivales(List<Festival> festivales) {
        this.festivales = festivales;
    }

    // TO STRING
    @Override
    public String toString() {
        return "UnidadVenta [id=" + id
                + ", nombreComercial=" + nombreComercial
                + ", superficie=" + superficie
                + ", codigo=" + codigo + "]";
    }
}