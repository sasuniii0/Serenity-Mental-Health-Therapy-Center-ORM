module SerenityMentalHealthTherapyCenter.ORM {
    requires jakarta.persistence;
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;
    requires jbcrypt;
    requires static lombok;
    requires org.hibernate.orm.core;

    requires java.naming;
    requires java.desktop;

    opens lk.ijse.gdse.controller to javafx.fxml;
    opens lk.ijse.gdse.config to jakarta.persistence;
    opens lk.ijse.gdse.entity to org.hibernate.orm.core;

    exports lk.ijse.gdse;
    exports lk.ijse.gdse.controller;
}