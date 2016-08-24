package world;

import java.util.ArrayList;

import bug.Bug;
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
import obstacle.Obstacle;
import plant.Plant;

public class Animate extends Application {
	BorderPane border;
	GridPane grid;
	HBox buttons;
	Timeline timeLine;
	World world;
	ArrayList<Bug> bugs;
	ArrayList<Plant> plants;
	ArrayList<Obstacle> obstacles;

	@Override
	public void start(Stage arg0) throws Exception {
		setUpLayout();
		Scene scene = new Scene(border, 500, 500);
		arg0.setScene(scene);
		arg0.show();
		generateWorld();

		KeyFrame frame = new KeyFrame(Duration.millis(400), new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				world.updateWorld();
				updateObjects();
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
				if (play.getText() == "Play") {
					timeLine.play();
					play.setText("Stop");

				} else if (play.getText() == "Stop") {
					timeLine.stop();
					play.setText("Play");

				}
			}
		});
		Button clear = new Button("Clear");
		clear.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub

			}
		});
		buttons.getChildren().addAll(play, clear);
	}

	public void generateWorld() {
		world = new World(30, 30);
		world.fillWorld(10, 9, 5);
		updateObjects();

	}

	public void updateObjects() {
		bugs = world.getBugs();
		plants = world.getPlants();
		obstacles = world.getObstacles();
		for (int i = 0; i < bugs.size(); i++) {
			int columnIndex = bugs.get(i).getPosx();
			int rowIndex = bugs.get(i).getPosy();
			grid.add(bugs.get(i), columnIndex, rowIndex);
		}
		for (int x = 0; x < plants.size(); x++) {
			int columnIndex = plants.get(x).getPosX();
			int rowIndex = plants.get(x).getPosY();
			grid.add(plants.get(x), columnIndex, rowIndex);
		}
		for (int y = 0; y < obstacles.size(); y++) {
			int columnIndex = obstacles.get(y).getPosX();
			int rowIndex = obstacles.get(y).getPosY();
			grid.add(obstacles.get(y), columnIndex, rowIndex);
		}

	}

	public static void main(String[] args) {
		launch();
	}

}
