package negocio;

import java.util.List;

import dao.DetallePedidoDao;
import datos.DetallePedido;

public class DetallePedidoABM {

    DetallePedidoDao dao = new DetallePedidoDao();

    public int agregar(DetallePedido objeto) {
        return dao.agregar(objeto);
    }

    public void actualizar(DetallePedido objeto) {
        dao.actualizar(objeto);
    }

    public void eliminar(DetallePedido objeto) {
        dao.eliminar(objeto);
    }

    public DetallePedido traer(int idDetallePedido) {
        return dao.traer(idDetallePedido);
    }

    public List<DetallePedido> traer() {
        return dao.traer();
    }

}