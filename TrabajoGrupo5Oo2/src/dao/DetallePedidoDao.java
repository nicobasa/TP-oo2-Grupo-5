package dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import datos.DetallePedido;

public class DetallePedidoDao {

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

    public int agregar(DetallePedido objeto) {
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

    public void actualizar(DetallePedido objeto) {
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

    public void eliminar(DetallePedido objeto) {
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

    public DetallePedido traer(int idDetallePedido) {
        DetallePedido objeto = null;
        try {
            iniciaOperacion();
            objeto = (DetallePedido) session.get(DetallePedido.class, idDetallePedido);
        } finally {
            session.close();
        }
        return objeto;
    }

    public List<DetallePedido> traer() {
        List<DetallePedido> lista = new ArrayList<DetallePedido>();
        try {
            iniciaOperacion();
            Query<DetallePedido> query = session.createQuery("from DetallePedido d", DetallePedido.class);
            lista = query.getResultList();
        } finally {
            session.close();
        }
        return lista;
    }

}