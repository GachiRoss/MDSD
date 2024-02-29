package main;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import main.metamodel.Machine;
import main.metamodel.State;
import main.metamodel.Transition;
import main.metamodel.Transition.ConditionType;
import main.metamodel.Transition.OperationType;

public class StateMachine {
	
	List<State> states = new ArrayList<State>();
	State initialState; 
	String operationName;
	int operationValue;
	String conditionName;
	int conditionValue;
	String name;
	String event;
	String target;
	Transition.ConditionType conditionType;
	Transition.OperationType operationType;
	
	List<Transition> transitions = new ArrayList<Transition>();
	List<String> tempTarget = new ArrayList<String>();
	HashMap<String, Integer> integers = new HashMap<String, Integer>();
	
	
	public HashMap<String, Integer> getIntegers() {
		return integers;
	}
	
	public void addTransition() {
		if (event == null) {
			return;
		}
		
		this.transitions.add(new Transition(this.event, this.target, this.operationName, this.operationValue, this.conditionName, this.conditionValue, this.operationType, this.conditionType));
		
		this.event = null;
		this.target = null;

		
	}
			

	public Machine build() {
		this.addTransition();
		if (!this.states.isEmpty()) {
			this.states.get(this.states.size() - 1).setTransitions(this.transitions);
		}
		
		for (int i = 0; i < this.states.size(); i++) {
			for (int j = 0; j < this.states.get(i).getTransitions().size(); j++) {
					for ( int h = 0; h < this.states.size() ; h++) {
						if (this.states.get(h).getName().equals(this.states.get(i).getTransitions().get(j).getTempTarget())) {
							this.states.get(i).getTransitions().get(j).setTarget(this.states.get(h));
						}
					}
			}
		}
		Machine machine = new Machine(this.states, this.initialState, this.getIntegers() );
		return machine;
	}

		

	public StateMachine state(String string) {
		this.addTransition();
		if (!this.states.isEmpty()) {
			this.states.get(this.states.size() - 1).setTransitions(this.transitions);
		};
		State state = new State(string);
		this.states.add(state);
		this.transitions = new ArrayList<Transition>();
		return this;
	}

	public StateMachine initial() {
		this.initialState = this.states.get(this.states.size() - 1 );
		return this;
	}

	public StateMachine when(String string) {		
		this.operationType = null;	
		this.addTransition();
		this.event = string;
		
		
		return this;
	}

	public StateMachine to(String string) {
		this.target = string;
		
		return this;
	}

	public StateMachine integer(String string) {
			this.getIntegers().put(string, this.getIntegers().size()-1);					
		
		return this;
	}

	public StateMachine set(String string, int i) {
		this.operationName = string;
		this.operationValue = i;
		
		this.operationType = OperationType.SET;
		
		return this;
	}

	public StateMachine increment(String string) {
		this.operationName = string;
		this.operationType = OperationType.INCREMENT;
		return this;
	}

	public StateMachine decrement(String string) {
		this.operationName = string;
		this.operationType = OperationType.DECREMENT;
		return this;
	}

	public StateMachine ifEquals(String string, int i) {
		this.conditionName = string;
		this.conditionValue = i;
		if (this.getIntegers().containsKey(string)) {
			this.getIntegers().replace(string, i);
			this.conditionType = ConditionType.IFEQUALS;
		}
		return this;
	}

	public StateMachine ifGreaterThan(String string, int i) {
		this.conditionName = string;
		this.conditionValue = i;
		if (this.getIntegers().containsKey(string)) {
			this.getIntegers().replace(string, i);
			this.conditionType = ConditionType.IFGREATERTHAN;
		}
		return this;
	}

	public StateMachine ifLessThan(String string, int i) {
		this.conditionName = string;
		this.conditionValue = i;
		if (this.getIntegers().containsKey(string)) {
			this.getIntegers().replace(string, i);
			this.conditionType = ConditionType.IFLESSTHAN;
		}
		return this;
	}




}
