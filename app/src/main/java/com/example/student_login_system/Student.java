package com.example.student_login_system;

import java.util.Objects;

public class Student {
    private String username;
    private String studentID;
    private String email;
    private String password;
    private static int IDsum=1;

    public Student(String username,String email,String password){
        this.username=username;
        this.studentID="S"+String.format("%06d",IDsum);
        this.email=email;
        this.password=password;
        IDsum++;
    }
    public Student(){
        this.username="";
        this.password="00000000";
        this.email="";
        this.studentID="S000001";
        IDsum++;
    }
    public Student(String studentID,String password){
        this.username="";
        this.password=password;
        this.email="";
        this.studentID=studentID;
    }
    public Student(String studentID){
        this.username="";
        this.password="00000000";
        this.email="";
        this.studentID=studentID;
    }
    public Student(int ID){
        this.username="";
        this.password=null;
        this.email="";
        this.studentID="S"+String.format("%06d",ID);
    }

    public static int getIDsum() {
        return IDsum;
    }
    public static void setIDsum(int IDsum) {
        Student.IDsum = IDsum;
    }

    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }

    public String getStudentID() { return studentID; }
    public void setStudentID(String studentID) { this.studentID = studentID; }

    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    public boolean equals(String studentID,String password) {
        if (this.studentID.equals(studentID) &&
                this.password.equals(password) )
            return true;
        else return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(username, studentID, email, password);
    }
}
