package application;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class BashProcess {

	private ArrayList<String> _result;
	public  BashProcess(String command) {
		_result = new ArrayList<String>();
		try {
			ProcessBuilder pb = new ProcessBuilder(command);
			Process process = pb.start();

			BufferedReader stdout = new BufferedReader(new InputStreamReader(process.getInputStream()));

			int exitStatus = process.waitFor();

			if (exitStatus == 0) {
				String line;
				while ((line = stdout.readLine()) != null) {
					_result.add(line);
				}
			}else {
				String line;
				while ((line = stdout.readLine()) != null) {
					System.err.println(line);
				}
			}
		} catch(Exception e) {
			e.printStackTrace();
		}

	}	
	public  BashProcess(String path, String command) {
		_result = new ArrayList<String>();
		try {
			ProcessBuilder pb = new ProcessBuilder(path,command);
			Process process = pb.start();

			BufferedReader stdout = new BufferedReader(new InputStreamReader(process.getInputStream()));

			int exitStatus = process.waitFor();

			if (exitStatus == 0) {
				String line;
				while ((line = stdout.readLine()) != null) {
					_result.add(line);
				}
			}else {
				String line;
				while ((line = stdout.readLine()) != null) {
					System.err.println(line);
				}
			}
		} catch(Exception e) {
			e.printStackTrace();
		}

	}	

	public BashProcess(String path, String command, String filename) {
		System.out.println("0");
		_result = new ArrayList<String>();
		try {
			System.out.println("1");
			ProcessBuilder pb = new ProcessBuilder(path,command,filename);
			Process process = pb.start();

			System.out.println("2");
			BufferedReader stdout = new BufferedReader(new InputStreamReader(process.getInputStream()));

			System.out.println("3");
			int exitStatus = process.waitFor();

			System.out.println("4");
			if (exitStatus == 0) {
				System.out.println("5");
				String line;
				while ((line = stdout.readLine()) != null) {
					System.out.println("6");
					_result.add(line);
				}
			}else {
				System.out.println("7");
				String line;
				while ((line = stdout.readLine()) != null) {
					System.err.println(line);
				}
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	public ArrayList<String> getresult() {
		return _result;	
	}


}
