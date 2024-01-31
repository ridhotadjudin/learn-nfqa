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


public class TransactionElement {

	HandleElement handleElement = new HandleElement()
	VerifyElement verifyElement = new VerifyElement()
	ParseDataType parseDataType = new ParseDataType()

	def searchByID(HashMap containerHashMap) {
		HashMap hashMapTransaction = containerHashMap.get("Transaction")

		WebUI.waitForElementPresent(findTestObject('Object Repository/Transaction/searchEmployeeID'), GlobalVariable.timeoutLoadingInSecond)

		handleElement.clickElementAndType(findTestObject('Object Repository/Transaction/searchEmployeeID'), hashMapTransaction.get('tEmployeeID'), true)

		WebUI.waitForElementPresent(findTestObject('Object Repository/Transaction/searchEmployeeID'), GlobalVariable.timeoutLoadingInSecond)
	}

	def searchByName(HashMap containerHashMap) {
		HashMap hashMapTransaction = containerHashMap.get("Transaction")

		WebUI.waitForElementPresent(findTestObject('Object Repository/Transaction/searchEmployeeName'), GlobalVariable.timeoutLoadingInSecond)

		handleElement.clickElementAndType(findTestObject('Object Repository/Transaction/searchEmployeeName'), hashMapTransaction.get('tEmployeeName'), true)

		WebUI.waitForElementPresent(findTestObject('Object Repository/Transaction/searchEmployeeName'), GlobalVariable.timeoutLoadingInSecond)
	}

	def clickBack() {
		handleElement.clickElement(findTestObject('Object Repository/Transaction/btnBackToList'))
	}

	def clearSearchID() {
		handleElement.clickElement(findTestObject('Object Repository/Transaction/btnClearSearchEmployeeID'))
	}

	def clearSearchName() {
		handleElement.clickElement(findTestObject('Object Repository/Transaction/btnClearSearchEmployeeName'))
	}

	def addTestData(HashMap containerHashMap) {
		HashMap hashMapTransaction = containerHashMap.get("Transaction")

		// click add
		WebUI.waitForElementVisible(findTestObject('Object Repository/Transaction/btnAddNewRecord'), GlobalVariable.timeoutElementInSecond, FailureHandling.STOP_ON_FAILURE)
		handleElement.clickElement(findTestObject('Object Repository/Transaction/btnAddNewRecord'))

		// input employee id
		handleElement.clickElementAndType(findTestObject('Object Repository/Transaction/Add/inputEmployeeID'), hashMapTransaction.get("tEmployeeID"))

		// input employee name
		handleElement.clickElementAndType(findTestObject('Object Repository/Transaction/Add/inputEmployeeName'), hashMapTransaction.get("tEmployeeName"))

		// input date birth
		handleElement.clickElementAndType(findTestObject('Object Repository/Transaction/Add/inputDateBirth'), hashMapTransaction.get("tDateBirth"))

		// input married status
		handleElement.clickElement(findTestObject('Object Repository/Transaction/btnIsMarriedNO'))

		// input district
		handleElement.clickElementAndType(findTestObject('Object Repository/Transaction/Add/inputDistrict'), hashMapTransaction.get("tDistrict"))
		handleElement.clickElement(findTestObject('Object Repository/Transaction/Add/inputDistrictClick'))

		// input multi district
		handleElement.clickElement(findTestObject('Object Repository/Transaction/Add/inputMultipleDistrict'))
		handleElement.clickElementAndType(findTestObject('Object Repository/Transaction/Add/searchDistrictCode'), hashMapTransaction.get("tMultiDistrict"))
		handleElement.clickElement(findTestObject('Object Repository/Transaction/Add/inputMultipleDistrictCode'))
		handleElement.clickElement(findTestObject('Object Repository/Transaction/Add/inputMultipleDistrictClick'))

		// check if button save is click able and click save
		if(WebUI.verifyElementClickable(findTestObject('Object Repository/Transaction/Add/btnSave'), FailureHandling.OPTIONAL)) {
			handleElement.clickElement(findTestObject('Object Repository/Transaction/Add/btnSave'))
		} else {
			verifyElement.verifyElementNotClickable(findTestObject('Object Repository/Transaction/Add/btnSave'))
		}

		if(hashMapTransaction.get('TYPE_TESTCASE').toString().toLowerCase().startsWith("neg")){
			verifyElement.verifyElementVisible(findTestObject('Object Repository/Transaction/Add/txtValidationResult'))
		} else {
			WebUI.waitForElementVisible(findTestObject('Object Repository/Transaction/Add/txtAfterSave'), GlobalVariable.timeoutLoadingInSecond)
			handleElement.clickElement(findTestObject('Object Repository/Transaction/Add/btnAfterSave'))
		}
	}

	def verifyViewDetail(HashMap containerHashMap) {
		HashMap hashMapTransaction = containerHashMap.get("Transaction")

		boolean temp = false
		searchByID(containerHashMap)

		// click row table view detail
		handleElement.clickElement(findTestObject('Object Repository/Transaction/btnGetDetail', [('tEmployeeID') : hashMapTransaction.get('tEmployeeID'), ('tEmployeeName') : hashMapTransaction.get('tEmployeeName')]))

		// verify id
		temp = verifyElement.verifyElementHaveText(findTestObject('Object Repository/Transaction/Detail/txtEmployeeID'), hashMapTransaction.get('tEmployeeID').toString())
		WebUI.verifyEqual(temp, true)

		// verify name
		temp = verifyElement.verifyElementHaveText(findTestObject('Object Repository/Transaction/Detail/txtEmployeeName'), hashMapTransaction.get('tEmployeeName').toString())
		WebUI.verifyEqual(temp, true)

		clickBack()
		clearSearchID()
	}

	def updateData(HashMap containerHashMap) {
		HashMap hashMapTransaction = containerHashMap.get("Transaction")

		searchByID(containerHashMap)

		// click row table edit
		handleElement.clickElement(findTestObject('Object Repository/Transaction/btnGetDetail', [('tEmployeeID') : hashMapTransaction.get('tEmployeeID'), ('tEmployeeName') : hashMapTransaction.get('tEmployeeName')]))

		// input new name
		handleElement.clickElementAndTypeSkipStrNull(findTestObject('Object Repository/Transaction/Edit/inputEmployeeID'), hashMapTransaction.get("tEmployeeID"))

		// input new name
		handleElement.clickElementAndTypeSkipStrNull(findTestObject('Object Repository/Transaction/Edit/inputEmployeeName'), hashMapTransaction.get("tEmployeeName"))

		// check if button save is click able and click save
		if(WebUI.verifyElementClickable(findTestObject('Object Repository/Transaction/Add/btnSave'), FailureHandling.OPTIONAL)) {
			handleElement.clickElement(findTestObject('Object Repository/Transaction/Add/btnSave'))
		} else {
			verifyElement.verifyElementNotClickable(findTestObject('Object Repository/Transaction/Add/btnSave'))
		}

		if(hashMapTransaction.get('TYPE_TESTCASE').toString().toLowerCase().startsWith("neg")){
			verifyElement.verifyElementVisible(findTestObject('Object Repository/Transaction/Add/txtValidationResult'))
		} else {
			WebUI.waitForElementVisible(findTestObject('Object Repository/Transaction/Add/txtAfterSave'), GlobalVariable.timeoutLoadingInSecond)
			handleElement.clickElement(findTestObject('Object Repository/Transaction/Add/btnAfterSave'))
		}

		boolean temp = false

		searchByID(containerHashMap)

		// verify code
		temp = verifyElement.verifyElementHaveText(findTestObject('Object Repository/Transaction/listEmployeeID'), hashMapTransaction.get('tEmployeeID').toString())
		WebUI.verifyEqual(temp, true)

		// verify name
		temp = verifyElement.verifyElementHaveText(findTestObject('Object Repository/Transaction/listEmployeeName'), hashMapTransaction.get('tEmployeeName').toString())
		WebUI.verifyEqual(temp, true)

		clearSearchID()
	}


}
