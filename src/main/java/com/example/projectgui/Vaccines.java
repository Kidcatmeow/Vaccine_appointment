package com.example.projectgui;

import java.sql.*;

// get the number of available doses from database(put into a variable) --> subtract it --> put back in the database(update)

public class Vaccines {

    //encapsulation - private
    private String vaccine_name;
    private float vaccine_price;
    private boolean second_dose;



    public static int available_dose (String vaccine_name) {
        try {
            //Get a connection to database
            //Syntax --> Connection name = DriverManager.getConnection(servername,username,password);
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/vaccination_system", "root", "");
            //Prepare a SQL statement
            PreparedStatement ps = con.prepareStatement("select available_dose from vaccine where vaccine_type = ?");
            //set ? to the value that you want
            ps.setString(1, vaccine_name);
            //Execute SQL query
            ResultSet search_result = ps.executeQuery();
            //Process result set
            while (search_result.next()) {
                int dose = search_result.getInt("available_dose");
                return dose;
            }
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
        return 0;
    }


    public static void updateVaccine(String vaccine_type) throws SQLException {
        //Get connection from database
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/vaccination_system", "root", "");
        //Prepare a statement
        PreparedStatement ps = con.prepareStatement("update vaccine set available_dose =? where vaccine_type=?");
           int vaccine_dose = available_dose(vaccine_type); //get the return value from available dose (function อันข้างบน)
           vaccine_dose--;
           ps.setInt(1,vaccine_dose);
           ps.setString(2,vaccine_type);
           ps.executeUpdate();

    }
    public String toString() {//override method toString() ให้เวลาเรียก System.out.print(Object) แล้วจะได้ไม่ได้ผลลัพธ์เป็น hashcode
        return "Vaccine Name: " + vaccine_name + "\n" + "Vaccine Price: " + vaccine_price + "\n" +
                "Second Dose: " + second_dose;
    }

    public static void returnVaccine(String vaccine_type) throws SQLException {
        //Get connection from database
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/vaccination_system", "root", "");
        //Prepare a statement
        PreparedStatement ps = con.prepareStatement("update vaccine set available_dose =? where vaccine_type=?");
        int vaccine_dose = available_dose(vaccine_type); //get the return value from available dose (function อันข้างบน)
        vaccine_dose++;
        ps.setInt(1,vaccine_dose);
        ps.setString(2,vaccine_type);
        ps.executeUpdate();

    }
    /* Difference between PreparedStatement and CreateStatement
    PreparedStatement-suitable to use when you want to run SQL with conditions (using ? in the statement)
    CreateStatement -view simple data
    syntax --> Statement name = connection_name.createStatement("command");
    https://stackoverflow.com/questions/45972001/when-is-it-better-to-use-a-statement-over-a-preparedstatement */



}