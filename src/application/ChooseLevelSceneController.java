package application;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;

public class ChooseLevelSceneController implements Initializable {
	@FXML
	private Button _backToMainButton;
	@FXML
	private Button _beginnerButton;
	@FXML
	private Button _advancedButton;
	private MainController _mainController;
	private Database _database;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		_database = MainController.getDatabase();
	}

	// Event Listener on Button[#_backToMainButton].onAction
	@FXML
	public void backToMainButtonClicked(ActionEvent event) {
		_mainController.goToHome();
	}

	// Event Listener on Button[#_beginnerButton].onAction
	@FXML
	public void beginnerButtonClicked(ActionEvent event) {
		_database.generateQuestionList(MainController.NUMBER_OF_QUESTIONS, MainController.NUMBER_LOWER_BOUND,
				MainController.NUMBER_UPPER_BOUND_BEGINNER);
		_mainController.setLevel("Beginner");
		_mainController.showRecordScene();
	}

	// Event Listener on Button[#_advancedButton].onAction
	@FXML
	public void advancedButtonClicked(ActionEvent event) {
		_database.generateQuestionList(MainController.NUMBER_OF_QUESTIONS, MainController.NUMBER_LOWER_BOUND,
				MainController.NUMBER_UPPER_BOUND_ADVANCED);
		_mainController.setLevel("Advanced");
		_mainController.showRecordScene();
	}

	public void setMainController(MainController mainController) {
		_mainController = mainController;
	}
}
