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

public class UpdateUserLogic {
    public static UserClass checkUserId (String userid) {
        try {
            Connection conn = DatabaseConnection.getConnection();
            String query = "SELECT *FROM USERTABLE WHERE USERID=?";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, userid);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                String userids = rs.getString("USERID");
                String usernames = rs.getString("USERNAME");
                String passwords = rs.getString("PASSWORD");
                String fnames = rs.getString("FNAME");
                String mnames = rs.getString("MNAME");
                String lnames = rs.getString("LNAME");
                String roles = rs.getString("ROLE");
                UserClass usrDBobj = new UserClass(userids, usernames, passwords, fnames, mnames, lnames, roles);
                /*rs.close();
                stmt.close();
                conn.close();*/
                return usrDBobj;
            } else {
                JOptionPane.showMessageDialog(null, "User ID is Invalid!");
                /*rs.close();
                stmt.close();
                conn.close();*/
                return null;
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
            return null;
        }
    }
    
    public static void updateUser (UserClass usrObj) {
        try {
            Connection conn = DatabaseConnection.getConnection();
            String query = "UPDATE USERTABLE SET USERNAME=?,PASSWORD=?,FNAME=?,MNAME=?,LNAME=?,ROLE=? WHERE USERID=?";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, usrObj.getUsername());
            stmt.setString(2, usrObj.getPassword());
            stmt.setString(3, usrObj.getFname());
            stmt.setString(4, usrObj.getMname());
            stmt.setString(5, usrObj.getLname());
            stmt.setString(6, usrObj.getRole());
            stmt.setString(7, usrObj.getUserid());
            int rowsaffected = stmt.executeUpdate();
            if (rowsaffected > 0) {
                JOptionPane.showMessageDialog(null, "Update Successfull!");
            } else {
                JOptionPane.showMessageDialog(null, "Update Failed!");
            }
            /*stmt.close();
            conn.close();*/
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }
    
}
