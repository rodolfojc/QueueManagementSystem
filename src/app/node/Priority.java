package app.node;

public enum Priority {
	
	// PRIORITIES
    HIGH	(3), 
    MEDIUM	(2), 
    LOW		(1)
    ;
    
	// GET VALUE OF ENUMS
	public final int levelValue;
	
	// CONSTRUCTOR
	Priority(int levelValue) {
		this.levelValue = levelValue;
	}
	
	// GETTER
	public int getLevelValue() {
		return levelValue;
	}	
	
}
