package dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import datos.Pedido;

public class PedidoDao {

    private static Session session;
    private Transaction tx;

    private void iniciaOperacion() throws HibernateException {
        session = HibernateUtil.getSessionFactory().openSession();
        tx = session.beginTransaction();
    }

    private void manejaExcepcion(HibernateException he) throws HibernateException {
        tx.rollback();
        throw new HibernateException("ERROR en la capa de acceso a datos", he);
    }

    public int agregar(Pedido objeto) {
        int id = 0;
        try {
            iniciaOperacion();
            id = Integer.parseInt(session.save(objeto).toString());
            tx.commit();
        } catch (HibernateException he) {
            manejaExcepcion(he);
        } finally {
            session.close();
        }
        return id;
    }

    public void actualizar(Pedido objeto) {
        try {
            iniciaOperacion();
            session.update(objeto);
            tx.commit();
        } catch (HibernateException he) {
            manejaExcepcion(he);
        } finally {
            session.close();
        }
    }

    public void eliminar(Pedido objeto) {
        try {
            iniciaOperacion();
            session.delete(objeto);
            tx.commit();
        } catch (HibernateException he) {
            manejaExcepcion(he);
        } finally {
            session.close();
        }
    }

    public Pedido traer(int idPedido) {
        Pedido objeto = null;
        try {
            iniciaOperacion();
            objeto = (Pedido) session.get(Pedido.class, idPedido);
        } finally {
            session.close();
        }
        return objeto;
    }

    public List<Pedido> traer() {
        List<Pedido> lista = new ArrayList<Pedido>();
        try {
            iniciaOperacion();
            Query<Pedido> query = session.createQuery("from Pedido p", Pedido.class);
            lista = query.getResultList();
        } finally {
            session.close();
        }
        return lista;
    }
    
    public List<Integer> traerIdsPedidosMayorValor(double montoMinimo) {
        List<Integer> ids = new ArrayList<Integer>();
        try {
            iniciaOperacion();
            String hql = "SELECT p.id FROM DetallePedido d "
                       + "JOIN d.pedido p "
                       + "GROUP BY p.id "
                       + "HAVING SUM(d.cantidad * d.precioUnitario) > :montoMinimo";
            Query<Integer> query = session.createQuery(hql, Integer.class);
            query.setParameter("montoMinimo", montoMinimo);
            ids = query.getResultList();
        } finally {
            session.close();
        }
        return ids;
    }

    public List<Pedido> traerPedidosPorIds(List<Integer> ids) {
        List<Pedido> pedidos = new ArrayList<Pedido>();
        if (ids == null || ids.isEmpty()) {
            return pedidos;
        }
        try {
            iniciaOperacion();
            String hql = "SELECT DISTINCT p FROM Pedido p "
                       + "JOIN FETCH p.festival "
                       + "JOIN FETCH p.unidadDeVenta "
                       + "JOIN FETCH p.detalles d "
                       + "JOIN FETCH d.plato "
                       + "WHERE p.id IN (:ids) "
                       + "ORDER BY p.fechaTransaccion DESC";
            Query<Pedido> query = session.createQuery(hql, Pedido.class);
            query.setParameterList("ids", ids);
            pedidos = query.getResultList();
        } finally {
            session.close();
        }
        return pedidos;
    }

}