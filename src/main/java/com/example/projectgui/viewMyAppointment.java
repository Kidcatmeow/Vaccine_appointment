package com.example.projectgui;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Paint;
import javafx.scene.text.Text;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class viewMyAppointment implements Initializable {
    @FXML
    TextField UserID;
    @FXML
    Button searchBtn;
    @FXML
    Text name;
    @FXML
    Text patience_type;
    @FXML
    Text appointment_place;
    @FXML
    Text vaccine_type;
    @FXML
    Text appointment_date;
    @FXML
    Text second_dose;
    @FXML
    Button backbutton;
    @FXML
    Pane pane;

    @FXML
    Text error_text;

    @FXML
    Text third_dose;


    public void checkID(){
        int id = Integer.parseInt(UserID.getText());
        name.setText(Patient.search_my_appointment(id,"name"));
        patience_type.setText(Patient.search_my_appointment(id,"patient type"));
        appointment_place.setText(Patient.search_my_appointment(id,"appointment place"));
        vaccine_type.setText(Patient.search_my_appointment(id,"vaccine type"));
        appointment_date.setText(Patient.search_my_appointment(id,"appointment date"));
        second_dose.setText(Patient.search_my_appointment(id,"second dose"));
        third_dose.setText(Patient.search_my_appointment(id,"third dose"));
        if(name.getText().isEmpty()){
            error_text.setText("NOT FOUND");
        }else {
            error_text.setText("");
        }
    }

    public void toMainmenu() throws IOException {
        AnchorPane mainPane = FXMLLoader.load(getClass().getResource("mainmenu.fxml"));
        pane.getChildren().setAll(mainPane);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        name.setText("");
        patience_type.setText("");
        appointment_date.setText("");
        appointment_place.setText("");
        vaccine_type.setText("");
        appointment_date.setText("");
        second_dose.setText("");
        error_text.setText("");
        third_dose.setText("");
    }
}
