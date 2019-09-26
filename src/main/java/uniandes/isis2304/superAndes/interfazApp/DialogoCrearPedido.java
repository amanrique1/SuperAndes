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

import main.java.model.EstadoPedido;
import main.java.model.TipoProducto;
import main.java.model.TipoPromocion;
import uniandes.isis2304.superAndes.persistencia.PersistenciaSuperAndes;

public class DialogoCrearPedido extends JDialog implements ActionListener
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
	private JLabel lblFechaAcordada;

	/**
	 * Campo de texto para el nombre.
	 */
	private JTextField txtFechaAcordada;


	/**
	 * Etiqueta de la ciudad
	 */
	private JLabel lblEstado;

	/**
	 * Campo de texto para la ciudad.
	 */
	private JTextField txtEstado;

	/**
	 * Etiqueta de la direccion
	 */
	private JLabel lblIdSucursal;

	/**
	 * Campo de texto para la direccion.
	 */
	private JTextField txtIdSucursal;

	private JLabel lblIdProveedor;

	/**
	 * Campo de texto para la direccion.
	 */
	private JTextField txtIdProveedor;




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
	public DialogoCrearPedido( PersistenciaSuperAndes mundo )
	{
		this.mundo = mundo;
		setLayout( new BorderLayout( ) );
		setSize( 500, 300 );
		setTitle( "Agregar Pedido" );
		setLocationRelativeTo( null );

		JPanel campos = new JPanel( );
		campos.setLayout( new GridLayout( 4, 2 ) );
		campos.setBorder( new EmptyBorder( 30, 30, 30, 30 ) );
		add( campos, BorderLayout.CENTER );

		lblFechaAcordada = new JLabel( "Fecha acordada: " );
		campos.add( lblFechaAcordada );
		txtFechaAcordada = new JTextField( );
		campos.add( txtFechaAcordada );

		lblEstado = new JLabel( "Estado: " );
		campos.add( lblEstado );
		txtEstado = new JTextField( );
		campos.add( txtEstado );

		lblIdSucursal = new JLabel( "Id Sucursal: " );
		campos.add( lblIdSucursal );
		txtIdSucursal = new JTextField( );
		campos.add( txtIdSucursal );

		lblIdProveedor = new JLabel( "Id Proveedor: " );
		campos.add( lblIdProveedor );
		txtIdProveedor = new JTextField( );
		campos.add( txtIdProveedor );



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
				String estado=txtEstado.getText();
				estado=estado.toUpperCase();
				estado=estado.replace(" ", "_");
				EstadoPedido pEstado=EstadoPedido.valueOf(estado);
				mundo.requerimiento9(txtFechaAcordada.getText(), pEstado,Long.parseLong( txtIdSucursal.getText()), txtIdProveedor.getText());
				JOptionPane.showMessageDialog(this,"La promocion se agrego satisfactoriamente");
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
