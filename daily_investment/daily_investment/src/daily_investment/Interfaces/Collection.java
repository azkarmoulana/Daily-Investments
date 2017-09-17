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
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Date;
import java.util.Vector;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Ishan Darshana
 */
public class Collection extends javax.swing.JFrame {

    GetAllCustomerResultSet getAllCustomerDetails = new GetAllCustomerResultSet();
    AllConfigIsHere allConfigIsHere = new AllConfigIsHere();
    Database_Config databaseConfig = new Database_Config();
    MessageIcons messageIcons = new MessageIcons();

    /**
     * Creates new form Collection
     */
    public Collection() {
        initComponents();
        getContentPane().setBackground(Color.WHITE);
        this.setExtendedState(MAXIMIZED_BOTH);
        setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource(messageIcons.getFrameIcon())));
        fill_Agent_And_Location_ComboBox();
        TableCellColour();
    }

    void TableCellColour() {

        DefaultTableCellRenderer color_1 = new DefaultTableCellRenderer();
        DefaultTableCellRenderer color_2 = new DefaultTableCellRenderer();

        color_1.setBackground(new Color(214, 217, 224));
        color_1.setHorizontalAlignment(JLabel.RIGHT);
        info.getColumnModel().getColumn(8).setCellRenderer(color_1);
        info.getColumnModel().getColumn(10).setCellRenderer(color_1);

        color_2.setBackground(new Color(255, 51, 51));
        color_2.setForeground(Color.white);
        color_2.setHorizontalAlignment(JLabel.RIGHT);
        info.getColumnModel().getColumn(12).setCellRenderer(color_2);

    }

    void setTableCellData(int row) {

        try {
            double total_Days_Before_Paid = (double) info.getModel().getValueAt(row, 7);

            double collection = (double) info.getModel().getValueAt(row, 8);
            int rental = (int) info.getModel().getValueAt(row, 4);
            info.setValueAt(total_Days_Before_Paid + collection, row, 9);//set totaldays for get a look :)
            info.setValueAt(rental * Double.parseDouble(collection + ""), row, 10);

            //calculate arreas
            String startDate = (String) info.getModel().getValueAt(row, 2);
            String today = allConfigIsHere.getDateFormat().format(new Date());

            long d1 = allConfigIsHere.getDateFormat().parse(startDate).getTime();
            long d2 = allConfigIsHere.getDateFormat().parse(today).getTime();
            int dateDifferent = (int) Math.abs((d1 - d2) / (1000 * 60 * 60 * 24));
            double total_Days_After_Paid = (double) info.getModel().getValueAt(row, 9);

            double areas = (double) (dateDifferent - total_Days_After_Paid);
            info.setValueAt(rental * areas, row, 11);

        } catch (Exception e) {
            System.out.println(e);
        }

    }

    //autofill agent and location combox while opening frame
    void fill_Agent_And_Location_ComboBox() {

        try {

            String sql_00 = "SELECT name FROM employees";
            ResultSet rs_00 = new Database_Config().getStatement().executeQuery(sql_00);
            while (rs_00.next()) {

                kbxAgent.addItem(rs_00.getString("name"));
            }

            String sql_01 = "SELECT lineName FROM c_location";
            ResultSet rs_01 = new Database_Config().getStatement().executeQuery(sql_01);
            while (rs_01.next()) {

                txtLineName.addItem(rs_01.getString("lineName"));
            }

        } catch (Exception e) {
            System.out.println(e);
        }

    }

    //load processing customers according to lineName and Agent
    void loadCustomers() {
        String lineName;
        try {

            DefaultTableModel dtm = (DefaultTableModel) info.getModel();
            allConfigIsHere.removeAllTableData(dtm);

            if (txtLineName.getSelectedItem().toString().equals("All Lines")) {
                lineName = "AND";
            } else {
                lineName = "AND LOCATION.lineName = '" + txtLineName.getSelectedItem().toString() + "' AND ";
            }

            getAllCustomerDetails.setRs_00("WHERE EMPLOYEES.name = '" + kbxAgent.getSelectedItem().toString() + "' " + lineName + " CUSTOMERS.status = 'P'");
            while (getAllCustomerDetails.getRs_00().next()) {

                Vector v = new Vector();
                v.add(getAllCustomerDetails.getRs_00().getInt("idCustomers"));
                v.add(getAllCustomerDetails.getRs_00().getString("firstName") + " " + getAllCustomerDetails.getRs_00().getString("lastName"));
                v.add(getAllCustomerDetails.getRs_00().getString("startDate"));
                v.add(getAllCustomerDetails.getRs_00().getDouble("loanAmount"));
                v.add(getAllCustomerDetails.getRs_00().getInt("rental"));
                v.add(getAllCustomerDetails.getRs_00().getDouble("paid"));
                v.add(getAllCustomerDetails.getRs_00().getDouble("toPaid"));
                v.add(getAllCustomerDetails.getRs_00().getDouble("totalDays"));
                v.add(0.0);
                v.add(getAllCustomerDetails.getRs_00().getDouble("totalDays"));
                v.add(0.0);
                v.add(getAllCustomerDetails.getRs_00().getDouble("areas"));
                v.add(getAllCustomerDetails.getRs_00().getInt("idloanDetails"));

                dtm.addRow(v);

            }

            //coustomer count for label
            if (dtm.getRowCount() == 1) {
                lblCustomerCount.setText(dtm.getRowCount() + " Customer");
            } else if (dtm.getRowCount() == 0) {
                lblCustomerCount.setText(null);
            } else if (dtm.getRowCount() > 1) {
                lblCustomerCount.setText(dtm.getRowCount() + " Customers");
            }

            //add extra empty row to the end.prevent ArrayIndexOutOfBoundsException 
            Vector v = new Vector();
            v.add("");
            dtm.addRow(v);

        } catch (Exception e) {
            System.out.println(e);
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

        txtLineName = new javax.swing.JComboBox();
        jButton1 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        info = new javax.swing.JTable();
        kbxAgent = new javax.swing.JComboBox();
        jButton2 = new javax.swing.JButton();
        jButton8 = new javax.swing.JButton();
        lblCustomerCount = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Collection");
        setMinimumSize(new java.awt.Dimension(1194, 634));

        txtLineName.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtLineName.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "All Lines" }));
        txtLineName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtLineNameActionPerformed(evt);
            }
        });

        jButton1.setBackground(new java.awt.Color(0, 0, 0));
        jButton1.setFont(new java.awt.Font("Dialog", 0, 20)); // NOI18N
        jButton1.setForeground(new java.awt.Color(255, 255, 255));
        jButton1.setText("SAVE");
        jButton1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton1.setFocusPainted(false);
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jScrollPane1.setBackground(new java.awt.Color(0, 204, 204));
        jScrollPane1.setForeground(new java.awt.Color(255, 102, 0));

        info.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        info.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Code", "Name", "Start Date", "Loan Amount", "Rental", "Paid 5", "To Pay 6", "Days 7", "C 8", "Days 9", "Collection 10", "Ars", "LC"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Double.class, java.lang.Double.class, java.lang.Double.class, java.lang.Double.class, java.lang.Double.class, java.lang.Double.class, java.lang.Double.class, java.lang.Double.class, java.lang.Double.class
            };
            boolean[] canEdit = new boolean [] {
                true, false, false, false, false, false, false, false, true, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        info.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_ALL_COLUMNS);
        info.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        info.setSelectionBackground(new java.awt.Color(0, 102, 102));
        info.getTableHeader().setReorderingAllowed(false);
        info.addAncestorListener(new javax.swing.event.AncestorListener() {
            public void ancestorMoved(javax.swing.event.AncestorEvent evt) {
                infoAncestorMoved(evt);
            }
            public void ancestorAdded(javax.swing.event.AncestorEvent evt) {
            }
            public void ancestorRemoved(javax.swing.event.AncestorEvent evt) {
            }
        });
        info.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentMoved(java.awt.event.ComponentEvent evt) {
                infoComponentMoved(evt);
            }
        });
        info.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                infoKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                infoKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                infoKeyTyped(evt);
            }
        });
        jScrollPane1.setViewportView(info);
        if (info.getColumnModel().getColumnCount() > 0) {
            info.getColumnModel().getColumn(1).setPreferredWidth(200);
            info.getColumnModel().getColumn(12).setPreferredWidth(5);
        }

        kbxAgent.setBackground(new java.awt.Color(204, 204, 204));
        kbxAgent.setFont(new java.awt.Font("Dialog", 0, 13)); // NOI18N
        kbxAgent.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                kbxAgentActionPerformed(evt);
            }
        });

        jButton2.setBackground(new java.awt.Color(0, 0, 0));
        jButton2.setFont(new java.awt.Font("Dialog", 0, 22)); // NOI18N
        jButton2.setForeground(new java.awt.Color(255, 255, 255));
        jButton2.setText("By Name");
        jButton2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton2.setFocusPainted(false);
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton8.setBackground(new java.awt.Color(255, 51, 51));
        jButton8.setFont(new java.awt.Font("Dialog", 1, 30)); // NOI18N
        jButton8.setForeground(new java.awt.Color(255, 255, 255));
        jButton8.setText("MENU");
        jButton8.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });

        lblCustomerCount.setFont(new java.awt.Font("Segoe UI Light", 1, 24)); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton8, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1)
                        .addContainerGap())
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(txtLineName, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(kbxAgent, javax.swing.GroupLayout.PREFERRED_SIZE, 202, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(lblCustomerCount, javax.swing.GroupLayout.PREFERRED_SIZE, 304, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 57, Short.MAX_VALUE)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 226, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(11, 11, 11))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(kbxAgent, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtLineName, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblCustomerCount, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 469, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton8, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        setSize(new java.awt.Dimension(1194, 634));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void txtLineNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtLineNameActionPerformed

        loadCustomers();

    }//GEN-LAST:event_txtLineNameActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed

        try {

            int tableRowCount = info.getRowCount();
            for (int i = 0; i < tableRowCount - 1; i++) { //tableRowCount-1 = avoid last empty row

                double paid = (double) info.getValueAt(i, 5) + (double) info.getValueAt(i, 10);
                int rental = (int) info.getValueAt(i, 4);
                int toPaid = (int) (rental * 65 - paid);
                double totalDays = (double) info.getValueAt(i, 9);
                double areas = (double) info.getValueAt(i, 11);
                int idLoanDetails = (int) info.getValueAt(i, 12);

                databaseConfig.getConnection().setAutoCommit(false);
                PreparedStatement updateLoanDetails = null;
                String updateSQL = "UPDATE loandetails SET paid = ?,toPaid=?,totalDays=?,areas=? WHERE idloanDetails=?";
                updateLoanDetails = databaseConfig.getConnection().prepareStatement(updateSQL);
                updateLoanDetails.setDouble(1, paid);
                updateLoanDetails.setDouble(2, toPaid);
                updateLoanDetails.setDouble(3, totalDays);
                updateLoanDetails.setDouble(4, areas);
                updateLoanDetails.setInt(5, idLoanDetails);
                updateLoanDetails.executeUpdate();

                //set customer status as finished
                if (toPaid <= 0) {
                    int idCustomers = (int) info.getValueAt(i, 0);
                    String updateCustomerStatus = "UPDATE customers SET status = ? WHERE idCustomers=?";
                    updateLoanDetails = databaseConfig.getConnection().prepareStatement(updateCustomerStatus);
                    updateLoanDetails.setString(1, "F");
                    updateLoanDetails.setInt(2, idCustomers);
                    updateLoanDetails.executeUpdate();

                }
                databaseConfig.getConnection().commit();

            }

            Toolkit.getDefaultToolkit().beep();
            JOptionPane.showMessageDialog(null, "Daily collection has been updated.! ", "Note", JOptionPane.ERROR_MESSAGE, messageIcons.getOk());

            loadCustomers();

        } catch (Exception e) {
            System.out.println(e);
            e.printStackTrace();
        }

    }//GEN-LAST:event_jButton1ActionPerformed

    private void infoComponentMoved(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_infoComponentMoved
        // TODO add your handling code here:
    }//GEN-LAST:event_infoComponentMoved

    private void infoAncestorMoved(javax.swing.event.AncestorEvent evt) {//GEN-FIRST:event_infoAncestorMoved
        // TODO add your handling code here:
    }//GEN-LAST:event_infoAncestorMoved

    private void infoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_infoKeyPressed
        if (evt.getKeyChar() >= '0' && evt.getKeyChar() <= '9') {

            if (info.getSelectedColumn() == 8) {
                info.setValueAt("", info.getSelectedRow(), info.getSelectedColumn());
            }
        }

    }//GEN-LAST:event_infoKeyPressed

    private void infoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_infoKeyReleased

        try {
            if (evt.getKeyCode() == KeyEvent.VK_ENTER || evt.getKeyCode() == 40) { //40 = down key

                setTableCellData(info.getSelectedRow() - 1);
            } else if (evt.getKeyCode() == 38) { // 38 = up key
                setTableCellData(info.getSelectedRow() + 1);
            } else if (evt.getKeyCode() == 39 || evt.getKeyCode() == 37 || evt.getKeyCode() == 9) {
                setTableCellData(info.getSelectedRow());
            }

        } catch (Exception e) {
            e.printStackTrace();
        }


    }//GEN-LAST:event_infoKeyReleased

    private void infoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_infoKeyTyped


    }//GEN-LAST:event_infoKeyTyped

    private void kbxAgentActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_kbxAgentActionPerformed
        loadCustomers();
    }//GEN-LAST:event_kbxAgentActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
       new SearchByName().setVisible(true);
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
        new Menu().menuObject.setVisible(true);
        dispose();
    }//GEN-LAST:event_jButton8ActionPerformed

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
            java.util.logging.Logger.getLogger(Collection.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Collection.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Collection.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Collection.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Collection().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable info;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JComboBox kbxAgent;
    private javax.swing.JLabel lblCustomerCount;
    private javax.swing.JComboBox txtLineName;
    // End of variables declaration//GEN-END:variables
}
