/**
 * 
 */
package uniandes.isis2304.superAndes.persistencia;

import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import main.java.model.Comprador;
import main.java.model.Empresa;
import main.java.model.Sucursal;
import main.java.model.UsuariosComprasRFC10;

/**
 * @author Andres
 *
 */
public class SQLCompradores {
	

	private final static String SQL = PersistenciaSuperAndes.SQL;

	/* ****************************************************************
	 * 			Atributos
	 *****************************************************************/
	/**
	 * El manejador de persistencia general de la aplicaci�n
	 */
	private PersistenciaSuperAndes pp;

	/* ****************************************************************
	 * 			M�todos
	 *****************************************************************/

	/**
	 * Constructor
	 * @param pp - El Manejador de persistencia de la aplicaci�n
	 */
	public SQLCompradores (PersistenciaSuperAndes pp)
	{
		this.pp = pp;
	}


	public void agregarComprador (PersistenceManager pm, String pIdentificacio,String pNombre,String pCorreo) 
	{
		Query q1 = pm.newQuery(SQL, "INSERT INTO " + pp.darTablaCompradores() + "(identificador, nombre, correoElectronico, puntos) values (?, ?, ?, ?)");
		q1.setParameters(pIdentificacio, pNombre, pCorreo,0);
		q1.executeUnique();
	}


	public String eliminarCompradorPorNombre (PersistenceManager pm, String nombre)
	{
		Query q1 = pm.newQuery(SQL, "SELECT identificador FROM " + pp.darTablaCompradores () + " WHERE nombre = ?");
		q1.setResultClass(String.class);
		String identificador=(String)q1.executeUnique();
		Query q2 = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaCompradores () + " WHERE nombre = ?");
		q2.setParameters(nombre);
		q2.executeUnique();
		return identificador;
	}

	public void eliminarCompradorPorIdentificador (PersistenceManager pm, String id)
	{

		Query q2 = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaCompradores () + " WHERE identificador = ?");
		q2.setParameters(id);
		q2.executeUnique();

	}

	public Comprador darCompradorPorIdentificador (PersistenceManager pm, String identificador) 
	{
		String nombre=darNombre(pm, identificador);
		String correo=darCorreo(pm, identificador);
		double puntos=darPuntos(pm, identificador);
		Comprador comprador = new Comprador(nombre,correo,identificador,puntos); 

		return comprador;
	}


	public String darNombre(PersistenceManager pm, String identificador)
	{
		Query q1 = pm.newQuery(SQL, "SELECT nombre FROM " + pp.darTablaCompradores () + " WHERE identificador = ?");
		q1.setParameters(identificador);
		String nombre=q1.executeUnique()+"";

		return nombre;
	}

	public String darCorreo(PersistenceManager pm, String identificador)
	{
		Query q1 = pm.newQuery(SQL, "SELECT correoElectronico FROM " + pp.darTablaCompradores () + " WHERE identificador = ?");
		q1.setParameters(identificador);
		String correo=q1.executeUnique()+"";

		return correo;
	}

	public double darPuntos(PersistenceManager pm, String identificador)
	{
		Query q1 = pm.newQuery(SQL, "SELECT puntos FROM " + pp.darTablaCompradores () + " WHERE identificador = ?");
		q1.setParameters(identificador);
		double puntos=Double.parseDouble(q1.executeUnique()+"");

		return puntos;
	}
	public List<Comprador> darCompradores (PersistenceManager pm)
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM "+ pp.darTablaCompradores());
		q.setResultClass(Comprador.class);
		return (List<Comprador>) q.executeList();

	}

	public List<Comprador> darClientesFrecuentes(PersistenceManager pm)
	{
		Query q1 = pm.newQuery(SQL, "select * from(SELECT fecha from "+pp.darTablaFacturasCompradores()+" group by fecha order by fecha asc) where rownum=1");
		q1.setResultClass(Timestamp.class);
		Timestamp fechaMenor=(Timestamp)q1.executeUnique();
		Calendar calendarMenor = Calendar.getInstance();
		calendarMenor.setTimeInMillis(fechaMenor.getTime());
		Calendar actual = Calendar.getInstance();
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yy");
		List<Comprador> frecuentes=darCompradores(pm);
		while(calendarMenor.compareTo(actual)<0)
		{
			String query="select identificador,nombre,correoelectronico,puntos from compradores c inner join (select idcomprador from (select idcomprador,count(numero)as compras from facturascomprador where fecha>To_date("+dateFormat.format(calendarMenor)+", 'DD/MM/YY') and fecha<To_date("+dateFormat.format(aumentarMes(calendarMenor))+", 'DD/MM/YY') group by numero, idcomprador)where compras>1)x on c.identificador=x.idComprador;";
			Query q2 = pm.newQuery(SQL, query);
			q2.setResultClass(Comprador.class);
			List<Comprador>compradoresMes=(List<Comprador>) q2.executeList();
			frecuentes=intersection(frecuentes, compradoresMes);
			calendarMenor=aumentarMes(calendarMenor);
		}
		return frecuentes;

	}
	private Calendar aumentarMes(Calendar calendarMenor)
	{
		calendarMenor.add(Calendar.MONTH, 1);
		return calendarMenor;
	}

	public <T> List<T> intersection(List<T> list1, List<T> list2) {
		List<T> list = new ArrayList<T>();

		for (T t : list1) {
			if(list2.contains(t)) {
				list.add(t);
			}
		}

		return list;
	}
	
	
	public List<UsuariosComprasRFC10> RFC10Gerente(PersistenceManager pm,String idSucursal,String fechaInicial, String fechaFinal, String codigoProducto, List<String> criteriosOrden, List<Boolean> ascendenciaCriterios)
	{		
		List<Comprador> lista=new ArrayList<>();
		String sentencia="SELECT nombre, identificador, correoelectronico, fecha, cantidadVendida, idProductoSucursal as CodigoProducto, idSucursal FROM COMPRADORES"
				+ " INNER JOIN (SELECT * FROM FACTURASCOMPRADOR INNER JOIN "
				+ "(SELECT * FROM PRODUCTOSVENDIDOS WHERE idProductoSucursal LIKE '%"+codigoProducto+"') ON FACTURASCOMPRADOR.numero=NoFactura WHERE "
				+ "FECHA between '"+fechaInicial+"' AND '"+fechaFinal+"' AND idSucursal='"+idSucursal+"') ON COMPRADORES.identificador=idComprador";
		
		if(!criteriosOrden.isEmpty()) {
			sentencia=sentencia+" ORDER BY";
			for(int i=0;i<criteriosOrden.size();i++)
			{
				String orden=ascendenciaCriterios.get(i)==true?"ASC":"DESC";
				if(i==0)
					sentencia=sentencia+" "+criteriosOrden.get(i)+" "+orden;
				else 
					sentencia=sentencia+", "+criteriosOrden.get(i)+" "+orden;

			}
		}
		
		Query q = pm.newQuery(SQL, sentencia);
		q.setResultClass(UsuariosComprasRFC10.class);
		return (List<UsuariosComprasRFC10>)q.executeList();

	}
	
	public List<UsuariosComprasRFC10> RFC10Administrador(PersistenceManager pm,String fechaInicial, String fechaFinal, String codigoProducto, List<String> criteriosOrden, List<Boolean> ascendenciaCriterios)
	{		
		List<Comprador> lista=new ArrayList<>();
		String sentencia="SELECT nombre, identificador, correoelectronico, fecha, cantidadVendida, idProductoSucursal as CodigoProducto FROM COMPRADORES"
				+ " INNER JOIN (SELECT * FROM FACTURASCOMPRADOR INNER JOIN "
				+ "(SELECT * FROM PRODUCTOSVENDIDOS WHERE idProductoSucursal LIKE '%"+codigoProducto+"') ON FACTURASCOMPRADOR.numero=NoFactura WHERE "
				+ "FECHA between '"+fechaInicial+"' AND '"+fechaFinal+"') ON COMPRADORES.identificador=idComprador";
		if(!criteriosOrden.isEmpty()) {
			sentencia=sentencia+" ORDER BY";
			for(int i=0;i<criteriosOrden.size();i++)
			{
				String orden=ascendenciaCriterios.get(i)==true?"ASC":"DESC";
				if(i==0)
					sentencia=sentencia+" "+criteriosOrden.get(i)+" "+orden;
				else 
					sentencia=sentencia+", "+criteriosOrden.get(i)+" "+orden;

			}
		}
		
		Query q = pm.newQuery(SQL, sentencia);
		q.setResultClass(UsuariosComprasRFC10.class);
		return (List<UsuariosComprasRFC10>)q.executeList();

	}
	
	public List<Comprador> RFC11Gerente(PersistenceManager pm,String idSucursal,String fechaInicial, String fechaFinal, String codigoProducto, List<String> criteriosOrden, List<Boolean> ascendenciaCriterios)
	{		
		String sentencia="SELECT nombre, identificador, correoelectronico FROM COMPRADORES"
				+ " LEFT JOIN (SELECT * FROM FACTURASCOMPRADOR INNER JOIN "
				+ "(SELECT * FROM PRODUCTOSVENDIDOS WHERE idProductoSucursal LIKE '%"+codigoProducto+"') ON FACTURASCOMPRADOR.numero=NoFactura WHERE "
				+ "FECHA between '"+fechaInicial+"' AND '"+fechaFinal+"' AND idSucursal='"+idSucursal+"') ON COMPRADORES.identificador=idComprador "
				+ "WHERE idComprador IS NULL";
		
		if(!criteriosOrden.isEmpty()) {
			sentencia=sentencia+" ORDER BY";
			for(int i=0;i<criteriosOrden.size();i++)
			{
				String orden=ascendenciaCriterios.get(i)==true?"ASC":"DESC";
				if(i==0)
					sentencia=sentencia+" "+criteriosOrden.get(i)+" "+orden;
				else 
					sentencia=sentencia+", "+criteriosOrden.get(i)+" "+orden;

			}
		}
		
		Query q = pm.newQuery(SQL, sentencia);
		q.setResultClass(Comprador.class);
		return (List<Comprador>)q.executeList();

	}
	
	public List<Comprador> RFC11Administrador(PersistenceManager pm,String fechaInicial, String fechaFinal, String codigoProducto, List<String> criteriosOrden, List<Boolean> ascendenciaCriterios)
	{		
		String sentencia="SELECT nombre, identificador, correoelectronico FROM COMPRADORES"
				+ " LEFT JOIN (SELECT * FROM FACTURASCOMPRADOR INNER JOIN "
				+ "(SELECT * FROM PRODUCTOSVENDIDOS WHERE idProductoSucursal LIKE '%"+codigoProducto+"') ON FACTURASCOMPRADOR.numero=NoFactura WHERE "
				+ "FECHA between '"+fechaInicial+"' AND '"+fechaFinal+"') ON COMPRADORES.identificador=idComprador "
				+ "WHERE idComprador IS NULL";
		if(!criteriosOrden.isEmpty()) {
			sentencia=sentencia+" ORDER BY";
			for(int i=0;i<criteriosOrden.size();i++)
			{
				String orden=ascendenciaCriterios.get(i)==true?"ASC":"DESC";
				if(i==0)
					sentencia=sentencia+" "+criteriosOrden.get(i)+" "+orden;
				else 
					sentencia=sentencia+", "+criteriosOrden.get(i)+" "+orden;

			}
		}
		
		Query q = pm.newQuery(SQL, sentencia);
		q.setResultClass(Comprador.class);
		return (List<Comprador>)q.executeList();

	}
	
	
	public int getAnioInicioOperacion(PersistenceManager pm) {
		Query q = pm.newQuery(SQL, "SELECT MIN(Anio) from(SELECT EXTRACT(year FROM FECHA)as Anio FROM FACTURASCOMPRADOR GROUP BY EXTRACT(year FROM FECHA))");
		int anio=Integer.parseInt(q.executeUnique()+"");
		return anio;
	}
	public int getUltimoAnioOperacion(PersistenceManager pm) {
		Query q = pm.newQuery(SQL, "SELECT MAX(Anio) from(SELECT EXTRACT(year FROM FECHA)as Anio FROM FACTURASCOMPRADOR GROUP BY EXTRACT(year FROM FECHA))");
		int anio=Integer.parseInt(q.executeUnique()+"");
		return anio;
	}

	public int getMesInicioOperacion(PersistenceManager pm,int anioInicio) {
		Query q = pm.newQuery(SQL, "SELECT MIN(mes) from(SELECT EXTRACT(month FROM FECHA)as mes FROM FACTURASCOMPRADOR WHERE EXTRACT(year FROM FECHA)='"+anioInicio+"' GROUP BY EXTRACT(month FROM FECHA))");
		int mes=Integer.parseInt(q.executeUnique()+"");
		return mes;
	}
	public int getUltimoMesOperacion(PersistenceManager pm,int anioFin) {
		Query q = pm.newQuery(SQL, "SELECT MAX(mes) from(SELECT EXTRACT(month FROM FECHA)as mes FROM FACTURASCOMPRADOR WHERE EXTRACT(year FROM FECHA)='"+anioFin+"' GROUP BY EXTRACT(month FROM FECHA))");
		int mes=Integer.parseInt(q.executeUnique()+"");
		return mes;
	}
	public int getBuenosClientesCompraCadaMesAleter(PersistenceManager pm) {
		int anioInicio=getAnioInicioOperacion(pm);
		int anioFin=getUltimoAnioOperacion(pm);
		int mesInicio=getMesInicioOperacion(pm,anioInicio);
		int mesFin=getUltimoMesOperacion(pm,anioFin);
		int aniosCompletos= anioFin-anioInicio-1;
		int meses=0;
		if(aniosCompletos==-1) meses=mesFin-mesInicio+1;
		else if(aniosCompletos==0)meses=mesFin+(12-mesInicio+1);
		else meses=(aniosCompletos*12)+mesFin+(12-mesInicio+1);
		return 0;

	}
	
	public int getNumeroMesesOperacion(PersistenceManager pm) {
		Query q = pm.newQuery(SQL, "select COUNT(*) FROM(SELECT EXTRACT(month FROM FECHA)as mes,EXTRACT(year FROM FECHA)as Anio FROM FACTURASCOMPRADOR GROUP BY EXTRACT(year FROM FECHA),EXTRACT(month FROM FECHA))");
		int mes=Integer.parseInt(q.executeUnique()+"");
		return mes;
	}
	
	
	public List<Comprador> RFC13BuenosClientesCompraCadaMes(PersistenceManager pm) {
		
		int mesesOperacion=getNumeroMesesOperacion(pm);
		mesesOperacion=5;
		Query q = pm.newQuery(SQL, "SELECT * FROM COMPRADORES WHERE identificador IN (SELECT idComprador FROM(SELECT COUNT(idComprador)as mesesComprados, idComprador FROM(SELECT EXTRACT(month FROM FECHA)as mes,EXTRACT(year FROM FECHA)as Anio, idComprador FROM FACTURASCOMPRADOR GROUP BY EXTRACT(year FROM FECHA),EXTRACT(month FROM FECHA),idComprador) GROUP BY idComprador)WHERE mesesComprados>="+mesesOperacion+")");
		q.setResultClass(Comprador.class);
		return (List<Comprador>)q.executeList();
	}
	
	public List<Comprador> RFC13BuenosClientesCompraProductosCostosos(PersistenceManager pm) {
		
		Query q = pm.newQuery(SQL, "SELECT * FROM COMPRADORES WHERE identificador IN(SELECT idComprador FROM FACTURASCOMPRADOR WHERE numero IN (SELECT NoFactura FROM PRODUCTOSVENDIDOS WHERE idProductoSucursal IN (SELECT idproductosucursal FROM PRODUCTOSSUCURSAL WHERE PRECIO >= 100000)) AND numero NOT IN (SELECT NoFactura FROM PRODUCTOSVENDIDOS WHERE idProductoSucursal IN (SELECT idproductosucursal FROM PRODUCTOSSUCURSAL WHERE PRECIO < 100000) AND idProductoSucursal NOT IN (SELECT idproductosucursal FROM PRODUCTOSSUCURSAL WHERE PRECIO >= 100000)))");
		q.setResultClass(Comprador.class);
		return (List<Comprador>)q.executeList();
	}
	
	public List<Comprador> RFC13BuenosClientesCompraTecnologiaHerramientas(PersistenceManager pm) {
		
		Query q = pm.newQuery(SQL, "SELECT * FROM COMPRADORES WHERE identificador IN(SELECT idComprador FROM FACTURASCOMPRADOR WHERE numero IN (SELECT NoFactura FROM PRODUCTOSVENDIDOS WHERE idProductoSucursal IN (SELECT idproductosucursal FROM PRODUCTOSSUCURSAL WHERE codigoProducto in (SELECT codigoBarras FROM PRODUCTOS WHERE tipoProducto IN (SELECT nombreTipo FROM TIPOPRODUCTO WHERE nombreCategoria='Tools' OR nombreCategoria='Electronics')))) AND numero NOT IN (SELECT NoFactura FROM PRODUCTOSVENDIDOS WHERE idProductoSucursal IN (SELECT idproductosucursal FROM PRODUCTOSSUCURSAL  WHERE codigoProducto in (SELECT codigoBarras FROM PRODUCTOS WHERE tipoProducto IN (SELECT nombreTipo FROM TIPOPRODUCTO WHERE nombreCategoria<>'Tools' AND nombreCategoria<>'Electronics'))) AND idProductoSucursal NOT IN (SELECT idproductosucursal FROM PRODUCTOSSUCURSAL WHERE codigoProducto in (SELECT codigoBarras FROM PRODUCTOS WHERE tipoProducto IN (SELECT nombreTipo FROM TIPOPRODUCTO WHERE nombreCategoria='Tools' OR nombreCategoria='Electronics')))))");
		q.setResultClass(Comprador.class);
		return (List<Comprador>)q.executeList();
	}
	
	
//	SELECT nombre , identificador,correoelectronico,fecha,cantidadVendida,idProductoSucursal as CodigoProducto FROM COMPRADORES  INNER JOIN 
//    (SELECT * FROM FACTURASCOMPRADOR INNER JOIN 
//        (SELECT * FROM PRODUCTOSVENDIDOS WHERE idProductoSucursal LIKE '%f1f1f1')
//        ON FACTURASCOMPRADOR.numero=NoFactura
//        WHERE FECHA between '26-10-2018' AND '31-10-2018')
//    ON COMPRADORES.identificador=idComprador ORDER BY cantidadVendida desc, identificador ASC;  
	
//	SELECT * FROM COMPRADORES WHERE identificador in (SELECT idComprador FROM FACTURASCOMPRADOR WHERE FECHA between '10-01-2019'
//			AND '12-01-2019' AND numero in (SELECT NoFactura FROM PRODUCTOSVENDIDOS WHERE idProductoSucursal LIKE '%f1f1f1'));
	
	
//	SELECT nombre , identificador,correoelectronico,fecha,cantidadVendida,idProductoSucursal as CodigoProducto FROM COMPRADORES  INNER JOIN 
//    (SELECT * FROM FACTURASCOMPRADOR INNER JOIN 
//        (SELECT * FROM PRODUCTOSVENDIDOS WHERE idProductoSucursal LIKE '%f1f1f1')
//        ON FACTURASCOMPRADOR.numero=NoFactura
//        WHERE FECHA between '26-10-2000' AND '31-10-3000')
//    ON COMPRADORES.identificador=idComprador GROUP BY nombre ORDER BY numero desc, identificador ASC; 
	
	
//	SELECT idProductoSucursal ,SUM(cantidadVendida)as cantidadVendida , to_char(to_date(fecha, 'dd-mm-yyyy'), 'ww') as numeroSemana
//    FROM FACTURASCOMPRADOR INNER JOIN PRODUCTOSVENDIDOS ON FACTURASCOMPRADOR.numero=NoFactura GROUP BY idProductoSucursal,to_char(to_date(fecha, 'dd-mm-yyyy'), 'ww');    

}
