package main.metamodel;


import java.util.List;

public class State {
	
	String name;
	List<Transition> transitions;

	public void setTransitions(List<Transition> transitions) {
		this.transitions = transitions;
	}
	
	public State(String string) {
		this.name = string;

	}

	public Object getName() {
		return this.name;
	}

	public List<Transition> getTransitions() {
		return this.transitions;
	}

	public Transition getTransitionByEvent(String string) {
		for (int i = 0; i < this.transitions.size(); i++) {
			if ( this.transitions.get(i).getEvent().equals(string)) {
				return this.transitions.get(i);
			}
		}
		return null;
	}
}
