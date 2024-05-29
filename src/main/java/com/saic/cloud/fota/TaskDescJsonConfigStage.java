package com.saic.cloud.fota;

import com.saic.cloud.fota.constants.ToolContants;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class TaskDescJsonConfigStage extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/TaskDescJsonConfig.fxml"));
        primaryStage.setTitle(ToolContants.TASK_DESC_JSON_CONFIG_STAGE_TITLE);
        primaryStage.setScene(new Scene(root));
        primaryStage.setFullScreen(true);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
