package main.metamodel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Machine {
	
	List<State> states;
	State initialState;
	HashMap<String, Integer> integers;

	
	public HashMap<String, Integer> getIntegers() {
		return integers;
	}
	

	public int getIntegerValue(String string) {
		return this.integers.get(string);
		
	}
	
	
	public void setIntegerValue(String string, int integer) {
		this.integers.put(string, integer);
		
	}
	
	public Machine(List<State> states, State initialState, HashMap<String, Integer> integers) {
		this.states = states; 
		this.initialState = initialState; 
		this.integers = integers;  
	}

	public List<State> getStates() {
		return this.states;
	}

	public State getInitialState() {
		return this.initialState;
	}

	
	
	public State getState(String string) {
		for ( int i = 0; i < this.states.size(); i++) {
			if (this.states.get(i).getName().equals(string)) {
				return this.states.get(i);
			}
		}
		return null;
	}

	public int numberOfIntegers() {
		
		return getIntegers().size();
	}

	public boolean hasInteger(String string) {
		for (int i = 0; i < this.getIntegers().size(); i++) {
			if ( this.getIntegers().containsKey(string))
			return true; 
		}
		return false;
	}

}

