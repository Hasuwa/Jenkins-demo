package AmazonCartTest;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class AddToCartTest2 {
	WebDriver driver;
	WebElement itemB;
	String itemBName;

	final String AMAZON_PATH = "https://www.amazon.co.jp/";
	final String RANKING_XPATH = "/html/body/div[1]/header/div/div[4]/div[2]/div[2]/div/a[3]";
	final String CATEGORY1_XPATH = "/html/body/div[1]/div[2]/div[2]/div/div/div/div[2]/div/div[2]/div/div/div[2]/div[14]/a";
	final String CATEGORY2_XPATH = "/html/body/div[1]/div[2]/div[2]/div/div/div/div[2]/div/div[2]/div/div/div[2]/div[16]/a";
	final String ITEM_A_XPATH = "/html/body/div[1]/div[3]/div/div/div[1]/div/div/div[2]/div[1]/div[1]/div[1]/div/div[2]/div/a[2]/span/div";
	final String ADDTOCART_BUTTON_ID = "add-to-cart-button";
	final String CART_XPATH = "/html/body/div[2]/header/div/div[1]/div[3]/div/a[4]";
	final String CART_ID = "nav-cart";
	final String CART_ITEM_XPATH = "/html/body/div[1]/div[2]/div[3]/div[5]/div/div[2]/div[1]/div/form/div[2]/div[3]/div[4]/div/div[2]/ul/li[1]/span/a/span[1]/span/span[2]";
	//------------メソッド------------

	/**
	 * ChromeDriverのパスを指定
	 */
	public void setPropatyPath() {
		final String CHOROME_DRIVER_PATH = "chromeDriver/chromedriver.exe";
		System.setProperty("webdriver.chrome.driver", CHOROME_DRIVER_PATH);
	}

	/**
	 * ①サイト（Amazonホーム）を開く
	 */
	public void siteOpen() {
		driver = new ChromeDriver();
		driver.get(AMAZON_PATH);
	}

	/**
	 * ②ウィンドウを最大化
	 */
	public void maximizeWindow() {
		driver.manage().window().maximize();
		System.out.println("site opened2");
	}

	/**
	 * ランキングページに遷移し、ジャンル2を指定
	 */
	public void rankingClick2() {
		Duration waitTime = Duration.ofSeconds(30);
		WebDriverWait wait = new WebDriverWait(driver, waitTime);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(RANKING_XPATH)));
		this.driver.findElement(By.xpath(RANKING_XPATH)).click();
		System.out.println("ランキング画面2");
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(CATEGORY2_XPATH)));
		this.driver.findElement(By.xpath(CATEGORY2_XPATH)).click();
		System.out.println("カテゴリー選択2");
	}

	/**
	 * 商品を選択し商品ページに遷移する
	 */
	public void itemBClick() {
		Duration waitTime = Duration.ofSeconds(3);
		WebDriverWait wait = new WebDriverWait(driver, waitTime);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(ITEM_A_XPATH)));
		itemB = this.driver.findElement(By.xpath(ITEM_A_XPATH));
		itemBName = itemB.getText();
		itemB.click();
		System.out.println("カートに追加した商品2：" + itemBName);
	}

	/**
	 * カートに入れるボタンをクリック
	 */
	public void addToCartButtonClick() {
		Duration waitTime = Duration.ofSeconds(3);
		WebDriverWait wait = new WebDriverWait(driver, waitTime);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(ADDTOCART_BUTTON_ID)));
		this.driver.findElement(By.id(ADDTOCART_BUTTON_ID)).click();
		System.out.println("カートに入れる2");
	}

	/*
	 * カートを開く
	 */
	public void cartButtonClick() {
		Duration waitTime = Duration.ofSeconds(3);
		WebDriverWait wait = new WebDriverWait(driver, waitTime);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(CART_ID)));
		this.driver.findElement(By.id(CART_ID)).click();
		System.out.println("カートを開く2");
	}

	@BeforeMethod(alwaysRun = true)
	public void beforeCheckItemA() {
		setPropatyPath();
		siteOpen();
		maximizeWindow();
	}

	@AfterMethod(alwaysRun = true)
	public void afterCheckItemA() {
		driver.quit();
		System.out.println("site closed2");
	}


	/**
	 * [Title]
	 * 商品が正しくカートに入るか確認
	 * [step]
	 * 1.Amazon開く
	 * 2.ランキング画面に遷移
	 * 3.「」ジャンルに遷移
	 * 4.１位の商品を選択
	 * 5.カートに入れるボタンを押下
	 * [result]
	 * 5．カートに入れた商品が正しい
	 */
	@Test(groups = "cartCheck2")
	public void checkItemB() {
		rankingClick2();
		itemBClick();
		addToCartButtonClick();
		cartButtonClick();
		Duration waitTime = Duration.ofSeconds(3);
		WebDriverWait wait = new WebDriverWait(driver, waitTime);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(CART_ITEM_XPATH)));
		String goodBInBasket = this.driver.findElement(By.xpath(CART_ITEM_XPATH)).getText();
		System.out.println("カート内の商品2：" + goodBInBasket);
		boolean result = goodBInBasket.regionMatches(1, itemBName, 1, 6);
		// Check cart item
		Assert.assertTrue(result);
	}
}
