package by.epam.svirski.lesson3.service;

import by.epam.svirski.lesson3.entities.Ball;
import by.epam.svirski.lesson3.entities.Basket;
import by.epam.svirski.lesson3.utils.BasketException;

public class ActionsOnBasket {

	public static final String EXCEPTION_WEIGHT_LIMIT = "basket weight limit reached";
	public static final String EXCEPTION_QUANTITY_LIMIT = "the limit on the number of balls in the basket is reached";
	public static final String EXCEPTION_BASKET_NOT_EXIST = "basket doesn't exist";

	public static Basket createNewBasket(int maxCapacity, double maxWeight) throws BasketException {
		Basket basket = Basket.createBasket(maxCapacity, maxWeight);
		return basket;
	}

	public static void clearBasket() throws BasketException {
		Basket.clearBasket();
	}

	public static void addBallToBasket(Basket basket, String collor, double weight) throws BasketException {
		if (Basket.isBasketExist()) {
			if (basket.getCurrentQuantityOfBalls().size() + 1 <= basket.getMaxCapacity()) {
				if (basket.getWeightOfBasket() + weight <= basket.getMaxWeight()) {
					Ball newBall = new Ball(collor, weight);
					basket.overrideCurrentQuantityOfBalls(newBall);
					basket.overrideWeightOfBasket(newBall);
				} else {
					throw new BasketException(EXCEPTION_WEIGHT_LIMIT);
				}

			} else {
				throw new BasketException(EXCEPTION_QUANTITY_LIMIT);
			}

		} else {
			throw new BasketException(EXCEPTION_BASKET_NOT_EXIST);
		}
	}

	public static double getResultWeight(Basket basket) {
		return basket.getWeightOfBasket();
	}

	public static int getQuantityOfBalls(Basket basket, String collor) {
		int quantityOfBlueBalls = 0;
		for (Ball ball : basket.getCurrentQuantityOfBalls()) {
			if (ball.getCollor().equalsIgnoreCase(collor)) {
				quantityOfBlueBalls++;
			}
		}

		return quantityOfBlueBalls;
	}

}
