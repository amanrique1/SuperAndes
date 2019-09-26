/**
 * 
 */
package uniandes.isis2304.superAndes.persistencia;

import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import main.java.model.PromocionesVendidas;


/**
 * @author Andres
 *
 */
public class SQLPromocionesVendidas {

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
	public SQLPromocionesVendidas (PersistenciaSuperAndes pp,SQLFacturasComprador pSqlFacturas,SQLProductosSucursal pSqlProductosSucursal)
	{
		this.pp = pp;
		sqlFacturas=pSqlFacturas;
		sqlProductosSucursal=pSqlProductosSucursal;
	}


	public void agregarPromocionesVendidas (PersistenceManager pm,long noFactura,int cantidad, long idPromocion) throws Exception 
	{
		if(sqlFacturas.darFacturaCompradorPorNumero(pm, noFactura)==null||sqlProductosSucursal.darPromocionSucursal(pm, idPromocion)==null)
		{
			throw new Exception("Datos Invalidos");
		}
		Query q1 = pm.newQuery(SQL, "INSERT INTO " + pp.darTablaPromocionesVendidas() + "(noFactura, cantidad, idPromocion) values (?, ?, ?)");
		q1.setParameters(  noFactura,  cantidad,idPromocion);
		q1.executeUnique();
	}


	public void eliminarPromocionesVendidasVacios (PersistenceManager pm)
	{

		Query q2 = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaPromocionesVendidas () + " WHERE CantidadVendida = 0");
		q2.executeUnique();

	}

	public void disminuirPromocionesVendidasDeFacturaEnCantidad (PersistenceManager pm,long noFactura,int cant,long idPromocion)
	{

		Query q3 = pm.newQuery(SQL, "UPDATE " + pp.darTablaPromocionesVendidas () + " SET cantidad=cantidad-? WHERE noFactura=? AND idPromocion=?");
		q3.setParameters( cant,noFactura ,idPromocion);
		q3.executeUnique();

	}

	public void aumentarPromocionesVendidasDeFacturaEnCantidad (PersistenceManager pm,long noFactura,int cant,long idPromocion)
	{

		Query q3 = pm.newQuery(SQL, "UPDATE " + pp.darTablaPromocionesVendidas () + " SET cantidad=cantidad+? WHERE noFactura=? AND idPromocion=?");
		q3.setParameters( cant,noFactura ,idPromocion);
		q3.executeUnique();

	}

	public int darCantidadPromocionesVendidasDeFactura (PersistenceManager pm, long noFactura, long idPromocion )
	{
		Query q = pm.newQuery(SQL, "SELECT cantidad FROM "+ pp.darTablaFacturasCompradores()+ " WHERE noFactura=? AND idPromocion=?");
		q.setParameters(noFactura,idPromocion);
		return (int) q.executeUnique();

	}

	public PromocionesVendidas darProductoVendido (PersistenceManager pm, long noFactura, long idPromocion )
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM "+ pp.darTablaPromocionesVendidas()+ " WHERE noFactura=? AND idPromocion=?");
		q.setResultClass(PromocionesVendidas.class);
		q.setParameters(noFactura,idPromocion);
		return (PromocionesVendidas) q.executeUnique();

	}

	public List<PromocionesVendidas> darPromocionesVendidas (PersistenceManager pm, long noFactura)
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM "+ pp.darTablaPromocionesVendidas()+ " WHERE noFactura= ?");
		q.setResultClass(PromocionesVendidas.class);
		q.setParameters(noFactura);
		return (List<PromocionesVendidas>) q.executeList();

	}



	public List<PromocionesVendidas> darPromocionesSucursal (PersistenceManager pm, long idSucursal)
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM "+ pp.darTablaFacturasCompradores()+ " WHERE idSucursal= ?");
		q.setResultClass(PromocionesVendidas.class);
		q.setParameters(idSucursal);
		return (List<PromocionesVendidas>) q.executeList();

	}

}
