/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project.hospital;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author mar00
 */
public class rooms {

    static int delflag;
    int roomnum;
    static int check;

    public int getRoomnum() {
        return roomnum;
    }

    public void setRoomnum(int roomnum) {
        this.roomnum = roomnum;
    }

    public int getCheck() {
        return check;
    }

    public void setCheck(int check) {
        this.check = check;
    }

    public rooms(int roomnum, int check) {
        this.roomnum = roomnum;
        this.check = check;
    }

    public rooms() {

    }

    public static rooms selectroom(int roomnum) {

        rooms r = new rooms();
        try {
            // connection when he want to search about rooms he inter the number and check if the room is empty or not and display massage
            Connection con = DriverManager.getConnection("jdbc:sqlite:C:/Users/mar00/Documents/NetBeansProjects/project hospital/hospital.db");
            Statement st = con.createStatement();
            st.execute("Select * from rooms where roomnum = " + roomnum);
            ResultSet rs = st.getResultSet();
            while (rs.next()) {
                r.setRoomnum(rs.getInt("roomnum"));
                r.setCheck(rs.getInt("Check"));
                if (rs.getInt("Check") == 0) {

                    System.out.println("This room is Available");
                } else {
                    System.out.println("This room is not Available");
                }

            }

        } catch (SQLException ex) {
            System.out.println("Error in searching rooms " + ex.toString());
        }

        return r;
    }

}
