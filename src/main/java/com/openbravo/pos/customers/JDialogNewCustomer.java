package com.openbravo.pos.customers;

import com.openbravo.basic.BasicException;
import com.openbravo.data.gui.ComboBoxValModel;
import com.openbravo.data.gui.MessageInf;
import com.openbravo.data.loader.LocalRes;
import com.openbravo.data.loader.SentenceExec;
import com.openbravo.data.loader.SentenceList;
import com.openbravo.data.loader.TableDefinition;
import com.openbravo.format.Formats;
import com.openbravo.pos.forms.AppLocal;
import com.openbravo.pos.forms.AppView;
import com.openbravo.pos.forms.DataLogicSales;
import java.awt.Component;
import java.awt.Dialog;
import java.awt.Frame;
import java.awt.Window;
import java.util.List;
import java.util.UUID;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class JDialogNewCustomer extends javax.swing.JDialog {
    
    private DataLogicCustomers dlCustomer;
    private DataLogicSales dlSales;
    private ComboBoxValModel m_CategoryModel;
    private SentenceList m_sentcat;
    private SentenceExec m_sentinsert;
    private TableDefinition tcustomers;
    private CustomerInfoExt selectedCustomer;
    private Object m_oId;
    
    /** Creates new form quick New Customer
     * @param parent */
    protected JDialogNewCustomer(java.awt.Frame parent) {
        super(parent, true);
    }
    
    /** Creates new form quick New Customer
     * @param parent */
    protected JDialogNewCustomer(java.awt.Dialog parent) {
        super(parent, true);
    } 
    
    private void init(AppView app) {

        try {
            dlSales = (DataLogicSales) app.getBean("com.openbravo.pos.forms.DataLogicSales");
            dlCustomer = (DataLogicCustomers) app.getBean("com.openbravo.pos.customers.DataLogicCustomers");
            m_sentcat = dlSales.getTaxCustCategoriesList();
            tcustomers = dlCustomer.getTableCustomers();

            initComponents();        
            m_jSearchkey.setEnabled(true);

            m_CategoryModel = new ComboBoxValModel();
            List a = m_sentcat.list();
            a.add(0, null);

            m_CategoryModel = new ComboBoxValModel(a);
            m_jCategory.setModel(m_CategoryModel);      
            
            getRootPane().setDefaultButton(m_jBtnOK);
        } catch (BasicException ex) {
        }
    }
    
    
     public Object createValue() throws BasicException {
        Object[] customer = new Object[27];
        customer[0] =  m_oId;        
        customer[1] = m_jSearchkey.getText();
        customer[2] = null;
        customer[3] = Formats.STRING.parseValue(txtFirstName.getText() + " " + txtLastName.getText());
        customer[4] = m_CategoryModel.getSelectedKey();
        customer[5] = null;
        customer[6] = Formats.CURRENCY.parseValue(txtMaxdebt.getText(), 0.0);
        customer[7] = Formats.STRING.parseValue(txtAddress1.getText());
        customer[8] = Formats.STRING.parseValue(txtAddress2.getText());
        customer[9] = Formats.STRING.parseValue(txtPostal.getText());
        customer[10] = Formats.STRING.parseValue(txtCity.getText());
        customer[11] = Formats.STRING.parseValue(txtRegion.getText());
        customer[12] = Formats.STRING.parseValue(txtCountry.getText());
        customer[13] = Formats.STRING.parseValue(txtFirstName.getText());
        customer[14] = Formats.STRING.parseValue(txtLastName.getText());
        customer[15] = Formats.STRING.parseValue(txtEmail.getText());
        customer[16] = Formats.STRING.parseValue(txtPhone.getText());
        customer[17] = Formats.STRING.parseValue(txtPhone2.getText());
        customer[18] = null;        
        customer[19] = Formats.STRING.parseValue(txtNotes.getText());
        customer[20] = true;
        customer[21] = null;
        customer[22] = 0.0;
        customer[23] = null;  
        customer[24] = m_jVip.isSelected();
        customer[25] = Formats.DOUBLE.parseValue(txtDiscount.getText());
        customer[26] = null;
        
        return customer;
    }

    
      private void selectedVip(boolean  value){
        m_jVip.setSelected(value);
        jLblDiscountpercent.setVisible(value);
        txtDiscount.setVisible(value);
        
    }
      
    public static JDialogNewCustomer getDialog(Component parent,AppView app) {
         
        Window window = getWindow(parent);
        
        JDialogNewCustomer quicknewcustomer;        

        if (window instanceof Frame) { 
            quicknewcustomer = new JDialogNewCustomer((Frame) window);
        } else {
            quicknewcustomer = new JDialogNewCustomer((Dialog) window);
        }
        
        quicknewcustomer.init(app);         
        
        return quicknewcustomer;
    } 
    
    protected static Window getWindow(Component parent) {
        if (parent == null) {
            return new JFrame();
        } else if (parent instanceof Frame || parent instanceof Dialog) {
            return (Window)parent;
        } else {
            return getWindow(parent.getParent());
        }
    }
    
    public CustomerInfoExt getSelectedCustomer() {
        return selectedCustomer;
    }
    
   
    
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents()
    {

        jPanel3 = new javax.swing.JPanel();
        jLblSearchkey = new javax.swing.JLabel();
        m_jSearchkey = new javax.swing.JTextField();
        jLblFirstName = new javax.swing.JLabel();
        txtFirstName = new javax.swing.JTextField();
        jLblLastName = new javax.swing.JLabel();
        txtLastName = new javax.swing.JTextField();
        jLblEmail = new javax.swing.JLabel();
        txtEmail = new javax.swing.JTextField();
        jLblTelephone1 = new javax.swing.JLabel();
        jLblTelephone2 = new javax.swing.JLabel();
        txtPhone2 = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextPane1 = new javax.swing.JTextPane();
        txtPhone = new javax.swing.JTextField();
        address1 = new javax.swing.JLabel();
        txtAddress1 = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        txtAddress2 = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txtCity = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txtRegion = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txtCountry = new javax.swing.JTextField();
        notes = new javax.swing.JLabel();
        jLblCustTaxCat = new javax.swing.JLabel();
        m_jCategory = new javax.swing.JComboBox();
        jLabel1 = new javax.swing.JLabel();
        txtMaxdebt = new javax.swing.JTextField();
        jLblDiscount = new javax.swing.JLabel();
        txtDiscount = new javax.swing.JTextField();
        jLblDiscountpercent = new javax.swing.JLabel();
        jLblVIP = new javax.swing.JLabel();
        m_jVip = new javax.swing.JCheckBox();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtNotes = new javax.swing.JTextArea();
        postal = new javax.swing.JLabel();
        txtPostal = new javax.swing.JTextField();
        m_jBtnOK = new javax.swing.JButton();
        m_jBtnCancel = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle(AppLocal.getIntString("label.customer")); // NOI18N
        setPreferredSize(new java.awt.Dimension(610, 430));
        setResizable(false);

        jPanel3.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N

        jLblSearchkey.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLblSearchkey.setText(AppLocal.getIntString("label.searchkeym")); // NOI18N
        jLblSearchkey.setPreferredSize(new java.awt.Dimension(150, 30));

        m_jSearchkey.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        m_jSearchkey.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        m_jSearchkey.setPreferredSize(new java.awt.Dimension(300, 30));
        m_jSearchkey.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                m_jSearchkeyActionPerformed(evt);
            }
        });

        jLblFirstName.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLblFirstName.setText(AppLocal.getIntString("label.firstname")); // NOI18N
        jLblFirstName.setAlignmentX(0.5F);
        jLblFirstName.setPreferredSize(new java.awt.Dimension(150, 30));

        txtFirstName.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        txtFirstName.setPreferredSize(new java.awt.Dimension(300, 30));
        txtFirstName.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                txtFirstNameActionPerformed(evt);
            }
        });

        jLblLastName.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLblLastName.setText(AppLocal.getIntString("label.lastname")); // NOI18N
        jLblLastName.setPreferredSize(new java.awt.Dimension(150, 30));

        txtLastName.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        txtLastName.setPreferredSize(new java.awt.Dimension(300, 30));

        jLblEmail.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLblEmail.setText(AppLocal.getIntString("label.email")); // NOI18N
        jLblEmail.setPreferredSize(new java.awt.Dimension(150, 30));

        txtEmail.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        txtEmail.setPreferredSize(new java.awt.Dimension(300, 30));

        jLblTelephone1.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLblTelephone1.setText(AppLocal.getIntString("label.phone")); // NOI18N
        jLblTelephone1.setPreferredSize(new java.awt.Dimension(150, 30));

        jLblTelephone2.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLblTelephone2.setText(AppLocal.getIntString("label.phone2")); // NOI18N
        jLblTelephone2.setPreferredSize(new java.awt.Dimension(150, 30));

        txtPhone2.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        txtPhone2.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        txtPhone2.setPreferredSize(new java.awt.Dimension(300, 30));
        txtPhone2.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                txtPhone2ActionPerformed(evt);
            }
        });

        jTextPane1.setEditable(false);
        jTextPane1.setFont(new java.awt.Font("Arial Black", 0, 18)); // NOI18N
        jTextPane1.setText("\tSearch Key should be in the format\t\tBOOK NUMBER / BOOK PAGE  \n\n\t\t\t\t\tExample: 23/900");
        jScrollPane1.setViewportView(jTextPane1);

        txtPhone.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        txtPhone.setPreferredSize(new java.awt.Dimension(300, 30));

        address1.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        address1.setText("Address Line 1");
        address1.setPreferredSize(new java.awt.Dimension(150, 30));

        txtAddress1.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        txtAddress1.setPreferredSize(new java.awt.Dimension(300, 30));

        jLabel2.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel2.setText("Address Line 2");
        jLabel2.setPreferredSize(new java.awt.Dimension(120, 30));

        txtAddress2.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        txtAddress2.setPreferredSize(new java.awt.Dimension(300, 30));

        jLabel3.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel3.setText("Town");
        jLabel3.setPreferredSize(new java.awt.Dimension(120, 30));

        txtCity.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        txtCity.setPreferredSize(new java.awt.Dimension(300, 30));

        jLabel4.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel4.setText("County");
        jLabel4.setPreferredSize(new java.awt.Dimension(120, 30));

        txtRegion.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        txtRegion.setPreferredSize(new java.awt.Dimension(300, 30));

        jLabel5.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel5.setText("Country");
        jLabel5.setPreferredSize(new java.awt.Dimension(120, 30));

        txtCountry.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        txtCountry.setText("Ireland");
        txtCountry.setPreferredSize(new java.awt.Dimension(300, 30));

        notes.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        notes.setText("Notes");
        notes.setPreferredSize(new java.awt.Dimension(120, 30));

        jLblCustTaxCat.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLblCustTaxCat.setText(AppLocal.getIntString("label.custtaxcategory")); // NOI18N
        jLblCustTaxCat.setMaximumSize(new java.awt.Dimension(140, 25));
        jLblCustTaxCat.setMinimumSize(new java.awt.Dimension(140, 25));
        jLblCustTaxCat.setPreferredSize(new java.awt.Dimension(150, 30));

        m_jCategory.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        m_jCategory.setPreferredSize(new java.awt.Dimension(300, 30));
        m_jCategory.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                m_jCategoryActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel1.setText(AppLocal.getIntString("label.maxdebt")); // NOI18N
        jLabel1.setMaximumSize(new java.awt.Dimension(140, 25));
        jLabel1.setMinimumSize(new java.awt.Dimension(140, 25));
        jLabel1.setPreferredSize(new java.awt.Dimension(150, 30));

        txtMaxdebt.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        txtMaxdebt.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        txtMaxdebt.setText("5000");
        txtMaxdebt.setPreferredSize(new java.awt.Dimension(300, 30));
        txtMaxdebt.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                txtMaxdebtActionPerformed(evt);
            }
        });

        jLblDiscount.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLblDiscount.setText(AppLocal.getIntString("label.discount")); // NOI18N
        jLblDiscount.setPreferredSize(new java.awt.Dimension(55, 30));

        txtDiscount.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        txtDiscount.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        txtDiscount.setPreferredSize(new java.awt.Dimension(50, 30));

        jLblDiscountpercent.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLblDiscountpercent.setText("%");
        jLblDiscountpercent.setPreferredSize(new java.awt.Dimension(12, 30));

        jLblVIP.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLblVIP.setText(AppLocal.getIntString("label.vip")); // NOI18N
        jLblVIP.setPreferredSize(new java.awt.Dimension(150, 30));

        m_jVip.setForeground(new java.awt.Color(0, 188, 243));
        m_jVip.setPreferredSize(new java.awt.Dimension(21, 30));
        m_jVip.addChangeListener(new javax.swing.event.ChangeListener()
        {
            public void stateChanged(javax.swing.event.ChangeEvent evt)
            {
                m_jVipnone(evt);
            }
        });

        txtNotes.setColumns(20);
        txtNotes.setRows(5);
        jScrollPane2.setViewportView(txtNotes);

        postal.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        postal.setText("Post Code");
        postal.setPreferredSize(new java.awt.Dimension(120, 30));

        txtPostal.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        txtPostal.setPreferredSize(new java.awt.Dimension(300, 30));

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 926, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel3Layout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(postal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLblSearchkey, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLblFirstName, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLblLastName, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLblEmail, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLblTelephone1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLblTelephone2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(address1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(m_jSearchkey, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtFirstName, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtLastName, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtEmail, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtPhone, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtPhone2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtAddress1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtAddress2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtCity, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtRegion, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtCountry, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtPostal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(notes, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLblCustTaxCat, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLblDiscount, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLblVIP, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(m_jCategory, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(txtDiscount, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLblDiscountpercent, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(txtMaxdebt, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(m_jVip, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane2))))
                .addGap(15, 15, 15))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLblSearchkey, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(m_jSearchkey, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(notes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(25, 25, 25)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLblFirstName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtFirstName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLblLastName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtLastName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLblCustTaxCat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(m_jCategory, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLblEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtMaxdebt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtPhone, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLblTelephone2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLblDiscount, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtDiscount, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLblDiscountpercent, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLblTelephone1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtPhone2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLblVIP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(m_jVip, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(25, 25, 25)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(address1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtAddress1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtAddress2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtCity, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtRegion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(postal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtPostal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtCountry, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(19, 19, 19))
        );

        m_jBtnOK.setBackground(new java.awt.Color(40, 167, 69));
        m_jBtnOK.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        m_jBtnOK.setForeground(new java.awt.Color(255, 255, 255));
        m_jBtnOK.setText(AppLocal.getIntString("Button.OK")); // NOI18N
        m_jBtnOK.setBorderPainted(false);
        m_jBtnOK.setFocusPainted(false);
        m_jBtnOK.setFocusable(false);
        m_jBtnOK.setMargin(new java.awt.Insets(8, 16, 8, 16));
        m_jBtnOK.setPreferredSize(new java.awt.Dimension(140, 45));
        m_jBtnOK.setRequestFocusEnabled(false);
        m_jBtnOK.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                m_jBtnOKActionPerformed(evt);
            }
        });

        m_jBtnCancel.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        m_jBtnCancel.setText(AppLocal.getIntString("Button.Cancel")); // NOI18N
        m_jBtnCancel.setFocusPainted(false);
        m_jBtnCancel.setFocusable(false);
        m_jBtnCancel.setMargin(new java.awt.Insets(8, 16, 8, 16));
        m_jBtnCancel.setPreferredSize(new java.awt.Dimension(140, 45));
        m_jBtnCancel.setRequestFocusEnabled(false);
        m_jBtnCancel.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                m_jBtnCancelActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(m_jBtnCancel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(m_jBtnOK, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(15, 15, 15))
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 613, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(m_jBtnOK, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(m_jBtnCancel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(25, 25, 25))
        );

        jPanel3.getAccessibleContext().setAccessibleName("");

        setSize(new java.awt.Dimension(972, 708));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void m_jBtnOKActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_m_jBtnOKActionPerformed

        if ("".equals(m_jSearchkey.getText())
                || "".equals(txtFirstName.getText()) || "".equals(txtLastName.getText())) {
            JOptionPane.showMessageDialog(
                null, 
                    AppLocal.getIntString("message.customercheck"), 
                    "Customer check", 
                JOptionPane.ERROR_MESSAGE); 
        } else {    
            try {
                m_oId = UUID.randomUUID().toString();
                Object customer = createValue();

                int status = tcustomers.getInsertSentence().exec(customer);
            
                if (status>0){
                    selectedCustomer =  dlSales.loadCustomerExt(m_oId.toString());
                    dispose();
                } else {
                    MessageInf msg = new MessageInf(MessageInf.SGN_NOTICE, 
                       LocalRes.getIntString("message.nosave"), "Error save");
                    msg.show(this);
                }
        
            } catch (BasicException ex) {
                MessageInf msg = new MessageInf(MessageInf.SGN_NOTICE, 
                   LocalRes.getIntString("message.nosave"), ex);
                msg.show(this);
            }
        }
    }//GEN-LAST:event_m_jBtnOKActionPerformed

    private void m_jBtnCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_m_jBtnCancelActionPerformed
        
        dispose();
        
    }//GEN-LAST:event_m_jBtnCancelActionPerformed

private void m_jVipnone(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_m_jVipnone

}//GEN-LAST:event_m_jVipnone

    private void txtMaxdebtActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_txtMaxdebtActionPerformed
    {//GEN-HEADEREND:event_txtMaxdebtActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtMaxdebtActionPerformed

    private void m_jCategoryActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_m_jCategoryActionPerformed
    {//GEN-HEADEREND:event_m_jCategoryActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_m_jCategoryActionPerformed

    private void m_jSearchkeyActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_m_jSearchkeyActionPerformed
    {//GEN-HEADEREND:event_m_jSearchkeyActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_m_jSearchkeyActionPerformed

    private void txtFirstNameActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_txtFirstNameActionPerformed
    {//GEN-HEADEREND:event_txtFirstNameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtFirstNameActionPerformed

    private void txtPhone2ActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_txtPhone2ActionPerformed
    {//GEN-HEADEREND:event_txtPhone2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtPhone2ActionPerformed
    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel address1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLblCustTaxCat;
    private javax.swing.JLabel jLblDiscount;
    private javax.swing.JLabel jLblDiscountpercent;
    private javax.swing.JLabel jLblEmail;
    private javax.swing.JLabel jLblFirstName;
    private javax.swing.JLabel jLblLastName;
    private javax.swing.JLabel jLblSearchkey;
    private javax.swing.JLabel jLblTelephone1;
    private javax.swing.JLabel jLblTelephone2;
    private javax.swing.JLabel jLblVIP;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextPane jTextPane1;
    private javax.swing.JButton m_jBtnCancel;
    private javax.swing.JButton m_jBtnOK;
    private javax.swing.JComboBox m_jCategory;
    private javax.swing.JTextField m_jSearchkey;
    private javax.swing.JCheckBox m_jVip;
    private javax.swing.JLabel notes;
    private javax.swing.JLabel postal;
    private javax.swing.JTextField txtAddress1;
    private javax.swing.JTextField txtAddress2;
    private javax.swing.JTextField txtCity;
    private javax.swing.JTextField txtCountry;
    private javax.swing.JTextField txtDiscount;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JTextField txtFirstName;
    private javax.swing.JTextField txtLastName;
    private javax.swing.JTextField txtMaxdebt;
    private javax.swing.JTextArea txtNotes;
    private javax.swing.JTextField txtPhone;
    private javax.swing.JTextField txtPhone2;
    private javax.swing.JTextField txtPostal;
    private javax.swing.JTextField txtRegion;
    // End of variables declaration//GEN-END:variables
    
}
