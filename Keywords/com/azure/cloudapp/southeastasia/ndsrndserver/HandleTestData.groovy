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
import com.kms.katalon.core.configuration.RunConfiguration
import com.kms.katalon.core.util.KeywordUtil
import com.kms.katalon.core.testdata.reader.ExcelFactory

public class HandleTestData {
	static String pathDataFile = RunConfiguration.getProjectDir() + "/Data Files/"
	static String workingDirectoryData = pathDataFile.replace("/","\\")

	List<String> listRegressionTestId = new ArrayList<String>()

	public List<HashMap> readTestData(String pathFileTestData, String sheetName, boolean isUsingFirstRowAsHeader) throws IOException {
		//		String pathFileTestData = workingDirectoryData + pathFile
		List<HashMap> listHashMap = new ArrayList<HashMap>()

		KeywordUtil.logInfo("pathFileTestData = " + pathFileTestData)
		KeywordUtil.logInfo("sheetName = " + sheetName)

		try {
			TestData data = ExcelFactory.getExcelDataWithDefaultSheet(pathFileTestData, sheetName, isUsingFirstRowAsHeader)
			List<List<Object>> listAllData = data.getAllData()
			KeywordUtil.logInfo("size data = " + listAllData.size())
			String[] getHeaderColumnName = data.getColumnNames()

			boolean headerTestTypeExist = checkStringHeaderNameExist(getHeaderColumnName, "testType")

			for(int i = 0; i < listAllData.size(); i++) {
				List<Object> getListData = listAllData.get(i)
				HashMap<Object, Object> hashMapSetKeyAndValueFromTestData = new HashMap<Object, Object>()

				for(int j = 0; j < getHeaderColumnName.length; j++) {
					String keyName = getHeaderColumnName[j]
					String valueData = getListData.get(j)

					if(!keyName.equals(null) || !keyName.equals("")) {
						if(valueData.equals(null) && !keyName.equals(null)) {
							hashMapSetKeyAndValueFromTestData.put(keyName, "")
						} else {
							hashMapSetKeyAndValueFromTestData.put(keyName, valueData)
						}
					}
				}

				//some sheet doesn't have column testType, if there is then check testType value
				if(headerTestTypeExist) {
					boolean isNotRegression = !hashMapSetKeyAndValueFromTestData.get("testType").toString().toLowerCase().equals("regression")
					if(isNotRegression) {
						listHashMap.add(hashMapSetKeyAndValueFromTestData)
					} else {
						listRegressionTestId.add(hashMapSetKeyAndValueFromTestData.get('testData').toString())
					}
				}
				//else if testType not exist then just add hash map
				else {
					listHashMap.add(hashMapSetKeyAndValueFromTestData)
				}

			}
			KeywordUtil.logInfo("listHashMap di Method readTestData class com.nd.automation.HandleTestData : "+listHashMap)
		}catch(Exception e) {
			KeywordUtil.logInfo(e.getMessage())
		}
		KeywordUtil.logInfo("Final size data: "+listHashMap.size())
		return listHashMap
	}

	public List<HashMap> filterDataHashMap(List<HashMap> listHashMap, String keyNameData, String valueDocument) {
		List<HashMap> hasil = new ArrayList<HashMap>()

		for (int i = 0; i < listHashMap.size(); i++) {
			HashMap hashTC = listHashMap.get(i)

			if(hashTC.get(keyNameData).equals(valueDocument)) {
				hasil.add(hashTC)
			} else {
				continue
			}
		}
		return hasil
	}

	def processDataPerTD(String pathFile, String sheetName, boolean isUsingFirstRowAsHeader, int columnNumberInTD){
		String pathFileTestData = workingDirectoryData + pathFile
		HashMap<Object, HashMap<Object, Object>> listTestData = new HashMap<Object, HashMap<Object, Object>>()

		try {
			TestData data = ExcelFactory.getExcelDataWithDefaultSheet(pathFileTestData, sheetName, isUsingFirstRowAsHeader)
			List<Object> listAllData = data.getAllData()
			String[] getHeaderColumnName = data.getColumnNames()

			for(int i = 0; i < listAllData.size(); i++){
				HashMap<Object, Object> dataTD = new HashMap<Object, Object>()
				String[] getRowData = listAllData.get(i)
				String currentDocTD = getRowData[columnNumberInTD]

				for(int j = 2; j < getHeaderColumnName.length; j++){
					String keyName = getHeaderColumnName[j]
					String value = getRowData[j]

					dataTD.put(keyName, value)
				}
				listTestData.put(currentDocTD, dataTD)
			}
		} catch(Exception e) {
			KeywordUtil.logInfo(e.getMessage())
		}
		return listTestData
	}



	public HashMap filterOneRowData(List<HashMap> listHashMap, String keyNameFilter, String valueDocumentFilter) {
		HashMap hasil = new HashMap()

		for (int k = 0; k < listHashMap.size(); k++) {
			HashMap hashTC = listHashMap.get(k)

			if(hashTC.get(keyNameFilter).equals(valueDocumentFilter)) {
				hasil = hashTC
				break;
			}
			else {
				continue;
			}
		}

		return hasil;
	}


	//regression
	public List<HashMap> readTestDataRegression(String pathFileTestData, String sheetName, boolean isUsingFirstRowAsHeader) throws IOException {

		List<HashMap> listHashMap = new ArrayList<HashMap>()

		KeywordUtil.logInfo("pathFileTestData = " + pathFileTestData)
		KeywordUtil.logInfo("sheetName = " + sheetName)

		try {
			TestData data = ExcelFactory.getExcelDataWithDefaultSheet(pathFileTestData, sheetName, isUsingFirstRowAsHeader)
			List<List<Object>> listAllData = data.getAllData()
			KeywordUtil.logInfo("size data = " + listAllData.size())
			String[] getHeaderColumnName = data.getColumnNames()

			boolean headerTestTypeExist = checkStringHeaderNameExist(getHeaderColumnName, "testType")

			for(int i = 0; i < listAllData.size(); i++) {
				List<Object> getListData = listAllData.get(i)
				HashMap<Object, Object> hashMapSetKeyAndValueFromTestData = new HashMap<Object, Object>()

				for(int j = 0; j < getHeaderColumnName.length; j++) {
					String keyName = getHeaderColumnName[j]
					String valueData = getListData.get(j)

					if(!keyName.equals(null) || !keyName.equals("")) {
						if(valueData.equals(null) && !keyName.equals(null)) {
							hashMapSetKeyAndValueFromTestData.put(keyName, "")
						} else {
							hashMapSetKeyAndValueFromTestData.put(keyName, valueData)
						}
					}
				}

				//some sheet doesn't have column testType, if there is then check testType value
				if(headerTestTypeExist) {
					boolean isRegression = hashMapSetKeyAndValueFromTestData.get("testType").toString().toLowerCase().equals("regression")
					if(isRegression) {
						listHashMap.add(hashMapSetKeyAndValueFromTestData)
						listRegressionTestId.add(hashMapSetKeyAndValueFromTestData.get('testData').toString())
					}
				}
				//else if testType not exist then just add hash map
				else {
					listHashMap.add(hashMapSetKeyAndValueFromTestData)
				}


			}
			KeywordUtil.logInfo("listHashMap di Method readTestData class com.nd.automation.HandleTestData : "+listHashMap)
		}catch(Exception e) {
			KeywordUtil.logInfo(e.getMessage())
		}
		KeywordUtil.logInfo("Final size data: "+listHashMap.size())
		return listHashMap
	}


	public boolean checkStringHeaderNameExist(String[] listHeaderName, String keyName) {
		boolean isKeyPresent = false
		for(int i=0; i<listHeaderName.length; i++) {
			if(listHeaderName[i].equals(keyName)) {
				isKeyPresent = true
				listRegressionTestId.clear()
				break
			}
		}
		return isKeyPresent
	}


	public boolean checkHashMapKeyNameExist(HashMap hashMap, Object keyName) {
		// Get the key to be removed
		String keyToBeChecked = keyName.toString();

		// Print the initial HashMap
		System.out.println("HashMap: "+ hashMap);

		// Get the iterator over the HashMap
		Iterator<Map.Entry<Object, Object> > iterator = hashMap.entrySet().iterator();

		// flag to store result
		boolean isKeyPresent = false;

		// Iterate over the HashMap
		while (iterator.hasNext()) {
			// Get the entry at this iteration
			Map.Entry<Object, Object> entry = iterator.next();

			// Check if this key is the required key
			if (keyToBeChecked.equals(entry.getKey())) {
				isKeyPresent = true;
			}
		}

		// Print the result
		System.out.println("Does key "+ keyToBeChecked+ " exists: "+ isKeyPresent);

		return isKeyPresent
	}

	boolean checkIfTestIdIsRegression(String testData) {
		boolean isRegression = false
		for(int i=0; i<listRegressionTestId.size(); i++) {
			if(listRegressionTestId.get(i).equals(testData)) {
				isRegression = true
				break
			}
		}
		return isRegression
	}

}
