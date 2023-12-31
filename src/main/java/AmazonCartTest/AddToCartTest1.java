package AmazonCartTest;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class AddToCartTest1 extends AddToCartTestBase {
	final int TEST_NUM_A = 1;
	final int TEST_NUM_B = 2;
	
	@BeforeTest(groups = "checkA")
	public void beforeCheckE() {
		beforeCheckItem(TEST_NUM_A);
	}

	@AfterTest(groups = "checkA")
	public void afterCheckE() {
		afterCheckItem(TEST_NUM_A);
	}
	
	@BeforeTest(groups = "checkB")
	public void beforeCheckF() {
		beforeCheckItem(TEST_NUM_B);
	}

	@AfterTest(groups = "checkB")
	public void afterCheckF() {
		afterCheckItem(TEST_NUM_B);
	}

	/**
	 * 商品を選択し商品ページに遷移する
	 */
	public void itemClickFail(int testNum) {
		Duration waitTime = Duration.ofSeconds(3);
		WebDriverWait wait = new WebDriverWait(driver, waitTime);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(ITEM_XPATH)));
		item = this.driver.findElement(By.xpath(ITEM_XPATH));
		//itemNameを取得しないことでassertでfailさせる
//		itemName = item.getText();
		item.click();
		System.out.println("カートに追加した商品 " + testNum + "：" + itemName);
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
	@Test(groups = "checkA")
	public void checkItemA() {
		final int CATEGORY_NUM = 1;
		rankingClick(CATEGORY_NUM, TEST_NUM_A);
		itemClickFail(TEST_NUM_A);
		addToCartButtonClick(TEST_NUM_A);
		cartButtonClick(TEST_NUM_A);
		Duration waitTime = Duration.ofSeconds(3);
		WebDriverWait wait = new WebDriverWait(driver, waitTime);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(CART_ITEM_XPATH)));
		String goodAInBasket = this.driver.findElement(By.xpath(CART_ITEM_XPATH)).getText();
		System.out.println("カート内の商品" + TEST_NUM_A + "：" + goodAInBasket);
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
	@Test(groups = "checkB")
	public void checkItemB() {
		final int CATEGORY_NUM = 2;
		rankingClick(CATEGORY_NUM, TEST_NUM_B);
		itemClickFail(TEST_NUM_B);
		addToCartButtonClick(TEST_NUM_B);
		cartButtonClick(TEST_NUM_B);
		Duration waitTime = Duration.ofSeconds(3);
		WebDriverWait wait = new WebDriverWait(driver, waitTime);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(CART_ITEM_XPATH)));
		String goodAInBasket = this.driver.findElement(By.xpath(CART_ITEM_XPATH)).getText();
		System.out.println("カート内の商品" + TEST_NUM_B + "：" + goodAInBasket);
		boolean result = goodAInBasket.regionMatches(1, itemName, 1, 8);
		// Check cart item
		Assert.assertTrue(result);
	}
}
