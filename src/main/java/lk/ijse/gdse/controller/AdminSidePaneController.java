package lk.ijse.gdse.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.*;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import lk.ijse.gdse.bo.BOFactory;
import lk.ijse.gdse.bo.custom.*;

import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

public class AdminSidePaneController implements Initializable {

    @FXML
    private Button BtnFinacialReport;

    @FXML
    private Button BtnPatientTherapyHistory;

    @FXML
    private Button BtnTrackPayments;

    @FXML
    private AreaChart<String , Number> GraphTherapyPerform;

    @FXML
    private LineChart<String , Number> GraphTherapySession;

    @FXML
    private Pane PanePatient;

    @FXML
    private Pane PaneProgram;

    @FXML
    private Pane PaneTherapist;

    @FXML
    private Pane PaneUser;

    @FXML
    private CategoryAxis XTherapistPerform;

    @FXML
    private CategoryAxis XTherapySession;

    @FXML
    private NumberAxis YAxisTherapistPerform;

    @FXML
    private NumberAxis YTherapySession;

    @FXML
    private AnchorPane root;

    private final PatientManageBO patientBO = BOFactory.getBoFactory().getBO(BOFactory.BOTypes.PATIENT);
    private final TherapyProgramManageBO programBO = BOFactory.getBoFactory().getBO(BOFactory.BOTypes.THERAPY_PROGRAM);
    private final TherapistManageBO therapistBO = BOFactory.getBoFactory().getBO(BOFactory.BOTypes.THERAPIST);
    private final PaymentManageBO paymentBO = BOFactory.getBoFactory().getBO(BOFactory.BOTypes.PAYMENT);
    private final TherapySessionBO sessionBO = BOFactory.getBoFactory().getBO(BOFactory.BOTypes.THERAPY_SESSION);
    @FXML
    void BtnFinacialReportOnAction(ActionEvent event) {
        try {
            // Generate financial summary report
            Map<String, Double> financialData = paymentBO.getMonthlyRevenue();
            String reportContent = "Monthly Financial Report\n\n";

            for (Map.Entry<String, Double> entry : financialData.entrySet()) {
                reportContent += String.format("%s: Rs.%.2f\n", entry.getKey(), entry.getValue());
            }

            double total = financialData.values().stream().mapToDouble(Double::doubleValue).sum();
            reportContent += String.format("\nTotal Revenue: Rs.%.2f", total);

            // Show report in dialog
            showReportDialog("Financial Report", reportContent);

            // Update chart
            updateFinancialChart(financialData);

        } catch (Exception e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Failed to generate financial report").show();
        }
    }

    @FXML
    void BtnPatientTherapyHistoryOnAction(ActionEvent event) {
        /*try {
            // Generate patient therapy history report
            //Map<String, Integer> therapyCounts = sessionBO.getPatientSessionCounts();
            String reportContent = "Patient Therapy History\n\n";

            for (Map.Entry<String, Integer> entry : therapyCounts.entrySet()) {
                String patientName = patientBO.getPatientNameById(entry.getKey());
                reportContent += String.format("%s: %d sessions\n",
                        patientName != null ? patientName : entry.getKey(),
                        entry.getValue());
            }

            // Show report in dialog
            showReportDialog("Patient Therapy History", reportContent);

            // Update chart
            updateTherapyHistoryChart(therapyCounts);

        } catch (Exception e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Failed to generate therapy history").show();
        }*/
    }

    @FXML
    void BtnTrackPaymentsOnAction(ActionEvent event) {
        try {
            // Generate payment tracking report
            List<Object[]> pendingPayments = paymentBO.getPendingPayments();
            String reportContent = "Pending Payments Report\n\n";

            for (Object[] payment : pendingPayments) {
                reportContent += String.format("Patient: %s, Amount Due: Rs.%.2f, Due Date: %s\n",
                        patientBO.getPatientNameById((String)payment[0]),
                        (Double)payment[1],
                        payment[2].toString());
            }

            // Show report in dialog
            showReportDialog("Pending Payments", reportContent);

        } catch (Exception e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Failed to generate payment report").show();
        }
    }

    @FXML
    void PanePatientOnAction(MouseEvent event) {
        try {
            // Generate patient statistics report
            long totalPatients = patientBO.getTotalPatientCount();
            Map<String, Long> patientsByGender = patientBO.getPatientCountByGender();

            String reportContent = String.format("Patient Statistics\n\nTotal Patients: %d\n", totalPatients);
            for (Map.Entry<String, Long> entry : patientsByGender.entrySet()) {
                reportContent += String.format("%s: %d\n", entry.getKey(), entry.getValue());
            }

            // Show report in dialog
            showReportDialog("Patient Statistics", reportContent);

        } catch (Exception e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Failed to generate patient report").show();
        }
    }

    @FXML
    void PaneProgramOnAction(MouseEvent event) {
        try {
            // Generate program statistics report
            Map<String, Integer> programEnrollments = programBO.getProgramEnrollmentCounts();
            String reportContent = "Therapy Program Statistics\n\n";

            for (Map.Entry<String, Integer> entry : programEnrollments.entrySet()) {
                reportContent += String.format("%s: %d enrollments\n", entry.getKey(), entry.getValue());
            }

            // Show report in dialog
            showReportDialog("Program Statistics", reportContent);

            // Update chart
            updateProgramChart(programEnrollments);

        } catch (Exception e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Failed to generate program report").show();
        }
    }

    @FXML
    void PaneTherapistOnAction(MouseEvent event) {
        /*try {
            // Generate therapist performance report
            Map<String, Integer> therapistSessions = therapistBO.getTherapistSessionCounts();
            String reportContent = "Therapist Performance\n\n";

            for (Map.Entry<String, Integer> entry : therapistSessions.entrySet()) {
                reportContent += String.format("%s: %d sessions conducted\n",
                        therapistBO.getTherapistNameById(entry.getKey()),
                        entry.getValue());
            }

            // Show report in dialog
            showReportDialog("Therapist Performance", reportContent);

            // Update chart
            updateTherapistChart(therapistSessions);

        } catch (Exception e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Failed to generate therapist report").show();
        }*/
    }

    @FXML
    void PaneUserOnAction(MouseEvent event) {
        try {
            // Generate user activity report
            Map<String, String> recentLogins = getRecentUserLogins(); // Implement this in your BO
            String reportContent = "User Activity Report\n\n";

            for (Map.Entry<String, String> entry : recentLogins.entrySet()) {
                reportContent += String.format("User: %s, Last Login: %s\n",
                        entry.getKey(), entry.getValue());
            }

            // Show report in dialog
            showReportDialog("User Activity", reportContent);

        } catch (Exception e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Failed to generate user report").show();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        initializeCharts();

        refreshAllCharts();
    }
    private void initializeCharts() {
        // Financial Chart
        XYChart.Series<String, Number> financialSeries = new XYChart.Series<>();
        financialSeries.setName("Monthly Revenue");
        GraphTherapyPerform.getData().add(financialSeries);

        // Therapy History Chart
        XYChart.Series<String, Number> therapySeries = new XYChart.Series<>();
        therapySeries.setName("Sessions");
        GraphTherapySession.getData().add(therapySeries);
    }
    private void refreshAllCharts() {
       /* try {
          //  updateFinancialChart(paymentBO.getMonthlyRevenue());
           // updateTherapyHistoryChart(sessionBO.getPatientSessionCounts());
            //updateProgramChart(programBO.getProgramEnrollmentCounts());
           // updateTherapistChart(therapistBO.getTherapistSessionCounts());
        } catch (Exception e) {
            e.printStackTrace();
        }*/
    }

    private void updateFinancialChart(Map<String, Double> financialData) {
        XYChart.Series<String, Number> series = (XYChart.Series<String, Number>) GraphTherapyPerform.getData().get(0);
        series.getData().clear();

        for (Map.Entry<String, Double> entry : financialData.entrySet()) {
            series.getData().add(new XYChart.Data<>(entry.getKey(), entry.getValue()));
        }
    }

    private void updateTherapyHistoryChart(Map<String, Integer> therapyCounts) {
        XYChart.Series<String, Number> series = (XYChart.Series<String, Number>) GraphTherapySession.getData().get(0);
        series.getData().clear();

        // Get top 5 patients by session count
        therapyCounts.entrySet().stream()
                .sorted(Map.Entry.<String, Integer>comparingByValue().reversed())
                .limit(5)
                .forEach(entry -> {
                    String patientName = patientBO.getPatientNameById(entry.getKey());
                    series.getData().add(new XYChart.Data<>(
                            patientName != null ? patientName : entry.getKey(),
                            entry.getValue()
                    ));
                });
    }
    private void updateProgramChart(Map<String, Integer> programEnrollments) {
        // Implement similar to other chart updates
    }

    private void updateTherapistChart(Map<String, Integer> therapistSessions) {
        // Implement similar to other chart updates
    }

    private void showReportDialog(String title, String content) {
        TextArea textArea = new TextArea(content);
        textArea.setEditable(false);
        textArea.setWrapText(true);

        Dialog<Void> dialog = new Dialog<>();
        dialog.setTitle(title);
        dialog.getDialogPane().setContent(textArea);
        dialog.getDialogPane().getButtonTypes().add(ButtonType.CLOSE);
        dialog.showAndWait();
    }

    private Map<String, String> getRecentUserLogins() {
        // Implement this method to return recent user logins
        // Example: {"admin": "2023-10-15", "reception": "2023-10-14"}
        return new HashMap<>();
    }
}
