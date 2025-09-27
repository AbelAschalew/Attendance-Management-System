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
import java.time.LocalDate;
import javax.swing.JOptionPane;
import java.sql.Date;

public class CheckinCheckoutLogic {
    public static String checkStatus(String employeeid) {
        try {
            Connection conn = DatabaseConnection.getConnection();
            String query = "SELECT *FROM EMPLOYEETABLE WHERE EMPLOYEEID=?";
            String status = "";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, employeeid);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                status = rs.getString("STATUS");
                /*rs.close();
                stmt.close();
                conn.close();*/
                return status;
            } else {
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
    
    public static String getEmployeeId(UserClass usrObj) {
        try {
            Connection conn = DatabaseConnection.getConnection();
            String query = "SELECT *FROM EMPLOYEETABLE WHERE FNAME=? AND MNAME=? AND LNAME=?";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, usrObj.getFname());
            stmt.setString(2, usrObj.getMname());
            stmt.setString(3, usrObj.getLname());
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                /*rs.close();
                stmt.close();
                conn.close();*/
                return rs.getString("EMPLOYEEID");
            } else {
                JOptionPane.showMessageDialog(null, "Can not find employeeid!");
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
    
    public static void checkOut (String employeeid) {
        try {
            Connection conn = DatabaseConnection.getConnection();
            String query = "UPDATE EMPLOYEETABLE SET STATUS='Absent' WHERE EMPLOYEEID=?";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, employeeid);
            int rowsaffected = stmt.executeUpdate();
            if (rowsaffected > 0) {
                JOptionPane.showMessageDialog(null, "Check-Out Successfull!");
            } else {
                JOptionPane.showMessageDialog(null, "Check-Out Failed!");
            }
            /*stmt.close();
            conn.close();*/
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }
    
    public static void checkIn(String employeeid) {
        int attid = autogenerateAttId();
        if (attid == -1) {
            JOptionPane.showMessageDialog(null, "AttID returned null!");
        } else {
            Date attdate = getCurrentDate();
            String status = "Present";
            int x = checkTransaction(employeeid);
            if (x == 1) {
                try {
                    Connection conn = DatabaseConnection.getConnection();
                    String query = "INSERT INTO ATTENDANCETABLE VALUES(?,?,?,?)";
                    PreparedStatement stmt = conn.prepareStatement(query);
                    stmt.setInt(1, attid);
                    stmt.setString(2, employeeid);
                    stmt.setDate(3, attdate);
                    stmt.setString(4, status);
                    int rawsaffected = stmt.executeUpdate();
                    if (rawsaffected > 0) {
                        updateEmployeeStatus(employeeid);
                    } else {
                        JOptionPane.showMessageDialog(null, "Check-In Failed");
                    }
                    /*stmt.close();
                    conn.close();*/
                } catch (SQLException e) {
                    JOptionPane.showMessageDialog(null, "Check-In Error: "+e.getMessage());
                }
            } else {
                JOptionPane.showMessageDialog(null, "Comeback tommorrow!");
            }
            
        }
    }
    
    public static int autogenerateAttId () {
        try {
            Connection conn = DatabaseConnection.getConnection();
            String query = "SELECT *FROM ATTENDANCETABLE";
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            int attId = 0;
            while (rs.next()) {
                attId = rs.getInt("ATTENDANCEID");
            }
            if (attId == 0) {
                /*rs.close();
                st.close();
                conn.close();*/
                return 1;
            } else {
                /*char[] myChar = attId.toCharArray();
                int fst = Character.getNumericValue(myChar[8]);
                int snd = (Character.getNumericValue(myChar[7])) * 10;
                int thr = (Character.getNumericValue(myChar[6])) * 100;
                int frt = (Character.getNumericValue(myChar[5])) * 1000;
                int fth = (Character.getNumericValue(myChar[4])) * 10000;
                int attNum = fst+snd+thr+frt+fth;
                String attnum = String.valueOf(attNum);
                String attIds = "Att"+attnum;*/
                attId++;
                /*rs.close();
                st.close();
                conn.close();*/
                return attId;
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Can not find employeeid!"+e.getMessage());
            return -1;
        }
    }
    
    public static Date getCurrentDate() {
        LocalDate localDate = LocalDate.now();
        Date sqlDate = Date.valueOf(localDate);
        return sqlDate;
    }
    
    public static void updateEmployeeStatus(String employeeid) {
        try {
            Connection conn = DatabaseConnection.getConnection();
            String query = "UPDATE EMPLOYEETABLE SET STATUS='Present' WHERE EMPLOYEEID=?";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, employeeid);
            int rowsaffected = stmt.executeUpdate();
            if (rowsaffected > 0) {
                JOptionPane.showMessageDialog(null, "Check-In Successfull");
            } else {
                JOptionPane.showMessageDialog(null, "Cannot update employee status");
            }
            /*stmt.close();
            conn.close();*/
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }
    
    public static int checkTransaction(String employeeid) {
        Date currentDate = getCurrentDate();
        try {
            Connection conn = DatabaseConnection.getConnection();
            String query = "SELECT *FROM ATTENDANCETABLE WHERE EMPLOYEEID=? AND ATTDATE=?";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, employeeid);
            stmt.setDate(2, currentDate);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                /*rs.close();
                stmt.close();
                conn.close();*/
                return 0;
            } else {
                /*rs.close();
                stmt.close();
                conn.close();*/
                return 1;
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
            return 0;
        }
    }
    
}
