package negocio;

import java.util.List;

import dao.EmpleadoDao;
import datos.Empleado;

public class EmpleadoABM {

    EmpleadoDao dao = new EmpleadoDao();

    public int agregar(Empleado objeto) {
        return dao.agregar(objeto);
    }

    public void actualizar(Empleado objeto) {
        dao.actualizar(objeto);
    }

    public void eliminar(Empleado objeto) {
        dao.eliminar(objeto);
    }

    public Empleado traer(int idEmpleado) {
        return dao.traer(idEmpleado);
    }

    public List<Empleado> traer() {
        return dao.traer();
    }

}