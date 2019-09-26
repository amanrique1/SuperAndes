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

public class DialogoCrearProveedor extends JDialog implements ActionListener
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

	/**
	 * Etiqueta del nombre.
	 */
	private JLabel lblNombre;

	/**
	 * Campo de texto para el nombre.
	 */
	private JTextField txtNombre;

	/**
	 * Etiqueta de la ciudad
	 */
	private JLabel lblCorreo;

	/**
	 * Campo de texto para la ciudad.
	 */
	private JTextField txtCorreo;

	/**
	 * Etiqueta de la direccion
	 */
	private JLabel lblNit;

	/**
	 * Campo de texto para la direccion.
	 */
	private JTextField txtNit;

	private JLabel lblTelefono;

	/**
	 * Campo de texto para la direccion.
	 */
	private JTextField txtTelefono;


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
	public DialogoCrearProveedor( PersistenciaSuperAndes mundo )
	{
		this.mundo = mundo;
		setLayout( new BorderLayout( ) );
		setSize( 500, 300 );
		setTitle( "Agregar Proveedor" );
		setLocationRelativeTo( null );

		JPanel campos = new JPanel( );
		campos.setLayout( new GridLayout( 4, 2 ) );
		campos.setBorder( new EmptyBorder( 30, 30, 30, 30 ) );
		add( campos, BorderLayout.CENTER );

		lblNit = new JLabel( "NIT: " );
		campos.add( lblNit );
		txtNit = new JTextField( );
		campos.add( txtNit );
		lblNombre = new JLabel( "Nombre: " );
		campos.add( lblNombre );
		txtNombre = new JTextField( );
		campos.add( txtNombre );
		lblCorreo = new JLabel( "Correo: " );
		campos.add( lblCorreo );
		txtCorreo = new JTextField( );
		campos.add( txtCorreo );
		lblTelefono = new JLabel( "Telefono: " );
		campos.add( lblTelefono );
		txtTelefono = new JTextField( );
		campos.add( txtTelefono );




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
				mundo.agregarProveedor(txtNit.getText(), txtNombre.getText(), txtCorreo.getText(),Long.parseLong( txtTelefono.getText()));
				JOptionPane.showMessageDialog(this,txtNombre.getText()+" se agrego satisfactoriamente");
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
