package lk.ijse.gdse.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import lk.ijse.gdse.bo.BOFactory;
import lk.ijse.gdse.bo.custom.TherapistManageBO;
import lk.ijse.gdse.bo.custom.TherapyProgramManageBO;
import lk.ijse.gdse.dto.TherapistDTO;
import lk.ijse.gdse.dto.TherapyProgramDTO;
import lk.ijse.gdse.dto.tm.TherapistTM;
import lk.ijse.gdse.dto.tm.TherapyProgramTM;

import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

public class TherapistManageController implements Initializable {

    @FXML
    private Button BtnAdd;

    @FXML
    private Button BtnDelete;

    @FXML
    private Button BtnReset;

    @FXML
    private Button BtnUpdate;

    @FXML
    private LineChart<?, ?> ChartTherapist;

    @FXML
    private ComboBox<String> CmbTherapyProgram;

    @FXML
    private TableColumn<TherapistTM, String> ColEmail;

    @FXML
    private TableColumn<TherapistTM, String> ColId;

    @FXML
    private TableColumn<TherapistTM, String> ColName;

    @FXML
    private TableColumn<TherapistTM, String> ColPhone;

    @FXML
    private TableColumn<TherapistTM, String> ColProgramId;

    @FXML
    private TableColumn<TherapistTM, String> ColTherapyProgarm;

    @FXML
    private TableView<TherapistTM> TblTherapist;

    @FXML
    private Text TxtDate;

    @FXML
    private TextField TxtEmail;

    @FXML
    private Text TxtId;

    @FXML
    private TextField TxtName;

    @FXML
    private TextField TxtPhone;

    @FXML
    private TextField TxtProgramId;

    @FXML
    private AnchorPane root;

    @FXML
    private CategoryAxis xaxis;

    @FXML
    private NumberAxis yaxis;

    TherapistManageBO therapistManageBO =  BOFactory.getBoFactory().getBO(BOFactory.BOTypes.THERAPIST);
    TherapyProgramManageBO therapyProgramBO =  BOFactory.getBoFactory().getBO(BOFactory.BOTypes.THERAPY_PROGRAM);

    @FXML
    void BtnAddOnAction(ActionEvent event) {
        String id = TxtId.getText();
        String name = TxtName.getText();
        String email = TxtEmail.getText();
        String phone = TxtPhone.getText();
        String therapyProgramName = CmbTherapyProgram.getValue();
        String therapyProgramId =  TxtProgramId.getText();

        try{
            TherapistDTO therapistDTO = new TherapistDTO(id, name, email, phone, therapyProgramId,therapyProgramName);
            boolean isAdded = therapistManageBO.addTherapist(therapistDTO);
            if (isAdded) {
                loadAllTherapist();
                loadPrograms();
                therapistManageBO.generateNextTherapistId();
            }
        }catch (Exception e){
            e.printStackTrace();

        }
    }

    private void loadPrograms() {
        try {
            List<TherapyProgramDTO> programs = therapyProgramBO.getAllPrograms();

            // Use a map to relate names to DTOs
            Map<String, TherapyProgramDTO> programMap = new HashMap<>();

            ObservableList<String> programNames = FXCollections.observableArrayList();

            for (TherapyProgramDTO program : programs) {
                programNames.add(program.getName());
                programMap.put(program.getName(), program);
            }

            CmbTherapyProgram.setItems(programNames);

            // Handle selection event
            CmbTherapyProgram.setOnAction(event -> {
                String selectedName = CmbTherapyProgram.getSelectionModel().getSelectedItem();
                if (selectedName != null) {
                    TherapyProgramDTO selectedProgram = programMap.get(selectedName);
                    if (selectedProgram != null) {
                        TxtProgramId.setText(selectedProgram.getId());
                    }
                }
            });

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @FXML
    void BtnDeleteOnAction(ActionEvent event) {
        String id = TxtId.getText();
        boolean isDeleted = therapistManageBO.deleteTherapist(id);
        if (isDeleted) {
            loadAllTherapist();
            therapistManageBO.generateNextTherapistId();
        }else {
            new Alert(Alert.AlertType.ERROR,"Therapist not deleted").show();
        }
    }

    @FXML
    void BtnResetOnAction(ActionEvent event) {
        TxtId.setText("");
        TxtName.clear();
        TxtEmail.clear();
        TxtPhone.clear();
        CmbTherapyProgram.getSelectionModel().clearSelection();
        TxtProgramId.clear();
    }

    @FXML
    void BtnUpdateOnAction(ActionEvent event) {
        String id = TxtId.getText();
        String name = TxtName.getText();
        String email = TxtEmail.getText();
        String phone = TxtPhone.getText();
        String therapyProgramName = CmbTherapyProgram.getValue();
        String therapyProgramId =  TxtProgramId.getText();

        try{
            TherapistDTO therapistDTO = new TherapistDTO(id, name, email, phone, therapyProgramId,therapyProgramName);
            boolean isUpdated = therapistManageBO.updateTherapist(therapistDTO);
            if (isUpdated) {
                loadAllTherapist();
                loadPrograms();
                therapistManageBO.generateNextTherapistId();
            }
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    @FXML
    void CmbTherapyProgramOnAction(ActionEvent event) {
        String therapyProgram = CmbTherapyProgram.getValue().toString();
        String[] split = therapyProgram.split(" ");
        String therapyProgramId = split[0];
        TxtProgramId.setText(therapyProgramId);

    }

    public void TblTherapistOnAction(MouseEvent mouseEvent) {
        TherapistTM therapistTM = TblTherapist.getSelectionModel().getSelectedItem();
        if (therapistTM != null) {
            TxtId.setText(therapistTM.getId());
            TxtName.setText(therapistTM.getName());
            TxtEmail.setText(therapistTM.getEmail());
            TxtPhone.setText(therapistTM.getContactNumber());
            CmbTherapyProgram.setValue(therapistTM.getTherapyProgramName());
            TxtProgramId.setText(therapistTM.getTherapyProgramId());
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setCellValueFactory();
        loadAllTherapist();
        loadPrograms();

        String id = null;
        try{
            id = therapistManageBO.generateNextTherapistId();
            TxtId.setText(id);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private void loadAllTherapist() {
        ObservableList<TherapistTM> obList = FXCollections.observableArrayList();
        try{
            List<TherapistDTO> therapistList = therapistManageBO.getAllTherapist();
            for (TherapistDTO therapistDTO : therapistList) {
                TherapistTM tm = new TherapistTM(
                     therapistDTO.getId(),
                        therapistDTO.getName(),
                        therapistDTO.getEmail(),
                        therapistDTO.getContactNumber(),
                        therapistDTO.getTherapyProgramId(),
                        therapistDTO.getTherapyProgramName()
                );
                obList.add(tm);
            }

            TblTherapist.setItems(obList);
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    private void setCellValueFactory() {
        ColId.setCellValueFactory(new PropertyValueFactory<>("id"));
        ColName.setCellValueFactory(new PropertyValueFactory<>("name"));
        ColEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        ColPhone.setCellValueFactory(new PropertyValueFactory<>("contactNumber"));
        ColProgramId.setCellValueFactory(new PropertyValueFactory<>("therapyProgramId"));
        ColTherapyProgarm.setCellValueFactory(new PropertyValueFactory<>("therapyProgramName"));
    }
}
