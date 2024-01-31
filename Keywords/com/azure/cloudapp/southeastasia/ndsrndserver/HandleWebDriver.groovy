package com.azure.cloudapp.southeastasia.ndsrndserver

import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject

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
import com.kms.katalon.core.webui.driver.DriverFactory
import com.kms.katalon.core.webui.driver.WebUIDriverType
import com.kms.katalon.core.util.KeywordUtil

public class HandleWebDriver {

	def startAndOpenWebBrowser(String urlBrowser) {

		//KeywordUtil.logInfo("URL ENV = " + urlBrowser)

		try {
			WebUIDriverType executedBrowser = DriverFactory.getExecutedBrowser()

			switch (executedBrowser) {
				case WebUIDriverType.CHROME_DRIVER:

					WebUI.openBrowser('', FailureHandling.STOP_ON_FAILURE)

					WebUI.waitForPageLoad(GlobalVariable.timeoutElementInSecond, FailureHandling.STOP_ON_FAILURE)

					WebUI.maximizeWindow(FailureHandling.STOP_ON_FAILURE)

					WebUI.navigateToUrl(urlBrowser)

					WebUI.waitForPageLoad(GlobalVariable.timeoutElementInSecond, FailureHandling.STOP_ON_FAILURE)

					break;

				default:
					WebUI.openBrowser('', FailureHandling.STOP_ON_FAILURE)

					WebUI.waitForPageLoad(GlobalVariable.timeoutElementInSecond, FailureHandling.STOP_ON_FAILURE)

					WebUI.maximizeWindow(FailureHandling.STOP_ON_FAILURE)

					WebUI.navigateToUrl(urlBrowser)

					WebUI.waitForPageLoad(GlobalVariable.timeoutElementInSecond, FailureHandling.STOP_ON_FAILURE)
			}
		}catch(Exception e) {

			KeywordUtil.markFailedAndStop(e.printStackTrace())
		}
	}

	def closeBrowser() {
		WebUI.closeBrowser()
	}

}
