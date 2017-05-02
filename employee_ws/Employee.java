/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package employee_ws;

import java.sql.*;
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
@WebService(serviceName = "Employee")
public class Employee {

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
     * Web service operation
     */
    public List<EmployeeList> getDetails() {
        ArrayList<EmployeeList> customerDetails = new ArrayList<EmployeeList>();
        if (conn != null) {
            try {
                String sql = "SELECT * FROM `employee`";
                stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(sql);
                while (rs.next()) {
                    EmployeeList ela = new EmployeeList();
                    ela.setName(rs.getString("name"));
                    ela.setUserName(rs.getString("username"));
                    ela.setPostion(rs.getString("position"));
                    ela.setPassword(rs.getString("password"));
                    customerDetails.add(ela);
                }
            } catch (SQLException ex) {
                Logger.getLogger(Employee.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            EmployeeList ela = new EmployeeList();
            ela.setName(null);
            ela.setPassword(null);
            ela.setPostion(null);
            ela.setUserName(null);

        }

        return customerDetails;
    }

    public boolean checkLogin(String userName, String passWord) {
        try {
            makeConnection();
            System.out.println("Connection established\n");
        } catch (SQLException ex) {
            ex.printStackTrace();
            System.err.println("Connection Failure\n");
        }

        try {
            String sql = "SELECT username, password FROM employee WHERE username = '" + userName + "' AND password = '" + passWord + "'";
            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            if (!rs.next()) {
                return false;
            } else {
                return true;
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false;
    }

    public boolean createEmployee(String name, String position, String uName, String pWord) {
        try {
            makeConnection();
            System.out.println("Connection established\n");
        } catch (SQLException ex) {
            ex.printStackTrace();
            System.err.println("Connection Failure");
        }

        try {
            String sql = "INSERT INTO employee(name, position, username, password) VALUES('" + name + "','" + position + "','" + uName + "','" + pWord + "')";
            stmt = conn.createStatement();
            int rs = stmt.executeUpdate(sql);

            return true;

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return false;
    }

    public boolean editEmployee(String name, String position, String uName, String pWord) {
        try {
            makeConnection();
            System.out.println("Connection established\n");
        } catch (SQLException ex) {
            ex.printStackTrace();
            System.err.println("Connection Failure");
        }

        try {
            String sql = "UPDATE employee SET name='" + name + "', position='" + position + "', username='" + uName + "', password='" + pWord + "' WHERE username='" + uName + "'";
            stmt = conn.createStatement();
            int rs = stmt.executeUpdate(sql);

            return true;

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return false;
    }

    public boolean deleteEmployee(String uName) {
        try {
            makeConnection();
            System.out.println("Connection established\n");
        } catch (SQLException ex) {
            ex.printStackTrace();
            System.err.println("Connection Failure");
        }

        try {
            String sql = "DELETE FROM employee WHERE username='" + uName + "'";
            stmt = conn.createStatement();
            int rs = stmt.executeUpdate(sql);

            return true;

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return false;
    }

}
