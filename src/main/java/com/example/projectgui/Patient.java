package com.example.projectgui;



import javafx.scene.control.TextField;

import java.sql.*;
import java.util.Locale;

public class Patient {
    //connect to database
    public static String search_my_appointment(int patientID,String column){

        try {
            //change from university to vaccination_system
            Connection con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/vaccination_system","root","");
            PreparedStatement ps=con.prepareStatement("select * from patient where patientID = ?"); //prepare a sql statement
            ps.setInt(1,patientID); // replace '?' with patientID
            ResultSet search_result=ps.executeQuery(); // run the code
            while(search_result.next()){
                switch (column.toLowerCase(Locale.ROOT)){
                    case "name" ->{
                        String name=search_result.getString("patient_name");
                        return name;
                    }
                    case "patient type" ->{
                        String patient_type = search_result.getString("patient_type");
                        return patient_type;
                    }
                    case "appointment place" ->{
                        String app_place = search_result.getString("appointment_place");
                        return app_place;
                    }
                    case "vaccine type" ->{
                        String vaccine_type = search_result.getString("vaccine_type");
                        return vaccine_type;
                    }
                    case "appointment date" ->{
                        String app_date = search_result.getString("appointment_date");
                        return app_date;
                    }
                    case "second dose" ->{
                        String second_dose = search_result.getString("second_dose_date");
                        return second_dose;
                    }
                    case "third dose" ->{
                        String third_dose = search_result.getString("third_dose_date");
                        return third_dose;
                    }
                    default -> {
                        String name=search_result.getString("patient_name");
                        String patient_type = search_result.getString("patient_type");
                        String app_place = search_result.getString("appointment_place");
                        String vaccine_type = search_result.getString("vaccine_type");
                        String app_date = search_result.getString("appointment_date");
                        String second_dose = search_result.getString("second_dose_date");
                        String thrid_dose = search_result.getString("third_dose_date");
                        String[] list_of_item = {name,patient_type,app_place,vaccine_type,app_date,second_dose,thrid_dose};
                        return list_of_item.toString();
                    }
                }
            }
            search_result.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            System.out.println();
        }
        return null;
    }


    public static void input_patient_info(int ID, String Patient_name, String patient_type, String appointment_place, String vaccine_type, String appointment_date, String second_dose_date,String third_dose_date) {
        try {
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/vaccination_system", "root", "");
            PreparedStatement ps = con.prepareStatement("insert into patient(patientID,patient_name,patient_type,appointment_place,vaccine_type,appointment_date,second_dose_date,third_dose_date)value (?,?,?,?,?,?,?,?)");
            ps.setInt(1, ID);
            ps.setString(2, Patient_name);
            ps.setString(3, patient_type);
            ps.setString(4, appointment_place);
            ps.setString(5, vaccine_type);
            ps.setString(6, appointment_date);
            ps.setString(7,second_dose_date);
            ps.setString(8,third_dose_date);
            ps.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public static void update_patient_info(int ID,String column,String newdata) throws SQLException {
        Connection con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/vaccination_system", "root", "");
        String statement = "update patient set "+column+"=";
        PreparedStatement ps = con.prepareStatement(statement+" ? where patientID=?");
        ps.setString(1,newdata);
        ps.setInt(2,ID);
        ps.executeUpdate();
        con.close();
    }

    public static int getCurrentPatientID() throws SQLException {
        Connection con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/vaccination_system", "root", "");
        PreparedStatement ps=con.prepareStatement("select patientID from patient order by patientID desc limit 1"); // เอาไว้หาตัวเลขที่เยอะที่สุด,  ตัวเลขที่เยอะที่สุด = patientID ล่าสุด เพราะเราใช้ระบบ + ทีละหนึ่งต่อการ submit
        ResultSet rs = ps.executeQuery();
        while (rs.next()){
            int currentID = rs.getInt("patientID");
            return currentID;
        }
        return 0;
    }

    public static boolean exist_in_database (int PatientID) throws SQLException {
        Connection con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/vaccination_system", "root", "");
        PreparedStatement ps=con.prepareStatement("select patientID from patient where patientID=?");
        ps.setInt(1,PatientID);
        ResultSet rs = ps.executeQuery();
        if(rs.next()){
            return true;
        }else return false;
    }
    public static boolean same_name_and_id (int PatientID, String PatientName) throws SQLException {
        Connection con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/vaccination_system", "root", "");
        //check if the given patient_name matches with the patientID
        PreparedStatement ps = con.prepareStatement("select * from patient where Patient_Name like ? and patientID =?");
        ps.setString(1,PatientName);
        ps.setInt(2,PatientID);
        ResultSet rs = ps.executeQuery();
        if(rs.next()){
            return true;
        }else return false;
    }

    public static void delete_patient_info(int PatientID) throws SQLException{
        Connection con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/vaccination_system", "root", "");
        PreparedStatement ps= con.prepareStatement("delete from patient where patientID=?");
        ps.setInt(1,PatientID);
        ps.executeUpdate();
    }

}
