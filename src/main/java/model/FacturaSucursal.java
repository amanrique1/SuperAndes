package main.java.model;

public class FacturaSucursal {
	
	private Long idFactura;
	
	private Long idSucursal;
	
	public FacturaSucursal(Long pIdFactura,Long pIdSucursal)
	{
		
		idFactura=pIdFactura;
		idSucursal=pIdSucursal;
		
	}

	/**
	 * @return the idSucursal
	 */
	public Long getIdSucursal() {
		return idSucursal;
	}

	/**
	 * @param idSucursal the idSucursal to set
	 */
	public void setIdSucursal(Long idSucursal) {
		this.idSucursal = idSucursal;
	}

}
