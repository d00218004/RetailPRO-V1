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
package com.openbravo.pos.payment;

import com.openbravo.format.Formats;
import com.openbravo.pos.customers.CustomerInfoExt;
import com.openbravo.pos.forms.AppLocal;
import com.openbravo.pos.util.RoundUtils;
import java.awt.Component;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

/**
 *
 * @author  adrianromero
 */
public class JPaymentDebt extends javax.swing.JPanel implements JPaymentInterface {

    private JPaymentNotifier notifier;
    private CustomerInfoExt customerext;
    private double m_dPaid;
    private double m_dTotal;

    /** Creates new form JPaymentDebt
     * @param notifier */
    public JPaymentDebt(JPaymentNotifier notifier) {

        this.notifier = notifier;

        initComponents();

        m_jTendered.addPropertyChangeListener("Edition", new RecalculateState());
        m_jTendered.addEditorKeys(m_jKeys);

    }

    /**
     *
     * @param customerext
     * @param dTotal
     * @param transID
     */
    @Override
    public void activate(CustomerInfoExt customerext, double dTotal, String transID) {

        this.customerext = customerext;
        m_dTotal = dTotal;

        m_jTendered.reset();

        if (customerext == null) {
            m_jName.setText(null);
            m_jNotes.setText(null);
            txtMaxdebt.setText(null);
            txtCurdate.setText(null);
            txtCurdebt.setText(null);

            m_jKeys.setEnabled(false);
            m_jTendered.setEnabled(false);


        } else {
            m_jName.setText(customerext.getName());
            m_jNotes.setText(customerext.getNotes());
            txtMaxdebt.setText(Formats.CURRENCY.formatValue(RoundUtils.getValue(customerext.getMaxdebt())));
            txtCurdate.setText(Formats.DATE.formatValue(customerext.getCurdate()));
            txtCurdebt.setText(Formats.CURRENCY.formatValue(RoundUtils.getValue(customerext.getAccdebt())));

            if (RoundUtils.compare(RoundUtils.getValue(customerext.getAccdebt()), RoundUtils.getValue(customerext.getMaxdebt())) >= 0) {
                m_jKeys.setEnabled(false);
                m_jTendered.setEnabled(false);
            } else {
                m_jKeys.setEnabled(true);
                m_jTendered.setEnabled(true);
                m_jTendered.activate();
            }
        }

        printState();

    }

    /**
     *
     * @return
     */
    @Override
    public PaymentInfo executePayment() {
        return new PaymentInfoTicket(m_dPaid, "debt");
    }

    /**
     *
     * @return
     */
    @Override
    public Component getComponent() {
        return this;
    }

    private void printState() {

        if (customerext == null) {
            m_jMoneyEuros.setText(null);
            jlblMessage.setText(AppLocal.getIntString("message.nocustomernodebt"));
            notifier.setStatus(false, false);
        } else {
            Double value = m_jTendered.getDoubleValue();
            if (value == null || value == 0.0) {
                m_dPaid = m_dTotal;
            } else {
                m_dPaid = value;
            }

            m_jMoneyEuros.setText(Formats.CURRENCY.formatValue(m_dPaid));


//            if (RoundUtils.compare(RoundUtils.getValue(customerext.getAccdebt()) + m_dPaid, 
//                    RoundUtils.getValue(customerext.getMaxdebt())) >= 0) {
            if ((customerext.getAccdebt() + RoundUtils.round(m_dPaid)) - (customerext.getMaxdebt()) > 0) {
                jlblMessage.setText(AppLocal.getIntString("message.customerdebtexceded"));
                notifier.setStatus(false, false);
            } else {
                jlblMessage.setText(null);
                int iCompare = RoundUtils.compare(m_dPaid, m_dTotal);
                // if iCompare > 0 then the payment is not valid
                notifier.setStatus(m_dPaid > 0.0 && iCompare <= 0, iCompare == 0);
            }
        }
    }

    private class RecalculateState implements PropertyChangeListener {

        @Override
        public void propertyChange(PropertyChangeEvent evt) {
            printState();
        }
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents()
    {

        jPanel5 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        m_jMoneyEuros = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        m_jName = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtMaxdebt = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txtCurdebt = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        txtCurdate = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        m_jNotes = new javax.swing.JTextArea();
        jlblMessage = new javax.swing.JTextArea();
        jPanel6 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        m_jKeys = new com.openbravo.editor.JEditorKeys();
        m_jTendered = new com.openbravo.editor.JEditorCurrencyPositive();

        setLayout(new java.awt.BorderLayout());

        jPanel5.setLayout(new java.awt.BorderLayout());

        jLabel8.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel8.setText(AppLocal.getIntString("label.debt")); // NOI18N
        jLabel8.setPreferredSize(new java.awt.Dimension(100, 30));

        m_jMoneyEuros.setBackground(new java.awt.Color(255, 255, 255));
        m_jMoneyEuros.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        m_jMoneyEuros.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        m_jMoneyEuros.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        m_jMoneyEuros.setMaximumSize(new java.awt.Dimension(170, 170));
        m_jMoneyEuros.setName(""); // NOI18N
        m_jMoneyEuros.setOpaque(true);
        m_jMoneyEuros.setPreferredSize(new java.awt.Dimension(100, 30));

        jLabel3.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel3.setText(AppLocal.getIntString("label.name")); // NOI18N
        jLabel3.setPreferredSize(new java.awt.Dimension(100, 30));

        m_jName.setEditable(false);
        m_jName.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        m_jName.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        m_jName.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        m_jName.setMaximumSize(new java.awt.Dimension(170, 170));
        m_jName.setName(""); // NOI18N
        m_jName.setOpaque(false);
        m_jName.setPreferredSize(new java.awt.Dimension(100, 30));

        jLabel12.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel12.setText(AppLocal.getIntString("label.notes")); // NOI18N
        jLabel12.setPreferredSize(new java.awt.Dimension(100, 30));

        jLabel2.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel2.setText(AppLocal.getIntString("label.maxdebt")); // NOI18N
        jLabel2.setPreferredSize(new java.awt.Dimension(100, 30));

        txtMaxdebt.setEditable(false);
        txtMaxdebt.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        txtMaxdebt.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        txtMaxdebt.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        txtMaxdebt.setMaximumSize(new java.awt.Dimension(170, 170));
        txtMaxdebt.setName(""); // NOI18N
        txtMaxdebt.setPreferredSize(new java.awt.Dimension(100, 30));

        jLabel4.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel4.setText(AppLocal.getIntString("label.curdebt")); // NOI18N
        jLabel4.setPreferredSize(new java.awt.Dimension(100, 30));

        txtCurdebt.setEditable(false);
        txtCurdebt.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        txtCurdebt.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        txtCurdebt.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        txtCurdebt.setMaximumSize(new java.awt.Dimension(170, 170));
        txtCurdebt.setName(""); // NOI18N
        txtCurdebt.setPreferredSize(new java.awt.Dimension(100, 30));

        jLabel6.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel6.setText(AppLocal.getIntString("label.curdate")); // NOI18N
        jLabel6.setPreferredSize(new java.awt.Dimension(100, 30));

        txtCurdate.setEditable(false);
        txtCurdate.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        txtCurdate.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        txtCurdate.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        txtCurdate.setMaximumSize(new java.awt.Dimension(170, 170));
        txtCurdate.setName(""); // NOI18N
        txtCurdate.setPreferredSize(new java.awt.Dimension(100, 30));

        m_jNotes.setEditable(false);
        m_jNotes.setBackground(new java.awt.Color(240, 240, 240));
        m_jNotes.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        m_jNotes.setLineWrap(true);
        m_jNotes.setCaretColor(new java.awt.Color(255, 255, 255));
        m_jNotes.setEnabled(false);
        m_jNotes.setMaximumSize(new java.awt.Dimension(170, 170));
        m_jNotes.setName(""); // NOI18N
        m_jNotes.setPreferredSize(new java.awt.Dimension(230, 30));
        jScrollPane1.setViewportView(m_jNotes);

        jlblMessage.setEditable(false);
        jlblMessage.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jlblMessage.setForeground(new java.awt.Color(204, 0, 102));
        jlblMessage.setLineWrap(true);
        jlblMessage.setWrapStyleWord(true);
        jlblMessage.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jlblMessage.setFocusable(false);
        jlblMessage.setPreferredSize(new java.awt.Dimension(170, 30));
        jlblMessage.setRequestFocusEnabled(false);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jlblMessage, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jScrollPane1)
                            .addComponent(m_jName, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(m_jMoneyEuros, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtMaxdebt, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtCurdebt, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtCurdate, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addGap(2, 2, 2))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(3, 3, 3)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(m_jMoneyEuros, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(2, 2, 2)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addComponent(m_jName, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(2, 2, 2)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(2, 2, 2)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addComponent(txtMaxdebt, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(2, 2, 2)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addComponent(txtCurdebt, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(2, 2, 2)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addComponent(txtCurdate, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(2, 2, 2)
                .addComponent(jlblMessage, javax.swing.GroupLayout.DEFAULT_SIZE, 144, Short.MAX_VALUE)
                .addGap(2, 2, 2))
        );

        jPanel5.add(jPanel4, java.awt.BorderLayout.CENTER);

        jPanel6.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        jPanel5.add(jPanel6, java.awt.BorderLayout.PAGE_END);

        add(jPanel5, java.awt.BorderLayout.CENTER);

        jPanel2.setLayout(new java.awt.BorderLayout());

        jPanel1.setLayout(new javax.swing.BoxLayout(jPanel1, javax.swing.BoxLayout.Y_AXIS));

        jPanel3.setBorder(javax.swing.BorderFactory.createEmptyBorder(5, 5, 5, 5));
        jPanel3.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        jPanel1.add(jPanel3);

        jPanel2.add(jPanel1, java.awt.BorderLayout.NORTH);

        m_jKeys.setMaximumSize(new java.awt.Dimension(300, 2147483647));
        m_jKeys.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                m_jKeysActionPerformed(evt);
            }
        });
        jPanel2.add(m_jKeys, java.awt.BorderLayout.CENTER);

        m_jTendered.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        m_jTendered.setPreferredSize(new java.awt.Dimension(200, 30));
        jPanel2.add(m_jTendered, java.awt.BorderLayout.PAGE_END);

        add(jPanel2, java.awt.BorderLayout.EAST);
    }// </editor-fold>//GEN-END:initComponents

    private void m_jKeysActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_m_jKeysActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_m_jKeysActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea jlblMessage;
    private com.openbravo.editor.JEditorKeys m_jKeys;
    private javax.swing.JLabel m_jMoneyEuros;
    private javax.swing.JTextField m_jName;
    private javax.swing.JTextArea m_jNotes;
    private com.openbravo.editor.JEditorCurrencyPositive m_jTendered;
    private javax.swing.JTextField txtCurdate;
    private javax.swing.JTextField txtCurdebt;
    private javax.swing.JTextField txtMaxdebt;
    // End of variables declaration//GEN-END:variables
}
