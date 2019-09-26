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

import main.java.model.TipoProducto;
import main.java.model.TipoPromocion;
import uniandes.isis2304.superAndes.persistencia.PersistenciaSuperAndes;

public class DialogoCrearPromocion extends JDialog implements ActionListener
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
	private JLabel lblTipoPromocion;

	/**
	 * Campo de texto para el nombre.
	 */
	private JTextField txtTipoPromocion;

	/**
	 * Etiqueta de la direccion
	 */
	private JLabel lblFechaIni;

	/**
	 * Campo de texto para la direccion.
	 */
	private JTextField txtFechaIni;

	/**
	 * Etiqueta de la ciudad
	 */
	private JLabel lblX;

	/**
	 * Campo de texto para la ciudad.
	 */
	private JTextField txtX;

	/**
	 * Etiqueta de la direccion
	 */
	private JLabel lblY;

	/**
	 * Campo de texto para la direccion.
	 */
	private JTextField txtY;

	private JLabel lblDescripcion;

	/**
	 * Campo de texto para la direccion.
	 */
	private JTextField txtDescripcion;

	private JLabel lblFechaFin;

	/**
	 * Campo de texto para la direccion.
	 */
	private JTextField txtFechaFin;


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
	public DialogoCrearPromocion( PersistenciaSuperAndes mundo )
	{
		this.mundo = mundo;
		setLayout( new BorderLayout( ) );
		setSize( 500, 300 );
		setTitle( "Agregar Promocion" );
		setLocationRelativeTo( null );

		JPanel campos = new JPanel( );
		campos.setLayout( new GridLayout( 6, 2 ) );
		campos.setBorder( new EmptyBorder( 30, 30, 30, 30 ) );
		add( campos, BorderLayout.CENTER );

		lblTipoPromocion = new JLabel( "Tipo promocion: " );
		campos.add( lblTipoPromocion );
		txtTipoPromocion = new JTextField( );
		campos.add( txtTipoPromocion );

		lblFechaIni = new JLabel( "Fecha inicial: " );
		campos.add( lblFechaIni );
		txtFechaIni = new JTextField( );
		campos.add( txtFechaIni );

		lblFechaFin = new JLabel( "Fecha final: " );
		campos.add( lblFechaFin );
		txtFechaFin = new JTextField( );
		campos.add( txtFechaFin );

		lblX = new JLabel( "Cantidad menor: " );
		campos.add( lblX );
		txtX = new JTextField( );
		campos.add( txtX );

		lblY = new JLabel( "Cantidad mayor: " );
		campos.add( lblY );
		txtY = new JTextField( );
		campos.add( txtY );

		lblDescripcion = new JLabel( "Descripcion: " );
		campos.add( lblDescripcion );
		txtDescripcion = new JTextField( );
		campos.add( txtDescripcion );



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
				String tipo=txtTipoPromocion.getText();
				tipo=tipo.toUpperCase();
				tipo=tipo.replace(" ", "_");
				TipoPromocion pTipo=TipoPromocion.valueOf(tipo);
				mundo.agregarPromocion(txtDescripcion.getText(), txtFechaIni.getText(), txtFechaFin.getText(), Integer.parseInt(txtX.getText()),Integer.parseInt( txtY.getText()), pTipo);
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
