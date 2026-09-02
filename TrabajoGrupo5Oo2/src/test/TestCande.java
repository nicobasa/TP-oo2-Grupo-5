package test;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import dao.HibernateUtil;
import datos.Festival;
import datos.UnidadVenta;

public class TestCande {

public static void main(String[] args) {

Session session = null;
Transaction tx = null;

String nombreFestival = "Festival de la Primavera";

try {

session = HibernateUtil.getSessionFactory().openSession();
tx = session.beginTransaction();

// =====================================================
// 1. BUSCAR FESTIVAL
// =====================================================

Query<Festival> consultaFestival = session.createQuery(
"from Festival f where f.nombre = :nombre",
Festival.class);

consultaFestival.setParameter("nombre", nombreFestival);

Festival festival = consultaFestival.uniqueResult();

if (festival == null) {
System.out.println(
"No existe un festival con el nombre: "
+ nombreFestival);

tx.commit();
return;
}

// =====================================================
// 2. OBTENER RANKING DE UNIDADES DE VENTA
// SEGUN RECAUDACION
// =====================================================

Query<Object[]> consultaRanking = session.createQuery(
"select u, "
+ "sum(d.cantidad * d.precioUnitario) "
+ "from Pedido p "
+ "join p.unidadDeVenta u "
+ "join p.detalles d "
+ "where p.festival.id = :festivalId "
+ "group by u.id, u.nombreComercial, u.codigo "
+ "order by sum(d.cantidad * d.precioUnitario) desc",
Object[].class);

consultaRanking.setParameter(
"festivalId",
festival.getId());

List<Object[]> ranking = consultaRanking.getResultList();

// =====================================================
// 3. MOSTRAR RESULTADO
// =====================================================

System.out.println();
System.out.println("============================================================");
System.out.println(" RANKING DE UNIDADES DE VENTA");
System.out.println(" SEGUN RECAUDACION OBTENIDA");
System.out.println("============================================================");
System.out.println("Festival: " + festival.getNombre());
System.out.println();

if (ranking.isEmpty()) {

System.out.println(
"El festival no posee pedidos registrados.");

} else {

int posicion = 1;

for (Object[] fila : ranking) {

UnidadVenta unidad = (UnidadVenta) fila[0];

double recaudacion = fila[1] != null
? ((Number) fila[1]).doubleValue()
: 0;

System.out.println(
posicion + ". "
+ unidad.getNombreComercial()
+ " | Codigo: "
+ unidad.getCodigo()
+ " | Recaudacion: $"
+ String.format("%.2f", recaudacion));

posicion++;
}
}

System.out.println();
System.out.println("============================================================");
System.out.println(" RANKING GENERADO CORRECTAMENTE");
System.out.println("============================================================");

tx.commit();

} catch (Exception e) {

if (tx != null) {
tx.rollback();
}

e.printStackTrace();

} finally {

if (session != null) {
session.close();
}
}
}
}
