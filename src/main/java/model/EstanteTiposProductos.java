/**
 * 
 */
package main.java.model;

/**
 * @author Andres
 *
 */
public class EstanteTiposProductos {

private String tipoProducto;

private Long idEstante;

public EstanteTiposProductos(String pTipoProducto,Long pIdEstante)
{
	tipoProducto=pTipoProducto;
	idEstante=pIdEstante;
}

/**
 * @return the tipoProducto
 */
public String getTipoProducto() {
	return tipoProducto;
}

/**
 * @param tipoProducto the tipoProducto to set
 */
public void setTipoProducto(String tipoProducto) {
	this.tipoProducto = tipoProducto;
}

/**
 * @return the idEstante
 */
public Long getIdEstante() {
	return idEstante;
}

/**
 * @param idEstante the idEstante to set
 */
public void setIdEstante(Long idEstante) {
	this.idEstante = idEstante;
}
	
}
