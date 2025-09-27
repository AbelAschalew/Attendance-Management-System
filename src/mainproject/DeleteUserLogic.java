/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mainproject;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import static mainproject.DeleteEmployeeLogic.deleteEmployeeUser;

/**
 *
 * @author abela
 */
public class DeleteUserLogic {
    public static String checkId (String userid) {
        try {
            Connection conn = DatabaseConnection.getConnection();
            String query = "SELECT *FROM USERTABLE WHERE USERID=?";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, userid);
            ResultSet rs = stmt.executeQuery();
            String fullname = "";
            if (rs.next()) {
                fullname = rs.getString("FNAME")+" "+rs.getString("MNAME")+" "+rs.getString("LNAME");
                return fullname;
            } else {
                return "";
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
            return "";
        }
    }
    
    public static void deleteUser (String userid) {
        try {
            Connection conn = DatabaseConnection.getConnection();
            String query = "DELETE FROM USERTABLE WHERE USERID=?";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, userid);
            int rowsaffected = stmt.executeUpdate();
            if (rowsaffected > 0) {
                JOptionPane.showMessageDialog(null, "User Successfully Deleted!");
            } else {
                JOptionPane.showMessageDialog(null, "Delete Not Successful!");
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }
    
}
