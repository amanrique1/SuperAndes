/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uniandes.isis2304.superAndes.interfazApp;

import java.util.List;

import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import main.java.model.IndiceBodega;
import main.java.model.ProductoRFC12;
import main.java.model.Productos;
import main.java.model.Proveedor;
import main.java.model.ProveedorRF12;

/**
 *
 * @author Andres
 */
public class PanelProveedorRFC12 extends javax.swing.JPanel implements ListSelectionListener{
	
	private List<ProveedorRF12>provMayor;
	private List<ProveedorRF12>provMenor;

    /**
     * Creates new form PanelProductos
     */
    public PanelProveedorRFC12(List<ProveedorRF12>productos,List<ProveedorRF12>productos2) {
    	this.provMayor=productos;
    	provMenor=productos2;
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblImagen = new javax.swing.JLabel();
        scrollList = new javax.swing.JScrollPane();
        listSemanas = new javax.swing.JList<>();
        lblTitulo = new javax.swing.JLabel();
        lblProducto = new javax.swing.JLabel();
        lblValProd1 = new javax.swing.JLabel();
        lblCantidad = new javax.swing.JLabel();
        lblValCant1 = new javax.swing.JLabel();
        lblSemana = new javax.swing.JLabel();
        lblValSemana1 = new javax.swing.JLabel();
        lblImagen1 = new javax.swing.JLabel();
        lblProducto2 = new javax.swing.JLabel();
        lblValProd2 = new javax.swing.JLabel();
        lblSemana2 = new javax.swing.JLabel();
        lblCantidad2 = new javax.swing.JLabel();
        lblValSemana2 = new javax.swing.JLabel();
        lblValCant2 = new javax.swing.JLabel();
        lblMenor = new javax.swing.JLabel();
        lblMayor = new javax.swing.JLabel();
        lblValIdProv1 = new javax.swing.JLabel();
        lblIdProv2 = new javax.swing.JLabel();
        lblValIdProv2 = new javax.swing.JLabel();
        lblIdProv1 = new javax.swing.JLabel();

        lblImagen.setIcon(new javax.swing.ImageIcon(getClass().getResource("/uniandes/isis2304/superAndes/interfazApp/dataInterfaz/productos.png"))); // NOI18N
        lblImagen.setText("\n");

        listSemanas.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        scrollList.setViewportView(listSemanas);

        lblTitulo.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        lblTitulo.setForeground(new java.awt.Color(255, 0, 0));
        lblTitulo.setText("Proveedores");

        lblProducto.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        lblProducto.setText("Proveedor:");

        lblValProd1.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        lblValProd1.setText(" ");

        lblCantidad.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        lblCantidad.setText("Cantidad:");

        lblValCant1.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        lblValCant1.setText(" ");

        lblSemana.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        lblSemana.setText("Semana:");

        lblValSemana1.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        lblValSemana1.setText(" ");

        lblImagen1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/uniandes/isis2304/superAndes/interfazApp/dataInterfaz/bodega.png"))); // NOI18N
        lblImagen1.setText("\n");

        lblProducto2.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        lblProducto2.setText("Producto:");

        lblValProd2.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        lblValProd2.setText(" ");

        lblSemana2.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        lblSemana2.setText("Semana:");

        lblCantidad2.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        lblCantidad2.setText("Cantidad:");

        lblValSemana2.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        lblValSemana2.setText(" ");

        lblValCant2.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        lblValCant2.setText(" ");

        lblMenor.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        lblMenor.setForeground(new java.awt.Color(51, 153, 255));
        lblMenor.setText("Menor");

        lblMayor.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        lblMayor.setForeground(new java.awt.Color(51, 153, 255));
        lblMayor.setText("Mayor");

        lblValIdProv1.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        lblValIdProv1.setText(" ");

        lblIdProv2.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        lblIdProv2.setText("idProvedor:");

        lblValIdProv2.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        lblValIdProv2.setText(" ");

        lblIdProv1.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        lblIdProv1.setText("idProvedor:");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lblTitulo)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 359, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(9, 9, 9)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(109, 109, 109)
                                .addComponent(lblImagen1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(lblCantidad)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(lblValCant1))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(lblProducto)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(lblValProd1))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(lblSemana)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(lblValSemana1))
                                    .addComponent(lblMayor)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(lblIdProv1)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(lblValIdProv1)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(lblIdProv2)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(lblValIdProv2))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(lblCantidad2)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(lblValCant2))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(lblProducto2)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(lblValProd2))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(lblSemana2)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(lblValSemana2))
                                    .addComponent(lblMenor))
                                .addGap(112, 112, 112)))))
                .addComponent(scrollList, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(59, 59, 59))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(scrollList)
                .addGap(30, 30, 30))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(lblTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lblMayor)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblProducto)
                            .addComponent(lblValProd1))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblCantidad)
                            .addComponent(lblValCant1))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblSemana)
                            .addComponent(lblValSemana1)))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lblMenor)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblProducto2)
                            .addComponent(lblValProd2))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblCantidad2)
                            .addComponent(lblValCant2))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblSemana2)
                            .addComponent(lblValSemana2))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 9, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lblIdProv1)
                        .addComponent(lblValIdProv1))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lblIdProv2)
                        .addComponent(lblValIdProv2)))
                .addGap(18, 18, 18)
                .addComponent(lblImagen1))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel lblCantidad;
    private javax.swing.JLabel lblCantidad2;
    private javax.swing.JLabel lblIdProv1;
    private javax.swing.JLabel lblIdProv2;
    private javax.swing.JLabel lblImagen;
    private javax.swing.JLabel lblImagen1;
    private javax.swing.JLabel lblMayor;
    private javax.swing.JLabel lblMenor;
    private javax.swing.JLabel lblProducto;
    private javax.swing.JLabel lblProducto2;
    private javax.swing.JLabel lblSemana;
    private javax.swing.JLabel lblSemana2;
    private javax.swing.JLabel lblTitulo;
    private javax.swing.JLabel lblValCant1;
    private javax.swing.JLabel lblValCant2;
    private javax.swing.JLabel lblValIdProv1;
    private javax.swing.JLabel lblValIdProv2;
    private javax.swing.JLabel lblValProd1;
    private javax.swing.JLabel lblValProd2;
    private javax.swing.JLabel lblValSemana1;
    private javax.swing.JLabel lblValSemana2;
    private javax.swing.JList<String> listSemanas;
    private javax.swing.JScrollPane scrollList;
    // End of variables declaration//GEN-END:variables

	@Override
	public void valueChanged(ListSelectionEvent e) {
		// TODO Auto-generated method stub
		int semana=listSemanas.getSelectedIndex();
		
		lblValCant1.setText(provMayor.get(semana).getCantidadVendida()+"");
		lblValProd1.setText(provMayor.get(semana).getProveedor());
		lblValSemana1.setText(provMayor.get(semana).getNumeroSemana()+"");
		lblValIdProv1.setText(provMayor.get(semana).getIdProveedor()+"");
		lblValCant2.setText(provMenor.get(semana).getCantidadVendida()+"");
		lblValProd2.setText(provMenor.get(semana).getProveedor());
		lblValSemana2.setText(provMenor.get(semana).getNumeroSemana()+"");
		lblValIdProv2.setText(provMenor.get(semana).getIdProveedor()+"");
		
	}
}
