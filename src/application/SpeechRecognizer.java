package application;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;

public final class SpeechRecognizer {

	private static final HashMap<String, String> _dictionary = loadDictionary();
	// private static final Database _database = MainController.getDatabase();

	public static void checkCorrectness() {
		// TODO
	}

	/**
	 * This method uses a shell script which uses htk toolbox to do speech
	 * recognition. It will load the wav recording saved locally and return the
	 * recognized word.
	 * 
	 * @return recognized word from the locally saved (currentNumberValue).wav as a
	 *         string
	 * @throws IOException
	 */
	private static String recognizeRecording() throws IOException {
		// TODO

		// List<String> htkResult = process.getresult();
		List<String> htkResult = Files.readAllLines(Paths.get("recout.mlf"));

		String result = "";
		for (String s : htkResult) {
			System.out.println(s);
			if (!s.startsWith("#") && !s.startsWith("\"") && !s.startsWith(".") && !s.equals("sil")) {
				result = result + s + " ";
			}
		}

		// remove the last space
		if (result.endsWith(" ")) {
			result = result.substring(0, result.length() - 1);
		}

		return result;
	}

	/**
	 * Load the a dictionary file into a HashMap
	 * 
	 * @return HashMap of the Maori number dictionary
	 */
	public static HashMap<String, String> loadDictionary() {
		// TODO
		return null;

	}

}
