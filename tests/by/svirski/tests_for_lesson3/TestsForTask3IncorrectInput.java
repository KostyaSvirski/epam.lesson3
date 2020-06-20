package by.svirski.tests_for_lesson3;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.fail;

import org.testng.annotations.AfterGroups;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeGroups;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import by.svirski.lesson3.entities.Basket;
import by.svirski.lesson3.entities.Collors;
import by.svirski.lesson3.service.ActionsOnBasket;
import by.svirski.lesson3.utils.ProjectException;

public class TestsForTask3IncorrectInput {

	public Basket basket;
	public static final int CAPACITY_OF_BASKET = 5;
	public static final double MAX_WEIGHT_OF_BASKET = 100;

	@BeforeSuite
	public void createBasket() {
		try {
			basket = ActionsOnBasket.createNewBasket(CAPACITY_OF_BASKET, MAX_WEIGHT_OF_BASKET);
		} catch (ProjectException e) {
			System.out.println(e.getMessage());
			fail();
		}
	}

	@BeforeGroups(groups = { "input" })
	@DataProvider(name = "ballsParams")
	public Object[][] createParamsForBalls() {
		return new Object[][] { { Collors.GREEN, 10 }, { Collors.BLACK, 20 }, { Collors.BLUE, 10 }, { Collors.RED, 30 },
				{ Collors.BLUE, 10 } };
	}

	@Test(dataProvider = "ballsParams", groups = { "input" })
	public void testAddingBalls(Collors collor, double weight) {
		try {
			ActionsOnBasket.addBallToBasket(basket, collor, weight);
		} catch (ProjectException e) {
			System.out.println(e.getMessage());
			fail();
		}
	}

	@Test(groups = { "input" })
	public void testCalculatingOfWeight() {
		double actual = ActionsOnBasket.getWeightOfBasket(basket);
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
			ActionsOnBasket.addBallToBasket(basket, Collors.GREEN, 10);
			fail();
		} catch (ProjectException e) {
			assertEquals(e.getMessage(), "the limit on the number of balls in the basket is reached");
		}
	}

	@AfterGroups(groups = { "reachingLimit", "input" })
	@BeforeGroups(groups = { "overweightBasket" })
	@DataProvider(name = "secBallParams")
	public Object[][] createSecParamsForBalls() {
		return new Object[][] { { Collors.GREEN, 10 }, { Collors.BLUE, 20 }, { Collors.BLUE, 10 }, { Collors.BROWN, 30 } };
	}

	@Test
	public void recreateBasket() {
		try {
			ActionsOnBasket.clearBasket();
			basket = ActionsOnBasket.createNewBasket(CAPACITY_OF_BASKET, MAX_WEIGHT_OF_BASKET);
		} catch (ProjectException e) {
			e.printStackTrace();
			fail();
		}
	}

	@Test(dataProvider = "secBallParams", groups = { "overweightBasket" })
	public void AddingBallsSec(Collors collor, double weight) {
		try {
			ActionsOnBasket.addBallToBasket(basket, collor, weight);
		} catch (ProjectException e) {
			System.out.println(e.getMessage());
			fail();
		}
	}

	@Test(groups = { "overweightBasket" }, expectedExceptionsMessageRegExp = "basket weight limit reached")
	public void testOverweightOfVasket() {
		try {
			ActionsOnBasket.addBallToBasket(basket, Collors.GREEN, 100);
			fail();
		} catch (ProjectException e) {
			assertEquals(e.getMessage(), "basket weight limit reached");
		}
	}

	@AfterSuite
	public void deleteBasket() {
		try {
			ActionsOnBasket.clearBasket();
		} catch (ProjectException e) {
			System.out.println(e.getMessage());
			fail();
		}
	}
}
