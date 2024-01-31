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
import com.kms.katalon.core.util.KeywordUtil as KeywordUtil
import org.openqa.selenium.WebElement

public class VerifyElement {
	boolean result

	def verifyElementClickable(TestObject testObject) {

		if(WebUI.verifyElementClickable(testObject, FailureHandling.STOP_ON_FAILURE)) {
			result = true
		}
		else {
			result = false
		}

		return result
	}

	def verifyElementNotClickable(TestObject testObject) {

		if(WebUI.verifyElementNotClickable(testObject, FailureHandling.STOP_ON_FAILURE)) {
			result = true
		}
		else {
			result = false
		}

		return result
	}

	def verifyElementPresent(TestObject testObject) {

		if(WebUI.verifyElementPresent(testObject, GlobalVariable.timeoutElementInSecond, FailureHandling.STOP_ON_FAILURE)) {
			result = true
		}
		else {
			result = false
		}

		return result
	}

	def verifyElementNotPresent(TestObject testObject) {

		if(WebUI.verifyElementNotPresent(testObject, GlobalVariable.timeoutElementInSecond, FailureHandling.STOP_ON_FAILURE)) {
			result = true
		}
		else {
			result = false
		}

		return result
	}

	def verifyElementVisible(TestObject testObject) {

		if(WebUI.verifyElementVisible(testObject, FailureHandling.STOP_ON_FAILURE)) {
			result = true
		}
		else {
			result = false
		}

		return result
	}

	def verifyElementNotVisible(TestObject testObject) {

		if(WebUI.verifyElementNotVisible(testObject, FailureHandling.STOP_ON_FAILURE)) {
			result = true
		}
		else {
			result = false
		}

		return result
	}

	def verifyElementHaveText(TestObject testObject, String expectedText) {

		String getActualElementText = WebUI.getText(testObject, FailureHandling.STOP_ON_FAILURE)
		KeywordUtil.logInfo("Actual Text Value: "+getActualElementText)
		if(WebUI.verifyMatch(getActualElementText, expectedText, false, FailureHandling.STOP_ON_FAILURE)) {
			result = true
		}
		else {
			result = false
		}

		return result
	}

	def verifyTextPresent(String actualtext, boolean isUsingRegex) {

		if(WebUI.verifyTextPresent(actualtext, isUsingRegex, FailureHandling.STOP_ON_FAILURE)) {
			result = true
		}
		else {
			result = false
		}

		return result
	}

	def verifyTextNotPresent(String actualtext, boolean isUsingRegex) {

		if(WebUI.verifyTextNotPresent(actualtext, isUsingRegex, FailureHandling.STOP_ON_FAILURE)) {
			result = true
		}
		else {
			result = false
		}

		return result
	}
	
}
