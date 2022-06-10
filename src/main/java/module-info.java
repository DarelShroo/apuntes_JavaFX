module dad.preparacionexamendad {
    requires javafx.controls;
    requires javafx.fxml;

    opens dad.preparacionexamendad to javafx.fxml;
    exports dad.preparacionexamendad;
    exports dad.preparacionexamendad.SwitchScene;
    opens dad.preparacionexamendad.SwitchScene to javafx.fxml;
}