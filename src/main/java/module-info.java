module SerenityMentalHealthTherapyCenter.ORM {
    requires jakarta.persistence;
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires javafx.graphics;
    requires jbcrypt;
    requires static lombok;
    requires org.hibernate.orm.core;

    requires java.naming;
    requires java.desktop;
    requires java.mail;

    opens lk.ijse.gdse.controller to javafx.fxml;
    opens lk.ijse.gdse.dto.tm to javafx.base;
    opens lk.ijse.gdse.config to jakarta.persistence;
    opens lk.ijse.gdse.entity to org.hibernate.orm.core;

    exports lk.ijse.gdse;
    exports lk.ijse.gdse.controller;
}