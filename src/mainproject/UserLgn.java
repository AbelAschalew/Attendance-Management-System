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

public class UserLgn {
    public static UserClass authenticate(String username, String password) {
        String query = "SELECT *FROM USERTABLE WHERE USERNAME=? AND PASSWORD=?";
        try {
            Connection conn = DatabaseConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, username);
            stmt.setString(2, password);
            
            ResultSet rs = stmt.executeQuery();
            if(rs.next()) {
                /*rs.close();
                stmt.close();
                conn.close();*/
                return new UserClass (rs.getString("USERID"), rs.getString("USERNAME"), rs.getString("PASSWORD"), rs.getString("FNAME"), rs.getString("MNAME"), rs.getString("LNAME"), rs.getString("ROLE"));
                
            }
            /*rs.close();
            stmt.close();
            conn.close();*/
        } catch (Exception e) {
            System.out.println("Failed Authentication: "+e.getMessage());
        }
        return null;
    }
}
