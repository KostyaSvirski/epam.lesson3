package by.svirski.lesson3.service;

import by.svirski.lesson3.entities.Ball;
import by.svirski.lesson3.entities.Basket;
import by.svirski.lesson3.entities.Collors;
import by.svirski.lesson3.utils.ProjectException;

public class ActionsOnBasket {

	public static final String EXCEPTION_WEIGHT_LIMIT = "basket weight limit reached";
	public static final String EXCEPTION_QUANTITY_LIMIT = "the limit on the number of balls in the basket is reached";
	public static final String EXCEPTION_BASKET_NOT_EXIST = "basket doesn't exist";

	public static Basket createNewBasket(int maxCapacity, double maxWeight) throws ProjectException {
		Basket basket = Basket.createBasket(maxCapacity, maxWeight);
		return basket;
	}

	public static void clearBasket() throws ProjectException {
		Basket.clearBasket();
	}

	public static void addBallToBasket(Basket basket, Collors collor, double weight) throws ProjectException {
		if (Basket.isBasketExist()) {
			if (basket.getCurrentQuantityOfBalls().size() + 1 <= basket.getMaxCapacity()) {
				if (getWeightOfBasket(basket) + weight <= basket.getMaxWeight()) {
					Ball newBall = new Ball(collor, weight);
					basket.overrideCurrentQuantityOfBalls(newBall);
				} else {
					throw new ProjectException(EXCEPTION_WEIGHT_LIMIT);
				}

			} else {
				throw new ProjectException(EXCEPTION_QUANTITY_LIMIT);
			}

		} else {
			throw new ProjectException(EXCEPTION_BASKET_NOT_EXIST);
		}
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
	
	public static double getWeightOfBasket(Basket basket) {
		double weight = 0;
		for (Ball ball : basket.getCurrentQuantityOfBalls()) {
			weight += ball.getWeightOfBall();
		}
		return weight;
	}


}
