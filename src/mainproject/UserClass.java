/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mainproject;

/**
 *
 * @author abela
 */
public class UserClass {
    private String userid, username, password, fname, mname, lname, role;
    
    public UserClass (String userid, String username, String password, String fname, String mname, String lname, String role) {
        this.userid = userid;
        this.username = username;
        this.password = password;
        this.fname = fname;
        this.mname = mname;
        this.lname = lname;
        this.role = role;
    }
    
    public UserClass (String userid, String username, String role) {
        this.userid = userid;
        this.username = username;
        this.role = role;
    }
    
    public String getUserid () {
        return userid;
    }
    public void setUserid (String userid) {
        this.userid = userid;
    }
    
    public String getUsername () {
        return username;
    }
    public void setUsername (String username) {
        this.username = username;
    }
    
    public String getPassword () {
        return password;
    }
    public void setPassword (String password) {
        this.password = password;
    }
    
    public String getFname() {
        return fname;
    }
    public void setFname (String fname) {
        this.fname = fname;
    }
    
    public String getMname () {
        return mname;
    }
    public void setMname(String mname){
        this.mname = mname;
    }
    
    public String getLname(){
        return lname;
    }
    public void setLname(String lname){
        this.lname = lname;
    }
    
    public String getRole(){
        return role;
    }
    public void setRole(String role){
        this.role = role;
    }
    
}
