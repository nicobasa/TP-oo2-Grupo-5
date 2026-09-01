package datos;

import java.time.LocalDate;
import java.util.List;

public class Festival {

    // ATRIBUTOS
    private int id;
    private String nombre;
    private String temporada;
    private LocalDate fechaInicio;
    private LocalDate fechaFin;

    // COSTOS ESTABLECIDOS POR EL FESTIVAL
    private double costoPorSuperficie;
    private double costoPorMontaje;
    private double plusUsoElectricidad;
    private double sueldoBase;

    // RELACIONES
    private List<UnidadVenta> unidadesDeVenta;
    private List<Pedido> pedidos;

    // CONSTRUCTORES
    public Festival() {
        super();
    }

    public Festival(
            String nombre,
            String temporada,
            LocalDate fechaInicio,
            LocalDate fechaFin,
            double costoPorSuperficie,
            double costoPorMontaje,
            double plusUsoElectricidad,
            double sueldoBase) {

        super();

        this.nombre = nombre;
        this.temporada = temporada;
        this.fechaInicio = fechaInicio;
        setFechaFin(fechaFin);
        this.costoPorSuperficie = costoPorSuperficie;
        this.costoPorMontaje = costoPorMontaje;
        this.plusUsoElectricidad = plusUsoElectricidad;
        this.sueldoBase = sueldoBase;
    }

    // GETTERS Y SETTERS
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

    public String getTemporada() {
        return temporada;
    }

    public void setTemporada(String temporada) {
        this.temporada = temporada;
    }

    public LocalDate getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(LocalDate fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public LocalDate getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(LocalDate fechaFin) {

        if (fechaFin != null
                && fechaInicio != null
                && fechaFin.isBefore(fechaInicio)) {

            throw new IllegalArgumentException(
                    "La fecha de fin no puede ser anterior a la fecha de inicio");
        }

        this.fechaFin = fechaFin;
    }

    public double getCostoPorSuperficie() {
        return costoPorSuperficie;
    }

    public void setCostoPorSuperficie(double costoPorSuperficie) {
        this.costoPorSuperficie = costoPorSuperficie;
    }

    public double getCostoPorMontaje() {
        return costoPorMontaje;
    }

    public void setCostoPorMontaje(double costoPorMontaje) {
        this.costoPorMontaje = costoPorMontaje;
    }

    public double getPlusUsoElectricidad() {
        return plusUsoElectricidad;
    }

    public void setPlusUsoElectricidad(double plusUsoElectricidad) {
        this.plusUsoElectricidad = plusUsoElectricidad;
    }

    public double getSueldoBase() {
        return sueldoBase;
    }

    public void setSueldoBase(double sueldoBase) {
        this.sueldoBase = sueldoBase;
    }

    public List<UnidadVenta> getUnidadesDeVenta() {
        return unidadesDeVenta;
    }

    public void setUnidadesDeVenta(
            List<UnidadVenta> unidadesDeVenta) {

        this.unidadesDeVenta = unidadesDeVenta;
    }

    public List<Pedido> getPedidos() {
        return pedidos;
    }

    public void setPedidos(List<Pedido> pedidos) {
        this.pedidos = pedidos;
    }

    // TO STRING
    @Override
    public String toString() {

        return "Festival [id=" + id
                + ", nombre=" + nombre
                + ", temporada=" + temporada
                + ", fechaInicio=" + fechaInicio
                + ", fechaFin=" + fechaFin
                + ", costoPorSuperficie=" + costoPorSuperficie
                + ", costoPorMontaje=" + costoPorMontaje
                + ", plusUsoElectricidad=" + plusUsoElectricidad
                + ", sueldoBase=" + sueldoBase + "]";
    }
}