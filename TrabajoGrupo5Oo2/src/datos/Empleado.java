package datos;

import java.time.LocalDate;
import java.time.Period;

public class Empleado {

    // ATRIBUTOS
    private int id;
    private String nombre;
    private String apellido;
    private long dni;
    private LocalDate fechaNacimiento;
    private LocalDate fechaIngreso;
    private double sueldoBase;
    private UnidadVenta unidadVenta;

    // CONSTRUCTORES
    public Empleado() {
        super();
    }

    public Empleado(String nombre, String apellido, long dni,
            LocalDate fechaNacimiento, LocalDate fechaIngreso,
            double sueldoBase) {

        super();
        this.nombre = nombre;
        this.apellido = apellido;
        this.dni = dni;
        setFechaNacimiento(fechaNacimiento);
        this.fechaIngreso = fechaIngreso;
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

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public long getDni() {
        return dni;
    }

    public void setDni(long dni) {
        this.dni = dni;
    }

    public LocalDate getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(LocalDate fechaNacimiento) {

        if (fechaNacimiento == null) {
            throw new IllegalArgumentException(
                    "La fecha de nacimiento no puede ser nula");
        }

        if (Period.between(fechaNacimiento, LocalDate.now()).getYears() < 18) {
            throw new IllegalArgumentException(
                    "El empleado debe ser mayor de edad");
        }

        this.fechaNacimiento = fechaNacimiento;
    }

    public LocalDate getFechaIngreso() {
        return fechaIngreso;
    }

    public void setFechaIngreso(LocalDate fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
    }

    public double getSueldoBase() {
        return sueldoBase;
    }

    public void setSueldoBase(double sueldoBase) {
        this.sueldoBase = sueldoBase;
    }

    public UnidadVenta getUnidadVenta() {
        return unidadVenta;
    }

    public void setUnidadVenta(UnidadVenta unidadVenta) {
        this.unidadVenta = unidadVenta;
    }

    // ANTIGUEDAD
    public int calcularAntiguedad() {

        if (fechaIngreso == null) {
            return 0;
        }

        return Period.between(
                fechaIngreso,
                LocalDate.now()
        ).getYears();
    }

    // TO STRING
    @Override
    public String toString() {
        return "Empleado [id=" + id
                + ", nombre=" + nombre
                + ", apellido=" + apellido
                + ", dni=" + dni
                + ", fechaNacimiento=" + fechaNacimiento
                + ", fechaIngreso=" + fechaIngreso
                + ", sueldoBase=" + sueldoBase + "]";
    }
}