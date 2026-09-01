package datos;

public class Cajero extends Empleado {

    // ATRIBUTOS
    private String turno;

    // CONSTRUCTORES
    public Cajero() {
        super();
    }

    public Cajero(String turno) {
        super();
        setTurno(turno);
    }

    // GETTERS Y SETTERS
    public String getTurno() {
        return turno;
    }

    public void setTurno(String turno) {

        if (turno == null) {
            throw new IllegalArgumentException(
                    "El turno no puede ser nulo");
        }

        if (turno.equalsIgnoreCase("Manana")
                || turno.equalsIgnoreCase("Mañana")) {

            this.turno = "Manana";

        } else if (turno.equalsIgnoreCase("Noche")) {

            this.turno = "Noche";

        } else {

            throw new IllegalArgumentException(
                    "El turno del cajero debe ser Manana o Noche");
        }
    }

    // TO STRING
    @Override
    public String toString() {
        return "Cajero [turno=" + turno + "]";
    }
}