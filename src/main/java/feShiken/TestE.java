package feShiken;

import static org.testng.Assert.*;

import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import utility.CommonUtil;

public class TestE extends Test_Base {

	@BeforeMethod(alwaysRun = true)
	public void before() {
		testNum = 5;
		beforeTheCheck();
	}

	@AfterMethod(alwaysRun = true)
	public void after(ITestResult result) {
		if (result.getStatus() != ITestResult.SUCCESS) {
			CommonUtil.takeScreenShot(driver, ".\\result", result.getMethod().getMethodName());
		}
		afterTheCheck();
	}

	@Test(groups = "answerCheck")
	public void testE() {
		selectYear();
		startQuestion();
		//選択するアイウエのXPathを渡す
		answerQuestion(SEND_ANS_A_PATH);
		assertEquals(sentAnswer, correctAnswer);
	}
}
