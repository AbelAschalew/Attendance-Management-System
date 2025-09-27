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

public class UpdateEmployeeLogic {
    public static void updateEmployee(EmployeeClass empObj) {
        try {
            Connection conn = DatabaseConnection.getConnection();
            String employeeid = empObj.getEmployeeid();
            String query1 = "SELECT *FROM EMPLOYEETABLE WHERE EMPLOYEEID='"+employeeid+"'";
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query1);
            if (rs.next()) {
                String query2 = "UPDATE EMPLOYEETABLE SET FNAME=?, MNAME=?, LNAME=?, SHIFT=?, STATUS=? WHERE EMPLOYEEID=?";
                PreparedStatement stmt = conn.prepareStatement(query2);
                stmt.setString(1, empObj.getFname());
                stmt.setString(2, empObj.getMname());
                stmt.setString(3, empObj.getLname());
                stmt.setString(4, empObj.getShift());
                stmt.setString(5, empObj.getStatus());
                stmt.setString(6, empObj.getEmployeeid());
                int rowsaffected = stmt.executeUpdate();
                if (rowsaffected > 0) {
                    JOptionPane.showMessageDialog(null, "Update Successfull!");
                } else{
                    JOptionPane.showMessageDialog(null, "Update Failed!");
                }
                //stmt.close();
            } else {
                JOptionPane.showMessageDialog(null, "Employee ID is Invalid!");
            }
            /*st.close();
            rs.close();
            conn.close();*/
            
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }
    
    public static EmployeeClass checkEmployeeId(String employeeid) {
        try {
            Connection conn = DatabaseConnection.getConnection();
            //String employeeid = empObj.getEmployeeid();
            String query1 = "SELECT *FROM EMPLOYEETABLE WHERE EMPLOYEEID='"+employeeid+"'";
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query1);
            if (rs.next()) {
                String employeeids = rs.getString("EMPLOYEEID");
                String fnames = rs.getString("FNAME");
                String mnames = rs.getString("MNAME");
                String lnames = rs.getString("LNAME");
                String shifts = rs.getString("SHIFT");
                String statuss = rs.getString("STATUS");
                EmployeeClass empDBobj = new EmployeeClass(employeeids, fnames, mnames, lnames, shifts, statuss);
                //rs.close();
                //st.close();
                //conn.close();
                /*rs.close();
                st.close();
                conn.close();*/
                return empDBobj;
            } else {
                JOptionPane.showMessageDialog(null, "Employee ID is Invalid!");
                //rs.close();
                //st.close();
                //conn.close();
               /* rs.close();
                st.close();
                conn.close();*/
                return null;
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
            return null;
        } 
    }
    
}
