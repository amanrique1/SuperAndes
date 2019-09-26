package uniandes.isis2304.superAndes.persistencia;

import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import main.java.model.ProductoRFC12;
import main.java.model.Proveedor;
import main.java.model.ProveedorRF12;
import main.java.model.Sucursal;

public class SQLProveedor {
	
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
	public SQLProveedor (PersistenciaSuperAndes pp)
	{
		this.pp = pp;
	}
	
	
	public void agregarProveedor (PersistenceManager pm,String pNit,String pNombre,String pCorreo,long pTel) 
	{
		Query q1 = pm.newQuery(SQL, "INSERT INTO " + pp.darTablaProveedor() + "(NIT, nombre, correoElectronico, telefono) values (?, ?, ?, ?)");
		q1.setParameters(pNit,pNombre,pCorreo,pTel);
		q1.executeUnique();
	}

	
	public void eliminarProveedorPorNombre (PersistenceManager pm, String nombre)
	{
		
        Query q2 = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaProveedor () + " WHERE nombre = ?");
        q2.setParameters(nombre);
        q2.executeUnique();
	}

	public void eliminarProveedorPorNit (PersistenceManager pm, String nit)
	{
		
        Query q2 = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaProveedor () + " WHERE NIT = ?");
        q2.setParameters(nit);
        q2.executeUnique();
       
	}

	public Proveedor darProveedorPorNit (PersistenceManager pm, String identificador) 
	{
		Proveedor p=new Proveedor();
		p.setNit(darNit(pm,identificador));
		p.setNombre(darNombre(pm,identificador));
		p.setCorreoElectronico(darCorreo(pm,identificador));

		return p;
	}
	
	public String darNombre(PersistenceManager pm, String identificador)
	{
		Query q1 = pm.newQuery(SQL, "SELECT nombre FROM " + pp.darTablaProveedor () + " WHERE nit ='"+identificador+"'");
		return q1.executeUnique()+"";
		
	}
	public String darNit(PersistenceManager pm, String identificador)
	{
		Query q1 = pm.newQuery(SQL, "SELECT nit FROM " + pp.darTablaProveedor () + " WHERE nit ='"+identificador+"'");
		return q1.executeUnique()+"";
		
	}
	public String darCorreo(PersistenceManager pm, String identificador)
	{
		Query q1 = pm.newQuery(SQL, "SELECT correoElectronico FROM " + pp.darTablaProveedor () + " WHERE nit ='"+identificador+"'");
		return q1.executeUnique()+"";
		
	}
	
	public boolean existeProveedor(PersistenceManager pm, String identificador)
	{
		Query q1 = pm.newQuery(SQL, "SELECT nit FROM " + pp.darTablaProveedor () + " WHERE nit ='"+identificador+"'");
		String comp=q1.executeUnique()+"";
		if(comp.equals(identificador))
			return true;
		return false;
	}
	
	public Proveedor darProveedorPorNombre (PersistenceManager pm, String nombre) 
	{
		Query q1 = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaProveedor () + " WHERE nombre = ?");
		q1.setResultClass(Proveedor.class);
		q1.setParameters(nombre);
		Proveedor comp=(Proveedor)q1.executeUnique();
		return comp;
	}

	
	public List<Proveedor> darProveedores (PersistenceManager pm)
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM "+ pp.darTablaProveedor());
		q.setResultClass(Proveedor.class);
		return (List<Proveedor>) q.executeList();
		
	}
	
	
	public List<ProveedorRF12> RFC12MasSolicitado(PersistenceManager pm)
	{		
		String sentencia="SELECT tabla1.cantidadvendida, \r\n" + 
				"       tabla1.numerosemana, \r\n" + 
				"       tabla2.idproveedor, \r\n" + 
				"       tabla2.nombre AS proveedor \r\n" + 
				"FROM   (SELECT numerosemana, \r\n" + 
				"                Max(cantidadvendida)AS cantidadVendida \r\n" + 
				"         FROM   (SELECT idproveedor, \r\n" + 
				"                        Count (idproveedor)                                AS \r\n" + 
				"                        cantidadVendida, \r\n" + 
				"                        To_char(To_date(fechaentrega, 'dd-mm-yyyy'), 'ww') AS \r\n" + 
				"                        numeroSemana \r\n" + 
				"                 FROM   proveedor \r\n" + 
				"                        inner join pedido \r\n" + 
				"                                ON nit = idproveedor \r\n" + 
				"                 GROUP  BY idproveedor, \r\n" + 
				"                           To_char(To_date(fechaentrega, 'dd-mm-yyyy'), 'ww')) \r\n" + 
				"         GROUP  BY numerosemana) tabla1\r\n" + 
				"       left join (SELECT idproveedor, \r\n" + 
				"                Count(idproveedor)                                 AS \r\n" + 
				"                cantidadVendida \r\n" + 
				"                , \r\n" + 
				"                nombre, \r\n" + 
				"                To_char(To_date(fechaentrega, 'dd-mm-yyyy'), 'ww') AS \r\n" + 
				"                numeroSemana \r\n" + 
				"         FROM   proveedor \r\n" + 
				"                inner join pedido \r\n" + 
				"                        ON nit = idproveedor \r\n" + 
				"         GROUP  BY idproveedor, \r\n" + 
				"                   To_char(To_date(fechaentrega, 'dd-mm-yyyy'), 'ww'), \r\n" + 
				"                   nombre) tabla2 \r\n" + 
				"              ON tabla1.cantidadvendida = tabla2.cantidadvendida \r\n" + 
				"                 AND tabla1.numerosemana = tabla2.numerosemana \r\n" + 
				"ORDER  BY tabla1.numerosemana ";
		
		Query q = pm.newQuery(SQL, sentencia);
		q.setResultClass(ProveedorRF12.class);
		return (List<ProveedorRF12>)q.executeList();

	}
	
	public List<ProveedorRF12> RFC12MenosSolicitado(PersistenceManager pm)
	{		
		String sentencia="\r\n" + 
				"SELECT tabla1.cantidadvendida, \r\n" + 
				"       tabla1.numerosemana, \r\n" + 
				"       tabla2.idproveedor, \r\n" + 
				"       tabla2.nombre AS proveedor \r\n" + 
				"FROM   (SELECT numerosemana, \r\n" + 
				"                Min(cantidadvendida)AS cantidadVendida \r\n" + 
				"         FROM   (SELECT idproveedor, \r\n" + 
				"                        Count (idproveedor)                                AS \r\n" + 
				"                        cantidadVendida, \r\n" + 
				"                        To_char(To_date(fechaentrega, 'dd-mm-yyyy'), 'ww') AS \r\n" + 
				"                        numeroSemana \r\n" + 
				"                 FROM   proveedor \r\n" + 
				"                        inner join pedido \r\n" + 
				"                                ON nit = idproveedor \r\n" + 
				"                 GROUP  BY idproveedor, \r\n" + 
				"                           To_char(To_date(fechaentrega, 'dd-mm-yyyy'), 'ww')) \r\n" + 
				"         GROUP  BY numerosemana)tabla1 \r\n" + 
				"       left join (SELECT idproveedor, \r\n" + 
				"                Count(idproveedor)                                 AS \r\n" + 
				"                cantidadVendida \r\n" + 
				"                , \r\n" + 
				"                nombre, \r\n" + 
				"                To_char(To_date(fechaentrega, 'dd-mm-yyyy'), 'ww') AS \r\n" + 
				"                numeroSemana \r\n" + 
				"         FROM   proveedor \r\n" + 
				"                inner join pedido \r\n" + 
				"                        ON nit = idproveedor \r\n" + 
				"         GROUP  BY idproveedor, \r\n" + 
				"                   To_char(To_date(fechaentrega, 'dd-mm-yyyy'), 'ww'), \r\n" + 
				"                   nombre) tabla2 \r\n" + 
				"              ON tabla1.cantidadvendida = tabla2.cantidadvendida \r\n" + 
				"                 AND tabla1.numerosemana = tabla2.numerosemana \r\n" + 
				"ORDER  BY tabla1.numerosemana ";
		
		Query q = pm.newQuery(SQL, sentencia);
		q.setResultClass(ProveedorRF12.class);
		return (List<ProveedorRF12>)q.executeList();

	}


}
