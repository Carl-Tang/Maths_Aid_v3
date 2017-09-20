package application;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;

public class StartSceneController implements Initializable{
	@FXML
	private Button _startButton;
	private MainController _mainController;

	// Event Listener on Button[#_startButton].onAction
	@FXML
	public void startButtonClicked(ActionEvent event) {
		_mainController.showChooseLevelScene();
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
	}

	public void setMainController(MainController mainController) {
		_mainController = mainController;
	}
}
