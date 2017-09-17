/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package daily_investment.Interfaces;

import Class_Files.AllConfigIsHere;
import Class_Files.Database_Config;
import Class_Files.GetAllCustomerResultSet;
import Class_Files.MessageIcons;
import java.awt.Color;
import java.awt.Frame;
import java.awt.Toolkit;
import static java.awt.image.ImageObserver.WIDTH;
import java.sql.ResultSet;
import java.util.Vector;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;

/**
 *
 * @author Ishan Darshana
 */
public class getCustomersDetails extends javax.swing.JFrame {

    Database_Config databaseConfig = new Database_Config();
    GetAllCustomerResultSet getAllCustomerDetails = new GetAllCustomerResultSet();
    MessageIcons messageIcons = new MessageIcons();
    AllConfigIsHere allConfigIsHere = new AllConfigIsHere();
    static String status; //fill txtnic according to customer status 

    /**
     * Creates new form getCustomersDetails
     */
    public getCustomersDetails() {
        initComponents();
        getContentPane().setBackground(Color.WHITE);
        this.setExtendedState(Frame.MAXIMIZED_BOTH);
        setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource(messageIcons.getFrameIcon())));
        jPanel4.setVisible(false);
        lblPersonalData.setVisible(false);
        fillNic("P");

    }

    //get status finished and add to table
    void getFinishedCustomerDetails() {

        try {
            status = "F";

            DefaultTableModel dtm = (DefaultTableModel) ftableinished.getModel();
            allConfigIsHere.removeAllTableData(dtm);

            //send statement as string for after WHERE cluse in this method
            getAllCustomerDetails.setRs_00("WHERE CUSTOMERS.nic = '"+txtNic.getSelectedItem().toString()+"' AND CUSTOMERS.status = '"+status+"'");
            if (getAllCustomerDetails.getRs_00().first()) {

                //derive b day & gender from NIC    
                String idNumber = txtNic.getSelectedItem().toString();
                int month[] = {31, 29, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
                int year = 1900 + Integer.parseInt(idNumber.substring(0, 2));
                int day = Integer.parseInt(idNumber.substring(2, 5));
                int iff;

                if (day > 500) {
                    iff = day - 500;
                } else {
                    iff = day;
                }

                int mo = 0, da = 0;
                int days = iff;

                for (int i = 0; i < month.length; i++) {
                    if (days < month[i]) {
                        mo = i + 1;
                        da = days;
                        break;
                    } else {
                        days = days - month[i];
                    }
                }

                setFinishedCustomerDetails(year, mo, da, getAllCustomerDetails.getRs_00());

            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    //get status processing and add to labels
    void getProcessingCustomerDetails() {

        try {

            status = "P";

            getAllCustomerDetails.setRs_00("WHERE CUSTOMERS.nic = '"+txtNic.getSelectedItem().toString()+"' AND CUSTOMERS.status = '"+status+"'");
            if (getAllCustomerDetails.getRs_00().first()) {

                //derive b day & gender from NIC    
                String idNumber = txtNic.getSelectedItem().toString();
                int month[] = {31, 29, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
                int year = 1900 + Integer.parseInt(idNumber.substring(0, 2));
                int day = Integer.parseInt(idNumber.substring(2, 5));
                int iff;

                if (day > 500) {
                    iff = day - 500;
                } else {
                    iff = day;
                }

                int mo = 0, da = 0;
                int days = iff;

                for (int i = 0; i < month.length; i++) {
                    if (days < month[i]) {
                        mo = i + 1;
                        da = days;
                        break;
                    } else {
                        days = days - month[i];
                    }
                }

                setProcessingCustomerDetails(year, mo, da, getAllCustomerDetails.getRs_00());

            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    void setProcessingCustomerDetails(int year, int mo, int da, ResultSet rs_00) {

        try {
            lblCodeNumber.setText(rs_00.getString("idCustomers"));
            lblName.setText(rs_00.getString("firstName") + " " + rs_00.getString("lastName"));
            lblAddress.setText(rs_00.getString("address"));
            lblGender.setText(allConfigIsHere.getGender(txtNic.getSelectedItem().toString()));
            lblTelephone.setText(rs_00.getString("contactNumber"));
            lblBirthDate.setText(year + " - " + mo + " - " + da);

            lblStartDate.setText(rs_00.getString("startDate"));
            lblEndDate.setText(rs_00.getString("endDate"));
            lblLocation.setText(rs_00.getString("lineName"));

            lblLoanAmount.setText(rs_00.getString("loanAmount"));
            lblRental.setText(rs_00.getString("rental"));
            lblTotalPay.setText(rs_00.getString("paid"));
            lblToPay.setText(rs_00.getString("toPaid"));
            lblTotalDays.setText(rs_00.getString("totalDays"));
            lblAreasDays.setText(rs_00.getString("areas"));
            lblAgent.setText(rs_00.getString("name"));
        } catch (Exception e) {

            e.printStackTrace();

        }

    }

    void setFinishedCustomerDetails(int year, int mo, int da, ResultSet rs_00) {

        try {

        
            lblPersonalData.setText(rs_00.getString("firstName") + " " + rs_00.getString("lastName")+" | "+allConfigIsHere.getGender(txtNic.getSelectedItem().toString())+" | "+ year + " - " + mo + " - " + da);

            DefaultTableModel dtm = (DefaultTableModel) ftableinished.getModel();
            Vector v = new Vector();
            v.add(rs_00.getString("idCustomers"));
            v.add(rs_00.getString("startDate"));
            v.add(rs_00.getString("endDate"));
            v.add(rs_00.getString("loanAmount"));
            v.add(rs_00.getString("rental"));
            v.add(rs_00.getString("paid"));
            v.add(rs_00.getString("totalDays"));
            v.add(rs_00.getString("name"));
            v.add(rs_00.getString("areas"));

            dtm.addRow(v);

        } catch (Exception e) {

            e.printStackTrace();

        }

    }

    void fillNic(String status) {
        try {

            //add nic to combobox while opening jframe
            String sql_03 = "SELECT nic FROM customers WHERE status = '" + status + "'";
            ResultSet rs_03 = databaseConfig.getStatement().executeQuery(sql_03);
            while (rs_03.next()) {

                txtNic.addItem(rs_03.getString("nic"));
                AutoCompleteDecorator.decorate(txtNic);

            }

        } catch (Exception e) {

            e.printStackTrace();
        }

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel4 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        ftableinished = new javax.swing.JTable();
        jSeparator1 = new javax.swing.JSeparator();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        lblCodeNumber = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        lblName = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        lblLoanAmount = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        lblAddress = new javax.swing.JLabel();
        lblGender = new javax.swing.JLabel();
        lblTelephone = new javax.swing.JLabel();
        lblRental = new javax.swing.JLabel();
        lblBirthDate = new javax.swing.JLabel();
        lblEndDate = new javax.swing.JLabel();
        lblStartDate = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        lblTotalDays = new javax.swing.JLabel();
        lblToPay = new javax.swing.JLabel();
        lblTotalPay = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();
        jSeparator3 = new javax.swing.JSeparator();
        jLabel23 = new javax.swing.JLabel();
        lblAgent = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        lblAreasDays = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        lblLocation = new javax.swing.JLabel();
        lblPersonalData = new javax.swing.JLabel();
        txtNic = new javax.swing.JComboBox();
        jLabel2 = new javax.swing.JLabel();
        jCheckBox1 = new javax.swing.JCheckBox();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Customer Details");
        setMinimumSize(new java.awt.Dimension(1367, 786));

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder("Customers"));

        ftableinished.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Code ", "Start Date", "End Date", "Loan Amount", "Rental", "Total Paid", "Total Days", "Agent", "Areas Days"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        ftableinished.setColumnSelectionAllowed(true);
        ftableinished.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                ftableinishedMouseClicked(evt);
            }
        });
        ftableinished.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                ftableinishedKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                ftableinishedKeyReleased(evt);
            }
        });
        jScrollPane1.setViewportView(ftableinished);
        ftableinished.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_INTERVAL_SELECTION);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1)
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 449, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Details", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI Light", 0, 13))); // NOI18N

        jLabel1.setBackground(new java.awt.Color(0, 0, 0));
        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        jLabel1.setText("Code Number");

        jLabel3.setBackground(new java.awt.Color(0, 0, 0));
        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        jLabel3.setText("Name");

        lblCodeNumber.setBackground(new java.awt.Color(0, 0, 0));
        lblCodeNumber.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        lblCodeNumber.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);

        jLabel5.setBackground(new java.awt.Color(0, 0, 0));
        jLabel5.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        jLabel5.setText("Birthday");

        lblName.setBackground(new java.awt.Color(0, 0, 0));
        lblName.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        lblName.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);

        jLabel7.setBackground(new java.awt.Color(0, 0, 0));
        jLabel7.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        jLabel7.setText("Address");

        jLabel8.setBackground(new java.awt.Color(0, 0, 0));
        jLabel8.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        jLabel8.setText("Start Date");

        jLabel10.setBackground(new java.awt.Color(0, 0, 0));
        jLabel10.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        jLabel10.setText("Gender");

        jLabel11.setBackground(new java.awt.Color(0, 0, 0));
        jLabel11.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        jLabel11.setText("Telephone ");

        jLabel12.setBackground(new java.awt.Color(0, 0, 0));
        jLabel12.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        jLabel12.setText("End Date");

        jLabel15.setBackground(new java.awt.Color(0, 0, 0));
        jLabel15.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        jLabel15.setText("Loan Amount");

        lblLoanAmount.setBackground(new java.awt.Color(0, 0, 0));
        lblLoanAmount.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        lblLoanAmount.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);

        jLabel17.setBackground(new java.awt.Color(0, 0, 0));
        jLabel17.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        jLabel17.setText("Rental");

        lblAddress.setBackground(new java.awt.Color(0, 0, 0));
        lblAddress.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        lblAddress.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);

        lblGender.setBackground(new java.awt.Color(0, 0, 0));
        lblGender.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        lblGender.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);

        lblTelephone.setBackground(new java.awt.Color(0, 0, 0));
        lblTelephone.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        lblTelephone.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);

        lblRental.setBackground(new java.awt.Color(0, 0, 0));
        lblRental.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        lblRental.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);

        lblBirthDate.setBackground(new java.awt.Color(0, 0, 0));
        lblBirthDate.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        lblBirthDate.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);

        lblEndDate.setBackground(new java.awt.Color(0, 0, 0));
        lblEndDate.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        lblEndDate.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);

        lblStartDate.setBackground(new java.awt.Color(0, 0, 0));
        lblStartDate.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        lblStartDate.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);

        jLabel18.setBackground(new java.awt.Color(0, 0, 0));
        jLabel18.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        jLabel18.setText("Total Paid");

        jLabel19.setBackground(new java.awt.Color(0, 0, 0));
        jLabel19.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        jLabel19.setText("To Pay");

        jLabel20.setBackground(new java.awt.Color(0, 0, 0));
        jLabel20.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        jLabel20.setText("Total Days");

        lblTotalDays.setBackground(new java.awt.Color(0, 0, 0));
        lblTotalDays.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        lblTotalDays.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);

        lblToPay.setBackground(new java.awt.Color(0, 0, 0));
        lblToPay.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        lblToPay.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);

        lblTotalPay.setBackground(new java.awt.Color(0, 0, 0));
        lblTotalPay.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        lblTotalPay.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);

        jSeparator2.setBackground(new java.awt.Color(0, 0, 0));
        jSeparator2.setForeground(new java.awt.Color(0, 0, 0));

        jSeparator3.setBackground(new java.awt.Color(0, 0, 0));
        jSeparator3.setForeground(new java.awt.Color(0, 0, 0));

        jLabel23.setBackground(new java.awt.Color(0, 0, 0));
        jLabel23.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        jLabel23.setText("Agent");

        lblAgent.setBackground(new java.awt.Color(0, 0, 0));
        lblAgent.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        lblAgent.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);

        jLabel24.setBackground(new java.awt.Color(0, 0, 0));
        jLabel24.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        jLabel24.setText("Areas Days");

        lblAreasDays.setBackground(new java.awt.Color(0, 0, 0));
        lblAreasDays.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        lblAreasDays.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblAreasDays.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);

        jLabel26.setBackground(new java.awt.Color(0, 0, 0));
        jLabel26.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        jLabel26.setText("Location");

        lblLocation.setBackground(new java.awt.Color(0, 0, 0));
        lblLocation.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        lblLocation.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator3)
                    .addComponent(jSeparator2, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel23)
                            .addComponent(jLabel15)
                            .addComponent(jLabel1)
                            .addComponent(jLabel17)
                            .addComponent(jLabel19)
                            .addComponent(jLabel20)
                            .addComponent(jLabel18)
                            .addComponent(jLabel24)
                            .addComponent(jLabel5)
                            .addComponent(jLabel11)
                            .addComponent(jLabel10)
                            .addComponent(jLabel7)
                            .addComponent(jLabel3)
                            .addComponent(jLabel12)
                            .addComponent(jLabel8)
                            .addComponent(jLabel26))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblCodeNumber, javax.swing.GroupLayout.DEFAULT_SIZE, 231, Short.MAX_VALUE)
                            .addComponent(lblName, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lblAddress, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lblGender, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lblTelephone, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lblBirthDate, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lblStartDate, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lblEndDate, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lblLocation, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lblLoanAmount, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lblRental, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lblTotalPay, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lblToPay, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lblTotalDays, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lblAreasDays, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lblAgent, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(28, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jLabel1)
                    .addComponent(lblCodeNumber, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jLabel3)
                    .addComponent(lblName, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(8, 8, 8)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(lblAddress, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(lblGender, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(lblTelephone, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(lblBirthDate, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(lblStartDate, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(lblEndDate, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel12))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel26, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblLocation, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(35, 35, 35)
                .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(lblLoanAmount, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel15))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(lblRental, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel17))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel18)
                    .addComponent(lblTotalPay, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel19)
                    .addComponent(lblToPay, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel20)
                    .addComponent(lblTotalDays, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel24)
                    .addComponent(lblAreasDays, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblAgent, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel23))
                .addGap(79, 79, 79))
        );

        lblPersonalData.setFont(new java.awt.Font("Segoe UI Light", 1, 18)); // NOI18N
        lblPersonalData.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblPersonalData.setVerticalAlignment(javax.swing.SwingConstants.TOP);

        txtNic.setBackground(new java.awt.Color(204, 204, 204));
        txtNic.setEditable(true);
        txtNic.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtNicMouseClicked(evt);
            }
        });
        txtNic.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNicActionPerformed(evt);
            }
        });
        txtNic.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtNicKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtNicKeyReleased(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Segoe UI Light", 0, 24)); // NOI18N
        jLabel2.setText("NIC");

        jCheckBox1.setFont(new java.awt.Font("Segoe UI Light", 0, 20)); // NOI18N
        jCheckBox1.setText("Search Only Finished Details");
        jCheckBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox1ActionPerformed(evt);
            }
        });

        jButton3.setBackground(new java.awt.Color(0, 0, 0));
        jButton3.setForeground(new java.awt.Color(255, 255, 255));
        jButton3.setText("By Code");
        jButton3.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton3.setFocusPainted(false);
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton4.setBackground(new java.awt.Color(255, 51, 51));
        jButton4.setFont(new java.awt.Font("Segoe UI Light", 1, 30)); // NOI18N
        jButton4.setForeground(new java.awt.Color(255, 255, 255));
        jButton4.setText("MENU");
        jButton4.setBorderPainted(false);
        jButton4.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jButton4MouseEntered(evt);
            }
        });
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator1)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(lblPersonalData, javax.swing.GroupLayout.PREFERRED_SIZE, 772, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 163, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(5, 5, 5)
                        .addComponent(txtNic, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(11, 11, 11)
                        .addComponent(jButton3)
                        .addGap(10, 10, 10)
                        .addComponent(jCheckBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 283, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jCheckBox1)
                    .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtNic, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(15, 15, 15)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lblPersonalData, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );

        setSize(new java.awt.Dimension(1367, 786));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void ftableinishedMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ftableinishedMouseClicked

    }//GEN-LAST:event_ftableinishedMouseClicked

    private void ftableinishedKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_ftableinishedKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_ftableinishedKeyPressed

    private void ftableinishedKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_ftableinishedKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_ftableinishedKeyReleased

    private void txtNicMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtNicMouseClicked

    }//GEN-LAST:event_txtNicMouseClicked

    private void txtNicActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNicActionPerformed

        try {

            if (jCheckBox1.isSelected()) {
                getFinishedCustomerDetails();

            } else {
                getProcessingCustomerDetails();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }//GEN-LAST:event_txtNicActionPerformed

    private void txtNicKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNicKeyPressed

    }//GEN-LAST:event_txtNicKeyPressed

    private void txtNicKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNicKeyReleased

    }//GEN-LAST:event_txtNicKeyReleased

    private void jCheckBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBox1ActionPerformed

        DefaultTableModel dtm = (DefaultTableModel) ftableinished.getModel();
        allConfigIsHere.removeAllTableData(dtm);

        txtNic.removeAllItems();

        if (jCheckBox1.isSelected()) {

            ftableinished.setEnabled(true);
            jPanel1.setVisible(false);
            lblPersonalData.setVisible(true);
            jPanel4.setVisible(true);

            fillNic("F");
            getFinishedCustomerDetails();

        } else {

            ftableinished.setEnabled(false);
            jPanel1.setVisible(true);
            lblPersonalData.setVisible(false);
            jPanel4.setVisible(false);

            fillNic("P");
            getProcessingCustomerDetails();

        }


    }//GEN-LAST:event_jCheckBox1ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        try {

            String sql_00 = "SELECT * FROM customers CUSTOMERS "
                    + "INNER JOIN loandetails LOAN_DETAILS ON CUSTOMERS.loanDetails_idloanDetails = LOAN_DETAILS.idloanDetails "
                    + "INNER JOIN date DATE ON LOAN_DETAILS.date_iddate = DATE.iddate "
                    + "INNER JOIN amount AMOUNT ON LOAN_DETAILS.amount_idamount = AMOUNT.idamount "
                    + "INNER JOIN c_location LOCATION ON CUSTOMERS.Lines_idLines = LOCATION.idLines "
                    + "INNER JOIN employees EMPLOYEES ON CUSTOMERS.Employees_idEmployees = EMPLOYEES.idEmployees "
                    + "WHERE CUSTOMERS.nic = '" + txtNic.getSelectedItem().toString() + "' AND CUSTOMERS.status='P'";

            ResultSet rs_00 = databaseConfig.getStatement().executeQuery(sql_00);
            if (rs_00.first()) {

                //derive b day & gender from NIC    
                String idNumber = txtNic.getSelectedItem().toString();
                int month[] = {31, 29, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
                int year = 1900 + Integer.parseInt(idNumber.substring(0, 2));
                int day = Integer.parseInt(idNumber.substring(2, 5));
                int iff;

                if (day > 500) {
                    iff = day - 500;
                } else {
                    iff = day;
                }

                int mo = 0, da = 0;
                int days = iff;

                for (int i = 0; i < month.length; i++) {
                    if (days < month[i]) {
                        mo = i + 1;
                        da = days;
                        break;
                    } else {
                        days = days - month[i];
                    }
                }
                String gender;
                int d = Integer.parseInt(idNumber.substring(2, 5));
                if (d > 500) {
                    gender = "Female";
                } else {
                    gender = "Male";
                }

                lblCodeNumber.setText(rs_00.getString("idCustomers"));
                lblName.setText(rs_00.getString("firstName") + " " + rs_00.getString("lastName"));
                lblAddress.setText(rs_00.getString("address"));
                lblGender.setText(gender);
                lblTelephone.setText(rs_00.getString("contactNumber"));
                lblBirthDate.setText(year + " - " + mo + " - " + da);

                lblStartDate.setText(rs_00.getString("startDate"));
                lblEndDate.setText(rs_00.getString("endDate"));
                lblLocation.setText(rs_00.getString("lineName"));

                lblLoanAmount.setText(rs_00.getString("loanAmount"));
                lblRental.setText(rs_00.getString("rental"));
                lblTotalPay.setText(rs_00.getString("paid"));
                lblToPay.setText(rs_00.getString("toPaid"));
                lblTotalDays.setText(rs_00.getString("totalDays"));
                lblAreasDays.setText(rs_00.getString("areas"));
                lblAgent.setText(rs_00.getString("name"));

            }

        } catch (Exception e) {

            e.printStackTrace();
        }
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton4MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton4MouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton4MouseEntered

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        new Menu().menuObject.setVisible(true);
        dispose();
    }//GEN-LAST:event_jButton4ActionPerformed

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
            java.util.logging.Logger.getLogger(getCustomersDetails.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(getCustomersDetails.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(getCustomersDetails.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(getCustomersDetails.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new getCustomersDetails().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable ftableinished;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JCheckBox jCheckBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JLabel lblAddress;
    private javax.swing.JLabel lblAgent;
    private javax.swing.JLabel lblAreasDays;
    private javax.swing.JLabel lblBirthDate;
    private javax.swing.JLabel lblCodeNumber;
    private javax.swing.JLabel lblEndDate;
    private javax.swing.JLabel lblGender;
    private javax.swing.JLabel lblLoanAmount;
    private javax.swing.JLabel lblLocation;
    private javax.swing.JLabel lblName;
    private javax.swing.JLabel lblPersonalData;
    private javax.swing.JLabel lblRental;
    private javax.swing.JLabel lblStartDate;
    private javax.swing.JLabel lblTelephone;
    private javax.swing.JLabel lblToPay;
    private javax.swing.JLabel lblTotalDays;
    private javax.swing.JLabel lblTotalPay;
    private javax.swing.JComboBox txtNic;
    // End of variables declaration//GEN-END:variables
}
