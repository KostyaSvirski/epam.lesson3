package by.epam.svirski.tests_for_lesson3;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.fail;

import org.testng.annotations.AfterGroups;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeGroups;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import by.epam.svirski.lesson3.entities.Basket;
import by.epam.svirski.lesson3.service.ActionsOnBasket;
import by.epam.svirski.lesson3.utils.BasketException;

public class TestsForTask3IncorrectInput {

	public Basket basket;
	public static final int CAPACITY_OF_BASKET = 5;
	public static final double MAX_WEIGHT_OF_BASKET = 100;

	@BeforeSuite
	public void createBasket() {
		try {
			basket = ActionsOnBasket.createNewBasket(CAPACITY_OF_BASKET, MAX_WEIGHT_OF_BASKET);
		} catch (BasketException e) {
			System.out.println(e.getMessage());
			fail();
		}
	}

	@BeforeGroups(groups = { "input" })
	@DataProvider(name = "ballsParams")
	public Object[][] createParamsForBalls() {
		return new Object[][] { { "Green", 10 }, { "Blue", 20 }, { "Blue", 10 }, { "Red", 30 }, { "Blue", 10 } };
	}

	@Test(dataProvider = "ballsParams", groups = { "input" })
	public void testAddingBalls(String collor, double weight) {
		try {
			ActionsOnBasket.addBallToBasket(basket, collor, weight);
		} catch (BasketException e) {
			System.out.println(e.getMessage());
			fail();
		}
	}

	@Test(groups = { "input" })
	public void testCalculatingOfWeight() {
		double actual = ActionsOnBasket.getResultWeight(basket);
		double expected = 80.;
		assertEquals(actual, expected, 0.001);
	}

	@Test(groups = { "input" })
	public void testCalculatingOfCollorBalls() {
		double actual = ActionsOnBasket.getQuantityOfBalls(basket, "blue");
		double expected = 3;
		assertEquals(actual, expected);
	}

	@AfterGroups(groups = "input")
	@Test(groups = {
			"reachingLimit" }, expectedExceptionsMessageRegExp = "the limit on the number of balls in the basket is reached")
	public void testAddingExtraBall() {
		try {
			ActionsOnBasket.addBallToBasket(basket, "green", 10);
			fail();
		} catch (BasketException e) {
			assertEquals(e.getMessage(), "the limit on the number of balls in the basket is reached");
		}
	}
	@AfterGroups(groups = {"reachingLimit", "input"})
	@BeforeGroups(groups = {"overweightBasket"})
	@DataProvider(name = "secBallParams")
	public Object[][] createSecParamsForBalls(){
		return new Object[][] { { "Green", 10 }, { "Blue", 20 }, { "Blue", 10 }, { "Red", 30 }};
	}
	
	@Test
	public void recreateBasket() {
		try {
			ActionsOnBasket.clearBasket();
			basket = ActionsOnBasket.createNewBasket(CAPACITY_OF_BASKET, MAX_WEIGHT_OF_BASKET);
		} catch (BasketException e) {
			e.printStackTrace();
			fail();
		}
	}
	
	@Test(dataProvider = "secBallParams", groups = {"overweightBasket"} )
	public void AddingBallsSec(String collor, double weight) {
		try {
			ActionsOnBasket.addBallToBasket(basket, collor, weight);
		} catch (BasketException e) {
			System.out.println(e.getMessage());
			fail();
		}
	}
	
	@Test(groups = { "overweightBasket" }, expectedExceptionsMessageRegExp = "basket weight limit reached")
	public void testOverweightOfVasket() {
		try {
			ActionsOnBasket.addBallToBasket(basket, "green", 10);
			fail();
		} catch (BasketException e) {
			assertEquals(e.getMessage(), "basket weight limit reached");
		}
	}

	@AfterSuite
	public void deleteBasket() {
		try {
			ActionsOnBasket.clearBasket();
		} catch (BasketException e) {
			System.out.println(e.getMessage());
			fail();
		}
	}
}
