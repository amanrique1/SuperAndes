/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uniandes.isis2304.superAndes.interfazApp;

import java.awt.JobAttributes;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileReader;

import javax.swing.JOptionPane;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.stream.JsonReader;
import javax.swing.JLabel;
import javax.swing.JPanel;

import main.java.model.Sucursal;
import uniandes.isis2304.superAndes.persistencia.PersistenciaSuperAndes;

/**
 *
 * @author Andres
 */
public class InterfazLoginEmpleados extends javax.swing.JFrame implements ActionListener{

	/* ****************************************************************
	 * 			Constantes
	 *****************************************************************/

	/**
	 * Ruta al archivo de configuraciÃƒÂ³n de los nombres de tablas de la base de datos
	 */
	private static final String CONFIG_TABLAS = "./src/main/resources/config/TablasBD_A.json"; 

	/* ****************************************************************
	 * 			Atributos
	 *****************************************************************/
	/**
	 * Objeto JSON con los nombres de las tablas de la base de datos que se quieren utilizar
	 */
	private JsonObject tableConfig;

	private uniandes.isis2304.superAndes.persistencia.PersistenciaSuperAndes persistencia;

	/**
	 * Creates new form InterfazEmpleados
	 */
	public InterfazLoginEmpleados() {
		tableConfig = openConfig ("Tablas BD", CONFIG_TABLAS);
		persistencia = uniandes.isis2304.superAndes.persistencia.PersistenciaSuperAndes.getInstance (tableConfig);
		setResizable( false );
		setLocationRelativeTo(null);
		initComponents();
	}

	/**
	 * Lee datos de configuraciÃƒÂ³n para la aplicaciÃƒÂ³, a partir de un archivo JSON o con valores por defecto si hay errores.
	 * @param tipo - El tipo de configuraciÃƒÂ³n deseada
	 * @param archConfig - Archivo Json que contiene la configuraciÃƒÂ³n
	 * @return Un objeto JSON con la configuraciÃƒÂ³n del tipo especificado
	 * 			NULL si hay un error en el archivo.
	 */
	private JsonObject openConfig (String tipo, String archConfig)
	{
		JsonObject config = null;
		try 
		{
			Gson gson = new Gson( );
			FileReader file = new FileReader (archConfig);
			JsonReader reader = new JsonReader ( file );
			config = gson.fromJson(reader, JsonObject.class);
		} 
		catch (Exception e)
		{
			e.printStackTrace ();
			System.out.println("No se encontron un archivo de configuracion de interfaz valido: " + tipo);
		}	
		return config;
	}


	/**
	 * This method is called from within the constructor to initialize the form.
	 * WARNING: Do NOT modify this code. The content of this method is always
	 * regenerated by the Form Editor.
	 */
	@SuppressWarnings("unchecked")
	// <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
	private void initComponents() {

		lblLogo = new javax.swing.JLabel();
		lblSucursal = new javax.swing.JLabel();
		lblTitulo = new javax.swing.JLabel();
		txtIdSucursal = new javax.swing.JTextField();
		lblIdSucursal = new javax.swing.JLabel();
		btnIngresar = new java.awt.Button();
		btnRegistrar = new java.awt.Button();

		setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

		lblLogo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/uniandes/isis2304/superAndes/interfazApp/dataInterfaz/logo.png"))); // NOI18N

		lblSucursal.setIcon(new javax.swing.ImageIcon(getClass().getResource("/uniandes/isis2304/superAndes/interfazApp/dataInterfaz/sucursal.png"))); // NOI18N

		lblTitulo.setFont(new java.awt.Font("Times New Roman", 1, 36)); // NOI18N
		lblTitulo.setText("Ingreso Sucursal");



		lblIdSucursal.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
		lblIdSucursal.setText("idSucursal");

		btnIngresar.setActionCommand("INGRESAR\n");
		btnIngresar.setLabel("Ingresar");
		btnIngresar.addActionListener(this);

		btnRegistrar.setActionCommand("REGISTRAR_NUEVA");
		btnRegistrar.setLabel("Registrar Nueva");
		btnRegistrar.addActionListener(this);

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
		getContentPane().setLayout(layout);
		layout.setHorizontalGroup(
				layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(layout.createSequentialGroup()
						.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
								.addGroup(layout.createSequentialGroup()
										.addGap(99, 99, 99)
										.addComponent(lblLogo))
								.addGroup(layout.createSequentialGroup()
										.addContainerGap()
										.addComponent(lblSucursal, javax.swing.GroupLayout.PREFERRED_SIZE, 226, javax.swing.GroupLayout.PREFERRED_SIZE)
										.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
												.addGroup(layout.createSequentialGroup()
														.addGap(27, 27, 27)
														.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
																.addComponent(lblTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, 267, javax.swing.GroupLayout.PREFERRED_SIZE)
																.addGroup(layout.createSequentialGroup()
																		.addComponent(lblIdSucursal)
																		.addGap(28, 28, 28)
																		.addComponent(txtIdSucursal, javax.swing.GroupLayout.PREFERRED_SIZE, 189, javax.swing.GroupLayout.PREFERRED_SIZE))))
												.addGroup(layout.createSequentialGroup()
														.addGap(64, 64, 64)
														.addComponent(btnIngresar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
														.addGap(66, 66, 66)
														.addComponent(btnRegistrar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))))
						.addContainerGap(24, Short.MAX_VALUE))
				);
		layout.setVerticalGroup(
				layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(layout.createSequentialGroup()
						.addContainerGap()
						.addComponent(lblLogo)
						.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
								.addGroup(layout.createSequentialGroup()
										.addGap(33, 33, 33)
										.addComponent(lblSucursal))
								.addGroup(layout.createSequentialGroup()
										.addGap(18, 18, 18)
										.addComponent(lblTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
										.addGap(18, 18, 18)
										.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
												.addComponent(txtIdSucursal, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
												.addComponent(lblIdSucursal))
										.addGap(47, 47, 47)
										.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
												.addComponent(btnIngresar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
												.addComponent(btnRegistrar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
						.addContainerGap(55, Short.MAX_VALUE))
				);

		pack();
	}// </editor-fold>//GEN-END:initComponents

	private void txtIdSucursalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtIdSucursalActionPerformed
		// TODO add your handling code here:
	}//GEN-LAST:event_txtIdSucursalActionPerformed

	private void btnIngresarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnIngresarActionPerformed
		// TODO add your handling code here:

	}//GEN-LAST:event_btnIngresarActionPerformed

	/**
	 * @param args the command line arguments
	 */
	public static void main(String args[]) {
		/* Set the Nimbus look and feel */
		//<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
		/* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
		 * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
		 */
		try {
			for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
				if ("Nimbus".equals(info.getName())) {
					javax.swing.UIManager.setLookAndFeel(info.getClassName());
					break;
				}
			}
		} catch (ClassNotFoundException ex) {
			java.util.logging.Logger.getLogger(InterfazLoginEmpleados.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		} catch (InstantiationException ex) {
			java.util.logging.Logger.getLogger(InterfazLoginEmpleados.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		} catch (IllegalAccessException ex) {
			java.util.logging.Logger.getLogger(InterfazLoginEmpleados.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		} catch (javax.swing.UnsupportedLookAndFeelException ex) {
			java.util.logging.Logger.getLogger(InterfazLoginEmpleados.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		}
		//</editor-fold>
		//</editor-fold>

		/* Create and display the form */
		java.awt.EventQueue.invokeLater(new Runnable() {
			public void run() {
				new InterfazLoginEmpleados().setVisible(true);
			}
		});
	}

	
	/**
	 * Manejo de eventos del usuario.
	 * @param pEvento Evento de usuario. pEvento != null.
	 */
	public void actionPerformed( ActionEvent pEvento )
	{
		String comando = pEvento.getActionCommand( );

		if( comando.equals( "INGRESAR\n" ) )
		{
			try
			{
				long idSucursal=Long.parseLong(txtIdSucursal.getText());
				Sucursal sucur=persistencia.darSucursalPorId(idSucursal);
				if(sucur!=null)
				{
					JOptionPane.showMessageDialog(this, "Bienvenido a "+sucur.getNombre());
					this.dispose();
					InterfazEmpleados emp=new InterfazEmpleados(persistencia,sucur.getIdSucursal());
					emp.setVisible(true);
				}
				else
				{
					JOptionPane.showMessageDialog(this, "Revisa el id");
				}
			}
			catch (Exception e) {
				JOptionPane.showMessageDialog(this, "Revisa el id");
			}
		}
		else if( comando.equals( "REGISTRAR_NUEVA" ) )
		{
			DialogoCrearSucursal dialog=new DialogoCrearSucursal(persistencia);
			dialog.setVisible(true);
		}


	}
	// Variables declaration - do not modify//GEN-BEGIN:variables
	private java.awt.Button btnIngresar;
	private java.awt.Button btnRegistrar;
	private javax.swing.JLabel lblIdSucursal;
	private javax.swing.JLabel lblLogo;
	private javax.swing.JLabel lblSucursal;
	private javax.swing.JLabel lblTitulo;
	private javax.swing.JTextField txtIdSucursal;
        private JPanel panel;
	// End of variables declaration//GEN-END:variables
}
