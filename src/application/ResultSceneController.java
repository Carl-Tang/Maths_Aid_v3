package application;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

import javafx.scene.media.AudioClip;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import javafx.concurrent.Task;
import javafx.event.ActionEvent;

import javafx.scene.control.Label;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class ResultSceneController implements Initializable {
	@FXML
	private Button backToMainButton;
	@FXML
	private Label displayedCorrectness;
	@FXML
	private Button replayButton;
	@FXML
	private Button tryAgainButton;
	@FXML
	private Button nextButton;
	@FXML
	private Label maoriText;
	
	private Database _db;
	private String _currentNumber;
	private MainController _mainController;

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		_db = MainController.getDatabase();

		// load image to volume button
		String url;
		try {
			url = new File("volume.png").toURI().toURL().toString();
			Image volume = new Image(url);
			ImageView imageView = new ImageView(volume);
			imageView.setFitWidth(50);
			imageView.setFitHeight(50);
			replayButton.setGraphic(imageView);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}

		// get properties of the number needed from database and unpack them
		List<String> numProperties = _db.numPorperties();

		_currentNumber = numProperties.get(0);

		String correctness = numProperties.get(1);

		String showedCorrectness = "Wrong";
		if (correctness.toLowerCase().startsWith("t")) {
			showedCorrectness = "Correct";
		}
		displayedCorrectness.setText(showedCorrectness);

		String recognizedWord = numProperties.get(2);
		
		maoriText.setText("You said " + recognizedWord);
		if (_db.reportTrial() < 2) {
			//System.out.println(_db.reportTrial());
			tryAgainButton.setText("try again");
		} else {
			tryAgainButton.setVisible(false);
		}
		if (!_db.canTryAgain() | correctness.toLowerCase().startsWith("t")) {
			tryAgainButton.setVisible(false);
		}

	}

	// Event Listener on Button[#backToMainButton].onAction
	@FXML
	public void backToMain(ActionEvent event) {
		_mainController.goToHome();
	}

	// Event Listener on Button[#replayButton].onAction
	@FXML
	public void echo(ActionEvent event) {
		
		Task<Void> replay = new Task<Void>() {
			@Override
			public Void call() {
				
				//new BashProcess("./MagicStaff.sh", "replay", _currentNumber);
				System.gc();
				
				try {
					AudioClip replay = new AudioClip(new File(_currentNumber + ".wav").toURI().toURL().toString());
					replay.play();
				} catch (MalformedURLException e) {
					e.printStackTrace();
				}
				return null;
			}
		};
		new Thread(replay).start();
	}

	// Event Listener on Button[#tryAgainButton].onAction
	@FXML
	public void tryAgain(ActionEvent event) {
		_mainController.showRecordScene();
		new BashProcess("./MagicStaff.sh", "remove", _currentNumber);
	}

	// Event Listener on Button[#nextButton].onAction
	@FXML
	public void goNext(ActionEvent event) {
		_db.resetTrial();
		_mainController.getStatistics().update();
		_mainController.showRecordScene();
		_db.incrementQNum();
		new BashProcess("./MagicStaff.sh", "remove", _currentNumber);
	}

	public void setMainController(MainController mainController) {
		_mainController = mainController;

	}
}
