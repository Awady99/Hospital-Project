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
class Doctors {

    String Name;
    int id;
    String dep;

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

    public String getDep() {
        return dep;
    }

    public Doctors(String Name, int id, String dep) {
        this.Name = Name;
        this.id = id;
        this.dep = dep;
    }

    public Doctors() {

    }

    public void setDep(String dep) {
        this.dep = dep;
    }

    public static void insert(Doctors d) {

        try {
            Connection con = DriverManager.getConnection("jdbc:sqlite:C:/Users/mar00/Documents/NetBeansProjects/project hospital/hospital.db");

            //                 st.execute("insert into doctors (name,id,dep) value (?,?,?)");
            PreparedStatement s = con.prepareStatement("Insert into doctors (name,id,dep) values (?,?,?)");

            s.setString(1, d.getName());
            s.setInt(2, d.getId());
            s.setString(3, d.getDep());
            s.execute();
            System.out.println("Data Inserted");
//connection when he insrt new doc with name and id and dep
        } catch (SQLException ex) {
            System.out.println("Error" + ex.toString());
        }

    }

    public static Doctors select(int id) {
        Doctors d = new Doctors();

        try {

            Connection con = DriverManager.getConnection("jdbc:sqlite:C:/Users/mar00/Documents/NetBeansProjects/project hospital/hospital.db");
//                
            Statement s = con.createStatement();
            s.execute("select * from doctors where ID = " + id);

            ResultSet rs = s.getResultSet();
            while (rs.next()) {
                d.setName(rs.getString("Name"));
                d.setId(rs.getInt("ID"));
                d.setDep(rs.getString("Dep"));
                System.out.println(rs.getString("Name") + " " + rs.getInt("ID") + " " + rs.getString("Dep"));
            }

        } catch (SQLException ex) {
            System.out.println("Error in Selecting  " + ex.toString());
        }

        return d;

    }

    public static Doctors delete(int id) {
        Doctors d = new Doctors();
        try {

            Connection con = DriverManager.getConnection("jdbc:sqlite:C:/Users/mar00/Documents/NetBeansProjects/project hospital/hospital.db");

            PreparedStatement s = con.prepareStatement("delete from doctors where ID = " + id);
//             s.setInt(1, d.getId());
            s.execute();
            System.out.println("Delete Done!");

        } catch (SQLException ex) {
            System.out.println("Error deleting " + ex.toString());
        }

        return d;
    }

}
