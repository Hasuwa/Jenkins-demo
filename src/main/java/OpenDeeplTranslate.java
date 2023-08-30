

class OpenDeeplTranslate extends OpenTranslateSite {
	
	final String GOOGLE_BASE ="//*[@class=\"lmt__textarea_container lmt__raise_alternatives_placement\"]";
	final String GOOGLE_TRANSLATE_PATH =GOOGLE_BASE+"//span[contains(@class, \"sentence\")]";
	
	//コンストラクタ
	OpenDeeplTranslate() {
		this.sitePath = "https://www.deepl.com/translator";
		this.inputFieldPath = "/html/body/div[3]/main/div[6]/div[1]/div[2]/section[1]/div[3]/div[2]/d-textarea/div/p";
		this.translatedFieldPath = "/html/body/div[3]/main/div[5]/div[1]/div[2]/section[2]/div[3]/div[1]/d-textarea/div/p[1]/span";
		this.translatedFieldPath = "/html/body/div[3]/main/div[7]/div[1]/div[2]/section[2]/div[3]/div[1]/d-textarea/div/p/span";
	}
}