package lk.ijse.gdse.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

public class PaymentController {

    @FXML
    private Button BtnPay;

    @FXML
    private Button BtnPrint;

    @FXML
    private ComboBox<?> CmbProgram;

    @FXML
    private ComboBox<?> CmbStudentId;

    @FXML
    private TableColumn<?, ?> ColBalance;

    @FXML
    private TableColumn<?, ?> ColDate;

    @FXML
    private TableColumn<?, ?> ColId;

    @FXML
    private TableColumn<?, ?> ColName;

    @FXML
    private TableColumn<?, ?> ColPatientId;

    @FXML
    private TableColumn<?, ?> ColProgram;

    @FXML
    private TableColumn<?, ?> ColUpfrontPayment;

    @FXML
    private TableView<?> TblPayment;

    @FXML
    private Text TxtAmount;

    @FXML
    private TextField TxtBalance;

    @FXML
    private Text TxtDate;

    @FXML
    private Text TxtId;

    @FXML
    private Text TxtName;

    @FXML
    private TextField TxtProgramFee;

    @FXML
    private TextField TxtUpfrontPayment;

    @FXML
    private AnchorPane root;

    @FXML
    void BtnPayOnAction(ActionEvent event) {

    }

    @FXML
    void BtnPrintOnAction(ActionEvent event) {

    }

    @FXML
    void CmbProgramOnAction(ActionEvent event) {

    }

    @FXML
    void CmbStudentIdOnAction(ActionEvent event) {

    }

    public void TblPaymentOnAction(MouseEvent mouseEvent) {
    }
}
