package datos;

import java.time.LocalDateTime;
import java.util.List;

public class Pedido {

    // ATRIBUTOS
    private int id;
    private LocalDateTime fechaTransaccion;
    private Festival festival;
    private UnidadVenta unidadDeVenta;
    private List<DetallePedido> detalles;

    // CONSTRUCTORES
    public Pedido() {
        super();
    }

    public Pedido(
            LocalDateTime fechaTransaccion,
            Festival festival,
            UnidadVenta unidadDeVenta) {

        super();
        this.fechaTransaccion = fechaTransaccion;
        this.festival = festival;
        this.unidadDeVenta = unidadDeVenta;
    }

    // GETTERS Y SETTERS
    public int getId() {
        return id;
    }

    protected void setId(int id) {
        this.id = id;
    }

    public LocalDateTime getFechaTransaccion() {
        return fechaTransaccion;
    }

    public void setFechaTransaccion(
            LocalDateTime fechaTransaccion) {

        this.fechaTransaccion = fechaTransaccion;
    }

    public Festival getFestival() {
        return festival;
    }

    public void setFestival(Festival festival) {
        this.festival = festival;
    }

    public UnidadVenta getUnidadDeVenta() {
        return unidadDeVenta;
    }

    public void setUnidadDeVenta(
            UnidadVenta unidadDeVenta) {

        this.unidadDeVenta = unidadDeVenta;
    }

    public List<DetallePedido> getDetalles() {
        return detalles;
    }

    public void setDetalles(
            List<DetallePedido> detalles) {

        this.detalles = detalles;
    }

    // TO STRING
    @Override
    public String toString() {

        return "Pedido [id=" + id
                + ", fechaTransaccion=" + fechaTransaccion
                + ", festival="
                + (festival != null
                        ? festival.getNombre()
                        : null)
                + ", unidadDeVenta="
                + (unidadDeVenta != null
                        ? unidadDeVenta.getNombreComercial()
                        : null)
                + "]";
    }
}