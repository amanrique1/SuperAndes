/**
 * 
 */
package uniandes.isis2304.superAndes.persistencia;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import main.java.model.Comprador;
import main.java.model.ProductoRFC12;
import main.java.model.ProductosVendidos;

/**
 * @author Andres
 *
 */
public class SQLProductosVendidos {



	private final static String SQL = PersistenciaSuperAndes.SQL;

	/* ********
	 * 			Atributos
	 *********/
	/**
	 * El manejador de persistencia general de la aplicaci�n
	 */
	private PersistenciaSuperAndes pp;

	private SQLFacturasComprador sqlFacturas;

	private SQLProductosSucursal sqlProductosSucursal;

	/* ********
	 * 			M�todos
	 *********/

	/**
	 * Constructor
	 * @param pp - El Manejador de persistencia de la aplicaci�n
	 */
	public SQLProductosVendidos (PersistenciaSuperAndes pp,SQLFacturasComprador pSqlFacturas,SQLProductosSucursal pSqlProductosSucursal)
	{
		this.pp = pp;
		sqlFacturas=pSqlFacturas;
		sqlProductosSucursal=pSqlProductosSucursal;
	}


	public void agregarProductosVendidos (PersistenceManager pm,long noFactura,int cantidad, String idProductoSucursal)
	{
		Query q1 = pm.newQuery(SQL, "INSERT INTO " + pp.darTablaProductosVendidos() + "(noFactura, cantidadVendida, idProductoSucursal) values (?, ?, ?)");
		q1.setParameters(  noFactura,  cantidad,idProductoSucursal);
		q1.executeUnique();
	}


	public void eliminarProductosVendidosVacios (PersistenceManager pm)
	{

		Query q2 = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaProductosVendidos () + " WHERE cantidad = 0");
		q2.executeUnique();

	}

	public void disminuirProductosVendidosDeFacturaEnCantidad (PersistenceManager pm,long noFactura,int cant,String idProductoSucursal)
	{

		Query q3 = pm.newQuery(SQL, "UPDATE " + pp.darTablaProductosVendidos () + " SET cantidad=cantidad-? WHERE noFactura=? AND idProductoSucursal=?");
		q3.setParameters( cant,noFactura ,idProductoSucursal);
		q3.executeUnique();

	}

	public void aumentarProductosVendidosDeFacturaEnCantidad (PersistenceManager pm,long noFactura,int cant,String idProductoSucursal)
	{

		Query q3 = pm.newQuery(SQL, "UPDATE " + pp.darTablaProductosVendidos () + " SET cantidad=cantidad+? WHERE noFactura=? AND idProductoSucursal=?");
		q3.setParameters( cant,noFactura ,idProductoSucursal);
		q3.executeUnique();

	}

	public int darCantidadProductosVendidosDeFactura (PersistenceManager pm, long noFactura, String idProductoSucursal )
	{
		Query q = pm.newQuery(SQL, "SELECT cantidad FROM "+ pp.darTablaFacturasCompradores()+ " WHERE noFactura=? AND idProductoSucursal=?");
		q.setParameters(noFactura,idProductoSucursal);
		return (int) q.executeUnique();

	}

	public List<ProductosVendidos> darProductosVendidosDeFactura (PersistenceManager pm, long noFactura )
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM "+ pp.darTablaProductosVendidos()+ " WHERE noFactura=? ");
		q.setParameters(noFactura);
		return (List<ProductosVendidos>) q.executeList();

	}

	public ProductosVendidos darProductoVendido (PersistenceManager pm, long noFactura, String idProductoSucursal )
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM "+ pp.darTablaProductosVendidos()+ " WHERE noFactura=? AND idProductoSucursal=?");
		q.setResultClass(ProductosVendidos.class);
		q.setParameters(noFactura,idProductoSucursal);
		return (ProductosVendidos) q.executeUnique();

	}

	public List<ProductosVendidos> darProductosVendidos (PersistenceManager pm, long noFactura)
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM "+ pp.darTablaProductosVendidos()+ " WHERE noFactura= ?");
		q.setResultClass(ProductosVendidos.class);
		q.setParameters(noFactura);
		return (List<ProductosVendidos>) q.executeList();

	}



	public List<ProductosVendidos> darProductosSucursal (PersistenceManager pm, long idSucursal)
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM "+ pp.darTablaFacturasCompradores()+ " WHERE idSucursal= ?");
		q.setResultClass(ProductosVendidos.class);
		q.setParameters(idSucursal);
		return (List<ProductosVendidos>) q.executeList();

	}
	public List<ProductosVendidos> darProductosSucursalNumero (PersistenceManager pm, long numero)
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM "+ pp.darTablaFacturasCompradores()+ " WHERE numero= ?");
		q.setResultClass(ProductosVendidos.class);
		q.setParameters(numero);
		return (List<ProductosVendidos>) q.executeList();

	}

	public String darMesMayorDemanda(PersistenceManager pm, String tipo)
	{
		Query q1 = pm.newQuery(SQL, "select * from(SELECT fecha from "+pp.darTablaFacturasCompradores()+" group by fecha order by fecha asc) where rownum=1");
		q1.setResultClass(Timestamp.class);
		Timestamp fechaMenor=(Timestamp)q1.executeUnique();
		fechaMenor.setDate(1);
		Calendar calendarMenor = Calendar.getInstance();
		calendarMenor.setTimeInMillis(fechaMenor.getTime());
		Calendar actual = Calendar.getInstance();
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yy");

		Calendar fechaMay=calendarMenor;
		int cantidadMayor=0;
		while (calendarMenor.compareTo(actual)<0)
		{
			Query q2 = pm.newQuery(SQL,"select sum(ventasDia) from (select distinct sum(cantidadvendida) over(Partition by fecha)as ventasDia,fecha from (select * from(select * from facturascomprador f inner join productosvendidos p on f.numero=p.nofactura)x inner join productossucursal y on x.idproductosucursal=y.idproductosucursal)z inner join productos on z.codigoproducto=productos.codigobarras  where tipoproducto="+tipo+" and fecha > To_date("+dateFormat.format(calendarMenor)+", 'DD/MM/YY') and fecha < To_date("+dateFormat.format(aumentarMes(calendarMenor))+", 'DD/MM/YY')  group by cantidadvendida,fecha )");
			q2.setResultClass(Integer.class);
			int cantActual=(Integer)q2.executeUnique();
			if(cantActual>cantidadMayor)
			{
				cantidadMayor=cantActual;
				fechaMay=calendarMenor;
			}
			calendarMenor=aumentarMes(calendarMenor);

		}
		return dateFormat.format(fechaMay);

	}
	public String darMesMenorDemanda(PersistenceManager pm, String tipo)
	{
		Query q1 = pm.newQuery(SQL, "select * from(SELECT fecha from "+pp.darTablaFacturasCompradores()+" group by fecha order by fecha asc) where rownum=1");
		q1.setResultClass(Timestamp.class);
		Timestamp fechaMenor=(Timestamp)q1.executeUnique();
		fechaMenor.setDate(1);
		Calendar calendarMenor = Calendar.getInstance();
		calendarMenor.setTimeInMillis(fechaMenor.getTime());
		Calendar actual = Calendar.getInstance();
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yy");

		Calendar fechaMay=calendarMenor;
		int cantidadMayor=0;
		while (calendarMenor.compareTo(actual)<0)
		{
			Query q2 = pm.newQuery(SQL,"select sum(ventasDia) from (select distinct sum(cantidadvendida) over(Partition by fecha)as ventasDia,fecha from (select * from(select * from facturascomprador f inner join productosvendidos p on f.numero=p.nofactura)x inner join productossucursal y on x.idproductosucursal=y.idproductosucursal)z inner join productos on z.codigoproducto=productos.codigobarras  where tipoproducto="+tipo+" and fecha > To_date("+dateFormat.format(calendarMenor)+", 'DD/MM/YY') and fecha < To_date("+dateFormat.format(aumentarMes(calendarMenor))+", 'DD/MM/YY')  group by cantidadvendida,fecha )");
			q2.setResultClass(Integer.class);
			int cantActual=(Integer)q2.executeUnique();
			if(cantActual>cantidadMayor)
			{
				cantidadMayor=cantActual;
				fechaMay=calendarMenor;
			}
			calendarMenor=aumentarMes(calendarMenor);

		}
		return dateFormat.format(fechaMay);

	}

	public String darMesMayorIngreso(PersistenceManager pm, String tipo)
	{
		Query q1 = pm.newQuery(SQL, "select * from(SELECT fecha from "+pp.darTablaFacturasCompradores()+" group by fecha order by fecha asc) where rownum=1");
		q1.setResultClass(Timestamp.class);
		Timestamp fechaMenor=(Timestamp)q1.executeUnique();
		fechaMenor.setDate(1);
		Calendar calendarMenor = Calendar.getInstance();
		calendarMenor.setTimeInMillis(fechaMenor.getTime());
		Calendar actual = Calendar.getInstance();
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yy");

		Calendar fechaMay=calendarMenor;
		double cantidadMayor=0;
		while (calendarMenor.compareTo(actual)<0)
		{
			Query q2 = pm.newQuery(SQL,"select sum(ingresos) from (select distinct sum(cantidadvendida) over(Partition by fecha)as ventasDia,fecha,precio*cantidadvendida as ingresos from (select * from(select * from facturascomprador f inner join productosvendidos p on f.numero=p.nofactura)x inner join productossucursal y on x.idproductosucursal=y.idproductosucursal)z inner join productos on z.codigoproducto=productos.codigobarras  where tipoproducto="+tipo+" and fecha > To_date("+dateFormat.format(calendarMenor)+", 'DD/MM/YY') and fecha < To_date("+dateFormat.format(aumentarMes(calendarMenor))+", 'DD/MM/YY')  group by cantidadvendida,fecha,precio )");
			q2.setResultClass(Double.class);
			double cantActual=(Double)q2.executeUnique();
			if(cantActual>cantidadMayor)
			{
				cantidadMayor=cantActual;
				fechaMay=calendarMenor;
			}
			calendarMenor=aumentarMes(calendarMenor);

		}
		return dateFormat.format(fechaMay);

	}

	public String darSemanaMayorDemanda(PersistenceManager pm, String tipo)
	{
		Query q1 = pm.newQuery(SQL, "select * from(SELECT fecha from "+pp.darTablaFacturasCompradores()+" group by fecha order by fecha asc) where rownum=1");
		q1.setResultClass(Timestamp.class);
		Timestamp fechaMenor=(Timestamp)q1.executeUnique();
		int dia=fechaMenor.getDate();
		Calendar calendarMenor = Calendar.getInstance();
		int diaSemana=calendarMenor.get(Calendar.DAY_OF_WEEK);
		if(diaSemana!=1)
		{
			dia+=1-diaSemana;
		}
		fechaMenor.setDate(dia);
		calendarMenor.setTimeInMillis(fechaMenor.getTime());
		Calendar actual = Calendar.getInstance();
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yy");

		Calendar fechaMay=calendarMenor;
		int cantidadMayor=0;
		while (calendarMenor.compareTo(actual)<0)
		{
			Query q2 = pm.newQuery(SQL,"select sum(ventasDia) from (select distinct sum(cantidadvendida) over(Partition by fecha)as ventasDia,fecha from (select * from(select * from facturascomprador f inner join productosvendidos p on f.numero=p.nofactura)x inner join productossucursal y on x.idproductosucursal=y.idproductosucursal)z inner join productos on z.codigoproducto=productos.codigobarras  where tipoproducto="+tipo+" and fecha > To_date("+dateFormat.format(calendarMenor)+", 'DD/MM/YY') and fecha < To_date("+dateFormat.format(aumentarMes(calendarMenor))+", 'DD/MM/YY')  group by cantidadvendida,fecha )");
			q2.setResultClass(Integer.class);
			int cantActual=(Integer)q2.executeUnique();
			if(cantActual>cantidadMayor)
			{
				cantidadMayor=cantActual;
				fechaMay=calendarMenor;
			}
			calendarMenor=aumentarMes(calendarMenor);

		}
		return dateFormat.format(fechaMay);

	}
	public String darSemanaMenorDemanda(PersistenceManager pm, String tipo)
	{
		Query q1 = pm.newQuery(SQL, "select * from(SELECT fecha from "+pp.darTablaFacturasCompradores()+" group by fecha order by fecha asc) where rownum=1");
		q1.setResultClass(Timestamp.class);
		Timestamp fechaMenor=(Timestamp)q1.executeUnique();
		int dia=fechaMenor.getDate();
		Calendar calendarMenor = Calendar.getInstance();
		int diaSemana=calendarMenor.get(Calendar.DAY_OF_WEEK);
		if(diaSemana!=1)
		{
			dia+=1-diaSemana;
		}
		fechaMenor.setDate(dia);
		calendarMenor.setTimeInMillis(fechaMenor.getTime());
		Calendar actual = Calendar.getInstance();
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yy");

		Calendar fechaMay=calendarMenor;
		int cantidadMayor=0;
		while (calendarMenor.compareTo(actual)<0)
		{
			Query q2 = pm.newQuery(SQL,"select sum(ventasDia) from (select distinct sum(cantidadvendida) over(Partition by fecha)as ventasDia,fecha from (select * from(select * from facturascomprador f inner join productosvendidos p on f.numero=p.nofactura)x inner join productossucursal y on x.idproductosucursal=y.idproductosucursal)z inner join productos on z.codigoproducto=productos.codigobarras  where tipoproducto="+tipo+" and fecha > To_date("+dateFormat.format(calendarMenor)+", 'DD/MM/YY') and fecha < To_date("+dateFormat.format(aumentarMes(calendarMenor))+", 'DD/MM/YY')  group by cantidadvendida,fecha )");
			q2.setResultClass(Integer.class);
			int cantActual=(Integer)q2.executeUnique();
			if(cantActual>cantidadMayor)
			{
				cantidadMayor=cantActual;
				fechaMay=calendarMenor;
			}
			calendarMenor=aumentarMes(calendarMenor);

		}
		return dateFormat.format(fechaMay);

	}


	public String darSemanaMayorIngreso(PersistenceManager pm, String tipo)
	{
		Query q1 = pm.newQuery(SQL, "select * from(SELECT fecha from "+pp.darTablaFacturasCompradores()+" group by fecha order by fecha asc) where rownum=1");
		q1.setResultClass(Timestamp.class);
		Timestamp fechaMenor=(Timestamp)q1.executeUnique();
		int dia=fechaMenor.getDate();
		Calendar calendarMenor = Calendar.getInstance();
		int diaSemana=calendarMenor.get(Calendar.DAY_OF_WEEK);
		if(diaSemana!=1)
		{
			dia+=1-diaSemana;
		}
		fechaMenor.setDate(dia);
		calendarMenor.setTimeInMillis(fechaMenor.getTime());
		Calendar actual = Calendar.getInstance();
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yy");

		Calendar fechaMay=calendarMenor;
		double cantidadMayor=0;
		while (calendarMenor.compareTo(actual)<0)
		{
			Query q2 = pm.newQuery(SQL,"select sum(ingresos) from (select distinct sum(cantidadvendida) over(Partition by fecha)as ventasDia,fecha,precio*cantidadvendida as ingresos from (select * from(select * from facturascomprador f inner join productosvendidos p on f.numero=p.nofactura)x inner join productossucursal y on x.idproductosucursal=y.idproductosucursal)z inner join productos on z.codigoproducto=productos.codigobarras  where tipoproducto="+tipo+" and fecha > To_date("+dateFormat.format(calendarMenor)+", 'DD/MM/YY') and fecha < To_date("+dateFormat.format(aumentarSemana(calendarMenor))+", 'DD/MM/YY')  group by cantidadvendida,fecha,precio )");
			q2.setResultClass(Double.class);
			double cantActual=(Double)q2.executeUnique();
			if(cantActual>cantidadMayor)
			{
				cantidadMayor=cantActual;
				fechaMay=calendarMenor;
			}
			calendarMenor=aumentarMes(calendarMenor);

		}
		return dateFormat.format(fechaMay);

	}

	private Calendar aumentarMes(Calendar calendarMenor)
	{
		calendarMenor.add(Calendar.MONTH, 1);
		return calendarMenor;
	}

	private Calendar aumentarSemana(Calendar calendarMenor)
	{
		calendarMenor.add(Calendar.DAY_OF_YEAR, 7);
		return calendarMenor;
	}

	
	public List<ProductoRFC12> RFC12MasVendido(PersistenceManager pm)
	{		
		String sentencia="SELECT tabla1.cantidadVendida,tabla1.numeroSemana,tabla2.idProductoSucursal as producto FROM (SELECT numeroSemana, MAX(cantidadVendida)as cantidadVendida FROM (SELECT idProductoSucursal ,SUM(cantidadVendida)as cantidadVendida , to_char(to_date(fecha, 'dd-mm-yyyy'), 'ww') as numeroSemana FROM FACTURASCOMPRADOR INNER JOIN PRODUCTOSVENDIDOS ON FACTURASCOMPRADOR.numero=NoFactura GROUP BY idProductoSucursal,to_char(to_date(fecha, 'dd-mm-yyyy'), 'ww')) GROUP BY numeroSemana) tabla1 INNER JOIN (SELECT idProductoSucursal ,SUM(cantidadVendida)as cantidadVendida , to_char(to_date(fecha, 'dd-mm-yyyy'), 'ww') as numeroSemana FROM FACTURASCOMPRADOR INNER JOIN PRODUCTOSVENDIDOS ON FACTURASCOMPRADOR.numero=NoFactura GROUP BY idProductoSucursal,to_char(to_date(fecha, 'dd-mm-yyyy'), 'ww')) tabla2 ON tabla1.cantidadVendida=tabla2.cantidadVendida AND tabla1.numeroSemana=tabla2.numeroSemana  ORDER BY tabla1.numeroSemana";
		
		Query q = pm.newQuery(SQL, sentencia);
		q.setResultClass(ProductoRFC12.class);
		return (List<ProductoRFC12>)q.executeList();

	}
	
	public List<ProductoRFC12> RFC12MenosVendido(PersistenceManager pm)
	{		
		String sentencia="SELECT tabla1.cantidadVendida,tabla1.numeroSemana,tabla2.idProductoSucursal as producto FROM (SELECT numeroSemana, MIN(cantidadVendida)as cantidadVendida FROM (SELECT idProductoSucursal ,SUM(cantidadVendida)as cantidadVendida , to_char(to_date(fecha, 'dd-mm-yyyy'), 'ww') as numeroSemana FROM FACTURASCOMPRADOR INNER JOIN PRODUCTOSVENDIDOS ON FACTURASCOMPRADOR.numero=NoFactura GROUP BY idProductoSucursal,to_char(to_date(fecha, 'dd-mm-yyyy'), 'ww')) GROUP BY numeroSemana) tabla1 INNER JOIN (SELECT idProductoSucursal ,SUM(cantidadVendida)as cantidadVendida , to_char(to_date(fecha, 'dd-mm-yyyy'), 'ww') as numeroSemana FROM FACTURASCOMPRADOR INNER JOIN PRODUCTOSVENDIDOS ON FACTURASCOMPRADOR.numero=NoFactura GROUP BY idProductoSucursal,to_char(to_date(fecha, 'dd-mm-yyyy'), 'ww')) tabla2 ON tabla1.cantidadVendida=tabla2.cantidadVendida AND tabla1.numeroSemana=tabla2.numeroSemana  ORDER BY tabla1.numeroSemana";
		
		Query q = pm.newQuery(SQL, sentencia);
		q.setResultClass(ProductoRFC12.class);
		return (List<ProductoRFC12>)q.executeList();

	}

}
