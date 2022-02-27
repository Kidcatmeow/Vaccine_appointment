package com.example.projectgui;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class mainmenu implements Initializable {

    @FXML
    Button Bookvaccinebutton;

    @FXML
    Button viewmyappointmentka;

    @FXML
    AnchorPane helloworld;

    @FXML
    Button checkappointmentbutton;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void gotobookvaxx() throws IOException{
        AnchorPane mainpane = FXMLLoader.load(getClass().getResource("BookaVaccine.fxml"));
        helloworld.getChildren().setAll(mainpane);
    }

    public void viewmyappointment() throws IOException{
        AnchorPane mainpane = FXMLLoader.load(getClass().getResource("viewMyAppointment.fxml"));
        helloworld.getChildren().setAll(mainpane);
    }

    public void checkmyappointment() throws IOException{
        AnchorPane mainpane = FXMLLoader.load(getClass().getResource("ChangeAppointment.fxml"));
        helloworld.getChildren().setAll(mainpane);
    }

}

