

import org.openqa.selenium.By;

class OpenGoogleTranslate extends OpenTranslateSite {

	//定数定義
	String TO_ENGLISH_ELEMENT_ID = "i13";
	String GOOGLE_SITE_PATH = "https://translate.google.co.jp/";
	String GOOGLE_INPUT_FIELD_PATH = "/html/body/c-wiz/div/div[2]/c-wiz/div[2]/c-wiz/div/div[2]/div[3]/c-wiz[1]/span/span/div/textarea";
	//コンストラクタ
	OpenGoogleTranslate() {
		this.sitePath = GOOGLE_SITE_PATH;
		this.inputFieldPath = GOOGLE_INPUT_FIELD_PATH;
		this.translatedFieldPath = "/html/body/c-wiz/div/div[2]/c-wiz/div[2]/c-wiz/div/div[2]/div[3]/c-wiz[2]/div/div[9]/div/div[1]/span[1]/span/span";
	}

	//翻訳先言語で英語をクリック(googleだけ)
	void toEnglilsh() {
		this.driver.findElement(By.id(TO_ENGLISH_ELEMENT_ID)).click();
	}
}
