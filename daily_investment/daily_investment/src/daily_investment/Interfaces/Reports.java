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
import static daily_investment.Interfaces.SearchByName.finishedStatus;
import java.awt.Color;
import java.sql.Date;
import java.util.Vector;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Ishan Darshana
 */
public class Reports extends javax.swing.JFrame {

    GetAllCustomerResultSet getAllCustomerDetails = new GetAllCustomerResultSet();
    AllConfigIsHere allConfigIsHere = new AllConfigIsHere();
    Database_Config databaseConfig = new Database_Config();
    MessageIcons messageIcons = new MessageIcons();

    /**
     * Creates new form Reports
     */
    public Reports() {
        initComponents();
        getContentPane().setBackground(Color.WHITE);
        panelVisible();
        
    }

    void panelVisible() {

        if (allConfigIsHere.getReportVisible().equals("General")) {
            
            finalReportPanel.setVisible(false);
            buttonFinal.setVisible(false);
            
        } 

    }

    void addData_to_Table() {

        try {
            DefaultTableModel dtm = (DefaultTableModel) reportTable.getModel();
            while (getAllCustomerDetails.getRs_00().next()) {

                Vector v = new Vector();

                double totalDays = Double.parseDouble(getAllCustomerDetails.getRs_00().getString("totalDays"));
                double dayCount = Double.parseDouble(getAllCustomerDetails.getRs_00().getString("dayCount"));

                v.add(getAllCustomerDetails.getRs_00().getString("idCustomers"));
                v.add(getAllCustomerDetails.getRs_00().getString("firstName") + " " + getAllCustomerDetails.getRs_00().getString("lastName"));
                v.add(getAllCustomerDetails.getRs_00().getString("startDate"));
                v.add(getAllCustomerDetails.getRs_00().getString("endDate"));
                v.add(getAllCustomerDetails.getRs_00().getString("loanAmount"));
                v.add(getAllCustomerDetails.getRs_00().getString("rental"));
                v.add(getAllCustomerDetails.getRs_00().getString("contactNumber"));
                v.add(totalDays - dayCount);
                v.add(dayCount);
                v.add(totalDays);
                v.add(getAllCustomerDetails.getRs_00().getString("collection"));
                v.add(getAllCustomerDetails.getRs_00().getString("paid"));
                v.add(getAllCustomerDetails.getRs_00().getString("areas"));
                dtm.addRow(v);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    void tableAllClear() {

        DefaultTableModel dtm = (DefaultTableModel) reportTable.getModel();
        allConfigIsHere.removeAllTableData(dtm);
    }

    void getGeneralReport(String status, String agent, String location) {

        try {

            if (kbxstatus.getSelectedItem().toString().equals("All") && kbxAgent.getSelectedItem().toString().equals("All Agents") && kbxLineName.getSelectedItem().toString().equals("All Lines")) {
                getAllCustomerDetails.setRs_00("");

                addData_to_Table();

            } else {

                if (kbxstatus.getSelectedItem().toString().equals("All")) {
                    status = "in ('P','F')";
                } else if (kbxstatus.getSelectedItem().toString().equals("Processing")) {
                    status = " = 'P'";
                } else {
                    status = " = 'F'";
                }

                if (kbxAgent.getSelectedItem().toString().equals("All Agents")) {
                    agent = "";
                } else {
                    agent = "AND EMPLOYEES.name = '" + kbxAgent.getSelectedItem().toString() + "'";
                }

                if (kbxLineName.getSelectedItem().toString().equals("All Lines")) {
                    location = "";
                } else {
                    location = "AND LOCATION.lineName = '" + kbxLineName.getSelectedItem().toString() + "'";
                }

                getAllCustomerDetails.setRs_00("WHERE CUSTOMERS.status " + status + " " + agent + " " + location + "");
                addData_to_Table();

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

        buttonFinal = new javax.swing.JButton();
        kbxstatus = new javax.swing.JComboBox();
        finalReportPanel = new javax.swing.JPanel();
        jButton4 = new javax.swing.JButton();
        cbxNotPaid = new javax.swing.JCheckBox();
        buttonGeneral = new javax.swing.JButton();
        kbxLineName = new javax.swing.JComboBox();
        jLabel1 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        kbxAgent = new javax.swing.JComboBox();
        jScrollPane1 = new javax.swing.JScrollPane();
        reportTable = new javax.swing.JTable();
        jButton8 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Reports");

        buttonFinal.setBackground(new java.awt.Color(0, 0, 0));
        buttonFinal.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        buttonFinal.setForeground(new java.awt.Color(255, 255, 255));
        buttonFinal.setText("Final Report");
        buttonFinal.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        buttonFinal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonFinalActionPerformed(evt);
            }
        });

        kbxstatus.setBackground(new java.awt.Color(204, 204, 204));
        kbxstatus.setFont(new java.awt.Font("Dialog", 0, 15)); // NOI18N
        kbxstatus.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "All", "Processing", "Finished" }));
        kbxstatus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                kbxstatusActionPerformed(evt);
            }
        });

        finalReportPanel.setBackground(new java.awt.Color(255, 255, 255));

        jButton4.setBackground(new java.awt.Color(0, 0, 0));
        jButton4.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jButton4.setForeground(new java.awt.Color(255, 255, 255));
        jButton4.setText("Find");
        jButton4.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        cbxNotPaid.setFont(new java.awt.Font("Dialog", 0, 15)); // NOI18N
        cbxNotPaid.setText("Only Not Paid Report");
        cbxNotPaid.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        cbxNotPaid.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbxNotPaidActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout finalReportPanelLayout = new javax.swing.GroupLayout(finalReportPanel);
        finalReportPanel.setLayout(finalReportPanelLayout);
        finalReportPanelLayout.setHorizontalGroup(
            finalReportPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(finalReportPanelLayout.createSequentialGroup()
                .addGap(303, 303, 303)
                .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cbxNotPaid)
                .addGap(0, 14, Short.MAX_VALUE))
        );
        finalReportPanelLayout.setVerticalGroup(
            finalReportPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(finalReportPanelLayout.createSequentialGroup()
                .addGroup(finalReportPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbxNotPaid))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        buttonGeneral.setBackground(new java.awt.Color(0, 0, 0));
        buttonGeneral.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        buttonGeneral.setForeground(new java.awt.Color(255, 255, 255));
        buttonGeneral.setText("Get General");
        buttonGeneral.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        buttonGeneral.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonGeneralActionPerformed(evt);
            }
        });

        kbxLineName.setBackground(new java.awt.Color(204, 204, 204));
        kbxLineName.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        kbxLineName.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "All Lines", "Gampaha" }));
        kbxLineName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                kbxLineNameActionPerformed(evt);
            }
        });
        kbxLineName.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                kbxLineNameKeyReleased(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Segoe UI Light", 0, 19)); // NOI18N
        jLabel1.setText("Select Day of Week, Agent & Line Name Before Hit on Get Report");

        kbxAgent.setBackground(new java.awt.Color(204, 204, 204));
        kbxAgent.setFont(new java.awt.Font("Dialog", 0, 13)); // NOI18N
        kbxAgent.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "All Agents", "ishan" }));
        kbxAgent.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                kbxAgentActionPerformed(evt);
            }
        });

        reportTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Code", "Name", "Start Date", "End Date", "Loan Amount", "Telephone", "Rental", "Blns", "L", "Paid Days", "Last Installment", "Total Paid", "Areas Days"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        reportTable.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        reportTable.setSelectionBackground(new java.awt.Color(0, 204, 204));
        jScrollPane1.setViewportView(reportTable);

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

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(jLabel1)
                .addGap(43, 43, 43)
                .addComponent(finalReportPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 1141, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGroup(layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(buttonGeneral, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(7, 7, 7)
                .addComponent(buttonFinal, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(322, 322, 322)
                .addComponent(kbxstatus, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(7, 7, 7)
                .addComponent(kbxAgent, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(7, 7, 7)
                .addComponent(kbxLineName, javax.swing.GroupLayout.PREFERRED_SIZE, 206, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1129, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(layout.createSequentialGroup()
                .addGap(940, 940, 940)
                .addComponent(jButton8, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(9, 9, 9)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addComponent(jLabel1))
                    .addComponent(finalReportPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(9, 9, 9)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(11, 11, 11)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(buttonGeneral, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(buttonFinal, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(kbxstatus, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(kbxAgent, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(kbxLineName, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(13, 13, 13)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 419, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(7, 7, 7)
                .addComponent(jButton8, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        setSize(new java.awt.Dimension(1170, 652));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void buttonFinalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonFinalActionPerformed

    }//GEN-LAST:event_buttonFinalActionPerformed

    private void kbxstatusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_kbxstatusActionPerformed

        tableAllClear();
        getGeneralReport(kbxstatus.getSelectedItem().toString(), kbxAgent.getSelectedItem().toString(), kbxLineName.getSelectedItem().toString());

    }//GEN-LAST:event_kbxstatusActionPerformed

    private void buttonGeneralActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonGeneralActionPerformed


    }//GEN-LAST:event_buttonGeneralActionPerformed

    private void kbxLineNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_kbxLineNameActionPerformed
        tableAllClear();
        getGeneralReport(kbxstatus.getSelectedItem().toString(), kbxAgent.getSelectedItem().toString(), kbxLineName.getSelectedItem().toString());

    }//GEN-LAST:event_kbxLineNameActionPerformed

    private void kbxLineNameKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_kbxLineNameKeyReleased

    }//GEN-LAST:event_kbxLineNameKeyReleased

    private void kbxAgentActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_kbxAgentActionPerformed
        tableAllClear();
        getGeneralReport(kbxstatus.getSelectedItem().toString(), kbxAgent.getSelectedItem().toString(), kbxLineName.getSelectedItem().toString());
    }//GEN-LAST:event_kbxAgentActionPerformed

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
        new Menu().menuObject.setVisible(true);
        dispose();
    }//GEN-LAST:event_jButton8ActionPerformed

    private void cbxNotPaidActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbxNotPaidActionPerformed

    }//GEN-LAST:event_cbxNotPaidActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed

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
            java.util.logging.Logger.getLogger(Reports.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Reports.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Reports.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Reports.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Reports().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton buttonFinal;
    private javax.swing.JButton buttonGeneral;
    private javax.swing.JCheckBox cbxNotPaid;
    private javax.swing.JPanel finalReportPanel;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton8;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JComboBox kbxAgent;
    private javax.swing.JComboBox kbxLineName;
    private javax.swing.JComboBox kbxstatus;
    private javax.swing.JTable reportTable;
    // End of variables declaration//GEN-END:variables
}