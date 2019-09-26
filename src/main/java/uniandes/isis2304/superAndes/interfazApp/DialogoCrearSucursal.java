
package uniandes.isis2304.superAndes.interfazApp;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import uniandes.isis2304.superAndes.persistencia.PersistenciaSuperAndes;




public class DialogoCrearSucursal extends JDialog implements ActionListener
{

	// -----------------------------------------------------------------
	// Constantes
	// -----------------------------------------------------------------


	/**
	 * Comando para agregar la banda.
	 */
	public final static String AGREGAR = "Agregar Sucursal";

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
	private JLabel lblCiudad;

	/**
	 * Campo de texto para la ciudad.
	 */
	private JTextField txtCiudad;

	/**
	 * Etiqueta de la direccion
	 */
	private JLabel lblDireccion;

	/**
	 * Campo de texto para la direccion.
	 */
	private JTextField txtDireccion;


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
	 * @param mundo Instancia principal de la aplicaci�n.
	 */
	public DialogoCrearSucursal( PersistenciaSuperAndes mundo )
	{
		this.mundo = mundo;
		setLayout( new BorderLayout( ) );
		setSize( 500, 300 );
		setTitle( "Agregar Sucursal" );
		setLocationRelativeTo( null );

		JPanel campos = new JPanel( );
		campos.setLayout( new GridLayout( 3, 2 ) );
		campos.setBorder( new EmptyBorder( 30, 30, 30, 30 ) );
		add( campos, BorderLayout.CENTER );

		lblNombre = new JLabel( "Nombre: " );
		campos.add( lblNombre );
		txtNombre = new JTextField( );
		campos.add( txtNombre );
		lblCiudad = new JLabel( "Ciudad: " );
		campos.add( lblCiudad );
		txtCiudad = new JTextField( );
		campos.add( txtCiudad );
		lblDireccion = new JLabel( "Direccion: " );
		campos.add( lblDireccion );
		txtDireccion = new JTextField( );
		campos.add( txtDireccion );


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
			try {
				mundo.agregarSucursal(txtNombre.getText(), txtCiudad.getText(), txtDireccion.getText());
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
