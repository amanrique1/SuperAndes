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

public class DialogoCrearProducto extends JDialog implements ActionListener
{


	// -----------------------------------------------------------------
	// Constantes
	// -----------------------------------------------------------------


	/**
	 * Comando para agregar el proveedor.
	 */
	public final static String AGREGAR = "Agregar Proveedor";

	/**
	 * Comando para cancelar el proceso.
	 */
	public final static String CANCELAR = "Cancelar";

	// -----------------------------------------------------------------
	// Atributos
	// -----------------------------------------------------------------

	/**
	 * Instancia principal de la aplicaci�n.
	 */
	private PersistenciaSuperAndes mundo;

	// -----------------------------------------------------------------
	// Atributos de la interfaz
	// -----------------------------------------------------------------


	private JLabel lblCodigoBarras;

	private JTextField txtCodigoBarras;
	
	private JLabel lblNombre;

	private JTextField txtNombre;
	
	private JLabel lblMarca;

	private JTextField txtMarca;

	private JLabel lblPresentacion;

	private JTextField txtPresentacion;
	
	private JLabel lblUnidadPeso;

	private JTextField txtUnidadPeso;
	
	private JLabel lblCantidadPeso;

	private JTextField txtCantidadPeso;
	
	private JLabel lblUnidadVolumen;

	private JTextField txtUnidadVolumen;
	
	private JLabel lblCantidadVolumen;

	private JTextField txtCantidadVolumen;
	
	private JLabel lblTipoProducto;

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
	public DialogoCrearProducto( PersistenciaSuperAndes mundo )
	{
		this.mundo = mundo;
		setLayout( new BorderLayout( ) );
		setSize( 500, 300 );
		setTitle( "Agregar Producto" );
		setLocationRelativeTo( null );

		JPanel campos = new JPanel( );
		campos.setLayout( new GridLayout( 9, 2 ) );
		campos.setBorder( new EmptyBorder( 30, 30, 30, 30 ) );
		add( campos, BorderLayout.CENTER );

		lblCodigoBarras = new JLabel( "Codigo de barras: " );
		campos.add( lblCodigoBarras );
		txtCodigoBarras = new JTextField( );
		campos.add( txtCodigoBarras );
		lblNombre = new JLabel( "Nombre: " );
		campos.add( lblNombre );
		txtNombre = new JTextField( );
		campos.add( txtNombre );
		lblMarca = new JLabel( "Marca: " );
		campos.add( lblMarca );
		txtMarca = new JTextField( );
		campos.add( txtMarca );
		lblPresentacion = new JLabel( "Presentacion: " );
		campos.add( lblPresentacion );
		txtPresentacion = new JTextField( );
		campos.add( txtPresentacion );
		lblUnidadPeso = new JLabel( "Unidad peso: " );
		campos.add( lblUnidadPeso );
		txtUnidadPeso = new JTextField( );
		campos.add( txtUnidadPeso );
		lblCantidadPeso = new JLabel( "Cantidad Peso: " );
		campos.add( lblCantidadPeso );
		txtCantidadPeso = new JTextField( );
		campos.add( txtCantidadPeso );
		lblUnidadVolumen = new JLabel( "Unidad volumen: " );
		campos.add( lblUnidadVolumen );
		txtUnidadVolumen = new JTextField( );
		campos.add( txtUnidadVolumen );		
		lblCantidadVolumen = new JLabel( "Cantidad volumen: " );
		campos.add( lblCantidadVolumen );
		txtCantidadVolumen = new JTextField( );
		campos.add( txtCantidadVolumen );
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
				mundo.agregarProducto(txtCodigoBarras.getText(), txtNombre.getText(), txtMarca.getText(), txtPresentacion.getText(), txtUnidadPeso.getText(), Double.parseDouble(txtCantidadPeso.getText()), txtUnidadVolumen.getText(), Double.parseDouble(txtCantidadVolumen.getText()), txtTipoProducto.getText());
				JOptionPane.showMessageDialog(this,"El producto con codigo "+txtCodigoBarras.getText()+" se agrego satisfactoriamente");
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
