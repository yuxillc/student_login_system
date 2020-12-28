package com.example.student_login_system;

public class Student {
    private String username;
    private String studentID;
    private String email;
    private String password;
    private static int IDsum=1;

    public Student(String username,String email,String password){
        this.username=username;
        this.studentID="S"+String.format("%06",IDsum);
        this.email=email;
        this.password=password;
        IDsum++;
    }
    public Student(String username,String password){
        this.username=username;
        this.password=password;
        this.email="";
        this.studentID="S"+String.format("%06",IDsum);
        IDsum++;
    }
    public Student(String username){
        this.username=username;
        this.password="00000000";
        this.email="";
        this.studentID="S"+String.format("%06",IDsum);
        IDsum++;
    }
    public Student(){
        this.username="";
        this.password="00000000";
        this.email="";
        this.studentID="S"+String.format("%06",IDsum);
        IDsum++;
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

    public String getStudentID() {
        return studentID;
    }
    public void setStudentID(String studentID) {
        this.studentID = studentID;
    }

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
}
