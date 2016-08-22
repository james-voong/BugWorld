package world;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Animate extends Application {
	BorderPane border;
	GridPane grid;
	HBox buttons;
	Timeline timeLine;

	@Override
	public void start(Stage arg0) throws Exception {
		setUpLayout();
		Scene scene = new Scene(border, 500, 500);
		arg0.setScene(scene);
		arg0.show();

		World world = new World(500, 500);
		world.fillWorld(10, 9, 5);

		KeyFrame frame = new KeyFrame(Duration.millis(400), new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				world.updateWorld();
			}
		});
		timeLine = new Timeline(frame);
		timeLine.setCycleCount(javafx.animation.Animation.INDEFINITE);

	}

	public void setUpLayout() {
		border = new BorderPane();
		grid = new GridPane();
		buttons = new HBox();
		grid.setAlignment(Pos.CENTER);
		buttons.setAlignment(Pos.CENTER);
		border.setTop(buttons);
		border.setCenter(grid);
		setUpButtons();
	}

	public void setUpButtons() {
		Button play = new Button("Play");
		play.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				timeLine.play();
			}
		});
		Button clear = new Button("Clear");
		buttons.getChildren().addAll(play, clear);
	}

	public static void main(String[] args) {
		launch();
	}
}
