package by.svirski.lesson3.entities;

import java.util.ArrayList;

import by.svirski.lesson3.utils.ProjectException;

public class Basket {

	private static Basket basket;
	private final int maxCapacity;
	private final double maxWeight;

	private ArrayList<Ball> currentQuantityOfBalls;

	private Basket(int maxCapacity, double maxWeight) {
		this.maxCapacity = maxCapacity;
		this.maxWeight = maxWeight;
		this.currentQuantityOfBalls = new ArrayList<Ball>();
	}

	public static boolean isBasketExist() {
		if (basket != null) {
			return true;
		} else {
			return false;
		}
	}

	public static Basket createBasket(int maxCapacity, double maxWeight) throws ProjectException { // singleton basket
		if (basket != null) {
			throw new ProjectException("basket is alredy exist");
		}
		basket = new Basket(maxCapacity, maxWeight);
		return basket;
	}

	public static void clearBasket() throws ProjectException {
		if (basket == null) {
			throw new ProjectException("no basket exists");
		}
		basket = null;
	}

	public double getMaxWeight() {
		return maxWeight;
	}

	public int getMaxCapacity() {
		return maxCapacity;
	}

	public ArrayList<Ball> getCurrentQuantityOfBalls() {
		return currentQuantityOfBalls;
	}

	public void overrideCurrentQuantityOfBalls(Ball newBall) {
		currentQuantityOfBalls.add(newBall);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((currentQuantityOfBalls == null) ? 0 : currentQuantityOfBalls.hashCode());
		result = prime * result + maxCapacity;
		long temp;
		temp = Double.doubleToLongBits(maxWeight);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;			
		}
		if (!(obj instanceof Basket)) {
			return false;			
		}
		Basket other = (Basket) obj;
		if (currentQuantityOfBalls == null) {
			if (other.currentQuantityOfBalls != null) {
				return false;				
			}
		} else if (!currentQuantityOfBalls.equals(other.currentQuantityOfBalls)) {
			return false;			
		}
		if (maxCapacity != other.maxCapacity) {
			return false;			
		}
		if (Double.doubleToLongBits(maxWeight) != Double.doubleToLongBits(other.maxWeight)) {
			return false;			
		}
		return true;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Basket [maxCapacity=");
		builder.append(maxCapacity);
		builder.append(", maxWeight=");
		builder.append(maxWeight);
		builder.append(", currentQuantityOfBalls=");
		builder.append(currentQuantityOfBalls);
		builder.append("]");
		return builder.toString();
	}

	

}
