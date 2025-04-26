package lk.ijse.gdse.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.ResourceBundle;

public class PaymentController implements Initializable {

    @FXML
    private Button BtnPay;

    @FXML
    private Button BtnPay1;

    @FXML
    private Button BtnPay2;

    @FXML
    private Button BtnPrint;

    @FXML
    private TableColumn<?, ?> ColAmount;

    @FXML
    private TableColumn<?, ?> ColDate;

    @FXML
    private TableColumn<?, ?> ColDesc;

    @FXML
    private TableColumn<?, ?> ColId;

    @FXML
    private TableColumn<?, ?> ColPatientName;

    @FXML
    private TableColumn<?, ?> ColProgram;

    @FXML
    private TableColumn<?, ?> ColRemaining;

    @FXML
    private TableColumn<?, ?> ColSessionId;

    @FXML
    private TableColumn<?, ?> ColStatus;

    @FXML
    private RadioButton RadComplete;

    @FXML
    private RadioButton RadOngoing;

    @FXML
    private TableView<?> TblPayment;

    @FXML
    private Text TxtDate;

    @FXML
    private Text TxtDesc;

    @FXML
    private Text TxtFee;

    @FXML
    private Text TxtId;

    @FXML
    private Text TxtName;

    @FXML
    private Text TxtProgram;

    @FXML
    private TextField TxtRemainingAmount;

    @FXML
    private AnchorPane root;

    @FXML
    void BtnPayOnAction(ActionEvent event) {

    }

    @FXML
    void BtnPrintOnAction(ActionEvent event) {

    }

    @FXML
    void TblPaymentOnAction(MouseEvent event) {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
