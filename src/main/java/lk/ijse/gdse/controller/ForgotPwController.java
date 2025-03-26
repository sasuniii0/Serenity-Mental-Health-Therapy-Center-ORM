package lk.ijse.gdse.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.util.Objects;
import java.util.Properties;

public class ForgotPwController {
    @FXML
    private Button BtnBackToSignIn;

    @FXML
    private Button BtnVerify;

    @FXML
    private TextField TxtEmail;

    @FXML
    private AnchorPane root;

    @FXML
    void BtnBackToSignInOnAction(ActionEvent event) {

    }

    @FXML
    void BtnVerifyOnAction(ActionEvent event) {

    }

    public void onVerifyAction(ActionEvent actionEvent) {
        try {
            // Generate the verification code
            String verificationCode = new VerificationCodeGen().getCode(5);

            // Print the verification code to the terminal
            System.out.println("Verification Code: " + verificationCode);

            String fromEmail = "admin@gmail.com";
            String toEmail = TxtEmail.getText();
            String host = "127.0.0.1";

            Properties properties = System.getProperties();
            properties.setProperty("mail.smtp.host", host);
            properties.setProperty("mail.smtp.port", "587");
            // => node=> nodemailer, (sendGrid, twilio)
            Session session = Session.getDefaultInstance(properties);

            //-------------------------
            MimeMessage mimeMessage = new MimeMessage(session);
            mimeMessage.setFrom(new InternetAddress(fromEmail));
            mimeMessage.setSubject("Verification Code");
            mimeMessage.setText("Verification Code : " + verificationCode);
            NewPwController verify=new NewPwController ();
            //verify.setUserData(Integer.parseInt(verificationCode),toEmail);
            mimeMessage.addRecipient(Message.RecipientType.TO, new InternetAddress(toEmail));

            VerifyAccountController.setUserData(String.valueOf(Integer.parseInt(verificationCode)),toEmail);

            // Uncomment this to send the email
            // Transport.send(mimeMessage);
            System.out.println("Email preparation completed!");

            //=======================>

            Stage window = (Stage) BtnVerify.getScene().getWindow();
            window.close();
            Parent load = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/view/VerifyAccount.fxml")));
            Scene scene = new Scene(load);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.show();

        } catch (MessagingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void btnBackToSignInOnAction(MouseEvent mouseEvent) {
    }
}
