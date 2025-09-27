/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mainproject;

/**
 *
 * @author abela
 */
public class EmployeeClass {
    private String employeeid, fname, mname, lname, shift, status;
    
    public EmployeeClass (String employeeid, String fname, String mname, String lname, String shift, String status) {
        this.employeeid = employeeid;
        this.fname = fname;
        this.mname = mname;
        this.lname = lname;
        this.shift = shift;
        this.status = status;
    }
    
    public String getEmployeeid() {
        return employeeid;
    }
    public void setEmployeeid(String employeeid) {
        this.employeeid = employeeid;
    }
    
    public String getFname(){
        return fname;
    }
    public void setFname(String fname){
        this.fname = fname;
    }
    
    public String getMname(){
        return mname;
    }
    public void setMname(String mname){
        this.mname = mname;
    }
    
    public String getLname(){
        return lname;
    }
    public void setLname(String lname) {
        this.lname = lname;
    }
    
    public String getShift(){
        return shift;
    }
    public void setShift(String shift) {
        this.shift = shift;
    }
    
    public String getStatus(){
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    
}
