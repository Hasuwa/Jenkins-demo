package AmazonCartTest;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AddToCartTestBase {
	WebDriver driver;
	WebElement item;
	String itemName;

	final String AMAZON_PATH = "https://www.amazon.co.jp/";
	final String RANKING_XPATH = "/html/body/div[1]/header/div/div[4]/div[2]/div[2]/div/a[3]";
	final String ITEM_XPATH = "/html/body/div[1]/div[3]/div/div/div[1]/div/div/div[2]/div[1]/div[1]/div[1]/div/div[2]/div/a[2]/span/div";
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
	public void maximizeWindow(int testNum) {
		driver.manage().window().maximize();
		System.out.println("site opened "+testNum);
	}

	/**
	 * ランキングページに遷移し、ジャンル2を指定
	 * @param categoryNum
	 */
	 public void rankingClick(int categoryNum, int testNum) {
		Duration waitTime = Duration.ofSeconds(30);
		WebDriverWait wait = new WebDriverWait(driver, waitTime);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(RANKING_XPATH)));
		this.driver.findElement(By.xpath(RANKING_XPATH)).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div[1]/div[2]/div[2]/div/div/div/div[2]/div/div[2]/div/div/div[2]/div[" + categoryNum + "]/a")));
		this.driver.findElement(By.xpath("/html/body/div[1]/div[2]/div[2]/div/div/div/div[2]/div/div[2]/div/div/div[2]/div[" + categoryNum + "]/a")).click();
		System.out.println("カテゴリー選択 " + testNum);
	}

	/**
	 * 商品を選択し商品ページに遷移する
	 */
	public void itemClick(int testNum) {
		Duration waitTime = Duration.ofSeconds(3);
		WebDriverWait wait = new WebDriverWait(driver, waitTime);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(ITEM_XPATH)));
		item = this.driver.findElement(By.xpath(ITEM_XPATH));
		itemName = item.getText();
		item.click();
		System.out.println("カートに追加した商品 " + testNum + "：" + itemName);
	}

	/**
	 * カートに入れるボタンをクリック
	 */
	public void addToCartButtonClick(int testNum) {
		Duration waitTime = Duration.ofSeconds(3);
		WebDriverWait wait = new WebDriverWait(driver, waitTime);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(ADDTOCART_BUTTON_ID)));
		this.driver.findElement(By.id(ADDTOCART_BUTTON_ID)).click();
		System.out.println("カートに入れる "+ testNum);
	}

	/*
	 * カートを開く
	 */
	public void cartButtonClick(int testNum) {
		Duration waitTime = Duration.ofSeconds(3);
		WebDriverWait wait = new WebDriverWait(driver, waitTime);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(CART_ID)));
		this.driver.findElement(By.id(CART_ID)).click();
		System.out.println("カートを開く "+ testNum);
	}

	//	@BeforeMethod(alwaysRun = true)
	public void beforeCheckItem(int testNum) {
		setPropatyPath();
		siteOpen();
		maximizeWindow(testNum);
	}

	//	@AfterMethod(alwaysRun = true)
	public void afterCheckItem(int testNum) {
		driver.quit();
		System.out.println("site closed "+ testNum);
	}
}
