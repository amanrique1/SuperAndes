package model;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;


public class Bodega
{


	private double capacidadVolumen;


	private double capacidadPeso;


	private String unidadesPeso;


	private String unidadesVolumen;

	private ArrayList<ProductoVenta> productos;

	private TipoProducto tipoProductos;

	private ArrayList<Promocion> promociones;
	
	public Bodega(Double pCapacidadVol, Double pCapacidadPeso, String pUnidadesPeso, String pUnidadesVolumen,TipoProducto pTipo)
	{
		productos=new ArrayList<ProductoVenta>();
		promociones=new ArrayList<Promocion>();
		capacidadVolumen=pCapacidadVol;
		capacidadPeso=pCapacidadPeso;
		unidadesPeso=pUnidadesPeso;
		unidadesVolumen=pUnidadesVolumen;
		tipoProductos=pTipo;
	}

	public double getCapacidadVolumen() {
		return capacidadVolumen;
	}

	public void setCapacidadVolumen(double capacidadVolumen) {
		this.capacidadVolumen = capacidadVolumen;
	}

	public double getCapacidadPeso() {
		return capacidadPeso;
	}

	public void setCapacidadPeso(double capacidadPeso) {
		this.capacidadPeso = capacidadPeso;
	}

	public String getUnidadesPeso() {
		return unidadesPeso;
	}

	public void setUnidadesPeso(String unidadesPeso) {
		this.unidadesPeso = unidadesPeso;
	}

	public String getUnidadesVolumen() {
		return unidadesVolumen;
	}

	public void setUnidadesVolumen(String unidadesVolumen) {
		this.unidadesVolumen = unidadesVolumen;
	}

	public ArrayList<ProductoVenta> getProductos() {
		return productos;
	}

	public void setProductos(ArrayList<ProductoVenta> productos) {
		this.productos = productos;
	}

	public void addProducto(ProductoVenta producto) {
		productos.add(producto);
	}

	public TipoProducto getTipoProductos() {
		return tipoProductos;
	}

	public void setTipoProductos(TipoProducto tipoProductos) {
		this.tipoProductos = tipoProductos;
	}

	public ArrayList<Promocion> getPromociones() {
		return promociones;
	}

	public void setPromociones(ArrayList<Promocion> promociones) {
		this.promociones = promociones;
	}

	public void addPromociones(Promocion promocion) {
		promociones.add(promocion);
	}



}

