/**
 * Quinn Freas
 * https://github.com/qkfreas
 * Project: High Card
 * @author Quinn Freas
 */
package gui;

import controllers.Game;
import controllers.GameFiles;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import models.Player;

public class GUI extends Application {
	private BorderPane pane;
	private Game game;
	private Stage mainStage;
	private static Player player;
	private static GameFiles files;
	private Stage popup;

// constructor
	public GUI() {
		files = new GameFiles();
	}

// application launcher
	public void launchApp(String[] args) {
		Application.launch(args);
	}

// primary stage
	@Override
	public void start(Stage primaryStage) {
// creates the main pane
		pane = new BorderPane();
// sets welcome scene
		pane.setCenter(welcomeScene());
// creates the stage
		Scene scene = new Scene(pane, 675, 610);
		primaryStage.setTitle("High Card");
		primaryStage.setScene(scene);
		mainStage = primaryStage;
		mainStage.show();
	}

// welcome scene method
	private GridPane welcomeScene() {
		GridPane p = new GridPane();

		p.add(new Label(
				"Welcome!\nThe player and the dealer are both dealt cards at the same time.\nWhoever has the higher card wins!\nPress \"OK\" to continue."),
				0, 0);
		p.add(getTotalScore(), 0, 4);
		p.add(btnOK(), 2, 0);

		p.setHgap(10);
		p.setVgap(10);
		p.setAlignment(Pos.CENTER);
		GridPane.setValignment(btnOK(), VPos.CENTER);
		return p;
	}

// card images are displayed on center stage
	private BorderPane centerContent() {
		BorderPane p = new BorderPane();
// checks for image loading errors
		if (!game.getImageError()) {
			p.setLeft(dealerArea());
			p.setRight(playerArea());
			p.setCenter(deckArea());
		} else {
			p.getChildren().addAll(new Label("Error loading image"), new Label("Error loading image"));
			System.out.println("ERROR GETTING IMAGES");
		}

		return p;
	}

// deck area
	private VBox deckArea() {
		VBox p = new VBox(deckImage(), showCardsRemaining());

		p.setAlignment(Pos.CENTER);

		return p;
	}

// dealer area
	private VBox dealerArea() {
		VBox p = new VBox(game.getDealerCardImage(), new Label("\nDealer"));

		p.setPadding(new Insets(0, 0, 0, 20));
		p.setAlignment(Pos.CENTER);

		return p;
	}

// player area
	private VBox playerArea() {
		VBox p = new VBox(game.getPlayerCardImage(), new Label("\nPlayer"));

		p.setPadding(new Insets(0, 20, 0, 0));
		p.setAlignment(Pos.CENTER);

		return p;
	}

// create left side bar
	@SuppressWarnings("static-access")
	private VBox leftSideBar(boolean disable) {
		VBox p = new VBox(10);
		p.setStyle("-fx-background-color: silver;");

		Button btn2 = btnDeal(disable);

		p.setMargin(btn2, new Insets(50, 0, 0, 15));

		p.getChildren().add(btn2);

		p.setPadding(new Insets(0, 8, 0, 2));
		p.setAlignment(Pos.TOP_CENTER);

		return p;
	}

// creates right side bar
	private VBox rightSideBar() {
		VBox p = new VBox(0);
		p.setStyle("-fx-background-color: silver;");

		p.getChildren().addAll(gameMessages(), new Label("Rounds: " + game.getRoundsRemaining()));
		p.setPadding(new Insets(50, 10, 0, 10));

		return p;
	}

// creates deck image
	@SuppressWarnings("static-access")
	private StackPane deckImage() {
		StackPane p = new StackPane();

		for (int i = 0; i < game.getCardsRemaining(); i++) {
			ImageView img = game.getDeckImage();
			p.getChildren().add(img);
			p.setMargin(img, new Insets(0, i * .5, i * .5, 0));
		}

		return p;
	}

// generates content for game messages
	private Label gameMessages() {
		Label m = new Label("Wins: " + player.getWin() + "\nLosses: " + player.getLoss());

		return m;
	}

	private Label getTotalScore() {
		return new Label("Total Score:\n" + files.getRecord());
	}

// return a message for the cards remaining
	private Label showCardsRemaining() {
		return new Label("Cards remaining: " + game.getCardsRemaining());
	}

// deal button
// button starts disabled until player presses shuffle
	public Button btnDeal(boolean disable) {
		Button btn = new Button("Deal");

		btn.setDisable(disable);

		btn.setOnAction(new ButtonDealClass());

		return btn;
	}

// shuffle button
	public Button btnShuffle() {
		Button btn = new Button("Shuffle");

		btn.setOnAction(new ButtonShuffleClass());

		return btn;
	}

// OK button method
	public Button btnOK() {
		Button btn = new Button("OK");

		btn.setOnAction(new ButtonOKClass());

		return btn;
	}

// exit button
	private Button btnExit() {
		Button btn = new Button("Exit");

		btn.setOnAction(new ButtonExitClass());

		return btn;
	}

// game over content
	private BorderPane gameOver() {
		BorderPane p = new BorderPane();
		String temp;

		if (player.getWin() > player.getLoss()) {
			temp = "You won!";
		} else
			temp = "You lost.";

		FlowPane fp = new FlowPane(5, 0, btnExit(), btnOK());
		fp.setAlignment(Pos.CENTER);

		p.setLeft(getTotalScore());
		p.setRight(new VBox(new Label("This Game:"), gameMessages()));
		p.setBottom(fp);
		p.setCenter(new Label("Game over\n" + temp));

		p.setPadding(new Insets(10));

		return p;
	}

// file missing error
	private BorderPane fileError() {
		BorderPane p = new BorderPane();
		p.setCenter(new Label("ERROR: missing required files"));
		System.out.println("FILE ERROR: MISSING REQUIRED FILES");
		return p;
	}

// displays pop up window
	private void popUpWindow(String t, Pane p) {
		popup = new Stage();
		Scene sc = new Scene(p, 250, 150);
		popup.setTitle(t);
		popup.setScene(sc);
		popup.show();
	}

// OK button class
// interface
	class ButtonOKClass implements EventHandler<ActionEvent> {
		@Override
		public void handle(ActionEvent e) {
			try {
// creates Game object
				game = new Game();
				player = new Player();
// changes the scene to the game or resets the game
				pane.setCenter(centerContent());
				if (game.getImageError()) {
					popUpWindow("ERROR", fileError());
					System.out.println("ERROR AT GUI.BUTTONOKCLASS.HANDLE");
				}
				pane.setLeft(leftSideBar(false));
				pane.setRight(rightSideBar());
				popup.close();
			} catch (NoClassDefFoundError ncdfe) {
				popUpWindow("ERROR", fileError());
				files.createEmptyFile();
			} catch (NullPointerException npe) {
			}
		}
	}

// interface
	class ButtonShuffleClass implements EventHandler<ActionEvent> {
		@Override
		public void handle(ActionEvent e) {
			pane.getChildren().remove(leftSideBar(true));
			pane.setLeft(leftSideBar(false));
		}
	}

// interface
	class ButtonDealClass implements EventHandler<ActionEvent> {
		@Override
		public void handle(ActionEvent e) {
			try {
				game.deal();
				game.getWinner();
				pane.setCenter(centerContent());
				pane.getChildren().remove(rightSideBar());
				pane.setRight(rightSideBar());

				if (game.getRoundsRemaining() < 1) {
					pane.setLeft(leftSideBar(true));
					files.setRecord(player.getWin(), player.getLoss());
					game.resetCurrentID();
					popUpWindow("Game Over", gameOver());
				}
			} catch (NullPointerException npe) {

			}
		}
	}

// interface
	class ButtonExitClass implements EventHandler<ActionEvent> {
		@Override
		public void handle(ActionEvent e) {
			popup.close();
			mainStage.close();
			System.exit(0);
		}
	}

}
