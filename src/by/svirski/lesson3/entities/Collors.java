package by.svirski.lesson3.entities;

public enum Collors {
	
	GREEN("Green"),
	BLACK("Black"),
	BLUE("Blue"),
	BROWN("Brown"),
	RED("Red"),
	ORANGE("Orange"),
	YELLOW("Yellow");
	
	private String collor;

	private Collors(String collor) {
		this.collor = collor;
	}

	public String getCollor() {
		return collor;
	}
	
}
