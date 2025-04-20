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
import java.util.List;
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
        String name = TxtName.getText();
        double fee = Double.parseDouble(TxtFee.getText());
        String duration = TxtDuration.getText();
        TherapyProgramDTO therapyProgram = new TherapyProgramDTO(id, name, fee, duration);
        try {
            boolean isAdded = therapyProgramManageBO.addTherapyProgram(therapyProgram);
            if (isAdded) {
                new Alert(Alert.AlertType.CONFIRMATION, "Therapy Program Added").show();
                loadAllPrograms();
                clearFields();
                therapyProgramManageBO.generateNextTherapyProId();
            }
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR, "Failed to add Therapy Program").show();
        }
    }

    private void clearFields() {
        TxtId.setText("");
        TxtName.clear();
        TxtFee.clear();
        TxtDuration.clear();
    }

    @FXML
    void BtnDeleteOnAction(ActionEvent event) {
        String id = TxtId.getText();
        try {
            boolean isDeleted = therapyProgramManageBO.deleteTherapyProgram(id);
            if (isDeleted) {
                new Alert(Alert.AlertType.CONFIRMATION, "Therapy Program Deleted").show();
                loadAllPrograms();
                clearFields();
                therapyProgramManageBO.generateNextTherapyProId();
            }
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR, "Failed to delete Therapy Program").show();
        }
    }

    @FXML
    void BtnResetOnAction(ActionEvent event) {
        clearFields();
    }

    @FXML
    void BtnUpdateOnAction(ActionEvent event) {
        String id = TxtId.getText();
        String name = TxtName.getText();
        double fee = Double.parseDouble(TxtFee.getText());
        String duration = TxtDuration.getText();
        TherapyProgramDTO therapyProgram = new TherapyProgramDTO(id, name, fee, duration);
        try {
            boolean isUpdated = therapyProgramManageBO.updateTherapyProgram(therapyProgram);
            if (isUpdated) {
                new Alert(Alert.AlertType.CONFIRMATION, "Therapy Program Updated").show();
                loadAllPrograms();
                clearFields();
            }
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR, "Failed to update Therapy Program").show();
        }
    }

    public void TblProgramsOnAction(MouseEvent mouseEvent) {
        TherapyProgramTM therapyProgram = TblPrograms.getSelectionModel().getSelectedItem();
        if (therapyProgram != null) {
            TxtId.setText(therapyProgram.getId());
            TxtName.setText(therapyProgram.getName());
            TxtFee.setText(String.valueOf(therapyProgram.getFee()));
            TxtDuration.setText(therapyProgram.getDuration());
        }

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        TxtDate.setText(java.time.LocalDate.now().toString());
        setCellValueFactory();
        loadAllPrograms();

        String id;
        try {
            id = therapyProgramManageBO.generateNextTherapyProId();
            TxtId.setText(id);
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR, "Failed to generate ID").show();
        }
    }

    private void loadAllPrograms() {
        ObservableList<TherapyProgramTM> obList = FXCollections.observableArrayList();
        try{
            List<TherapyProgramDTO> therapyProgramList = therapyProgramManageBO.getAllPrograms();
            for (TherapyProgramDTO therapyProgramDTO : therapyProgramList) {
                TherapyProgramTM tm = new TherapyProgramTM(
                        therapyProgramDTO.getId(),
                        therapyProgramDTO.getName(),
                        therapyProgramDTO.getFee(),
                        therapyProgramDTO.getDuration()
                );
                obList.add(tm);
            }
            TblPrograms.setItems(obList);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void setCellValueFactory() {
        ColId.setCellValueFactory(new PropertyValueFactory<>("id"));
        ColName.setCellValueFactory(new PropertyValueFactory<>("name"));
        ColDuration.setCellValueFactory(new PropertyValueFactory<>("duration"));
        ColFee.setCellValueFactory(new PropertyValueFactory<>("fee"));
    }
}
