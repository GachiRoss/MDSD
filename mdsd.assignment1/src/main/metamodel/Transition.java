package main.metamodel;

public class Transition{
	
	String operationName;
	int operationValue;
	String conditionName;
	int conditionValue;
	String event; 
	State target;
	String tempTarget;
	OperationType operationType; 
	ConditionType conditionType;

	
	public enum OperationType {
		SET,
		INCREMENT,
		DECREMENT;
		
	}
	
	public enum ConditionType {
		IFEQUALS,
		IFGREATERTHAN,
		IFLESSTHAN;
		
	}
	
	public int getOperationValue() {
		return this.operationValue;
	}
	
	public String getTempTarget() {
		return tempTarget;
	}
	
	public void setTarget(State target) {
		this.target = target;
	}
	
	public Transition(String event, String tempTarget, String operationName, int operationValue, String conditionName, int conditionValue, OperationType operationType, ConditionType conditionType) {
		this.event = event;
		this.tempTarget = tempTarget;
		this.operationName = operationName;
		this.operationValue = operationValue;
		this.conditionName = conditionName;
		this.conditionValue = conditionValue;
		this.operationType = operationType;
		this.conditionType = conditionType;

	}
	
	public Object getEvent() {
		
		return this.event;
	}

	public State getTarget() {
		
		return this.target;
	}

	public boolean hasSetOperation() {
		return this.operationType == OperationType.SET;
	}

	public boolean hasIncrementOperation() {
		return this.operationType == OperationType.INCREMENT;
	}

	public boolean hasDecrementOperation() {
		return this.operationType == OperationType.DECREMENT;
	}

	public Object getOperationVariableName() {	
		return this.operationName; 
	}

	public boolean isConditional() {
		
		return this.conditionType != null;
	}

	public Object getConditionVariableName() {
		return this.conditionName;
	}

	public Integer getConditionComparedValue() {
		return this.conditionValue;
	}

	public boolean isConditionEqual() {
		return this.conditionType == ConditionType.IFEQUALS;
	}

	public boolean isConditionGreaterThan() {

		return this.conditionType == ConditionType.IFGREATERTHAN;
	}

	public boolean isConditionLessThan() {

		return this.conditionType == ConditionType.IFLESSTHAN;
	}

	public boolean hasOperation() {
		return this.operationName != null;
	}


}
