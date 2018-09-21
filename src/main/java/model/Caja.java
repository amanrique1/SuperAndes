package model;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class Caja
{
	
	
	private ArrayList<Factura> facturas;

	public Caja(){
		facturas=new ArrayList<Factura>();
	}

	
	public ArrayList<Factura> getFacturas() {
		return facturas;
	}


	public void setFacturas(ArrayList<Factura> facturas) {
		this.facturas = facturas;
	}


	
}

