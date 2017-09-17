/*
 * To change this template, choose Tools | Templates
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
import java.awt.event.KeyEvent;
import static java.awt.image.ImageObserver.WIDTH;
import java.util.List;
import java.util.Vector;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author IshAN DarsHanA
 */
public class SearchByName extends javax.swing.JFrame {

    GetAllCustomerResultSet getAllCustomerDetails = new GetAllCustomerResultSet();
    AllConfigIsHere allConfigIsHere = new AllConfigIsHere();
    Database_Config databaseConfig = new Database_Config();
    MessageIcons messageIcons = new MessageIcons();
    static String finishedStatus = "P";

    /**
     * Creates new form SearchByName
     */
    public SearchByName() {
        initComponents();
        setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource(messageIcons.getFrameIcon())));
        this.setExtendedState(Frame.MAXIMIZED_BOTH);
        getContentPane().setBackground(Color.WHITE);

    }

    void tableAllClear() {

        DefaultTableModel dtm = (DefaultTableModel) tableCustomerDetails.getModel();
        allConfigIsHere.removeAllTableData(dtm);
    }

    void search() {

        try {

            DefaultTableModel dtm = (DefaultTableModel) tableCustomerDetails.getModel();
            getAllCustomerDetails.setRs_00("WHERE CUSTOMERS.firstName LIKE '%" + txtName.getText() + "%' AND CUSTOMERS.status='" + finishedStatus + "'");
            while (getAllCustomerDetails.getRs_00().next()) {

                Vector v = new Vector();

                v.add(getAllCustomerDetails.getRs_00().getString("idCustomers"));
                v.add(getAllCustomerDetails.getRs_00().getString("nic"));
                v.add(getAllCustomerDetails.getRs_00().getString("firstName") + " " + getAllCustomerDetails.getRs_00().getString("lastName"));
                v.add(getAllCustomerDetails.getRs_00().getString("address"));
                v.add(getAllCustomerDetails.getRs_00().getString("name"));
                v.add(getAllCustomerDetails.getRs_00().getString("contactNumber"));
                v.add(getAllCustomerDetails.getRs_00().getString("startDate"));
                v.add(getAllCustomerDetails.getRs_00().getString("endDate"));
                v.add(getAllCustomerDetails.getRs_00().getString("loanAmount"));
                v.add(getAllCustomerDetails.getRs_00().getString("rental"));
                dtm.addRow(v);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jCheckBox1 = new javax.swing.JCheckBox();
        jScrollPane1 = new javax.swing.JScrollPane();
        tableCustomerDetails = new javax.swing.JTable();
        txtName = new javax.swing.JTextField();
        jButton2 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Search By Name");
        setMinimumSize(new java.awt.Dimension(1282, 610));

        jCheckBox1.setFont(new java.awt.Font("Segoe UI Light", 1, 20)); // NOI18N
        jCheckBox1.setText("Search Only Finished Details");
        jCheckBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox1ActionPerformed(evt);
            }
        });

        tableCustomerDetails.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        tableCustomerDetails.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Code", "NIC", "Name", "Address", "Agent", "Telephone", "Start Date", "End Date", "Loan Amount", "Rental"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tableCustomerDetails.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        tableCustomerDetails.setSelectionBackground(new java.awt.Color(0, 102, 102));
        tableCustomerDetails.getTableHeader().setReorderingAllowed(false);
        tableCustomerDetails.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableCustomerDetailsMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tableCustomerDetails);
        if (tableCustomerDetails.getColumnModel().getColumnCount() > 0) {
            tableCustomerDetails.getColumnModel().getColumn(0).setResizable(false);
            tableCustomerDetails.getColumnModel().getColumn(0).setPreferredWidth(10);
            tableCustomerDetails.getColumnModel().getColumn(1).setPreferredWidth(40);
            tableCustomerDetails.getColumnModel().getColumn(5).setPreferredWidth(10);
            tableCustomerDetails.getColumnModel().getColumn(6).setPreferredWidth(40);
            tableCustomerDetails.getColumnModel().getColumn(7).setPreferredWidth(40);
            tableCustomerDetails.getColumnModel().getColumn(9).setPreferredWidth(20);
        }

        txtName.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtName.setForeground(new java.awt.Color(102, 102, 102));
        txtName.setText(" Enter Part of Name....");
        txtName.setToolTipText("Please Enter Part of Name");
        txtName.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtNameMouseClicked(evt);
            }
        });
        txtName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNameActionPerformed(evt);
            }
        });
        txtName.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtNameKeyPressed(evt);
            }
        });

        jButton2.setBackground(new java.awt.Color(0, 0, 0));
        jButton2.setFont(new java.awt.Font("Dialog", 0, 20)); // NOI18N
        jButton2.setForeground(new java.awt.Color(255, 255, 255));
        jButton2.setText("BACK");
        jButton2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton2.setFocusPainted(false);
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jCheckBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 313, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 668, Short.MAX_VALUE)
                        .addComponent(txtName, javax.swing.GroupLayout.PREFERRED_SIZE, 263, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 231, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jCheckBox1)
                    .addComponent(txtName, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 523, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jCheckBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBox1ActionPerformed

        Toolkit.getDefaultToolkit().beep();
        JOptionPane.showMessageDialog(null, "System reverted to find finished customers!", "Security Alert!", WIDTH, messageIcons.getInfo());

        if (jCheckBox1.isSelected()) {
            finishedStatus = "F";

        } else {
            finishedStatus = "P";
        }

        tableAllClear();


    }//GEN-LAST:event_jCheckBox1ActionPerformed

    private void txtNameKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNameKeyPressed

        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            tableAllClear();

            if (!txtName.getText().equals("")) {
                search();
            }

        }
    }//GEN-LAST:event_txtNameKeyPressed

    private void txtNameMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtNameMouseClicked
        if (txtName.getText().equalsIgnoreCase(" Enter Part of Name....")) {
            txtName.setText(null);
        }
    }//GEN-LAST:event_txtNameMouseClicked

    private void tableCustomerDetailsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableCustomerDetailsMouseClicked


    }//GEN-LAST:event_tableCustomerDetailsMouseClicked

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed

        dispose();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void txtNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNameActionPerformed

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
            java.util.logging.Logger.getLogger(SearchByName.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(SearchByName.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(SearchByName.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(SearchByName.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new SearchByName().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton2;
    private javax.swing.JCheckBox jCheckBox1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tableCustomerDetails;
    private javax.swing.JTextField txtName;
    // End of variables declaration//GEN-END:variables
}
