package by.svirski.tests_for_lesson3;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.fail;

import org.testng.annotations.*;

import by.svirski.lesson3.entities.Basket;
import by.svirski.lesson3.entities.Collors;
import by.svirski.lesson3.service.ActionsOnBasket;
import by.svirski.lesson3.utils.ProjectException;

public class TestsForTask3CorrectInput {

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
	
	@BeforeGroups(groups = {"correctInput"})
	@DataProvider(name = "ballsParams")
	public Object[][] createParamsForBalls() {
		return new Object[][] { { Collors.GREEN, 10 }, { Collors.BLACK, 20 }, { Collors.BLUE, 10 }, { Collors.RED, 30 },
				{ Collors.BLUE, 10 }};
	}
	
	@Test(dataProvider = "ballsParams", groups = {"correctInput"})
	public void testAddingBalls(Collors collor, double weight) {
		try {
			ActionsOnBasket.addBallToBasket(basket, collor, weight);
		} catch (ProjectException e) {
			System.out.println(e.getMessage());
			fail();
		}
	}
	
	@Test(groups = {"correctInput"})
	public void testCalculatingOfWeight() {
		double actual = ActionsOnBasket.getWeightOfBasket(basket);
		double expected = 80.;
		assertEquals(actual, expected, 0.001);
	}
	
	@Test(groups = {"correctInput"})
	public void testCalculatingCollorBalls() {
		double actual = ActionsOnBasket.getQuantityOfBalls(basket, "blue");
		double expected = 2;
		assertEquals(actual, expected);
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
