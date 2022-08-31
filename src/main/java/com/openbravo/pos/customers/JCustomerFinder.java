//    uniCenta oPOS  - Touch Friendly Point Of Sale
//    Copyright © 2009-2020 uniCenta & previous Openbravo POS works
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

package com.openbravo.pos.customers;

import com.openbravo.basic.BasicException;
import com.openbravo.data.loader.QBFCompareEnum;
import com.openbravo.data.user.EditorCreator;
import com.openbravo.data.user.ListProvider;
import com.openbravo.data.user.ListProviderCreator;
import com.openbravo.pos.forms.AppLocal;
import com.openbravo.pos.forms.AppView;
import java.awt.*;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.*;

import java.awt.event.KeyEvent; //Jack

/**
 *
 * @author adrianromero
 */
public class JCustomerFinder extends javax.swing.JDialog implements EditorCreator {

    private CustomerInfo m_ReturnCustomer;
    private ListProvider lpr;
    private AppView appView;
    
    public class Global {
//        public static String s = "(new SearchKey)";
//        public static String n = m_jtxtName;

    }
    
    /*    Keyboard Events
     */

    public void searchKey() {
        search.setMnemonic(KeyEvent.VK_E); // Jack 
        executeSearch();
        

    }

    public void resetKey() {
        reset.setMnemonic(KeyEvent.VK_R); // Jack
        accountID.setText("");
        searchKey.setText("");
        address.setText("");
        name.setText("");
        phoneNumber.setText("");
        emailAddress.setText("");
        cleanSearch();

    }

    public void setAppView(AppView appView) {
        this.appView = appView;
    }

    /** Creates new form JCustomerFinder */
    private JCustomerFinder(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
    }

    /**
     * Creates new form JCustomerFinder
     */
    private JCustomerFinder(java.awt.Dialog parent, boolean modal) {
        super(parent, modal);
    }

    /**
     *
     * @param parent
     * @param dlCustomers
     * @return
     */
    public static JCustomerFinder getCustomerFinder(Component parent, DataLogicCustomers dlCustomers) {
        Window window = getWindow(parent);

        JCustomerFinder myMsg;
        if (window instanceof Frame) {
            myMsg = new JCustomerFinder((Frame) window, true);
        } else {
            myMsg = new JCustomerFinder((Dialog) window, true);
        }
        myMsg.init(dlCustomers);
        myMsg.applyComponentOrientation(parent.getComponentOrientation());

        return myMsg;
    }

    /**
     *
     * @return
     */
    public CustomerInfo getSelectedCustomer() {
        return m_ReturnCustomer;
    }

    private void init(DataLogicCustomers dlCustomers) {

        initComponents();

        jScrollPane1.getVerticalScrollBar().setPreferredSize(new Dimension(35, 35));

//        m_jtxtTaxID.addEditorKeys(m_jKeys);
//        m_jtxtSearchKey.addEditorKeys(m_jKeys);
//        m_jtxtName.addEditorKeys(m_jKeys);
//        m_jtxtPostal.addEditorKeys(m_jKeys);
//        m_jtxtPhone.addEditorKeys(m_jKeys);
//        m_jtxtName2.addEditorKeys(m_jKeys);

        accountID.setText("");
        searchKey.setText("");
        address.setText("");
        name.setText("");
        phoneNumber.setText("");
        emailAddress.setText("");

//        m_jtxtTaxID.activate();

        lpr = new ListProviderCreator(dlCustomers.getCustomerList(), this);

        jListCustomers.setCellRenderer(new CustomerRenderer());

        getRootPane().setDefaultButton(jcmdOK);

        m_ReturnCustomer = null;
        m_jKeys.setCustomerFinder(this);

    }

    /**
     *
     * @param customer
     */
    public void search(CustomerInfo customer) {

        if (customer == null || customer.getName() == null || customer.getName().equals("")) {
            accountID.setText("");
            searchKey.setText("");
            address.setText("");
            name.setText("");
            phoneNumber.setText("");
            emailAddress.setText("");

//            m_jtxtTaxID.activate();

            cleanSearch();
        } else {

            accountID.setText(customer.getTaxid());
            searchKey.setText(customer.getSearchkey());
            address.setText(customer.getName());
            name.setText(customer.getPcode());
            phoneNumber.setText(customer.getPhone1());
            emailAddress.setText(customer.getCemail());

//            m_jtxtTaxID.activate();

            executeSearch();
        }
    }

    private void cleanSearch() {
            accountID.setText("");
            searchKey.setText("");
            address.setText("");
            name.setText("");
            phoneNumber.setText("");
            emailAddress.setText("");
            
        jListCustomers.setModel(new MyListData(new ArrayList()));
    }

    /**
     * This method actions the customer data search
     */
    public void executeSearch() {
        
        try {
            jListCustomers.setModel(new MyListData(lpr.loadData()));
            if (jListCustomers.getModel().getSize() > 0) {
                jListCustomers.setSelectedIndex(0);
            } else {
                if(!name.getText().equals("")) {
                    
                    int n = JOptionPane.showConfirmDialog(
                        null,
                        AppLocal.getIntString("message.customernotfound"),
                        AppLocal.getIntString("title.editor"),
                        JOptionPane.YES_NO_OPTION);

                    if (n != 1) {
                        CustomerInfoGlobal customerInfoGlobal = CustomerInfoGlobal.getInstance();
                        CustomerInfoExt customerInfoExt = customerInfoGlobal.getCustomerInfoExt();
                        this.setVisible(false);
                        appView.getAppUserView().showTask("com.openbravo.pos.customers.CustomersPanel");
                        JOptionPane.showMessageDialog(null, 
                            "You must complete Account and Search Key Then Save to add to Ticket",
                            "Create Customer",JOptionPane.OK_OPTION);
                    }
                }
            }
        } catch (BasicException e) {
        }
    }

    /**
     *
     * @return creates object for search method
     * @throws BasicException
     */
    @Override
    public Object createValue() throws BasicException {

        Object[] afilter = new Object[12];
        
        // TaxID
        if (accountID.getText() == null || accountID.getText().equals("")) {
            afilter[0] = QBFCompareEnum.COMP_NONE;
            afilter[1] = null;
        } else {
            afilter[0] = QBFCompareEnum.COMP_RE;
            afilter[1] = "%" + accountID.getText() + "%";
        }

        // SearchKey
        if (searchKey.getText() == null || searchKey.getText().equals("")) {
            afilter[2] = QBFCompareEnum.COMP_NONE;
            afilter[3] = null;
        } else {
            afilter[2] = QBFCompareEnum.COMP_RE;
            afilter[3] = "%" + searchKey.getText() + "%";
        }

        // Name
        if (name.getText() == null || name.getText().equals("")) {
            afilter[4] = QBFCompareEnum.COMP_NONE;
            afilter[5] = null;
        } else {
            afilter[4] = QBFCompareEnum.COMP_RE;
            afilter[5] = "%" + name.getText() + "%";
        }

// Added JG 20 Sept 12
        // Postal
        if (address.getText() == null || address.getText().equals("")) {
            afilter[6] = QBFCompareEnum.COMP_NONE;
            afilter[7] = null;
        } else {
            afilter[6] = QBFCompareEnum.COMP_RE;
            afilter[7] = "%" + address.getText() + "%";
        }

// Added JG 20 Sept 12
        // Phone
        if (phoneNumber.getText() == null || phoneNumber.getText().equals("")) {
            afilter[8] = QBFCompareEnum.COMP_NONE;
            afilter[9] = null;
        } else {
            afilter[8] = QBFCompareEnum.COMP_RE;
            afilter[9] = "%" + phoneNumber.getText() + "%";
        }

// Added JG 20 Sept 12
        // Email
        if (emailAddress.getText() == null || emailAddress.getText().equals("")) {
            afilter[10] = QBFCompareEnum.COMP_NONE;
            afilter[11] = null;
        } else {
            afilter[10] = QBFCompareEnum.COMP_RE;
            afilter[11] = "%" + emailAddress.getText() + "%";
        }

        return afilter;
    }

    private static Window getWindow(Component parent) {
        if (parent == null) {
            return new JFrame();
        } else if (parent instanceof Frame || parent instanceof Dialog) {
            return (Window) parent;
        } else {
            return getWindow(parent.getParent());
        }
    }

    private static class MyListData extends javax.swing.AbstractListModel {

        private final java.util.List m_data;

        public MyListData(java.util.List data) {
            m_data = data;
        }

        @Override
        public Object getElementAt(int index) {
            return m_data.get(index);
        }

        @Override
        public int getSize() {
            return m_data.size();
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents()
    {

        textField1 = new java.awt.TextField();
        jPanel2 = new javax.swing.JPanel();
        m_jKeys = new com.openbravo.editor.JEditorKeys();
        jPanel8 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jcmdCancel = new javax.swing.JButton();
        jcmdOK = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jPanel7 = new javax.swing.JPanel();
        jLblTaxID = new javax.swing.JLabel();
        jLblSearchKey = new javax.swing.JLabel();
        jLblPostal = new javax.swing.JLabel();
        jLblName = new javax.swing.JLabel();
        jLblPhone = new javax.swing.JLabel();
        jLblEmail = new javax.swing.JLabel();
        search = new javax.swing.JButton();
        accountID = new javax.swing.JTextField();
        searchKey = new javax.swing.JTextField();
        address = new javax.swing.JTextField();
        name = new javax.swing.JTextField();
        phoneNumber = new javax.swing.JTextField();
        emailAddress = new javax.swing.JTextField();
        reset = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jListCustomers = new javax.swing.JList();
        jPanel6 = new javax.swing.JPanel();

        textField1.setText("textField1");

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle(AppLocal.getIntString("form.customertitle")); // NOI18N
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setPreferredSize(new java.awt.Dimension(750, 600));

        jPanel2.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        jPanel8.setLayout(new java.awt.BorderLayout());

        jcmdCancel.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jcmdCancel.setText(AppLocal.getIntString("button.Cancel")); // NOI18N
        jcmdCancel.setFocusPainted(false);
        jcmdCancel.setFocusable(false);
        jcmdCancel.setMargin(new java.awt.Insets(8, 16, 8, 16));
        jcmdCancel.setPreferredSize(new java.awt.Dimension(140, 45));
        jcmdCancel.setRequestFocusEnabled(false);
        jcmdCancel.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                jcmdCancelActionPerformed(evt);
            }
        });
        jPanel1.add(jcmdCancel);

        jcmdOK.setBackground(new java.awt.Color(40, 167, 69));
        jcmdOK.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jcmdOK.setForeground(new java.awt.Color(255, 255, 255));
        jcmdOK.setText(AppLocal.getIntString("button.OK")); // NOI18N
        jcmdOK.setBorderPainted(false);
        jcmdOK.setEnabled(false);
        jcmdOK.setFocusPainted(false);
        jcmdOK.setFocusable(false);
        jcmdOK.setMargin(new java.awt.Insets(8, 16, 8, 16));
        jcmdOK.setMaximumSize(new java.awt.Dimension(103, 44));
        jcmdOK.setMinimumSize(new java.awt.Dimension(103, 44));
        jcmdOK.setPreferredSize(new java.awt.Dimension(140, 45));
        jcmdOK.setRequestFocusEnabled(false);
        jcmdOK.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                jcmdOKActionPerformed(evt);
            }
        });
        jPanel1.add(jcmdOK);

        jPanel8.add(jPanel1, java.awt.BorderLayout.LINE_END);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(m_jKeys, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(m_jKeys, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(240, 240, 240)
                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        getContentPane().add(jPanel2, java.awt.BorderLayout.LINE_END);

        jPanel3.setPreferredSize(new java.awt.Dimension(450, 0));
        jPanel3.setLayout(new java.awt.BorderLayout());

        jPanel5.setLayout(new java.awt.BorderLayout());

        jLblTaxID.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLblTaxID.setText(AppLocal.getIntString("label.taxid")); // NOI18N
        jLblTaxID.setMaximumSize(new java.awt.Dimension(60, 15));
        jLblTaxID.setMinimumSize(new java.awt.Dimension(60, 15));
        jLblTaxID.setPreferredSize(new java.awt.Dimension(110, 30));

        jLblSearchKey.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLblSearchKey.setText(AppLocal.getIntString("label.searchkey")); // NOI18N
        jLblSearchKey.setMaximumSize(new java.awt.Dimension(60, 15));
        jLblSearchKey.setMinimumSize(new java.awt.Dimension(60, 15));
        jLblSearchKey.setPreferredSize(new java.awt.Dimension(110, 30));

        jLblPostal.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLblPostal.setText("Address");
        jLblPostal.setMaximumSize(new java.awt.Dimension(60, 15));
        jLblPostal.setMinimumSize(new java.awt.Dimension(60, 15));
        jLblPostal.setPreferredSize(new java.awt.Dimension(110, 30));

        jLblName.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLblName.setText(AppLocal.getIntString("label.prodname")); // NOI18N
        jLblName.setMaximumSize(new java.awt.Dimension(60, 15));
        jLblName.setMinimumSize(new java.awt.Dimension(60, 15));
        jLblName.setPreferredSize(new java.awt.Dimension(110, 30));

        jLblPhone.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        java.util.ResourceBundle bundle = java.util.ResourceBundle.getBundle("pos_messages"); // NOI18N
        jLblPhone.setText(bundle.getString("label.companytelephone")); // NOI18N
        jLblPhone.setPreferredSize(new java.awt.Dimension(110, 30));

        jLblEmail.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLblEmail.setText(bundle.getString("label.companyemail")); // NOI18N
        jLblEmail.setPreferredSize(new java.awt.Dimension(110, 30));

        search.setBackground(new java.awt.Color(0, 128, 255));
        search.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        search.setForeground(new java.awt.Color(255, 255, 255));
        search.setText(AppLocal.getIntString("button.executefilter")); // NOI18N
        search.setToolTipText("Execute Filter");
        search.setBorderPainted(false);
        search.setFocusPainted(false);
        search.setMargin(new java.awt.Insets(2, 2, 2, 2));
        search.setPreferredSize(new java.awt.Dimension(120, 45));
        search.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                searchActionPerformed(evt);
            }
        });

        accountID.setMinimumSize(null);
        accountID.setPreferredSize(new java.awt.Dimension(300, 30));

        searchKey.setPreferredSize(new java.awt.Dimension(300, 30));

        address.setPreferredSize(new java.awt.Dimension(300, 30));

        name.setPreferredSize(new java.awt.Dimension(300, 30));

        phoneNumber.setPreferredSize(new java.awt.Dimension(300, 30));

        emailAddress.setPreferredSize(new java.awt.Dimension(300, 30));

        reset.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        reset.setText(bundle.getString("button.reset")); // NOI18N
        reset.setToolTipText("Clear Filter");
        reset.setActionCommand("Reset ");
        reset.setFocusable(false);
        reset.setMargin(new java.awt.Insets(2, 2, 2, 2));
        reset.setPreferredSize(new java.awt.Dimension(120, 45));
        reset.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                resetActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLblName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLblSearchKey, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLblPostal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLblTaxID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(jLblEmail, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLblPhone, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(accountID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(searchKey, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(address, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(name, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(phoneNumber, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(emailAddress, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addComponent(reset, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(search, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(13, 13, 13)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLblTaxID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(accountID, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLblSearchKey, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(searchKey, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLblPostal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(address, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(name, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(3, 3, 3))
                    .addComponent(jLblName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLblPhone, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(phoneNumber, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLblEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(emailAddress, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(reset, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(search, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(22, Short.MAX_VALUE))
        );

        search.getAccessibleContext().setAccessibleName("");
        search.getAccessibleContext().setAccessibleDescription("");
        reset.getAccessibleContext().setAccessibleName("");

        jPanel5.add(jPanel7, java.awt.BorderLayout.CENTER);

        jPanel3.add(jPanel5, java.awt.BorderLayout.PAGE_START);

        jPanel4.setBorder(javax.swing.BorderFactory.createEmptyBorder(5, 5, 5, 5));
        jPanel4.setPreferredSize(new java.awt.Dimension(450, 140));
        jPanel4.setLayout(new java.awt.BorderLayout());

        jScrollPane1.setPreferredSize(new java.awt.Dimension(400, 147));

        jListCustomers.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jListCustomers.setFocusable(false);
        jListCustomers.setRequestFocusEnabled(false);
        jListCustomers.addMouseListener(new java.awt.event.MouseAdapter()
        {
            public void mouseClicked(java.awt.event.MouseEvent evt)
            {
                jListCustomersMouseClicked(evt);
            }
        });
        jListCustomers.addListSelectionListener(new javax.swing.event.ListSelectionListener()
        {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt)
            {
                jListCustomersValueChanged(evt);
            }
        });
        jScrollPane1.setViewportView(jListCustomers);

        jPanel4.add(jScrollPane1, java.awt.BorderLayout.CENTER);

        jPanel6.setPreferredSize(new java.awt.Dimension(432, 0));

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 422, Short.MAX_VALUE)
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        jPanel4.add(jPanel6, java.awt.BorderLayout.PAGE_START);

        jPanel3.add(jPanel4, java.awt.BorderLayout.CENTER);

        getContentPane().add(jPanel3, java.awt.BorderLayout.CENTER);

        setSize(new java.awt.Dimension(758, 634));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents
    private void jcmdOKActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jcmdOKActionPerformed

        m_ReturnCustomer = (CustomerInfo) jListCustomers.getSelectedValue();
        dispose();

    }//GEN-LAST:event_jcmdOKActionPerformed

    private void jcmdCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jcmdCancelActionPerformed
        
        dispose();

    }//GEN-LAST:event_jcmdCancelActionPerformed

    private void jListCustomersValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_jListCustomersValueChanged

        m_ReturnCustomer = (CustomerInfo) jListCustomers.getSelectedValue();
            
        if (m_ReturnCustomer != null) {
            m_ReturnCustomer = (CustomerInfo) jListCustomers.getSelectedValue();

//            if (m_ReturnCustomer != null) {
//                jImageViewerCustomer.setImage(m_ReturnCustomer.getImage());
//            }
        }         
        
        jcmdOK.setEnabled(jListCustomers.getSelectedValue() != null);

    }//GEN-LAST:event_jListCustomersValueChanged

    private void jListCustomersMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jListCustomersMouseClicked

        m_ReturnCustomer = (CustomerInfo) jListCustomers.getSelectedValue();
            
        if (m_ReturnCustomer != null) {
            m_ReturnCustomer = (CustomerInfo) jListCustomers.getSelectedValue();

//            if (m_ReturnCustomer != null) {
//                jImageViewerCustomer.setImage(m_ReturnCustomer.getImage());
//            }
        } 

    }//GEN-LAST:event_jListCustomersMouseClicked

    private void searchActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_searchActionPerformed
    {//GEN-HEADEREND:event_searchActionPerformed

        m_ReturnCustomer=null;
        executeSearch();

    }//GEN-LAST:event_searchActionPerformed

    private void resetActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_resetActionPerformed
    {//GEN-HEADEREND:event_resetActionPerformed
        accountID.setText("");
        searchKey.setText("");
        address.setText("");
        name.setText("");
        phoneNumber.setText("");
        emailAddress.setText("");
        cleanSearch();
        m_ReturnCustomer=null;
        executeSearch();
    }//GEN-LAST:event_resetActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField accountID;
    private javax.swing.JTextField address;
    private javax.swing.JTextField emailAddress;
    private javax.swing.JLabel jLblEmail;
    private javax.swing.JLabel jLblName;
    private javax.swing.JLabel jLblPhone;
    private javax.swing.JLabel jLblPostal;
    private javax.swing.JLabel jLblSearchKey;
    private javax.swing.JLabel jLblTaxID;
    private javax.swing.JList jListCustomers;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton jcmdCancel;
    private javax.swing.JButton jcmdOK;
    private com.openbravo.editor.JEditorKeys m_jKeys;
    private javax.swing.JTextField name;
    private javax.swing.JTextField phoneNumber;
    private javax.swing.JButton reset;
    private javax.swing.JButton search;
    private javax.swing.JTextField searchKey;
    private java.awt.TextField textField1;
    // End of variables declaration//GEN-END:variables
}
