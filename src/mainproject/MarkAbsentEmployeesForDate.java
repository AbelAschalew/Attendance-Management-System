package mainproject;

import java.sql.*;
import java.time.LocalDate;
import java.util.HashSet;
import javax.swing.JOptionPane;

public class MarkAbsentEmployeesForDate {

    public static void markAbsent(LocalDate date) {
        try  {
            Connection conn2 = DatabaseConnection.getConnection();
            // Step 1: Get all employee IDs
            String query1 = "SELECT EMPLOYEEID FROM EMPLOYEETABLE";
            Statement st1 = conn2.createStatement();
            ResultSet rs1 = st1.executeQuery(query1);
            HashSet<String> allEmpIds = new HashSet<>();
            while (rs1.next()) {
                allEmpIds.add(rs1.getString("EMPLOYEEID"));
            }
            //rs1.close();
            //st1.close();

            // Step 2: Get employees who already checked in on the given date
            String query2 = "SELECT EMPLOYEEID FROM ATTENDANCETABLE WHERE ATTDATE = ?";
            PreparedStatement pst = conn2.prepareStatement(query2);
            pst.setDate(1, Date.valueOf(date));
            ResultSet present = pst.executeQuery();
            while (present.next()) {
                allEmpIds.remove(present.getString("EMPLOYEEID")); // Remove those present
            }
            /*present.close();
            pst.close();*/

            // Step 3: Insert "Absent" for remaining employees
            String query3 = "INSERT INTO ATTENDANCETABLE (ATTENDANCEID, EMPLOYEEID, ATTDATE, STATUS) VALUES (?, ?, ?, ?)";
            PreparedStatement insert = conn2.prepareStatement(query3);

            for (String empID : allEmpIds) {
                int attId = CheckinCheckoutLogic.autogenerateAttId();
                insert.setInt(1, attId);
                insert.setString(2, empID);
                insert.setDate(3, Date.valueOf(date));
                insert.setString(4, "Absent");
                insert.addBatch();
            }
            insert.executeBatch();
            
            //JOptionPane.showMessageDialog(null, "Attendance Recored Inserted!");

            /*insert.close();*/
            //conn2.close();
            
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error while marking absent: " + e.getMessage());
        }
    }

    public static LocalDate lastProcessedDate() {
        try {
            Connection conn2 = DatabaseConnection.getConnection();
            String query = "SELECT MAX(ATTDATE) AS MAXDATES FROM ATTENDANCETABLE";
            Statement st = conn2.createStatement();
            ResultSet rs = st.executeQuery(query);
            if (rs.next() && rs.getDate("MAXDATES") != null) {
                return rs.getDate("MAXDATES").toLocalDate();
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error fetching last processed date: " + e.getMessage());
        }
        // If no data found, start from a default
        return LocalDate.now().minusDays(1);
    }

    public static void processAbsentEmployees() {
        LocalDate lastDate = lastProcessedDate();
        LocalDate today = LocalDate.now();

        while (lastDate.isBefore(today.minusDays(1))) {
            lastDate = lastDate.plusDays(1);
            markAbsent(lastDate);
        }

        //JOptionPane.showMessageDialog(null, "Missing attendance records filled. Absent employees marked.");
    }
}