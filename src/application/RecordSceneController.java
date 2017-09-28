package application;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;

import javafx.concurrent.Task;
import javafx.event.ActionEvent;

import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;

public class RecordSceneController implements Initializable {
	@FXML
	private Button backToMainButton;
	@FXML
	private Label displayedNumber;
	@FXML
	private Button recordButton;
	@FXML
	private ProgressBar progressBar;

	private Database _db;
	private MainController _mainController;
	private String crtNum;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		_db = MainController.getDatabase();
		if (_db.reportTrial() == 0) {
			crtNum = _db.nextNumber();
		} else {
			crtNum = _db.currentNumber();
		}
		displayedNumber.setText(crtNum);
	}

	// Event Listener on Button[#backToMainButton].onAction
	@FXML
	public void backToMain(ActionEvent event) {
		_mainController.goToHome();
	}

	// Event Listener on Button[#recordButton].onAction
	@FXML
	public void recording(ActionEvent event) {
		backToMainButton.setDisable(true);
		_db.incrementTrial();
		recordButton.setText("Recording...");
		recordButton.setDisable(true);
		Timer timer = new Timer();
		
		// set up a timer to update the progress bar every 100ms for three seconds
		timer.scheduleAtFixedRate(new TimerTask() {

			// i will iterate from 0 to 20 (to 2000ms)
			int i = 0;

			@Override
			public void run() {
				progressBar.setProgress(i / 30.0);
				i++;
				if (i == 30) {
					progressBar.setProgress(0);
					timer.cancel();
				}
			}
		}, 0, 100);
		
		// put the task of recording to an individual thread
		Task<Void> record = new Task<Void>() {
			@Override
			public Void call() {
				new BashProcess("./MagicStaff.sh", "record", crtNum);

				
				
				return null;
			}
		};
		
		//when finished, checkCorrectness of the answer
		//this task is put in another thread as the efficiency of recognize the questionable
		record.setOnSucceeded(e -> {
			Task<Void> check = new Task<Void>() {
				@Override
				public Void call() {
					SpeechRecognizer.checkCorrectness();
					return null;
				}
			};
			//change scene to show result upon success
			check.setOnSucceeded(rce -> {
				_mainController.showResultScene();
			});
			new Thread(check).start();
		});
		new Thread(record).start();
	}

	public void setMainController(MainController mainController) {
		_mainController = mainController;

	}
}