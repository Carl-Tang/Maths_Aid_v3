package application;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class Database {
 
	private	int _questionNum;
	private List<String> _toBeUsed;
	private String _numberTested;	
	
	//order: numValue, Correctness, maoriword
	private Map<Integer, List<String>> _userCloud;
	private Integer _trialNum;
	
	//constructer, initializing fields
	public Database() {
		_questionNum = 1;
		_trialNum = 0;
		_toBeUsed = new ArrayList<String>();
		_userCloud = new HashMap<Integer, List<String>>();
	}

	//generate a list of random numbers for the tests
	public void generateQuestionList(int sizeOfList, int lower, int upper) {
		if (!(lower > upper)) {
			for (int i = 0; i<sizeOfList; i++) {
				int randNum = new Random().nextInt(upper) + 1;
				_toBeUsed.add(Integer.toString(randNum));
			}
		}
	}
	
	//iterates to next number to be asked
	public String nextNumber() {
		String crtNum = null;
		if (!_toBeUsed.isEmpty()) {
			crtNum = _toBeUsed.get(0);
			_numberTested = crtNum;
			System.out.println("NUm tested "+crtNum);
			_toBeUsed.remove(0);
		}
		return crtNum;
	}


	public void updateResult(String maoriWord, boolean correctness){
		List<String> result = new ArrayList<String>();
		
		result.add(_numberTested);
		result.add(Boolean.toString(correctness));
		result.add(maoriWord);
		_userCloud.put(_questionNum, result);
	}
	
	//update question number i.e. 1-10
	public void incrementQNum() {
		_questionNum++;
	}
	
	//result list to report result of questions tested
	public List<String> getTestResults (){
		List<String> results = new ArrayList<String>();
		if(!(_userCloud.isEmpty())) {
			for(int i=1; i<_userCloud.size()+1; i++) {
				List<String> Aresult = new ArrayList<String>();
				Aresult.add(_userCloud.get(i).get(0));
				Aresult.add(_userCloud.get(i).get(1));
				
				//NumValue, Boolean
				String message = Aresult.get(0) + " " + Aresult.get(1);
				results.add(message);
			}
		}
		return results;
	}
	
	public String currentNumber() {
		return _numberTested;
		
	}
	
	public void incrementTrial() {
		_trialNum++;
	}
	
	public void resetTrial() {
		_trialNum = 0;
	}
	
	public int reportTrial() {
		return _trialNum;
	}
	
	public boolean canTryAgain() {
		boolean canTry = false;
		if (_trialNum < 2) {
			canTry = true;
		}
		return canTry;
		
	}
	
	//clear the fields to it initialized states. (Note: it will erase all the history)
	public void clearData() {
		_trialNum = 0;
		_questionNum = 1;
		_toBeUsed = new ArrayList<String>();
		_numberTested = null;
		_userCloud = new HashMap<Integer, List<String>>();
	}
	
	//three properties corresponding to numbertested, correctness and maori word of one question
	public List<String> numPorperties() {
		List<String> properties = new ArrayList<String>();
		int i = _userCloud.size();
		if(!(_userCloud.get(i) == null)) {
			properties.add(_userCloud.get(i).get(0));
			properties.add(_userCloud.get(i).get(1));
			properties.add(_userCloud.get(i).get(2));

			//NumValue, Boolean
		}
		return properties;
	}
	
	//check if there is any more question to ask
	public boolean hasNext() {
		boolean hasNext = true;
		if(_toBeUsed.isEmpty()) {
			hasNext = false;
		}
		return hasNext;
	}
	
}
