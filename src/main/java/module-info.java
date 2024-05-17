module fes.aragon.proyectooxxo {
    requires javafx.controls;
    requires javafx.fxml;


    exports fes.aragon.proyectooxxo.Inicio;
    opens fes.aragon.proyectooxxo.Inicio to javafx.fxml;
    exports fes.aragon.proyectooxxo.controller;
    opens fes.aragon.proyectooxxo.controller to javafx.fxml;
}