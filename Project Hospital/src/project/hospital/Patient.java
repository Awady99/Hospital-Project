/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project.hospital;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author mar00
 */
public class Patient {

    static int delFlag;
    String Name;
    int id;
    String status;

    public Patient(String Name, int id, String status) {
        this.Name = Name;
        this.id = id;
        this.status = status;
    }

    public Patient() {

    }

    public String getName() {
        return Name;
    }

    public void setName(String Name) {
        this.Name = Name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public static void insert(Patient p) {

        try {
            Connection con = DriverManager.getConnection("jdbc:sqlite:C:/Users/mar00/Documents/NetBeansProjects/project hospital/hospital.db");
            PreparedStatement s = con.prepareStatement("Insert into patient (name,id,status) values (?,?,?)");
            s.setString(1, p.getName());
            s.setInt(2, p.getId());
            s.setString(3, p.getStatus());
            s.execute();
            System.out.println("Patient Inserted");

//connection when he want to insert data for new patient by name and id ana satatus
        } catch (SQLException ex) {
            System.out.println("Error in Inserting" + ex.toString());
        }
    }

    public static Patient select(int id) {

        Patient p = new Patient();
        try {
            // connection when he want to search about patient he inter  the name and display all the data of this name
            Connection con = DriverManager.getConnection("jdbc:sqlite:C:/Users/mar00/Documents/NetBeansProjects/project hospital/hospital.db");
            Statement s = con.createStatement();
            s.execute("select * from patient where ID = " + id);

            ResultSet rs = s.getResultSet();
            while (rs.next()) {
                p.setName(rs.getString("Name"));
                p.setId(rs.getInt("ID"));
                p.setStatus(rs.getString("status"));

                System.out.println(rs.getString("Name") + " " + rs.getInt("ID") + " " + rs.getString("Status"));
            }

        } catch (SQLException ex) {
            System.out.println("Error in Finding Patient" + ex.toString());
        }

        return p;

    }

    public static Patient delete(int id) {

        Patient p = new Patient();
//    String a = null;
        try {

            Connection con = DriverManager.getConnection("jdbc:sqlite:C:/Users/mar00/Documents/NetBeansProjects/project hospital/hospital.db");

            PreparedStatement s = con.prepareStatement("delete from patient where ID = " + id);

            s.execute();
            System.out.println("Patient Data Deleted !");
            delFlag = 1;
            //connection when he want to delet data of any paient he enter the name and then delete all data of this name when he click ok delete
        } catch (SQLException ex) {
            System.out.println("Error in Deleting data " + ex);
            delFlag = 0;
        }

        return p;
    }

}
