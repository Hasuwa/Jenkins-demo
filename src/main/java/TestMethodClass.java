import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class TestMethodClass {

	OpenGoogleTranslate googleSite;
	OpenDeeplTranslate deelpeSite;
	String inputJapanese;
	private final String INPUT_SENTENCE_E001 = "電気";
	private final String INPUT_SENTENCE_E002 = "掃除";
	private final String INPUT_SENTENCE_E003 = "今日の朝ごはんはパンでした。";
	private final String INPUT_SENTENCE_E004 = "毎朝走っています。";
	
	/**
	 * 
	 * @param args
	 */
	//	@BeforeMethod(groups = { "google", "toEnglish" })
	//	@Test(groups = { "google", "toEnglish" })
	public void testbaseGoogle() {
		googleSite = new OpenGoogleTranslate();
		googleSite.prepareInputPhase();
		googleSite.toEnglilsh();
		googleSite.input(inputJapanese);
		googleSite.translatePhase();
	}

	//	@BeforeMethod(groups = { "deepl", "toEnglish" })
	//	@Test(groups = { "deepl", "toEnglish" })
	public void testbaseDeepl() {
		deelpeSite = new OpenDeeplTranslate();
		deelpeSite.prepareInputPhase();
		deelpeSite.input(inputJapanese);
		deelpeSite.translatePhase();
	}

	@AfterMethod(groups = { "google", "toEnglish" })
	public void closeGoogle() {
		googleSite.close();
		googleSite.translatedSentence = "";
		deelpeSite.translatedSentence = "";
		inputJapanese = "";
	}

	@AfterMethod(groups = { "deepl", "toEnglish" })
	public void closeDeepl() {
		deelpeSite.close();
		googleSite.translatedSentence = "";
		deelpeSite.translatedSentence = "";
		inputJapanese = "";
	}

	/**
	 * 日本語→英語
	 * 単語１（電気）
	 */
	@Test(groups = "toEnglish")
	public void compare001() {
		inputJapanese = INPUT_SENTENCE_E001;
		testbaseGoogle();
		testbaseDeepl();
		Assert.assertEquals(googleSite.translatedSentence, deelpeSite.translatedSentence);
	}

	/**
	 * 日本語→英語
	 * 単語２（掃除）
	 */
	@Parameters({ "INPUT_SENTENCE_E002" })
	@Test(groups = "toEnglish")
	public void compare002() {
		inputJapanese = INPUT_SENTENCE_E002;
		testbaseGoogle();
		testbaseDeepl();
		Assert.assertEquals(googleSite.translatedSentence, deelpeSite.translatedSentence);
	}

	/**
	 * 日本語→英語
	 * 文章１（今日の朝ごはんはパンでした。）
	 */
	@Parameters({ "INPUT_SENTENCE_E003" })
	@Test(groups = "toEnglish")
	public void compare003() {
		inputJapanese = INPUT_SENTENCE_E003;
		testbaseGoogle();
		testbaseDeepl();
		Assert.assertEquals(googleSite.translatedSentence, deelpeSite.translatedSentence);
	}

	/**
	 * 日本語→英語
	 * 文章２（毎朝走っています。）
	 */
	@Parameters({ "INPUT_SENTENCE_E004" })
	@Test(groups = "toEnglish")
	public void compare004() {
		inputJapanese = INPUT_SENTENCE_E004;
		testbaseGoogle();
		testbaseDeepl();
		Assert.assertEquals(googleSite.translatedSentence, deelpeSite.translatedSentence);
	}
}
