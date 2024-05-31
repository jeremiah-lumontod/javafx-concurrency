package org.jml.concurrency;

import javafx.application.Platform;
import javafx.beans.binding.When;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Worker;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import org.jml.concurrency.task.EvenNumTask;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MainController {

    @FXML
    private Button cancel;

    @FXML
    private TextField endTime;

    @FXML
    private TextArea exception;

    @FXML
    private Button exit;

    @FXML
    private Label message;

    @FXML
    private Label progress;

    @FXML
    private ProgressBar progressBar;

    @FXML
    private Label running;

    @FXML
    private Button start;

    @FXML
    private TextField startTime;

    @FXML
    private Label state;

    @FXML
    private Label title;

    @FXML
    private Label totalWork;

    @FXML
    private Label value;

    @FXML
    private Label workDone;

    private EvenNumTask task = new EvenNumTask(1, 20, 1000);

    private Worker<ObservableList<Integer>> worker;

    @FXML
    protected void onStartButtonClick() {
        try {

            task = new EvenNumTask(1, 20, 1000);
            task.setOnSucceeded(e -> worker.getValue().setAll(task.getValue()));
            task.run();
            //bindToWorker(worker);
/*            ExecutorService executorService = Executors.newFixedThreadPool(1);
            executorService.execute(task);
            executorService.shutdown();*/

        }catch(Exception e) {
            e.printStackTrace();
        }
    }
    @FXML
    protected void onCancelButtonClick() {
        task.cancel();
    }
    @FXML
    protected void onExitButtonClick() {
        Platform.exit();
    }

    @FXML
    public void initialize() {
        System.out.println("initialize");

    }

    private void bindToWorker(Worker<ObservableList<Integer>> worker) {
        title.textProperty().bind(worker.titleProperty());
        message.textProperty().bind(worker.messageProperty());
        running.textProperty().bind(worker.runningProperty().asString());
        state.textProperty().bind(worker.stateProperty().asString());
        totalWork.textProperty().bind(new When(worker.totalWorkProperty().isEqualTo(-1))
                .then("Unknown")
                .otherwise(worker.totalWorkProperty().asString()));
        workDone.textProperty().bind(new When(worker.workDoneProperty().isEqualTo(-1))
                .then("Unknown")
                .otherwise(worker.workDoneProperty().asString()));
        progress.textProperty().bind(new When(worker.progressProperty().isEqualTo(-1))
                .then("Unknown")
                .otherwise(worker.progressProperty().asString()));
        progressBar.progressProperty().bind(worker.progressProperty());
        value.textProperty().bind(worker.valueProperty().asString());
        exception.textProperty().bind(worker.exceptionProperty().asString());
    }



}