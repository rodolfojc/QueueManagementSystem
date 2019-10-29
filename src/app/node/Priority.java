package app.node;

public enum Priority {

    HIGH	(3), 
    MEDIUM	(2), 
    LOW		(1)
    ;
    
	public final int levelValue;
	
	Priority(int levelValue) {
		this.levelValue = levelValue;
	}

	public int getLevelValue() {
		return levelValue;
	}	
	
}
