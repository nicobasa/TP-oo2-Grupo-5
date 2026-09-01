package negocio;

import java.util.List;

import dao.FestivalDao;
import datos.Festival;

public class FestivalABM {

    FestivalDao dao = new FestivalDao();

    public int agregar(Festival objeto) {
        return dao.agregar(objeto);
    }

    public void actualizar(Festival objeto) {
        dao.actualizar(objeto);
    }

    public void eliminar(Festival objeto) {
        dao.eliminar(objeto);
    }

    public Festival traer(int idFestival) {
        return dao.traer(idFestival);
    }

    public List<Festival> traer() {
        return dao.traer();
    }

}