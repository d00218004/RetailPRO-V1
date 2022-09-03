//    uniCenta oPOS  - Touch Friendly Point Of Sale
//    Copyright © 2009-2020 uniCenta
//    https://unicenta.com
//
//    This file is part of uniCenta oPOS
//
//    uniCenta oPOS is free software: you can redistribute it and/or modify
//    it under the terms of the GNU General Public License as published by
//    the Free Software Foundation, either version 3 of the License, or
//    (at your option) any later version.
//
//   uniCenta oPOS is distributed in the hope that it will be useful,
//    but WITHOUT ANY WARRANTY; without even the implied warranty of
//    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
//    GNU General Public License for more details.
//
//    You should have received a copy of the GNU General Public License
//    along with uniCenta oPOS.  If not, see <http://www.gnu.org/licenses/>.

package com.openbravo.pos.ticket;

import java.util.List;
import com.openbravo.basic.BasicException;
import com.openbravo.data.gui.ComboBoxValModel;
import com.openbravo.data.gui.ListQBFModelNumber;
import com.openbravo.data.loader.QBFCompareEnum;
import com.openbravo.data.loader.SentenceList;
import com.openbravo.data.user.EditorCreator;
import com.openbravo.editor.JEditorKeys;
import com.openbravo.editor.JEditorString;
import com.openbravo.pos.forms.AppLocal;
import com.openbravo.pos.forms.DataLogicSales;
import com.openbravo.basic.BasicException;
import com.openbravo.data.user.ListProvider;
import com.openbravo.data.user.ListProviderCreator;
import com.openbravo.pos.forms.AppLocal;
import com.openbravo.pos.forms.DataLogicSales;
import com.openbravo.pos.panels.JProductFinder;
import com.openbravo.pos.ticket.ProductFilterSales;
import com.openbravo.pos.ticket.ProductInfoExt;
import com.openbravo.pos.ticket.ProductRenderer;
import java.awt.*;
import java.awt.event.ActionEvent;
import javax.swing.JFrame;

/**
 *
 * @author JG uniCenta
 */
public class ProductFilterSales extends javax.swing.JPanel implements EditorCreator {
    
    private ProductInfoExt m_ReturnProduct;
    private ListProvider lpr;
    public final static int PRODUCT_ALL = 0;
    public final static int PRODUCT_NORMAL = 1;
    public final static int PRODUCT_AUXILIAR = 2;
    public final static int PRODUCT_BUNDLE = 3;    
    private Object dlSales;
    
    private SentenceList m_sentcat;
    private ComboBoxValModel m_CategoryModel;
    
    /** Creates new form ProductFilterSales
     * @param dlSales
     * @param jKeys */
    public ProductFilterSales(DataLogicSales dlSales, JEditorKeys jKeys) {
        initComponents();
        
        // El modelo de categorias
        m_sentcat = dlSales.getCategoriesList();
        m_CategoryModel = new ComboBoxValModel();           
        
//        m_jCboPriceBuy.setModel(new ListQBFModelNumber());
        m_jCboPriceBuy.setModel(ListQBFModelNumber.getMandatoryNumber());
        m_jPriceBuy.addEditorKeys(jKeys);
        
//        m_jCboPriceSell.setModel(new ListQBFModelNumber());
        m_jCboPriceSell.setModel(ListQBFModelNumber.getMandatoryNumber());
        m_jPriceSell.addEditorKeys(jKeys);
        
//        m_jtxtName.addEditorKeys(jKeys);
        
//        m_jtxtBarCode.addEditorKeys(jKeys);
    }
    
    /**
     *
     */
    public void activate() {
        barcode.setText("");
        name.setText("");
        m_jPriceBuy.reset();
        m_jPriceSell.reset();
//        m_jtxtName.activate();
        
        barcode.setEnabled(true);
        try {
            List catlist = m_sentcat.list();
            catlist.add(0, null);
            m_CategoryModel = new ComboBoxValModel(catlist);
            m_jCategory.setModel(m_CategoryModel);
        } catch (BasicException eD) {
            // no hay validacion
        }
    }
    
    /**
     *
     * @return
     * @throws BasicException
     */
    public Object createValue() throws BasicException {
        
        Object[] afilter = new Object[10];
        
        // Nombre
        if (name.getText() == null || name.getText().equals("")) {
            afilter[0] = QBFCompareEnum.COMP_NONE;
            afilter[1] = null;
        } else {
            afilter[0] = QBFCompareEnum.COMP_RE;
            afilter[1] = "%" + name.getText() + "%";
        }
        
        // Precio de compra
        afilter[3] = m_jPriceBuy.getDoubleValue();
        afilter[2] = afilter[3] == null ? QBFCompareEnum.COMP_NONE : m_jCboPriceBuy.getSelectedItem();

        // Precio de venta
        afilter[5] = m_jPriceSell.getDoubleValue();
        afilter[4] = afilter[5] == null ? QBFCompareEnum.COMP_NONE : m_jCboPriceSell.getSelectedItem();
        
        // Categoria
        if (m_CategoryModel.getSelectedKey() == null) {
            afilter[6] = QBFCompareEnum.COMP_NONE;
            afilter[7] = null;
        } else {
            afilter[6] = QBFCompareEnum.COMP_EQUALS;
            afilter[7] = m_CategoryModel.getSelectedKey();
        }
        
        // el codigo de barras
        if (barcode.getText() == null || barcode.getText().equals("")) {
            afilter[8] = QBFCompareEnum.COMP_NONE;
            afilter[9] = null;
        } else{
            afilter[8] = QBFCompareEnum.COMP_RE;
            afilter[9] = "%" + barcode.getText() + "%";
        }
        
        return afilter;
    } 
    
    public void actionPerformed(ActionEvent e) {
  String numberStr = barcode.getText();
  numberStr = numberStr.trim();
  double number = Double.parseDouble(numberStr);
  number *= 2;
  barcode.setText("n * 2 = " + String.format("%.2f", number));
}
    
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents()
    {

        jLabel5 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        m_jCategory = new javax.swing.JComboBox();
        jLabel4 = new javax.swing.JLabel();
        m_jCboPriceBuy = new javax.swing.JComboBox();
        m_jPriceBuy = new com.openbravo.editor.JEditorCurrency();
        jLabel3 = new javax.swing.JLabel();
        m_jCboPriceSell = new javax.swing.JComboBox();
        m_jPriceSell = new com.openbravo.editor.JEditorCurrency();
        jLabel1 = new javax.swing.JLabel();
        jBtnReset = new javax.swing.JButton();
        barcode = new javax.swing.JTextField();
        name = new javax.swing.JTextField();

        setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        setPreferredSize(new java.awt.Dimension(450, 240));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel5.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel5.setText(AppLocal.getIntString("label.prodname")); // NOI18N
        jLabel5.setPreferredSize(new java.awt.Dimension(120, 30));
        add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 47, -1, -1));

        jLabel2.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel2.setText(AppLocal.getIntString("label.prodcategory")); // NOI18N
        jLabel2.setPreferredSize(new java.awt.Dimension(120, 30));
        add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 84, -1, -1));

        m_jCategory.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        m_jCategory.setPreferredSize(new java.awt.Dimension(250, 30));
        add(m_jCategory, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 84, 300, -1));

        jLabel4.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel4.setText(AppLocal.getIntString("label.prodpricebuy")); // NOI18N
        jLabel4.setPreferredSize(new java.awt.Dimension(120, 30));
        add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 120, -1, -1));

        m_jCboPriceBuy.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        m_jCboPriceBuy.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                m_jCboPriceBuyActionPerformed(evt);
            }
        });
        add(m_jCboPriceBuy, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 120, 150, 30));

        m_jPriceBuy.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        m_jPriceBuy.setPreferredSize(null);
        add(m_jPriceBuy, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 120, 140, 30));

        jLabel3.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel3.setText(AppLocal.getIntString("label.prodpricesell")); // NOI18N
        jLabel3.setPreferredSize(new java.awt.Dimension(120, 30));
        add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 156, -1, -1));

        m_jCboPriceSell.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        add(m_jCboPriceSell, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 156, 150, 30));

        m_jPriceSell.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        m_jPriceSell.setPreferredSize(null);
        add(m_jPriceSell, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 156, 140, 30));

        jLabel1.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel1.setText(AppLocal.getIntString("label.prodbarcode")); // NOI18N
        jLabel1.setPreferredSize(new java.awt.Dimension(120, 30));
        add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 11, -1, -1));

        jBtnReset.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jBtnReset.setText("Reset");
        jBtnReset.setPreferredSize(new java.awt.Dimension(120, 45));
        jBtnReset.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                jBtnResetActionPerformed(evt);
            }
        });
        add(jBtnReset, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 190, 300, -1));

        barcode.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        barcode.setMargin(new java.awt.Insets(4, 4, 4, 4));
        barcode.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                barcodeActionPerformed(evt);
            }
        });
        add(barcode, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 10, 300, 31));

        name.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        name.setMargin(new java.awt.Insets(4, 4, 4, 4));
        name.setPreferredSize(new java.awt.Dimension(120, 30));
        add(name, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 48, 300, -1));
    }// </editor-fold>//GEN-END:initComponents

    private void m_jCboPriceBuyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_m_jCboPriceBuyActionPerformed

    }//GEN-LAST:event_m_jCboPriceBuyActionPerformed

    private void jBtnResetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnResetActionPerformed
        barcode.setText("");
        name.setText("");
        m_jCboPriceBuy.setSelectedIndex(0);
        m_jCboPriceSell.setSelectedIndex(0);
        m_jPriceBuy.setDoubleValue(null);
        m_jPriceSell.setDoubleValue(null);
        barcode.setEnabled(true);
    }//GEN-LAST:event_jBtnResetActionPerformed

    private void barcodeActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_barcodeActionPerformed
    {//GEN-HEADEREND:event_barcodeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_barcodeActionPerformed

    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField barcode;
    private javax.swing.JButton jBtnReset;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JComboBox m_jCategory;
    private javax.swing.JComboBox m_jCboPriceBuy;
    private javax.swing.JComboBox m_jCboPriceSell;
    private com.openbravo.editor.JEditorCurrency m_jPriceBuy;
    private com.openbravo.editor.JEditorCurrency m_jPriceSell;
    private javax.swing.JTextField name;
    // End of variables declaration//GEN-END:variables
    
}
