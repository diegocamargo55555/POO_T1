module projeto1.appmitologia {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;
    requires transitive java.sql;
    requires java.desktop;

    opens projeto1.appmitologia.controller to javafx.fxml;
    exports projeto1.appmitologia.controller;

    opens projeto1.appmitologia.model to javafx.fxml;
    exports projeto1.appmitologia.model;

    opens projeto1.appmitologia.main to javafx.fxml;
    exports projeto1.appmitologia.main;

    opens projeto1.appmitologia.dao to javafx.fxml;
    exports projeto1.appmitologia.dao;
}
