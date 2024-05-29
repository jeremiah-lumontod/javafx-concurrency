module org.jml.concurrency {
    requires javafx.controls;
    requires javafx.fxml;


    opens org.jml.concurrency to javafx.fxml;
    exports org.jml.concurrency;
}