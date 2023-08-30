package rakutenIchibanTest;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class ItemHistoryTest {
	WebDriver driver;
	WebElement itemA;
	String itemAName;

	final String RAKUTEN_ICHIBA_PATH = "https://www.rakuten.co.jp/";
	final String ITEM_A_XPATH = "/html/body/div[1]/div[15]/div[1]/div/section[6]/div[1]/div/div[4]/div[2]/div[4]/div[2]/div[2]/div/div/div[1]/ul/li[2]/ul/li[2]/a";
	final String INBASKET_BUTTON_XPATH = "/html/body/div[10]/div/div[1]/div/div/div[2]/div/div[1]/button";
	final String BASKET_XPATH = "/html/body/div[3]/div/div[2]/form/div[2]/div[2]/ul/li[1]/a";
	final String HISTORY_LIST_CLASS_NAME = "/html/body/div[5]/div[5]/div[2]/div[2]/div[1]/form/div/ul/li/div[2]/div[2]/a";
	//------------メソッド------------

	/**
	 * ChromeDriverのパスを指定
	 */
	public void setPropatyPath() {
		final String CHOROME_DRIVER_PATH = "chromeDriver/chromedriver.exe";
		System.setProperty("webdriver.chrome.driver", CHOROME_DRIVER_PATH);
	}

	/**
	 * ①サイト（楽天市場）を開く
	 */
	public void siteOpen() {
		driver = new ChromeDriver();
		driver.get(RAKUTEN_ICHIBA_PATH);
	}

	/**
	 * ②ウィンドウを最大化
	 */
	public void maximizeWindow() {
		driver.manage().window().maximize();
		System.out.println("site opened");
	}

	/**
	 * 商品を選択し商品ページに遷移する
	 */
	public void itemAClick() {
		itemA = this.driver.findElement(By.xpath(ITEM_A_XPATH));
		itemAName = itemA.getText();
		itemA.click();
		System.out.println("クリックした商品名：" + itemAName);
	}

	/**
	 * 買い物かごに入れるボタンをクリック
	 */
	public void inBasketButtonClick() {
		
		this.driver.findElement(By.xpath(INBASKET_BUTTON_XPATH)).click();
		System.out.println("買い物かごに入れる");
	}
	
	/*
	 * 買い物かごを開く
	 */
	public void basketButtonClick() {
		this.driver.findElement(By.xpath(BASKET_XPATH)).click();
		System.out.println("買い物かごを開く");
	}

	@BeforeTest(groups = "basketCheck")
	public void beforeCheckItemA() {
		setPropatyPath();
		siteOpen();
		maximizeWindow();
		itemAClick();
		inBasketButtonClick();
	}

	@AfterTest(groups = "basketCheck")
	public void afterCheckItemA() {
		driver.quit();
		System.out.println("site closed");
	}

	@Test(groups = "basketCheck")
	public void checkItemA() {
		String goodInBasket = this.driver.findElement(By.xpath(HISTORY_LIST_CLASS_NAME)).getText();
		Assert.assertEquals(goodInBasket, itemAName);
		boolean result = goodInBasket.regionMatches(1,itemAName,1,8);
		//String str1 ="【あす楽】NANGA ナンガ";
		//String str2 ="【あす楽】NANGA ナンガ aa...";
		//boolean result = str1.regionMatches(1,str2,1,8);
		Assert.assertTrue(result);
	}
}
