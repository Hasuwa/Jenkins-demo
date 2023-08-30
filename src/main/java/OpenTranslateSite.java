

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

class OpenTranslateSite {
	String sitePath;
	String inputFieldPath;
	String translatedFieldPath;
	String translatedSentence;
	WebDriver driver;
	WebElement inputField;
	WebElement translatedField;

	//------------メソッド------------

	/**
	 * ブラウザdriverのパスを指定
	 */
	public void setPropatyPath() {
		final String PATH = "chromeDriver/chromedriver.exe";
		System.setProperty("webdriver.chrome.driver", PATH);
	}

	/**
	 * ①サイトを開く
	 * @param sPath URL
	 */
	public void siteOpen(String sPath) {
		driver = new ChromeDriver();
		driver.get(sPath);
	}

	/**
	 * ②ウィンドウを最大化
	 */
	public void maximizeWindow() {
		driver.manage().window().maximize();
	}

	/**
	 * ③入力欄を見つける
	 * @param ife
	 */
	public void findInputField(String ife) {
		inputField = driver.findElement(By.xpath(ife));
	}

	/**
	 * ①～③
	 */
	public void prepareInputPhase() {
		siteOpen(sitePath);
		maximizeWindow();
		findInputField(inputFieldPath);
	}

	/**
	 * ④入力欄にargsを入力
	 * @param ip
	 */
	public void input(String ip) {
		inputField.sendKeys(ip);
		System.out.println("入力：" + ip);
	}

	/**
	 * ⑤翻訳結果が出るまで待つ
	 * @param xp
	 */
	public void waitTranslate(String xp) {
		Duration waitTime = Duration.ofSeconds(10);
		WebDriverWait wait = new WebDriverWait(driver, waitTime);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xp)));
	}

	/**
	 * ⑥画面に表示された翻訳結果を取得して表示
	 * @param xp
	 */
	public void getTranslated(String xp) {
		translatedField = driver.findElement(By.xpath(xp));
		translatedSentence = translatedField.getText();
		System.out.println("翻訳：" + translatedSentence);
	}

	/**
	 * ⑤～⑥
	 */
	public void translatePhase() {
		waitTranslate(translatedFieldPath);
		getTranslated(translatedFieldPath);
		//サイト名とURLを取得して表示
		System.out.println("サイト名（URL）：" + driver.getTitle() + "（" + driver.getCurrentUrl() + "）");
	}

	/**
	 * ブラウザを閉じる
	 */
	public void close() {
		driver.quit();
		System.out.println("closed");
	}
	
	
}