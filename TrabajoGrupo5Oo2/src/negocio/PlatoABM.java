package negocio;

import java.util.List;

import dao.PlatoDao;
import datos.Plato;

public class PlatoABM {

    PlatoDao dao = new PlatoDao();

    public int agregar(Plato objeto) {
        return dao.agregar(objeto);
    }

    public void actualizar(Plato objeto) {
        dao.actualizar(objeto);
    }

    public void eliminar(Plato objeto) {
        dao.eliminar(objeto);
    }

    public Plato traer(int idPlato) {
        return dao.traer(idPlato);
    }

    public List<Plato> traer() {
        return dao.traer();
    }

}