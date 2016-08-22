package world;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class Animate extends Application {
	BorderPane border;
	GridPane grid;
	HBox buttons;

	@Override
	public void start(Stage arg0) throws Exception {
		setUpLayout();
		Scene scene = new Scene(border, 500, 500);
		arg0.setScene(scene);
		arg0.show();
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
		Button clear = new Button("Clear");
		buttons.getChildren().addAll(play, clear);
	}

	public static void main(String[] args) {
		launch();
	}
}
