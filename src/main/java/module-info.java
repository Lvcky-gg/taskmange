module learn.learn {
    requires javafx.controls;
    requires javafx.fxml;


    opens learn.learn to javafx.fxml;
    exports learn.learn;
}