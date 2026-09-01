package negocio;

import java.util.List;

import dao.PedidoDao;
import datos.Pedido;

public class PedidoABM {

    PedidoDao dao = new PedidoDao();

    public int agregar(Pedido objeto) {
        return dao.agregar(objeto);
    }

    public void actualizar(Pedido objeto) {
        dao.actualizar(objeto);
    }

    public void eliminar(Pedido objeto) {
        dao.eliminar(objeto);
    }

    public Pedido traer(int idPedido) {
        return dao.traer(idPedido);
    }

    public List<Pedido> traer() {
        return dao.traer();
    }

}