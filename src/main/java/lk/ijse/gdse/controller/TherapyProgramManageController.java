package lk.ijse.gdse.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import lk.ijse.gdse.bo.BOFactory;
import lk.ijse.gdse.bo.custom.TherapyProgramManageBO;
import lk.ijse.gdse.dto.TherapyProgramDTO;
import lk.ijse.gdse.dto.tm.TherapyProgramTM;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

public class TherapyProgramManageController implements Initializable {

    @FXML
    private Button BtnAdd;

    @FXML
    private Button BtnDelete;

    @FXML
    private Button BtnReset;

    @FXML
    private Button BtnUpdate;

    @FXML
    private TableColumn<TherapyProgramTM, String> ColDuration;

    @FXML
    private TableColumn<TherapyProgramTM, Double> ColFee;

    @FXML
    private TableColumn<TherapyProgramTM, String> ColId;

    @FXML
    private TableColumn<TherapyProgramTM, String> ColName;

    @FXML
    private TableView<TherapyProgramTM> TblPrograms;

    @FXML
    private Text TxtDate;

    @FXML
    private TextField TxtDuration;

    @FXML
    private TextField TxtFee;

    @FXML
    private Text TxtId;

    @FXML
    private TextField TxtName;

    @FXML
    private AnchorPane root;

    TherapyProgramManageBO therapyProgramManageBO = BOFactory.getBoFactory().getBO(BOFactory.BOTypes.THERAPY_PROGRAM);

    @FXML
    void BtnAddOnAction(ActionEvent event) {
        String id = TxtId.getText();
        String programName = TxtName.getText();
        String duration = TxtDuration.getText();
        String fee = TxtFee.getText();

        String namePattern = "^[A-Za-z ]+$";
        String nicPattern = "^[0-9]{9}[vVxX]$|^[0-9]{12}$";
        String emailPattern = "^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$";

        boolean hasErrors = false;
        StringBuilder errorMessage = new StringBuilder("Please correct the following errors:\n");

        String errorStyle = "-fx-border-color: red; -fx-text-fill: white; -fx-background-color: transparent;";
        String defaultStyle = "-fx-border-color: green; -fx-text-fill: white; -fx-background-color: transparent;";

        if (programName.isEmpty() || !programName.matches(namePattern)) {
            TxtName.setStyle(errorStyle);
            errorMessage.append("- Name is empty or in an incorrect format\n");
            hasErrors = true;

        }else{
            TxtName.setStyle(defaultStyle);
        }

        if (duration.isEmpty()) {
            TxtDuration.setStyle(errorStyle);
            errorMessage.append("- Duration is empty\n");
            hasErrors = true;

        }else{
            TxtDuration.setStyle(defaultStyle);
        }

        double programFee = -1;
        try {
            programFee = Double.parseDouble(fee);
            TxtFee.setStyle(defaultStyle);
        } catch (NumberFormatException e) {
            TxtFee.setStyle(errorStyle);
            errorMessage.append("- Program Fee  is empty or not a valid \n");
            hasErrors = true;
        }
        if (hasErrors) {
            new Alert(Alert.AlertType.ERROR, errorMessage.toString()).show();
            return;
        }

        boolean isSaved = therapyProgramManageBO.addTherapyProgram(new TherapyProgramDTO(id ,programName , duration , programFee));
        if (isSaved) {
            refreshPage();
            new Alert(Alert.AlertType.INFORMATION, "Program saved successfully!").show();
        } else {
            new Alert(Alert.AlertType.ERROR, "Failed to save program!").show();
        }

    }


    @FXML
    void BtnDeleteOnAction(ActionEvent event) {
        String programId = TxtId.getText();

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Do you want to delete this Program?", ButtonType.YES, ButtonType.NO);
        Optional<ButtonType> optionalButtonType = alert.showAndWait();

        if(optionalButtonType.isPresent() && optionalButtonType.get() == ButtonType.YES) {
            boolean isDeleted = therapyProgramManageBO.deleteTherapyProgram(programId);
            if(isDeleted) {
                refreshPage();
                new Alert(Alert.AlertType.INFORMATION, "Program deleted Successfully").show();
            }else{
                new Alert(Alert.AlertType.ERROR, "Failed to delete program").show();
            }
        }
    }

    @FXML
    void BtnResetOnAction(ActionEvent event) {

    }

    @FXML
    void BtnUpdateOnAction(ActionEvent event) {
        String id = TxtId.getText();
        String programName = TxtName.getText();
        String duration = TxtDuration.getText();
        String fee = TxtFee.getText();

        String namePattern = "^[A-Za-z ]+$";
        String nicPattern = "^[0-9]{9}[vVxX]$|^[0-9]{12}$";
        String emailPattern = "^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$";

        boolean hasErrors = false;
        StringBuilder errorMessage = new StringBuilder("Please correct the following errors:\n");

        String errorStyle = "-fx-border-color: red; -fx-text-fill: white; -fx-background-color: transparent;";
        String defaultStyle = "-fx-border-color: green; -fx-text-fill: white; -fx-background-color: transparent;";

        if (programName.isEmpty() || !programName.matches(namePattern)) {
            TxtName.setStyle(errorStyle);
            errorMessage.append("- Name is empty or in an incorrect format\n");
            hasErrors = true;

        }else{
            TxtName.setStyle(defaultStyle);
        }

        if (duration.isEmpty()) {
            TxtDuration.setStyle(errorStyle);
            errorMessage.append("- Duration is empty\n");
            hasErrors = true;

        }else{
            TxtDuration.setStyle(defaultStyle);
        }

        double programFee = -1;
        try {
            programFee = Double.parseDouble(fee);
            TxtFee.setStyle(defaultStyle);
        } catch (NumberFormatException e) {
            TxtFee.setStyle(errorStyle);
            errorMessage.append("- Program Fee  is empty or not a valid \n");
            hasErrors = true;
        }
        if (hasErrors) {
            new Alert(Alert.AlertType.ERROR, errorMessage.toString()).show();
            return;
        }

        boolean isUpdated = therapyProgramManageBO.updateTherapyProgram(new TherapyProgramDTO(id ,programName , duration , programFee));
        if (isUpdated) {
            refreshPage();
            new Alert(Alert.AlertType.INFORMATION, "Program saved successfully!").show();
        } else {
            new Alert(Alert.AlertType.ERROR, "Failed to save program!").show();
        }

    }

    public void TblProgramsOnAction(MouseEvent mouseEvent) {
        TherapyProgramTM programTM = TblPrograms.getSelectionModel().getSelectedItem();
        if (programTM != null) {
            TxtId.setText(programTM.getProgramId());
            TxtName.setText(programTM.getProgramName());
            TxtDuration.setText(programTM.getDuration());
            TxtFee.setText(String.valueOf(programTM.getFee()));

            BtnAdd.setDisable(true);
            BtnDelete.setDisable(false);
            BtnUpdate.setDisable(false);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ColId.setCellValueFactory(new PropertyValueFactory<>("programId"));
        ColName.setCellValueFactory(new PropertyValueFactory<>("programName"));
        ColDuration.setCellValueFactory(new PropertyValueFactory<>("duration"));
        ColFee.setCellValueFactory(new PropertyValueFactory<>("fee"));

        String defaultStyle = "-fx-border-color: yellow; -fx-text-fill: black; -fx-background-color: white; -fx-border-width: 2px;";

        TxtName.setStyle(defaultStyle);
        TxtDuration.setStyle(defaultStyle);
        TxtFee.setStyle(defaultStyle);

        TxtDate.getText();
    }

    private void loadAllPrograms() {

    }
    private void refreshPage() {
        loadNextProgramId();
        loadTableData();

        BtnAdd.setDisable(false);
        BtnDelete.setDisable(true);
        BtnUpdate.setDisable(true);

        TxtName.setText("");
        TxtDuration.setText("");
        TxtFee.setText("");

        String defaultStyle = "-fx-border-color: yellow; -fx-text-fill: black; -fx-background-color: white; -fx-border-width: 2px;";

        TxtName.setStyle(defaultStyle);
        TxtDuration.setStyle(defaultStyle);
        TxtFee.setStyle(defaultStyle);
    }

    private void loadTableData() {
        ArrayList<TherapyProgramDTO> programDTOS = (ArrayList<TherapyProgramDTO>) therapyProgramManageBO.getAllPrograms();
        ObservableList<TherapyProgramTM> programTMS = FXCollections.observableArrayList();

        for(TherapyProgramDTO programDTO : programDTOS){
            TherapyProgramTM programTM = new TherapyProgramTM(
                    programDTO.getId(),
                    programDTO.getProgramName(),
                    programDTO.getDuration(),
                    programDTO.getFee()

            );
            programTMS.add(programTM);
        }
        TblPrograms.setItems(programTMS);
    }

    private void loadNextProgramId() {
        String nextProgramId = therapyProgramManageBO.getNextProgramId();
        TxtId.setText(nextProgramId);
    }

}
