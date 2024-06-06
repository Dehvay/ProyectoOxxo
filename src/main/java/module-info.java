module fes.aragon.proyectooxxo {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.kordamp.bootstrapfx.core;
    requires de.jensd.fx.glyphs.fontawesome;
    requires java.desktop;

    exports fes.aragon.proyectooxxo.Inicio;
    opens fes.aragon.proyectooxxo.Inicio to javafx.fxml;
    exports fes.aragon.proyectooxxo.controller;
    opens fes.aragon.proyectooxxo.controller to javafx.fxml;
    opens fes.aragon.proyectooxxo.modelo to javafx.base;
}