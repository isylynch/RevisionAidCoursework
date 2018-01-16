package Views;

import Model.DatabaseConnection;
import Model.Folder;
import javafx.animation.KeyFrame;
import javafx.animation.ScaleTransition;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

import java.util.ArrayList;

public class LayoutGenerator {

    public static BorderPane make(String title, Boolean settingsPane, Boolean progressPane, Boolean homePane, DatabaseConnection database, ArrayList<Folder> topFolders) {
        //Creates root pane and the top panel
        BorderPane root = new BorderPane();
        HBox topPane = new HBox(20);
        topPane.setPadding(new Insets(5));
        root.setTop(topPane);       //sets the HBox to be the top panel

        //Adding Text and Image to top pane
        Label topText = new Label();
        topText.setText(title);         //will display the text given as an argument
        topText.setStyle("-fx-font-family: Impact; -fx-font-size: 30pt; -fx-font-weight: bold;");

        topPane.getChildren().add(topText);         //adds text to the pane


        //if the view needs a settings button, then this section of code will be carried out
        if (settingsPane || progressPane || homePane) {
            HBox buttons = new HBox(20);
            HBox.setHgrow(buttons, Priority.ALWAYS);
            buttons.setAlignment(Pos.CENTER_RIGHT);

            if(homePane) {
                Button homeBtn = new Button();
                Image homeImg = new Image("/images/homebutton.png", 30, 30, true, true);
                homeBtn.setGraphic(new ImageView(homeImg));
                homeBtn.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                        stage.setScene(HomeView.view(database, topFolders));
                    }
                });
                buttons.getChildren().add(homeBtn);
            }

            if(progressPane) {
                Button seeProgress = new Button("See Progress");
                seeProgress.setStyle("-fx-font-size: 18px");
                seeProgress.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                        stage.setScene(ProgressView.view(database, topFolders));
                    }
                });
                buttons.getChildren().add(seeProgress);
            }

            if(settingsPane) {
                Image settingsImg = new Image("/images/settings.png", 30, 30, false, true);
                Button settingsBtn = new Button();
                settingsBtn.setGraphic(new ImageView(settingsImg));
                settingsBtn.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        VBox settingsBar = new VBox();
                        settingsBar.setStyle("-fx-background-color: rgba(255,255,255,0.1)");
                        //Stage popUp = new Stage(StageStyle.TRANSPARENT);
                        //popUp.initOwner((Stage) ((Node) event.getSource()).getScene().getWindow());
                        //popUp.setScene(new Scene(settingsBar, 1024,600, Color.TRANSPARENT));
                        if(root.getEffect() == null) {
                            Rectangle rect = new Rectangle();
                            root.setEffect(new GaussianBlur());
                           // popUp.show();
                        }
                        else {
                            root.setEffect(null);
                           // popUp.hide();
                        }
                    }
                });
                buttons.getChildren().add(settingsBtn);
            }

            topPane.getChildren().add(buttons);
        }

        return root;

    }

}
