package AmazonCartTest;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class AddToCartTest2 extends AddToCartTestBase {
	final int TEST_NUM_C = 3;
	final int TEST_NUM_D = 4;
	
	@BeforeTest(groups = "checkC")
	public void beforeCheckE() {
		beforeCheckItem(TEST_NUM_C);
	}

	@AfterTest(groups = "checkC")
	public void afterCheckE() {
		afterCheckItem(TEST_NUM_C);
	}
	
	@BeforeTest(groups = "checkD")
	public void beforeCheckF() {
		beforeCheckItem(TEST_NUM_D);
	}

	@AfterTest(groups = "checkD")
	public void afterCheckF() {
		afterCheckItem(TEST_NUM_D);
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
	@Test(groups = "checkC")
	public void checkItemC() {
		final int CATEGORY_NUM = 3;
		rankingClick(CATEGORY_NUM, TEST_NUM_C);
		itemClick(TEST_NUM_C);
		//カートに入れる手順を飛ばしてfailさせる
//		addToCartButtonClick(TEST_NUM_C);
		cartButtonClick(TEST_NUM_C);
		Duration waitTime = Duration.ofSeconds(3);
		WebDriverWait wait = new WebDriverWait(driver, waitTime);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(CART_ITEM_XPATH)));
		String goodAInBasket = this.driver.findElement(By.xpath(CART_ITEM_XPATH)).getText();
		System.out.println("カート内の商品" + TEST_NUM_C + "：" + goodAInBasket);
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
	@Test(groups = "checkD")
	public void checkItemD() {
		final int CATEGORY_NUM = 4;
		rankingClick(CATEGORY_NUM, TEST_NUM_D);
		itemClick(TEST_NUM_D);
		addToCartButtonClick(TEST_NUM_D);
		cartButtonClick(TEST_NUM_D);
		Duration waitTime = Duration.ofSeconds(3);
		WebDriverWait wait = new WebDriverWait(driver, waitTime);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(CART_ITEM_XPATH)));
		String goodAInBasket = this.driver.findElement(By.xpath(CART_ITEM_XPATH)).getText();
		System.out.println("カート内の商品" + TEST_NUM_D + "：" + goodAInBasket);
		boolean result = goodAInBasket.regionMatches(1, itemName, 1, 8);
		// Check cart item
		Assert.assertTrue(result);
	}
}
