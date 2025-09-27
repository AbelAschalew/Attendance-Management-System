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

public class AddNewUser {
    public static void addNewUser(UserClass usrObj) {
        try {
            Connection conn = DatabaseConnection.getConnection();
            String query = "INSERT INTO USERTABLE VALUES(?,?,?,?,?,?,?)";
            PreparedStatement stmt = conn.prepareStatement(query);
            String usrId = autogenerateUserId();
            stmt.setString(1, usrId);
            //stmt.setString(1, usrObj.getUserid());
            stmt.setString(2, usrObj.getUsername());
            stmt.setString(3, usrObj.getPassword());
            stmt.setString(4, usrObj.getFname());
            stmt.setString(5, usrObj.getMname());
            stmt.setString(6, usrObj.getLname());
            stmt.setString(7, usrObj.getRole());
            int rowsaffected = stmt.executeUpdate();
            if (rowsaffected > 0) {
                JOptionPane.showMessageDialog(null, "User Successfully Added!");
            } else{
                JOptionPane.showMessageDialog(null, "User Not Added!");
            }
            /*stmt.close();
            conn.close();*/
        } catch(SQLException e){
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }
    
    public static String autogenerateUserId () {
        try {
            Connection conn = DatabaseConnection.getConnection();
            String query = "SELECT *FROM USERTABLE";
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            String usrId = null;
            while(rs.next()) {
                usrId = rs.getString("USERID");
            }
            if (usrId == null) {
                /*rs.close();
                st.close();
                conn.close();*/
                return "usr001";
            } else {
                char[] myChar = usrId.toCharArray();
                int fst = Character.getNumericValue(myChar[5]);
                int snd = (Character.getNumericValue(myChar[4])) * 10;
                int thr = (Character.getNumericValue(myChar[3])) * 100;
                int usrNum = fst+snd+thr;
                usrNum++;
                String usrnum = String.valueOf(usrNum);
                if (usrNum < 10) {
                    String empIds = "usr00"+usrnum;
                    /*rs.close();
                    st.close();
                    conn.close();*/
                    return empIds;
                } else if (usrNum > 10 && usrNum < 100) {
                    String empIds = "usr0"+usrnum;
                    /*rs.close();
                    st.close();
                    conn.close();*/
                    return empIds;
                } else {
                    String empIds = "usr"+usrnum;
                    /*rs.close();
                    st.close();
                    conn.close();*/
                    return empIds;
                }
                //return empIds;
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
            return null;
        }
    }
    
}
