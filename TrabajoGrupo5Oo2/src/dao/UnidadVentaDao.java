package dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import datos.UnidadVenta;

public class UnidadVentaDao {

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

    public int agregar(UnidadVenta objeto) {
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

    public void actualizar(UnidadVenta objeto) {
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

    public void eliminar(UnidadVenta objeto) {
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

    public UnidadVenta traer(int idUnidadVenta) {
        UnidadVenta objeto = null;
        try {
            iniciaOperacion();
            objeto = (UnidadVenta) session.get(UnidadVenta.class, idUnidadVenta);
        } finally {
            session.close();
        }
        return objeto;
    }

    public List<UnidadVenta> traer() {
        List<UnidadVenta> lista = new ArrayList<UnidadVenta>();
        try {
            iniciaOperacion();
            Query<UnidadVenta> query = session.createQuery(
                    "from UnidadVenta u",
                    UnidadVenta.class);
            lista = query.getResultList();
        } finally {
            session.close();
        }
        return lista;
    }

    public List<Object[]> traerRankingPorFestival(int idFestival) {

        List<Object[]> ranking = new ArrayList<Object[]>();

        try {
            iniciaOperacion();

            Query<Object[]> query = session.createQuery(
                    "select u, "
                    + "sum(d.cantidad * d.precioUnitario) "
                    + "from Pedido p "
                    + "join p.unidadDeVenta u "
                    + "join p.detalles d "
                    + "where p.festival.id = :festivalId "
                    + "group by u.id, u.nombreComercial, u.codigo "
                    + "order by sum(d.cantidad * d.precioUnitario) desc",
                    Object[].class);

            query.setParameter("festivalId", idFestival);

            ranking = query.getResultList();

        } finally {
            session.close();
        }

        return ranking;
    }

}
