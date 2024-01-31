package com.azure.cloudapp.southeastasia.ndsrndserver

import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject

import java.text.DateFormatSymbols
import java.text.DecimalFormat
import java.text.DecimalFormatSymbols
import java.text.NumberFormat
import java.text.SimpleDateFormat
import java.util.concurrent.ConcurrentHashMap.KeySetView

import org.openqa.selenium.By
import org.openqa.selenium.JavascriptExecutor
import org.openqa.selenium.Keys
import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement
import org.openqa.selenium.interactions.Actions

import com.kms.katalon.core.annotation.Keyword
import com.kms.katalon.core.checkpoint.Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.testcase.TestCase
import com.kms.katalon.core.testdata.TestData
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows



import internal.GlobalVariable

//additional
import com.kms.katalon.core.configuration.RunConfiguration
import com.kms.katalon.core.util.KeywordUtil
import com.kms.katalon.core.webui.common.WebUiCommonHelper
import com.kms.katalon.core.webui.driver.DriverFactory
import com.kms.katalon.core.webui.keyword.builtin.NavigateToUrlKeyword
import com.kms.katalon.core.webui.driver.WebUIDriverType
import com.azure.cloudapp.southeastasia.ndsrndserver.VerifyElement
import com.azure.cloudapp.southeastasia.ndsrndserver.HandleTestData

public class HandleElement {
	WebDriver driver = DriverFactory.getWebDriver();
	VerifyElement verifyElement = new VerifyElement()
	ParseDataType parseDataType = new ParseDataType()
	String currentDate = "";

	def clickElement(TestObject testObject) {
		try {
			if(WebUI.waitForElementClickable(testObject, GlobalVariable.timeoutElementInSecond, FailureHandling.STOP_ON_FAILURE)) {
				KeywordUtil.logInfo("Click Element = " + testObject.getObjectId())
				WebUI.click(testObject, FailureHandling.STOP_ON_FAILURE)
			}
			else {
				KeywordUtil.logInfo("Failed to Click Element in Object Repository : " + testObject.getObjectId())
			}
		} catch (Exception e) {
			KeywordUtil.markFailedAndStop(e.printStackTrace())
		}
	}

	def clickElementAndType(TestObject testObject, String sendValue, boolean doEnter) {
		try {
			clickElement(testObject)

			if(!getAttributeFromElement(testObject,"value").equals("")) {
				sendKeysTextToElement(testObject, Keys.chord(Keys.CONTROL, "a"))
				sendKeysTextToElement(testObject, Keys.chord(Keys.BACK_SPACE))
			}

			sendKeysTextToElement(testObject, sendValue)

			if(doEnter) {
				sendKeysTextToElement(testObject, Keys.chord(Keys.ENTER))
			}
		} catch (Exception e) {
			KeywordUtil.markFailedAndStop(e.printStackTrace())
		}
	}

	def Enter(TestObject testObject) {
		sendKeysTextToElement(testObject, Keys.chord(Keys.ENTER))
	}

	def clickElementAndType(TestObject testObject, String sendValue) {
		clickElementAndType(testObject, sendValue, false)
	}

	def navigateToUrl(String urlBrowser) {
		try {
			WebUI.navigateToUrl(urlBrowser)
			WebUI.waitForPageLoad(GlobalVariable.timeoutLoadingInSecond, FailureHandling.STOP_ON_FAILURE)
		} catch (Exception e) {
			KeywordUtil.markFailedAndStop(e.printStackTrace())
		}
	}

	def sendKeysTextToElement(TestObject testObject, Object valueText) {
		try {
			WebUI.sendKeys(testObject, valueText, FailureHandling.STOP_ON_FAILURE)
		}catch(Exception e) {
			KeywordUtil.logInfo(e.printStackTrace())
		}
	}

	def getTextFromElement(TestObject testObject) {
		String getText = ""

		try {
			getText =  WebUI.getText(testObject, FailureHandling.STOP_ON_FAILURE)
		} catch (Exception e) {
			KeywordUtil.markFailedAndStop(e.printStackTrace())
		}

		return getText
	}

	def getAttributeFromElement(TestObject testObject, String attributeName) {
		Object getAttribute = ""

		try {
			getAttribute = WebUI.getAttribute(testObject, attributeName, FailureHandling.STOP_ON_FAILURE)
		} catch (Exception e) {
			KeywordUtil.markFailedAndStop(e.printStackTrace())
		}

		return getAttribute
	}

	def login(String username, String password) {

		WebUI.waitForElementVisible(findTestObject('Login/inputUsername'), 3)
		clickElementAndType(findTestObject('Login/inputUsername'), username)
		clickElementAndType(findTestObject('Login/inputPassword'), password)
		clickElement(findTestObject('Login/btnLogin'))
		clickElement(findTestObject('Object Repository/Login/btnOpenTreeEngineerTest'))
		clickElement(findTestObject('Object Repository/Login/btnOpenTreeTransaction'))
		clickElement(findTestObject('Object Repository/Login/btnView'))

		WebUI.waitForPageLoad(GlobalVariable.timeoutLoadingInSecond, FailureHandling.STOP_ON_FAILURE)
		WebUI.waitForElementNotPresent(findTestObject('Login/inputUsername'), GlobalVariable.timeoutLoadingInSecond, FailureHandling.STOP_ON_FAILURE)
		WebUI.verifyElementNotPresent(findTestObject('Login/inputUsername'),GlobalVariable.timeoutElementInSecond, FailureHandling.STOP_ON_FAILURE)
	}

	def logout() {
		WebUI.waitForElementClickable(findTestObject('Object Repository/Global/Button/btnLogout'), GlobalVariable.timeoutElementInSecond, FailureHandling.STOP_ON_FAILURE)
		clickElement(findTestObject('Object Repository/Global/Button/btnLogout'))
		WebUI.waitForElementVisible(findTestObject('Object Repository/Global/Button/confirmLogout'), GlobalVariable.timeoutElementInSecond, FailureHandling.STOP_ON_FAILURE)
		clickElement(findTestObject('Object Repository/Global/Button/confirmLogout'))
	}

	def logoutAndCloseBrowser() {
		try {
			WebUI.waitForElementClickable(findTestObject('Object Repository/Global/Button/btnLogout'), GlobalVariable.timeoutElementInSecond, FailureHandling.STOP_ON_FAILURE)
			clickElement(findTestObject('Object Repository/Global/Button/btnLogout'))
			WebUI.waitForElementVisible(findTestObject('Object Repository/Global/Button/confirmLogout'), GlobalVariable.timeoutElementInSecond, FailureHandling.STOP_ON_FAILURE)
			clickElement(findTestObject('Object Repository/Global/Button/confirmLogout'))
			def driver = DriverFactory.getWebDriver()
			driver.quit()
			WebUI.closeBrowser(FailureHandling.STOP_ON_FAILURE)
		}catch(Exception e) {
			KeywordUtil.markFailedAndStop(e.getMessage())
		}
	}

	boolean checkElementClickable(TestObject testObject) {
		return WebUI.verifyElementClickable(testObject, FailureHandling.OPTIONAL)
	}

	def scrollElementScrollBarAndClick(TestObject untilTestObjectVisible, TestObject scrollBarElement, int offSet) {

		WebDriver driver = DriverFactory.getWebDriver()
		Actions builder = new Actions(driver)

		int timeoutScroll = GlobalVariable.timeoutLoadingInSecond

		//verify if drop down list is scroll able on showing panel DDL
		boolean selectElementIsVisible = WebUI.waitForElementVisible(untilTestObjectVisible, 1)

		//only do scroll if item drop down list still not visible and panel is scroll able, also time out scroll still not 0
		while(selectElementIsVisible == false && timeoutScroll > 0) {

			//do scroll
			WebElement sourceElement = WebUiCommonHelper.findWebElement(scrollBarElement, GlobalVariable.timeoutLoadingInSecond)
			builder.clickAndHold(sourceElement).build().perform()
			builder.moveByOffset(0, offSet).build().perform()
			builder.release().build().perform()

			//check selected item visible or not
			selectElementIsVisible = WebUI.waitForElementVisible(untilTestObjectVisible, 1)
			timeoutScroll--
		}

		if(timeoutScroll <= 0) {
			KeywordUtil.logInfo("Time out scroll for scroll bar!")
			return
		}

		if(selectElementIsVisible) {
			KeywordUtil.logInfo("Element found!")
			clickElement(untilTestObjectVisible)
		} else {
			KeywordUtil.logInfo("Element is not found!")
		}
	}

	def clickElementAndTypeSkipStrNull(TestObject testObject, String sendValue) {
		if(!sendValue.equals("null")) {
			clickElementAndType(testObject, sendValue)
		}
	}

	String returnStringYesNoByBoolean(boolean value) {
		String yesOrNo = "No"
		if(value) {
			yesOrNo = "Yes"
		}
		return yesOrNo
	}

	def clickBtnGoUpPage() {
		if(WebUI.waitForElementVisible(findTestObject('Object Repository/Global/btnGoToTop'), 1, FailureHandling.OPTIONAL)) {
			clickElement(findTestObject('Object Repository/Global/btnGoToTop'))
		}
	}


	def copyListHashMap(List<HashMap> listHashMap) {
		List<HashMap> listCopyHashMap = new ArrayList<HashMap>()

		for(int i=0; i<listHashMap.size(); i++) {
			Map<Object ,Object> map = new HashMap<>()
			for(Map.Entry<Object, Object> entry: listHashMap.get(i).entrySet()) {
				map.put(entry.getKey(), entry.getValue())
			}
			listCopyHashMap.add(map)
		}

		return listCopyHashMap
	}

	//handle files
	def checkFileExist(TestObject testObject, String filePath, String fileName) {
		// Define the directory path where your file is located
		String projectBaseDir = RunConfiguration.getProjectDir() + "/Data Files/"
		String fileDir = projectBaseDir.replace("/","\\");

		// Define the filename you want to retrieve
		//		String fileName = "yourfile.txt"

		// Create a File object for the directory
		File directory = new File(fileDir+filePath)

		// Check if the directory exists
		if (directory.exists()) {
			// Create a File object for the file
			File fileToRetrieve = new File(directory, fileName)

			// Check if the file exists
			if (fileToRetrieve.exists()) {
				// Perform any necessary actions with the file here
				// For example, you can print the file's absolute path
				println("File path: " + fileToRetrieve.getAbsolutePath())
			} else {
				println("File does not exist: " + fileName)
			}
		} else {
			println("Directory does not exist: " + (fileDir+filePath))
		}
	}

}
