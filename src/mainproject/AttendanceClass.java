/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mainproject;

import java.sql.Date;

/**
 *
 * @author abela
 */
public class AttendanceClass {
    private String attendanceid, employeeid, status;
    private Date attdate;
    
    public AttendanceClass (String attendanceid, String employeeid, Date attdate, String status) {
        this.attendanceid = attendanceid;
        this.employeeid = employeeid;
        this.attdate = attdate;
        this.status = status;
    }
    
    public String getAttendanceid(){
        return attendanceid;
    }
    public void setAttendanceid(String attendanceid){
        this.attendanceid = attendanceid;
    }
    
    public String getEmployeeid(){
        return employeeid;
    }
    public void setEmployeeid(String employeeid){
        this.employeeid = employeeid;
    }
    
    public Date getAttdate(){
        return attdate;
    }
    public void setAttdate(Date attdate){
        this.attdate = attdate;
    }
    
    public String getStatus(){
        return status;
    }
    public void setStatus(String status){
        this.status = status;
    }
    
}
