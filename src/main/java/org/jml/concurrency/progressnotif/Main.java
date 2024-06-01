package org.jml.concurrency.progressnotif;

import javafx.application.Application;
import javafx.concurrent.Task;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

  @Override
  public void start(Stage stage) {
    Group root = new Group();
    Scene scene = new Scene(root, 300, 150);
    stage.setScene(scene);
    stage.setTitle("Sample");

    Task<Integer> task = new Task<Integer>() {
      @Override protected Integer call() throws Exception {
          int iterations;
          for (iterations = 0; iterations < 10; iterations++) {
              if (isCancelled()) {
                  updateMessage("Cancelled");
                  break;
              }
              updateMessage("Iteration " + iterations);
              updateProgress(iterations, 1000);

              try {
                  Thread.sleep(100);
              } catch (InterruptedException interrupted) {
                  if (isCancelled()) {
                      updateMessage("Cancelled");
                      break;
                  }
              }
          }
          return iterations;
      }
  };


 
  task.run();
  System.out.println(task.getMessage());
  //  root.getChildren().add(t);


    stage.show();
  }

  public static void main(String[] args) {
    launch(args);
  }
}