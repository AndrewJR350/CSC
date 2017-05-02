/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package employee_ws;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;

/**
 *
 * @author Andrew JR
 */
@WebService(serviceName = "Customer")
public class Customer {

    private static Connection conn = null;
    private static Statement stmt = null;

    private Connection makeConnection() throws SQLException {
        if (conn == null) {
            new com.mysql.jdbc.Driver();
            conn = DriverManager.getConnection("jdbc:mysql://localhost/ws_db", "root", "");
        }
        return conn;
    }

    /**
     * This is a sample web service operation
     */
    public boolean deleteCustomer(String accountNumber) {
        try {
            makeConnection();
            System.out.println("Connection established\n");
        } catch (SQLException ex) {
            ex.printStackTrace();
            System.err.println("Connection Failure");
        }

        try {
            String sql = "DELETE FROM customer WHERE accountNumber='" + accountNumber + "'";
            stmt = conn.createStatement();
            int rs = stmt.executeUpdate(sql);

            return true;

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return false;
    }

    public List<CustomerList> getCustomerDetails() {
        try {
            makeConnection();
            System.out.println("Connection established\n");
        } catch (SQLException ex) {
            ex.printStackTrace();
            System.err.println("Connection Failure\n");
        }

        ArrayList<CustomerList> customerDetails = new ArrayList<CustomerList>();

        if (conn != null) {
            try {
                String sql = "SELECT * FROM `customer`";
                stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(sql);
                while (rs.next()) {
                    System.out.println("test");
                    CustomerList ela = new CustomerList();
                    ela.setName(rs.getString("name"));
                    ela.setAddress(rs.getString("address"));
                    ela.setBirthDate(rs.getString("birthDate"));
                    ela.setMobile(rs.getInt("moblie"));
                    ela.setEmail(rs.getString("email"));
                    ela.setAccountType(rs.getString("accountType"));
                    ela.setAccountNumber(rs.getString("accountNumber"));
                    ela.setSortCode(rs.getString("sortCode"));
                    ela.setBalance(rs.getInt("balance"));
                    ela.setCard(rs.getString("card"));
                    customerDetails.add(ela);
                }
            } catch (SQLException ex) {
                Logger.getLogger(Employee.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            System.out.println("test");
            CustomerList ela = new CustomerList();
            ela.setName(null);
            ela.setAddress(null);
            ela.setBirthDate(null);
            ela.setMobile(-45);
            ela.setEmail(null);
            ela.setAccountType(null);
            ela.setAccountNumber(null);
            ela.setSortCode(null);
            ela.setBalance(-45);
            ela.setCard(null);
            customerDetails.add(ela);
        }
        System.out.println(customerDetails.get(0).getAddress());
        return customerDetails;
    }

    public boolean createCustomer(String name, String birthDate, String address, int mobile, String email, String accountType, String accountNumber, String sortCode, int balance, String card) {
        try {
            makeConnection();
            System.out.println("Connection established\n");
        } catch (SQLException ex) {
            ex.printStackTrace();
            System.err.println("Connection Failure");
        }

        try {
            String sql = "INSERT INTO customer(name, birthDate, address , moblie, email, accountType, accountNumber, sortCode, balance, card) VALUES('" + name + "','" + birthDate + "','" + address + "'," + mobile + ",'" + email + "','" + accountType + "','" + accountNumber + "','" + sortCode + "'," + balance + ",'" + card + "')";
            stmt = conn.createStatement();
            int rs = stmt.executeUpdate(sql);

            return true;

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return false;
    }

    public boolean editcustomer(String name, String birthDate, String address, int mobile, String email, String accountType, String accountNumber, String sortCode, int balance, String card) {
//        System.out.println( name + " "+  birthDate + " "+  address + " "+  mobile + " "+  email + " "+  accountType + " "+  accountNumber + " "+  sortCode + " "+  balance + " "+  card);
        try {
            makeConnection();
            System.out.println("Connection established\n");
        } catch (SQLException ex) {
            ex.printStackTrace();
            System.err.println("Connection Failure");
        }

        try {
            String sql = "UPDATE `customer` SET `name`='" + name + "',`birthDate`='" + birthDate + "',`address`='" + address + "',`moblie`=" + mobile + ",`email`='" + email + "',`accountType`='" + accountType + "',`accountNumber`='" + accountNumber + "',`sortCode`='" + sortCode + "',`balance`=" + balance + ",`card`='" + card + "' WHERE accountNumber='" + accountNumber + "'";

            stmt = conn.createStatement();
            int rs = stmt.executeUpdate(sql);

            return true;

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return false;
    }

}
