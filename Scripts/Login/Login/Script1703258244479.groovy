import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject
import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.testng.keyword.TestNGBuiltinKeywords as TestNGKW
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import internal.GlobalVariable as GlobalVariable
import org.openqa.selenium.Keys as Keys

//additional
import com.azure.cloudapp.southeastasia.ndsrndserver.HandleWebDriver as HandleWebDriver
import com.azure.cloudapp.southeastasia.ndsrndserver.HandleTestData
import com.azure.cloudapp.southeastasia.ndsrndserver.HandleElement
import com.kms.katalon.core.util.KeywordUtil
import com.azure.cloudapp.southeastasia.ndsrndserver.TransactionElement as TransactionElement

HandleWebDriver handleWebDriver = new HandleWebDriver()

handleWebDriver.startAndOpenWebBrowser(GlobalVariable.baseUrl)

HandleElement handleElement = new HandleElement()

handleElement.login('EngineerTest1', 'M@sukN@wadata1')
