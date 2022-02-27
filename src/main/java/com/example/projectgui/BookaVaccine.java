package com.example.projectgui;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;


import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class BookaVaccine implements Initializable {

    @FXML
    Pane pane;

    @FXML
    TextField nameField;

    @FXML
    CheckBox conditionCheckbox;

    @FXML
    ToggleGroup vaccine;

    @FXML
    RadioButton sinovac;

    @FXML
    RadioButton astrazeneca;

    @FXML
    RadioButton pfizer;

    @FXML
    RadioButton johnson;

    @FXML
    ComboBox placeComboBox;

    @FXML
    DatePicker datePicker;

    @FXML
    Button submitButton;

    @FXML
    Text errorText;

    @FXML
    Text idText;

    @FXML
    Button backButton;


    String patient_type;
    String second_dose_date;
    String third_dose_date;
    int Patient_ID;//increment every time a submit button is pressed

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        nameField.setText("");
        idText.setText("");
        errorText.setText("");
        conditionCheckbox.setSelected(false);
        astrazeneca.setSelected(false);
        pfizer.setSelected(false);
        johnson.setSelected(false);
        errorText.setText("");

        placeComboBox.getItems().addAll("Icon Siam", "Siam Paragon","Robinson Latkrabang","Emquatier","Central World");

    }

    public void Submit() throws IOException {

        try {
            Patient_ID=Patient.getCurrentPatientID();
            Patient_ID++;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }



        if (nameField.getText().isEmpty() || placeComboBox.getItems().isEmpty() || (datePicker.getValue() == null)) {
            errorText.setText("Please fill in all the field");

        } else{

        //Get patient name
        String patient_name = nameField.getText();


        //shorthand if-else variable = condition ? True:False
        BookaVaccine.this.patient_type = BookaVaccine.this.conditionCheckbox.isSelected() ? "special" : "normal";


        //Get selected vaccine
        RadioButton selectedRadioButton = (RadioButton) vaccine.getSelectedToggle();
        String selected_vac = selectedRadioButton.getText();
//        vaccine.setUserData(vaccine.selectedToggleProperty().getName());
//        String selected_vac = vaccine.getUserData().toString();

        //Get selected place
        placeComboBox.setUserData(placeComboBox.getSelectionModel().getSelectedItem());
        String place = placeComboBox.getUserData().toString();

        //Get 1st appointment date
        String date = datePicker.getValue().toString();


        //calculate the appointment for second dose
        switch (selected_vac){
            case "Pfizer"->{
                Pfizer pfizer=new Pfizer();
                try {
                    second_dose_date=pfizer.schedule_seconddose(date);
                    third_dose_date=null;
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
            case "Sinovac"->{
                Sinovac sinovac=new Sinovac();
                try {
                    second_dose_date=sinovac.schedule_seconddose(date);
                    third_dose_date=sinovac.schedule_thirddose(second_dose_date);

                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
            case "AstraZeneca"->{
                AstraZeneca astraZeneca = new AstraZeneca();
                try {
                    second_dose_date=astraZeneca.schedule_seconddose(date);
                    third_dose_date=null;
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
            default -> {
                second_dose_date= null;
                third_dose_date=null;
            }
        }

        //Insert info into database

        try {
            int vaccine_inStock = Vaccines.available_dose(selected_vac);
            if(vaccine_inStock<=0){
                errorText.setText("Vaccine out of stock, choose again");
                idText.setText("");
            }else {
                Patient.input_patient_info(Patient_ID,patient_name,patient_type,place,selected_vac,date,second_dose_date,third_dose_date);
                Vaccines.updateVaccine(selected_vac);
                errorText.setText("Appointment booked");
                idText.setText("Your ID is "+ this.Patient_ID + ", you can use this ID to view and change your appointment");
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        }
    }


    public void toMainmenu() throws IOException {
        AnchorPane mainPane = FXMLLoader.load(getClass().getResource("mainmenu.fxml"));
        pane.getChildren().setAll(mainPane);
    }


}


