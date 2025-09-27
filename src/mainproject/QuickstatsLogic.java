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

public class QuickstatsLogic {
    public static int totalEmployees() {
        try {
            Connection conn = DatabaseConnection.getConnection();
            String query = "SELECT COUNT(EMPLOYEEID) AS TOTAL FROM EMPLOYEETABLE";
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            if (rs.next()) {
                int totalEmp = rs.getInt("TOTAL");
                /*rs.close();
                st.close();
                conn.close();*/
                return totalEmp;
            } else {
                /*rs.close();
                st.close();
                conn.close();*/
                return -1;
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
            return -1;
        }
    }
    
    public static int presentEmployees () {
        try {
            Connection conn = DatabaseConnection.getConnection();
            String query = "SELECT COUNT(EMPLOYEEID) AS TOTAL FROM EMPLOYEETABLE WHERE STATUS='Present'";
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            if (rs.next()) {
                int totalEmp = rs.getInt("TOTAL");
                /*rs.close();
                st.close();
                conn.close();*/
                return totalEmp;
            } else {
                /*rs.close();
                st.close();
                conn.close();*/
                return -1;
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
            return -1;
        }
    }
    
    public static int absentEmployees () {
        try {
            Connection conn = DatabaseConnection.getConnection();
            String query = "SELECT COUNT(EMPLOYEEID) AS TOTAL FROM EMPLOYEETABLE WHERE STATUS='Absent'";
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            if (rs.next()) {
                int totalEmp = rs.getInt("TOTAL");
                /*rs.close();
                st.close();
                conn.close();*/
                return totalEmp;
            } else {
                /*rs.close();
                st.close();
                conn.close();*/
                return -1;
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
            return -1;
        }
    }
    
}
