package AmazonCartTest;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class AddToCartTest5 extends AddToCartTestBase {
	final int TEST_NUM = 8;
	
	@BeforeTest(groups = "checkH")
	public void beforeCheck() {
		beforeCheckItem(TEST_NUM);
	}

	@AfterMethod(groups = "checkH")
	public void afterCheck() {
		afterCheckItem(TEST_NUM);
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
	@Test(groups = "checkH")
	public void checkItemH() {
		final int CATEGORY_NUM = 8;
		rankingClick(CATEGORY_NUM, TEST_NUM);
		itemClick(TEST_NUM);
		addToCartButtonClick(TEST_NUM);
		cartButtonClick(TEST_NUM);
		Duration waitTime = Duration.ofSeconds(3);
		WebDriverWait wait = new WebDriverWait(driver, waitTime);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(CART_ITEM_XPATH)));
		String goodAInBasket = this.driver.findElement(By.xpath(CART_ITEM_XPATH)).getText();
		System.out.println("カート内の商品" + TEST_NUM + "：" + goodAInBasket);
		boolean result = goodAInBasket.regionMatches(1, itemName, 1, 8);
		// Check cart item
		Assert.assertTrue(result);
	}
}
