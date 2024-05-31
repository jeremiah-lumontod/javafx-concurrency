module org.jml.concurrency {
    requires javafx.controls;
    requires javafx.fxml;


    opens org.jml.concurrency to javafx.fxml;
    exports org.jml.concurrency;
    exports org.jml.concurrency.fib;
    exports org.jml.concurrency.progressnotif;
    opens org.jml.concurrency.fib to javafx.fxml;
}