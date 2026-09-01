package negocio;

import java.util.List;

import dao.UnidadVentaDao;
import datos.UnidadVenta;

public class UnidadVentaABM {

    UnidadVentaDao dao = new UnidadVentaDao();

    public int agregar(UnidadVenta objeto) {
        return dao.agregar(objeto);
    }

    public void actualizar(UnidadVenta objeto) {
        dao.actualizar(objeto);
    }

    public void eliminar(UnidadVenta objeto) {
        dao.eliminar(objeto);
    }

    public UnidadVenta traer(int idUnidadVenta) {
        return dao.traer(idUnidadVenta);
    }

    public List<UnidadVenta> traer() {
        return dao.traer();
    }

}