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
import main.java.model.Pedido;

/**
 *
 * @author Andres
 */
public class PanelPedido extends javax.swing.JPanel implements  ListSelectionListener{

	private List<Pedido> pedidos;
	
	private Pedido pedidoAct;
	
    /**
     * Creates new form PanelPedido
     */
    public PanelPedido(List<Pedido>pedidos) {
    	this.pedidos=pedidos;
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

        lblEstado = new javax.swing.JLabel();
        lblValEstado = new javax.swing.JLabel();
        lblFechaEntregada = new javax.swing.JLabel();
        lblValFechaEntre = new javax.swing.JLabel();
        scrollList = new javax.swing.JScrollPane();
        listPedidos = new javax.swing.JList<>();
        lblImagen1 = new javax.swing.JLabel();
        lblIdProveedor = new javax.swing.JLabel();
        lblValIdProv = new javax.swing.JLabel();
        lblTitulo = new javax.swing.JLabel();
        lblIdSucursal = new javax.swing.JLabel();
        lblIdPedido = new javax.swing.JLabel();
        lblValIdSuc = new javax.swing.JLabel();
        lblValIdEstante = new javax.swing.JLabel();
        lblFechaAcor = new javax.swing.JLabel();
        lblValFecAcor = new javax.swing.JLabel();

        lblEstado.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        lblEstado.setText("Estado:");

        lblValEstado.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        lblValEstado.setText(" ");

        lblFechaEntregada.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        lblFechaEntregada.setText("Fecha Entregada:");

        lblValFechaEntre.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        lblValFechaEntre.setText(" ");

        listPedidos.addListSelectionListener( this );
        listPedidos.setListData(pedidos.toArray());
        listPedidos.setToolTipText("Pedidos");
        scrollList.setViewportView(listPedidos);

        lblImagen1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/uniandes/isis2304/superAndes/interfazApp/dataInterfaz/pedido.png"))); // NOI18N
        lblImagen1.setText("\n");

        lblIdProveedor.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        lblIdProveedor.setText("Id Proveedor:");

        lblValIdProv.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        lblValIdProv.setText(" ");

        lblTitulo.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        lblTitulo.setForeground(new java.awt.Color(255, 0, 0));
        lblTitulo.setText("Compras a proveedores");

        lblIdSucursal.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        lblIdSucursal.setText("Id Sucursal:");

        lblIdPedido.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        lblIdPedido.setText("Id:");

        lblValIdSuc.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        lblValIdSuc.setText(" ");

        lblValIdEstante.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        lblValIdEstante.setText(" ");

        lblFechaAcor.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        lblFechaAcor.setText("Fecha Acordada:");

        lblValFecAcor.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        lblValFecAcor.setText(" ");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblTitulo)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(lblFechaAcor)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(lblValFecAcor))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(lblIdPedido)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(lblValIdEstante))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(lblEstado)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(lblValEstado))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(lblFechaEntregada)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(lblValFechaEntre))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                        .addComponent(lblIdProveedor)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(lblValIdProv))
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                        .addComponent(lblIdSucursal)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(lblValIdSuc)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(lblImagen1)))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 23, Short.MAX_VALUE)
                .addComponent(scrollList, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(17, Short.MAX_VALUE))
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
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblIdPedido)
                    .addComponent(lblValIdEstante))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblFechaAcor)
                    .addComponent(lblValFecAcor))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblFechaEntregada)
                    .addComponent(lblValFechaEntre))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblEstado)
                    .addComponent(lblValEstado))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblIdSucursal)
                            .addComponent(lblValIdSuc))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblIdProveedor)
                            .addComponent(lblValIdProv))
                        .addGap(153, 153, 153))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lblImagen1)
                        .addGap(21, 21, 21))))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel lblEstado;
    private javax.swing.JLabel lblFechaAcor;
    private javax.swing.JLabel lblFechaEntregada;
    private javax.swing.JLabel lblIdPedido;
    private javax.swing.JLabel lblIdProveedor;
    private javax.swing.JLabel lblIdSucursal;
    private javax.swing.JLabel lblImagen1;
    private javax.swing.JLabel lblTitulo;
    private javax.swing.JLabel lblValEstado;
    private javax.swing.JLabel lblValFecAcor;
    private javax.swing.JLabel lblValFechaEntre;
    private javax.swing.JLabel lblValIdEstante;
    private javax.swing.JLabel lblValIdProv;
    private javax.swing.JLabel lblValIdSuc;
    private javax.swing.JList listPedidos;
    private javax.swing.JScrollPane scrollList;
    // End of variables declaration//GEN-END:variables

	@Override
	public void valueChanged(ListSelectionEvent arg0) {
		// TODO Auto-generated method stub
		pedidoAct = ( Pedido )listPedidos.getSelectedValue( );
		if( pedidoAct != null )
		{
			lblValEstado.setText(pedidoAct.getEstado().toString());
			lblValFecAcor.setText(pedidoAct.getIdPedido().toString());
			lblValFechaEntre.setText(pedidoAct.getFechaEntrega().toString());
			lblValIdProv.setText(pedidoAct.getIdProveedor());
			lblValIdSuc.setText(pedidoAct.getIdSucursal()+"");
		}
	}
}
