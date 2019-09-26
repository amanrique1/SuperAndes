package uniandes.isis2304.superAndes.interfazApp;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import uniandes.isis2304.superAndes.persistencia.PersistenciaSuperAndes;

public class DialogoCrearBodEst extends JDialog implements ActionListener
{


	// -----------------------------------------------------------------
	// Constantes
	// -----------------------------------------------------------------


	/**
	 * Comando para agregar el proveedor.
	 */
	public final static String AGREGAR = "Agregar";


	/**
	 * Comando para cancelar el proceso.
	 */
	public final static String CANCELAR = "Cancelar";

	public final static String ESTANTE="Estante";

	public final static String BODEGA="Bodega";

	// -----------------------------------------------------------------
	// Atributos
	// -----------------------------------------------------------------

	/**
	 * Instancia principal de la aplicaci�n.
	 */
	private PersistenciaSuperAndes mundo;

	private String aCrear;

	// -----------------------------------------------------------------
	// Atributos de la interfaz
	// -----------------------------------------------------------------

	/**
	 * Etiqueta del nombre.
	 */
	private JLabel lblCapacidadVolumen;

	/**
	 * Campo de texto para el nombre.
	 */
	private JTextField txtCapacidadVolumen;

	/**
	 * Etiqueta de la direccion
	 */
	private JLabel lblUnidadVolumen;

	/**
	 * Campo de texto para la direccion.
	 */
	private JTextField txtUnidadVolumen;

	/**
	 * Etiqueta de la ciudad
	 */
	private JLabel lblCapacidadPeso;

	/**
	 * Campo de texto para la ciudad.
	 */
	private JTextField txtCapacidadPeso;

	/**
	 * Etiqueta de la direccion
	 */
	private JLabel lblUnidadPeso;

	/**
	 * Campo de texto para la direccion.
	 */
	private JTextField txtUnidadPeso;

	private JLabel lblNivelReOrden;

	/**
	 * Campo de texto para la direccion.
	 */
	private JTextField txtNiverlReOrden;

	private JLabel lblIdSucursal;

	/**
	 * Campo de texto para la direccion.
	 */
	private JTextField txtIdSucursal;

	private JLabel lblTipoProducto;

	/**
	 * Campo de texto para la direccion.
	 */
	private JTextField txtTipoProducto;


	/**
	 * Bot�n para agregar.
	 */
	private JButton btnAgregar;

	/**
	 * Bot�n para cancelar el proceso.
	 */
	private JButton btnCancelar;

	// -----------------------------------------------------------------
	// Constructores
	// -----------------------------------------------------------------

	/**
	 * Crea la ventana de di�logo de la banda.
	 * @param pPrincipal Instancia principal de la aplicaci�n.
	 */
	public DialogoCrearBodEst( PersistenciaSuperAndes mundo,String aCrear )
	{
		this.mundo = mundo;
		this.aCrear=aCrear;
		setLayout( new BorderLayout( ) );
		setSize( 500, 300 );
		setTitle( "Agregar "+aCrear );
		setLocationRelativeTo( null );

		JPanel campos = new JPanel( );
		campos.setLayout( new GridLayout( 7, 2 ) );
		campos.setBorder( new EmptyBorder( 30, 30, 30, 30 ) );
		add( campos, BorderLayout.CENTER );

		lblCapacidadPeso = new JLabel( "Capacidad peso: " );
		campos.add( lblCapacidadPeso );
		txtCapacidadPeso = new JTextField( );
		campos.add( txtCapacidadPeso );

		lblUnidadPeso = new JLabel( "Unidad peso: " );
		campos.add( lblUnidadPeso );
		txtUnidadPeso = new JTextField( );
		campos.add( txtUnidadPeso );

		lblCapacidadVolumen = new JLabel( "Capacidad Volumen: " );
		campos.add( lblCapacidadVolumen );
		txtCapacidadVolumen = new JTextField( );
		campos.add( txtCapacidadVolumen );

		lblUnidadVolumen = new JLabel( "Unidad volumen: " );
		campos.add( lblUnidadVolumen );
		txtUnidadVolumen = new JTextField( );
		campos.add( txtUnidadVolumen );

		lblNivelReOrden = new JLabel( "Nivel ReOrden: " );
		campos.add( lblNivelReOrden );
		txtNiverlReOrden = new JTextField( );
		campos.add( txtNiverlReOrden );

		lblIdSucursal = new JLabel( "Id Sucursal: " );
		campos.add( lblIdSucursal );
		txtIdSucursal = new JTextField( );
		campos.add( txtIdSucursal );

		lblTipoProducto = new JLabel( "Tipo producto: " );
		campos.add( lblTipoProducto );
		txtTipoProducto = new JTextField( );
		campos.add( txtTipoProducto );




		JPanel botones = new JPanel( );
		botones.setBorder( new EmptyBorder( 0, 30, 20, 30 ) );
		botones.setLayout( new GridLayout( 1, 2 ) );
		add( botones, BorderLayout.SOUTH );

		btnAgregar = new JButton( "Agregar" );
		btnAgregar.setActionCommand( AGREGAR );
		btnAgregar.addActionListener( this );
		botones.add( btnAgregar );

		btnCancelar = new JButton( "Cancelar" );
		btnCancelar.addActionListener( this );
		btnCancelar.setActionCommand( CANCELAR );
		botones.add( btnCancelar );
	}

	// -----------------------------------------------------------------
	// M�todos
	// -----------------------------------------------------------------

	/**
	 * Manejo de eventos del usuario.
	 * @param pEvento Evento de usuario. pEvento != null.
	 */
	public void actionPerformed( ActionEvent pEvento )
	{
		String comando = pEvento.getActionCommand( );

		if( comando.equals( AGREGAR ) )
		{
			try
			{
				if(aCrear.equals(ESTANTE))
				{
					mundo.agregarEstante(Double.parseDouble(txtCapacidadVolumen.getText()), Double.parseDouble(txtCapacidadPeso.getText()), txtUnidadPeso.getText(), txtUnidadVolumen.getText(), Long.parseLong(txtIdSucursal.getText()), Double.parseDouble(txtNiverlReOrden.getText()), txtTipoProducto.getText());
					JOptionPane.showMessageDialog(this,"El estante se agrego satisfactoriamente");
				}
				else {
					mundo.agregarBodega(Double.parseDouble(txtCapacidadVolumen.getText()), Double.parseDouble(txtCapacidadPeso.getText()), txtUnidadPeso.getText(), txtUnidadVolumen.getText(), Long.parseLong(txtIdSucursal.getText()), Double.parseDouble(txtNiverlReOrden.getText()), txtTipoProducto.getText());
					JOptionPane.showMessageDialog(this,"El estante se agrego satisfactoriamente");
				}
					
				this.dispose();
			}catch (Exception e) {
				JOptionPane.showMessageDialog(this,"Verifica los datos");
			}
		}
		else if( comando.equals( CANCELAR ) )
		{
			this.dispose( );
		}


	}

}
