package by.epam.svirski.lesson3.service;

import java.util.ArrayList;

import by.epam.svirski.lesson3.entities.Ball;
import by.epam.svirski.lesson3.entities.Basket;
import by.epam.svirski.lesson3.utils.BasketException;

public class ActionsOnBasket {

	public static final String EXCEPTION_MESSAGE_WEIGHT = "basket weight limit reached";
	public static final String EXCEPTION_MESSAGE_QUANTITY = "the limit on the number of balls in the basket is reached";

	public static Basket createNewBasket(int maxCapacity, double maxWeight) throws BasketException {
		Basket basket = Basket.createBasket(maxCapacity, maxWeight);
		return basket;
	}

	public static void clearBasket() throws BasketException{
		Basket.clearBasket();
	}

	public static void addBallToBasket(Basket basket, String collor, double weight) throws BasketException {
		ArrayList<Ball> quantityOfBalls = basket.getCurrentQuantityOfBalls();
		if (quantityOfBalls.size() < basket.getMaxCapacity()) {
			if (basket.getWeightOfBasket() + weight <= basket.getMaxWeight()) {
				Ball newBall = new Ball(collor, weight);
				basket.overrideCurrentQuantityOfBalls(newBall);
				basket.overrideWeightOfBasket(newBall);
			} else {
				throw new BasketException(EXCEPTION_MESSAGE_WEIGHT);
			}

		} else {
			throw new BasketException(EXCEPTION_MESSAGE_QUANTITY);
		}
	}

	public static double getResultWeight(Basket basket) {
		return basket.getWeightOfBasket();
	}

	public static int getQuantityOfBlueBalls(Basket basket) {
		int quantityOfBlueBalls = 0;
		for (Ball ball : basket.getCurrentQuantityOfBalls()) {
			if (ball.getCollor().equalsIgnoreCase("blue")) {
				quantityOfBlueBalls++;
			}
		}

		return quantityOfBlueBalls;
	}

}
