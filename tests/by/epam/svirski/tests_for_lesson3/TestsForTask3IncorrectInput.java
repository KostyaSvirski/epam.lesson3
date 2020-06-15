package by.epam.svirski.tests_for_lesson3;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.fail;

import org.testng.annotations.*;

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
	
	@BeforeGroups(groups = {"correctInput"})
	@DataProvider(name = "ballsParams")
	public Object[][] createParamsForBalls() {
		return new Object[][] {{"Green", 10}, {"Blue", 20}, {"Blue", 10}, {"Red", 30}, {"Blue", 10}};
	}
	
	@Test(dataProvider = "ballsParams", groups = {"correctInput"})
	public void testAddingBalls(String collor, double weight) {
		try {
			ActionsOnBasket.addBallToBasket(basket, collor, weight);
		} catch (BasketException e) {
			System.out.println(e.getMessage());
			fail();
		}
	}
	
	@Test(groups = {"correctInput"})
	public void testCalculatingOfWeight() {
		double actual = ActionsOnBasket.getResultWeight(basket);
		double expected = 80.;
		assertEquals(actual, expected, 0.001);
	}
	
	@Test(groups = {"correctInput"})
	public void testCalculatingOfBlueBalls() {
		double actual = ActionsOnBasket.getQuantityOfBlueBalls(basket);
		double expected = 3;
		assertEquals(actual, expected);
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
