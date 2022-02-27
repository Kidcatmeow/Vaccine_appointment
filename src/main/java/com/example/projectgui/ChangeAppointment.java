package com.example.projectgui;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Paint;
import javafx.scene.text.Text;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class ChangeAppointment implements Initializable {

    @FXML
    AnchorPane pane;
    @FXML
    TextField UserID;
    @FXML
    Text errorText;
    @FXML
    Text prompt;
    @FXML
    Button submitButton;
    @FXML
    RadioButton changeVac;
    @FXML
    RadioButton changeDate;
    @FXML
    RadioButton changePlace;
    @FXML
    Text prompt_entervalue;
    @FXML
    DatePicker pickDate;
    @FXML
    ComboBox pickVac;
    @FXML
    ComboBox pickPlace;

    @FXML
    Button goback;
    @FXML
    Text statusText;

    @FXML
    Button confirmButton;

    @FXML
    TextField nameTextField;

    @FXML
    Text nameVerification;

    @FXML
    RadioButton changeapp;

    @FXML
    RadioButton deleteapp;

    @FXML
    Button deletebtn;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        reset();
        pickVac.getItems().addAll("Sinovac","AstraZeneca","Pfizer","Johnson");
        pickPlace.getItems().addAll("Icon Siam", "Siam Paragon","Robinson Latkrabang","Emquatier","Central World");
    }


    public void reset(){
        errorText.setText("");
        prompt.setText("");
        changeVac.setVisible(false);
        changeDate.setVisible(false);
        changePlace.setVisible(false);
        prompt_entervalue.setText("");
        pickDate.setVisible(false);
        pickPlace.setVisible(false);
        pickVac.setVisible(false);
        statusText.setText("");
        confirmButton.setVisible(false);
        nameVerification.setText("");
        changeapp.setVisible(false);
        deleteapp.setVisible(false);
        deletebtn.setVisible(false);

    }
    public void checkID() throws SQLException {
        int id = Integer.parseInt(UserID.getText());

        if(Patient.exist_in_database(id)){
            errorText.setText("User found");
            if(Patient.same_name_and_id(id,nameTextField.getText())){
                nameVerification.setText("Patient name matches");
                changeapp.setVisible(true);
                deleteapp.setVisible(true);
                if(changeapp.isSelected()){
                    reset();
                    changeapp.setVisible(true);
                    deleteapp.setVisible(true);
                    prompt.setText("What would you like to change?");
                    changeVac.setVisible(true);
                    changeDate.setVisible(true);
                    changePlace.setVisible(true);
                    deletebtn.setVisible(false);
                }else if(deleteapp.isSelected()){
                    reset();
                    changeapp.setVisible(true);
                    deleteapp.setVisible(true);
                    prompt.setText("Please confirm the deletion of your appointment");
                    deletebtn.setVisible(true);
                }

            }else{
                reset();
                nameVerification.setText("");
                errorText.setText("User found");
                nameVerification.setText("Name not match");

            }

        }else {
            reset();
            errorText.setText("User not found, enter again");
            UserID.setText("");

        }
    }

    public void select_whatToChange(){
        if(changeVac.isSelected()){
            prompt_entervalue.setText("Please select your new vaccine");
            pickVac.setVisible(true);
            pickDate.setVisible(false);
            pickPlace.setVisible(false);

        }else if(changeDate.isSelected()){
            prompt_entervalue.setText("Please choose your appointment date");
            pickVac.setVisible(false);
            pickDate.setVisible(true);
            pickPlace.setVisible(false);
        } else if (changePlace.isSelected()) {
            prompt_entervalue.setText("Please choose your appointment place");
            pickVac.setVisible(false);
            pickDate.setVisible(false);
            pickPlace.setVisible(true);
        }
        confirmButton.setVisible(true);
    }

    public void toMainmenu() throws IOException {
        AnchorPane mainPane = FXMLLoader.load(getClass().getResource("mainmenu.fxml"));
        pane.getChildren().setAll(mainPane);
    }

    public void insert_to_database() throws SQLException {
        int id = Integer.parseInt(UserID.getText());
        try {
            if(pickVac.isVisible()){
                String chosenvac = pickVac.getSelectionModel().getSelectedItem().toString();
                if(Vaccines.available_dose(chosenvac)==0){
                    statusText.setFill(Paint.valueOf("red"));
                    statusText.setText("Vaccine out of stock, choose again");
                }else{
                    statusText.setFill(Paint.valueOf("green"));
                    statusText.setText("Updated successfully, please check on 'View my appointment page'");
                    statusText.setVisible(true);
                    Vaccines.returnVaccine(Patient.search_my_appointment(id,"vaccine type"));
                    Patient.update_patient_info(id,"vaccine_type",chosenvac);
                    switch (pickVac.getSelectionModel().getSelectedItem().toString()){
                        case"Sinovac"->{
                            Sinovac sinovac=new Sinovac();
                            String new_second_dose_date = sinovac.schedule_seconddose(Patient.search_my_appointment(id,"appointment date"));
                            String new_third_dose_date = sinovac.schedule_thirddose(new_second_dose_date);
                            Patient.update_patient_info(id,"second_dose_date",new_second_dose_date);
                            Patient.update_patient_info(id,"third_dose_date",new_third_dose_date);
                            System.out.println(Patient.search_my_appointment(id,"vaccine type"));
                            Vaccines.updateVaccine("Sinovac");

                        }
                        case "AstraZeneca"->{
                            AstraZeneca astraZeneca = new AstraZeneca();
                            String new_second_dose_date = astraZeneca.schedule_seconddose(Patient.search_my_appointment(id,"appointment date"));
                            Patient.update_patient_info(id,"second_dose_date",new_second_dose_date);
                            Patient.update_patient_info(id,"third_dose_date",null);
                            Vaccines.updateVaccine("AstraZeneca");

                        }
                        case "Pfizer"->{
                            Pfizer pfizer= new Pfizer();
                            String new_second_dose_date = pfizer.schedule_seconddose(Patient.search_my_appointment(id,"appointment date"));
                            Patient.update_patient_info(id,"second_dose_date",new_second_dose_date);
                            Patient.update_patient_info(id,"third_dose_date",null);
                            Vaccines.updateVaccine("Pfizer");

                        }
                        case "Johnson"->{
                            Patient.update_patient_info(id,"second_dose_date",null);
                            Patient.update_patient_info(id,"third_dose_date",null);
                            Vaccines.updateVaccine("Johnson");

                        }
                    }

                }
            }else if(pickPlace.isVisible()){
                Patient.update_patient_info(id,"appointment_place",pickPlace.getSelectionModel().getSelectedItem().toString());
                statusText.setFill(Paint.valueOf("green"));
                statusText.setText("Updated successfully, please check on 'View my appointment page'");
                statusText.setVisible(true);
            }else if(pickDate.isVisible()){
                Patient.update_patient_info(id,"appointment_date",pickDate.getValue().toString());
                statusText.setFill(Paint.valueOf("green"));
                statusText.setText("Updated successfully, please check on 'View my appointment page'");
                statusText.setVisible(true);
            }
        }catch ( NullPointerException ne){
            statusText.setFill(Paint.valueOf("red"));
            statusText.setText("Null value detected, please choose something before confirming");
            statusText.setVisible(true);
        }
    }
    public void delete() throws SQLException {
        int id = Integer.parseInt(UserID.getText());
        Patient.delete_patient_info(id);
        statusText.setText("Deleted successfully");
    }
}
