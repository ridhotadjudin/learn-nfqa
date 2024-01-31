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
import java.math.RoundingMode

public class ParseDataType {
	public Integer parseToInteger(Object stringNumber) {

		int resultIntegerNumber = 0;

		try {
			resultIntegerNumber = Integer.parseInt(stringNumber)
		}catch(Exception e) {
			KeywordUtil.logInfo("FAILED parseToInteger ")
			KeywordUtil.logInfo(e.printStackTrace())
		}

		return resultIntegerNumber
	}

	public String parseFromNumberToString(Object number) {
		String resultStringNumber = "";

		try {

			String numberReplaced = number.toString().replace(',', '')

			Double doubleNumber = new Double(numberReplaced)

			BigDecimal bigDecimal = new BigDecimal(doubleNumber).setScale(2, RoundingMode.HALF_UP)

			resultStringNumber = bigDecimal.toString()
		}catch(Exception e) {
			KeywordUtil.logInfo("FAILED parseFromNumberToString ")
			KeywordUtil.logInfo(e.getMessage())
		}

		return resultStringNumber;
	}

	public String getSplitString(String stringValue, String splitBy, int index) {
		String hasil = stringValue.split(splitBy)[index]
		return hasil;
	}

	public String[] splitString(String stringValue, String splitBy) {
		String[] hasil = stringValue.split(splitBy)
		return hasil;
	}

	public HashSet parseSplitStringToHashSet(String stringValue, String splitBy){
		String[] hasil = stringValue.split(splitBy)
		HashSet hashSet = new HashSet(Arrays.asList(hasil));

		return hashSet;
	}

	public String parseFromNumberTableToString(Object number) {
		String resultStringNumber = "";

		try {

			String numberReplaced = number.toString().replace('.', '')
			numberReplaced = numberReplaced.toString().replace(',', '.')

			Double doubleNumber = new Double(numberReplaced)

			BigDecimal bigDecimal = new BigDecimal(doubleNumber).setScale(2, RoundingMode.HALF_UP)

			resultStringNumber = bigDecimal.toString()
		}catch(Exception e) {
			KeywordUtil.logInfo("FAILED parseFromNumberTableToString ")
			KeywordUtil.logInfo(e.printStackTrace())
		}

		return resultStringNumber;
	}
}
