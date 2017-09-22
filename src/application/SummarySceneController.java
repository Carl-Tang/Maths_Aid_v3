package application;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

import javafx.scene.chart.PieChart;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;

import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class SummarySceneController implements Initializable{
	@FXML
	private Button backToMainButton;
	@FXML
	private Label score;
	@FXML
	private PieChart pieChart;
	@FXML
	private Button goAdvancedButton;
	
	
	private MainController _mainController;
	private Database _db;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		_db = MainController.getDatabase();
		String userScore = Integer.toString(MainController.getScore());
		String maxScore = Integer.toString(MainController.NUMBER_OF_QUESTIONS);
		String toBeDisplayed = userScore + "/" + maxScore;
		score.setText(toBeDisplayed);
		//Preparing ObservbleList object         
		ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList( 
				new PieChart.Data("Correct", MainController.getScore()*10),
				new PieChart.Data("Wrong", (MainController.NUMBER_OF_QUESTIONS - MainController.getScore())*10));
		//Creating a Pie chart 
		pieChart.setData(pieChartData);
		if(MainController.getScore() < 8 || !MainController.getLevel().equals("Beginner")) {
			goAdvancedButton.setVisible(false);
		}
		//pieChart.set
	}

	// Event Listener on Button[#backToMainButton].onAction
	@FXML
	public void goBackToMain(ActionEvent event) {
		_mainController.goToHome();
	}
	// Event Listener on Button[#goAdvancedButton].onAction
	@FXML
	public void goAdvanced(ActionEvent event) {
		_db.generateQuestionList(MainController.NUMBER_OF_QUESTIONS, MainController.NUMBER_LOWER_BOUND, MainController.NUMBER_UPPER_BOUND_ADVANCED);
		_mainController.setLevel("Advanced");
		_mainController.showRecordScene();	}

	public void setMainController(MainController mainController) {
		_mainController = mainController;
		
	}
	
}
