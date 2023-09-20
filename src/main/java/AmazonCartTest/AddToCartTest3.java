package AmazonCartTest;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class AddToCartTest3 extends AddToCartTestBase {
	final int TEST_NUM_E = 5;
	final int TEST_NUM_F = 6;
	
	@BeforeTest(groups = "checkE")
	public void beforeCheckE() {
		beforeCheckItem(TEST_NUM_E);
	}

	@AfterTest(groups = "checkE")
	public void afterCheckE() {
		afterCheckItem(TEST_NUM_E);
	}
	
	@BeforeTest(groups = "checkF")
	public void beforeCheckF() {
		beforeCheckItem(TEST_NUM_F);
	}

	@AfterTest(groups = "checkF")
	public void afterCheckF() {
		afterCheckItem(TEST_NUM_F);
	}

	/**
	 * [Title]
	 * 商品が正しくカートに入るか確認
	 * [step]
	 * 1.Amazon開く
	 * 2.ランキング画面に遷移
	 * 3.カテゴリに遷移
	 * 4.１位の商品を選択
	 * 5.カートに入れるボタンを押下
	 * [result]
	 * 5．カートに入れた商品が正しい
	 */
	@Test(groups = "checkE")
	public void checkItemE() {
		final int CATEGORY_NUM = 5;
		rankingClick(CATEGORY_NUM, TEST_NUM_E);
		itemClick(TEST_NUM_E);
		addToCartButtonClick(TEST_NUM_E);
		cartButtonClick(TEST_NUM_E);
		Duration waitTime = Duration.ofSeconds(3);
		WebDriverWait wait = new WebDriverWait(driver, waitTime);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(CART_ITEM_XPATH)));
		String goodAInBasket = this.driver.findElement(By.xpath(CART_ITEM_XPATH)).getText();
		System.out.println("カート内の商品" + TEST_NUM_E + "：" + goodAInBasket);
		boolean result = goodAInBasket.regionMatches(1, itemName, 1, 8);
		// Check cart item
		Assert.assertTrue(result);
	}
	
	
	/**
	 * [Title]
	 * 商品が正しくカートに入るか確認
	 * [step]
	 * 1.Amazon開く
	 * 2.ランキング画面に遷移
	 * 3.カテゴリに遷移
	 * 4.１位の商品を選択
	 * 5.カートに入れるボタンを押下
	 * [result]
	 * 5．カートに入れた商品が正しい
	 */
	@Test(groups = "checkF")
	public void checkItemF() {
		final int CATEGORY_NUM = 6;
		rankingClick(CATEGORY_NUM, TEST_NUM_F);
		itemClick(TEST_NUM_F);
		addToCartButtonClick(TEST_NUM_F);
		cartButtonClick(TEST_NUM_F);
		Duration waitTime = Duration.ofSeconds(3);
		WebDriverWait wait = new WebDriverWait(driver, waitTime);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(CART_ITEM_XPATH)));
		String goodAInBasket = this.driver.findElement(By.xpath(CART_ITEM_XPATH)).getText();
		System.out.println("カート内の商品" + TEST_NUM_F + "：" + goodAInBasket);
		boolean result = goodAInBasket.regionMatches(1, itemName, 1, 8);
		// Check cart item
		Assert.assertTrue(result);
	}
}
