package by.svirski.lesson3.entities;

public class Ball {
	
	private Collors collor;
	private double weightOfBall;
	
	public Ball(Collors collor, double weightOfBall) {
		super();
		this.collor = collor;
		this.weightOfBall = weightOfBall;
	}

	public String getCollor() {
		return collor.getCollor();
	}

	public double getWeightOfBall() {
		return weightOfBall;
	}

	

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((collor == null) ? 0 : collor.hashCode());
		long temp;
		temp = Double.doubleToLongBits(weightOfBall);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;			
		}
		if (!(obj instanceof Ball)) {
			return false;			
		}
		Ball other = (Ball) obj;
		if (collor == null) {
			if (other.collor != null) {
				return false;				
			}
		} else if (!collor.equals(other.collor)) {
			return false;			
		}
		if (Double.doubleToLongBits(weightOfBall) != Double.doubleToLongBits(other.weightOfBall)) {
			return false;			
		}
		return true;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Ball [collor=");
		builder.append(collor);
		builder.append(", weightOfBall=");
		builder.append(weightOfBall);
		builder.append("]");
		return builder.toString();
	}
	
	
	
	
}
