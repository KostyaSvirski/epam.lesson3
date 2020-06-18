package by.epam.svirski.lesson3.entities;

import java.util.ArrayList;

import by.epam.svirski.lesson3.utils.BasketException;

public class Basket {

	private static Basket basket;
	private int maxCapacity;
	private double maxWeight;

	private double weightOfBasket;
	private ArrayList<Ball> currentQuantityOfBalls;

	private Basket(int maxCapacity, double maxWeight) {
		this.maxCapacity = maxCapacity;
		this.maxWeight = maxWeight;
		this.weightOfBasket = 0;
		this.currentQuantityOfBalls = new ArrayList<Ball>();
	}

	public static boolean isBasketExist() {
		if (basket != null) {
			return true;
		} else {
			return false;			
		}
	}

	public static Basket createBasket(int maxCapacity, double maxWeight) throws BasketException { // singleton basket
		if (basket != null) {
			throw new BasketException("basket is alredy exist");
		}
		basket = new Basket(maxCapacity, maxWeight);
		return basket;
	}

	public static void clearBasket() throws BasketException {
		if (basket == null) {
			throw new BasketException("no basket exists");
		}
		basket = null;
	}

	public double getMaxWeight() {
		return maxWeight;
	}

	public int getMaxCapacity() {
		return maxCapacity;
	}

	public double getWeightOfBasket() {
		return weightOfBasket;
	}

	public void overrideWeightOfBasket(Ball newBall) {
		this.weightOfBasket += newBall.getWeightOfBall();
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
		temp = Double.doubleToLongBits(weightOfBasket);
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
		if (Double.doubleToLongBits(weightOfBasket) != Double.doubleToLongBits(other.weightOfBasket)) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Basket [maxCapacity=");
		builder.append(maxCapacity);
		builder.append(", weightOfBasket=");
		builder.append(weightOfBasket);
		builder.append(", currentQuantityOfBalls=");
		builder.append(currentQuantityOfBalls);
		builder.append("]");
		return builder.toString();
	}

}
