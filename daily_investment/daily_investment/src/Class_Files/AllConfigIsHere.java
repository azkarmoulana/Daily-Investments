/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Class_Files;

import java.text.SimpleDateFormat;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Ishan Darshana
 */
public class AllConfigIsHere {

    

    private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    private String gender;
    private static String reportVisible;

    /**
     * @return the dateFormat
     */
    public SimpleDateFormat getDateFormat() {
        return dateFormat;
    }

    public void removeAllTableData(DefaultTableModel dtm) {
        for (int i = dtm.getRowCount() - 1; i >= 0; i--) {
            dtm.removeRow(i);
        }
    }

    /**
     * @return the gender
     */
    public String getGender(String nic) {

        int d = Integer.parseInt(nic.substring(2, 5));
        if (d > 500) {
            this.gender = "Female";
        } else {
            this.gender = "Male";
        }
        return gender;
    }
    
    
    
    
    
    /**
     * @return the reportVisible
     */
    public static String getReportVisible() {
        return reportVisible;
    }

    /**
     * @param aReportVisible the reportVisible to set
     */
    public static void setReportVisible(String aReportVisible) {
        reportVisible = aReportVisible;
    }

}
