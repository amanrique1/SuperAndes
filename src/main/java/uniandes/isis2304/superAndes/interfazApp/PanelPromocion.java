/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uniandes.isis2304.superAndes.interfazApp;

import java.util.List;

import javax.swing.JPanel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import main.java.model.Productos;
import main.java.model.Promocion;

/**
 *
 * @author Andres
 */
public class PanelPromocion extends JPanel implements ListSelectionListener{

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel lblDescripcion;
    private javax.swing.JLabel lblFechaFin;
    private javax.swing.JLabel lblFechaIni;
    private javax.swing.JLabel lblImagen;
    private javax.swing.JLabel lblTipo;
    private javax.swing.JLabel lblTitulo;
    private javax.swing.JLabel lblValFechaFin;
    private javax.swing.JLabel lblValFechaIni;
    private javax.swing.JLabel lblValTipo;
    private javax.swing.JList listPromos;
    private javax.swing.JScrollPane scrollList;
    // End of variables declaration//GEN-END:variables
    
    private List<Promocion>promos;
    
    private Promocion promoAct;
    
    /**
     * Creates new form PanelPromocion
     */
    public PanelPromocion(List<Promocion>promos) {
    	this.promos=promos;
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

        scrollList = new javax.swing.JScrollPane();
        listPromos = new javax.swing.JList<>();
        lblTitulo = new javax.swing.JLabel();
        lblTipo = new javax.swing.JLabel();
        lblValTipo = new javax.swing.JLabel();
        lblFechaIni = new javax.swing.JLabel();
        lblValFechaIni = new javax.swing.JLabel();
        lblFechaFin = new javax.swing.JLabel();
        lblValFechaFin = new javax.swing.JLabel();
        lblDescripcion = new javax.swing.JLabel();
        lblImagen = new javax.swing.JLabel();

        listPromos.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        scrollList.setViewportView(listPromos);

        lblTitulo.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        lblTitulo.setForeground(new java.awt.Color(255, 0, 0));
        lblTitulo.setText("Promociones Mas Populares");

        lblTipo.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        lblTipo.setText("Tipo:");

        lblValTipo.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        lblValTipo.setText("jLabel2");

        lblFechaIni.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        lblFechaIni.setText("Fecha Inicial");

        lblValFechaIni.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        lblValFechaIni.setText("jLabel2");

        lblFechaFin.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        lblFechaFin.setText("Fecha Final:");

        lblValFechaFin.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        lblValFechaFin.setText("jLabel2");

        lblImagen.setIcon(new javax.swing.ImageIcon(getClass().getResource("/uniandes/isis2304/superAndes/interfazApp/dataInterfaz/promoociones.png"))); // NOI18N
        lblImagen.setText("\n");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(24, 24, 24)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblTitulo)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                        .addComponent(lblFechaFin)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(lblValFechaFin))
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                            .addComponent(lblFechaIni)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                            .addComponent(lblValFechaIni))
                                        .addGroup(layout.createSequentialGroup()
                                            .addComponent(lblTipo)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                            .addComponent(lblValTipo)))))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(49, 49, 49)
                        .addComponent(lblDescripcion, javax.swing.GroupLayout.PREFERRED_SIZE, 224, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblImagen)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(scrollList, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(scrollList))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(24, 24, 24)
                        .addComponent(lblTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblTipo)
                            .addComponent(lblValTipo))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblFechaIni)
                            .addComponent(lblValFechaIni))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblFechaFin)
                            .addComponent(lblValFechaFin))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(79, 79, 79)
                                .addComponent(lblImagen))
                            .addGroup(layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(lblDescripcion, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addGap(30, 30, 30))
        );
    }// </editor-fold>//GEN-END:initComponents

	@Override
	public void valueChanged(ListSelectionEvent e) {
		// TODO Auto-generated method stub
		promoAct = ( Promocion )listPromos.getSelectedValue( );
		if( promoAct != null )
		{
			lblValFechaFin.setText(promoAct.getFechaFinal().toString());
			lblValFechaIni.setText(promoAct.getFechaFinal().toString());
			lblValTipo.setText(promoAct.getTipoPromocion().toString());
			lblDescripcion.setText(promoAct.getDescripcion());
		}
		
	}


  
}
