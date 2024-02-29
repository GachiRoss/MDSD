package main;

import main.metamodel.Machine;
import main.metamodel.State;
import main.metamodel.Transition;

public class MachineInterpreter {
	
	Machine machine;
	State currentState;
	Transition currentTransition; 
	
	
	
    public void run(Machine m) {
        this.machine = m;
        this.currentState = this.machine.getInitialState();
    }

    public State getCurrentState() {
       return this.currentState;
       
    }

    public void processEvent(String string) {
    	
    	for (int i = 0; i < this.currentState.getTransitions().size(); i++) {

			this.currentTransition = this.currentState.getTransitions().get(i);
			
    		if (currentTransition.getEvent().equals(string)) {
    			
      			if (currentTransition.isConditional()) {
        			int value = this.machine.getIntegerValue((String)currentTransition.getConditionVariableName());
        			
        			int comparedValue = this.currentState.getTransitions().get(i).getConditionComparedValue();
        			if (currentTransition.isConditionEqual() && value != comparedValue) {
        				continue; 
        			}else if (currentTransition.isConditionGreaterThan() && value <= comparedValue) {
        				continue; 
        			}else if (currentTransition.isConditionLessThan() && value >= comparedValue) {
                		continue; 
            		}	
    			
      			}
      			
    			if (currentTransition.hasOperation()) {
    			
    				if (currentTransition.hasSetOperation()) {
    				
    					this.machine.setIntegerValue(
    					
    							(String)currentTransition.getOperationVariableName(),
    						
    							currentTransition.getOperationValue()
    						
    							);
    				}
    				if (currentTransition.hasIncrementOperation()) {
        				
    					this.machine.setIntegerValue(
    					
    							(String)currentTransition.getOperationVariableName(),
    						
    							currentTransition.getOperationValue() + 1
    						
    							);
    				}
    				if (currentTransition.hasDecrementOperation())  {
        				
    					this.machine.setIntegerValue(
    					
    							(String)currentTransition.getOperationVariableName(),
    						
    							currentTransition.getOperationValue() - 1 
    						
    							);
    				}
    	
    				
    				
    			}
    			
    			
    			this.currentState = currentTransition.getTarget();
    		}
    	}
    	
    }				
		
    

    

    public int getInteger(String string) {
    	return this.machine.getIntegers().get(string);
    }
}
