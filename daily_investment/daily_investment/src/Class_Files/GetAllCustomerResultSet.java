/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Class_Files;

import java.sql.ResultSet;

/**
 *
 * @author Ishan Darshana
 */
public class GetAllCustomerResultSet {

    private ResultSet rs_00 = null;
    Database_Config databaseConfig = new Database_Config();

    /**
     * @return the rs_00
     */
    public ResultSet getRs_00() {
        return rs_00;
    }

    /**
     * @param rs_00 the rs_00 to set
     */
    public void setRs_00(String index1) {

        try {
            String sql_00 = "SELECT * FROM customers CUSTOMERS "
                    + "INNER JOIN loandetails LOAN_DETAILS ON CUSTOMERS.loanDetails_idloanDetails = LOAN_DETAILS.idloanDetails "
                    + "INNER JOIN date DATE ON LOAN_DETAILS.date_iddate = DATE.iddate "
                    + "INNER JOIN amount AMOUNT ON LOAN_DETAILS.amount_idamount = AMOUNT.idamount "
                    + "INNER JOIN c_location LOCATION ON CUSTOMERS.Lines_idLines = LOCATION.idLines "
                    + "INNER JOIN employees EMPLOYEES ON CUSTOMERS.Employees_idEmployees = EMPLOYEES.idEmployees "
                    + ""+index1+"";
            
            System.out.println(sql_00);
            this.rs_00 = databaseConfig.getStatement().executeQuery(sql_00);
           
        } catch (Exception e) {
            System.out.println(e);
        }

    }
    
    
    
    

}
