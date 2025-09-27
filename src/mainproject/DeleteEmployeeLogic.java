/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mainproject;

/**
 *
 * @author abela
 */

import java.sql.*;
import javax.swing.JOptionPane;

public class DeleteEmployeeLogic {
    
    private static String fname="", mname="", lname="";
    
    public static String checkId (String employeeid) {
        try {
            Connection conn = DatabaseConnection.getConnection();
            String query = "SELECT *FROM EMPLOYEETABLE WHERE EMPLOYEEID=?";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, employeeid);
            ResultSet rs = stmt.executeQuery();
            String fullname = "";
            if (rs.next()) {
                fullname = rs.getString("FNAME")+" "+rs.getString("MNAME")+" "+rs.getString("LNAME");
                fname = rs.getString("FNAME");
                mname = rs.getString("MNAME");
                lname = rs.getString("LNAME");
                return fullname;
            } else {
                return "";
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
            return "";
        }
    }
    
    public static void deleteEmployee (String employeeid) {
        try {
            Connection conn = DatabaseConnection.getConnection();
            String query = "DELETE FROM EMPLOYEETABLE WHERE EMPLOYEEID=?";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, employeeid);
            int rowsaffected = stmt.executeUpdate();
            if (rowsaffected > 0) {
                JOptionPane.showMessageDialog(null, "Employee Successfully Deleted!");
                deleteEmployeeUser();
            } else {
                JOptionPane.showMessageDialog(null, "Delete Not Successful!");
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }
    
    public static void deleteEmployeeUser () {
        try {
            Connection conn = DatabaseConnection.getConnection();
            String query = "DELETE FROM USERTABLE WHERE FNAME=? AND MNAME=? AND LNAME=?";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, fname);
            stmt.setString(2, mname);
            stmt.setString(3, lname);
            int rowsaffected = stmt.executeUpdate();
            if (rowsaffected > 0) {
                JOptionPane.showMessageDialog(null, "Employee also Deleted from User Record!");
            } else {
                JOptionPane.showMessageDialog(null, "Employee Not Deleted from User Record!");
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    } 
    
}
