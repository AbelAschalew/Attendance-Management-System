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

public class AddNewEmployee {
    public static void addNewEmployee(EmployeeClass empObj) {
        try {
            Connection conn = DatabaseConnection.getConnection();
            String query = "INSERT INTO EMPLOYEETABLE (EMPLOYEEID,FNAME,MNAME,LNAME,SHIFT,STATUS) VALUES(?,?,?,?,?,?)";
            PreparedStatement stmt = conn.prepareStatement(query);
            String empId = autogenerateEmployeeId();
            stmt.setString(1, empId);
            //stmt.setString(1, empObj.getEmployeeid());
            stmt.setString(2, empObj.getFname());
            stmt.setString(3, empObj.getMname());
            stmt.setString(4, empObj.getLname());
            stmt.setString(5, empObj.getShift());
            stmt.setString(6, empObj.getStatus());
            int x = stmt.executeUpdate();
            if (x > 0) {
                JOptionPane.showMessageDialog(null, "Employeee Successfully Added!");
                addEmpAsUser(empObj.getFname(), empObj.getMname(), empObj.getLname());
            } else {
                JOptionPane.showMessageDialog(null, "Employeee Not Added!");
            }
            /*conn.close();
            stmt.close();*/
        } catch (SQLException e) {
            System.out.println("Add employee sql error: "+e.getMessage());
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }
    
    public static String autogenerateEmployeeId () {
        try {
            Connection conn = DatabaseConnection.getConnection();
            String query = "SELECT *FROM EMPLOYEETABLE";
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            String empId = null;
            while(rs.next()) {
                empId = rs.getString("EMPLOYEEID");
            }
            if (empId == null) {
                /*rs.close();
                st.close();
                conn.close();*/
                return "emp001";
            } else {
                char[] myChar = empId.toCharArray();
                int fst = Character.getNumericValue(myChar[5]);
                int snd = (Character.getNumericValue(myChar[4])) * 10;
                int thr = (Character.getNumericValue(myChar[3])) * 100;
                int empNum = fst+snd+thr;
                empNum++;
                String empnum = String.valueOf(empNum);
                if (empNum < 10) {
                    String empIds = "emp00"+empnum;
                    /*rs.close();
                    st.close();
                    conn.close();*/
                    return empIds;
                } else if (empNum > 10 && empNum < 100) {
                    String empIds = "emp0"+empnum;
                    /*rs.close();
                    st.close();
                    conn.close();*/
                    return empIds;
                } else {
                    String empIds = "emp"+empnum;
                    /*rs.close();
                    st.close();
                    conn.close();*/
                    return empIds;
                }
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
            return null;
        }
    }
    
    public static void addEmpAsUser (String fname, String mname, String lname) {
        try {
            String userid = AddNewUser.autogenerateUserId();
            //String username = "root";
            String username = autogenerateUsername();
            String password = "root";
            String role = "Employee";
            Connection conn = DatabaseConnection.getConnection();
            String query = "INSERT INTO USERTABLE VALUES(?,?,?,?,?,?,?)";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, userid);
            stmt.setString(2, username);
            stmt.setString(3, password);
            stmt.setString(4, fname);
            stmt.setString(5, mname);
            stmt.setString(6, lname);
            stmt.setString(7, role);
            int rowsaffected = stmt.executeUpdate();
            if (rowsaffected > 0) {
                JOptionPane.showMessageDialog(null, "Also Employee is added as user with username='"+username+"' & password='root'");
            } else {
                JOptionPane.showMessageDialog(null, "Failed to add Employee as a User!");
            }
            /*stmt.close();
            conn.close();*/
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }
    
    public static String autogenerateUsername () {
        try {
            Connection conn = DatabaseConnection.getConnection();
            String query = "SELECT MAX(USERNAME) AS MAXUSRNM FROM USERTABLE WHERE USERNAME LIKE 'root%'";
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            if (rs.next()) {
                String dbUsr = rs.getString("MAXUSRNM");
                if (dbUsr == null) {
                    return "root";
                }
                char[] myChars = dbUsr.toCharArray();
                char[] chars = new char[dbUsr.length()-4];
                for (int i=dbUsr.length()-1, k=0; i>3; i--, k++) {
                    chars[k] = myChars[i];
                }
                int[] arr = new int[dbUsr.length()-4];
                for (int i=1, k=0; k<chars.length; k++) {
                    arr[k] = Character.getNumericValue(chars[k]);
                    arr[k] *= i;
                    i *= 10;
                }
                int numVal = 0;
                for (int i=0; i<arr.length; i++) {
                    numVal += arr[i];
                }
                numVal++;
                String strVal = String.valueOf(numVal);
                return "root"+strVal;
            } else {
                JOptionPane.showMessageDialog(null, "Auto username query returned null!");
                return "root";

            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
            return "root";
        }
    }
    
}
