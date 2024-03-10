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
import com.kms.katalon.core.testobject.ConditionType as ConditionType
import org.openqa.selenium.Keys as Keys
import com.kms.katalon.core.configuration.RunConfiguration
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.Path;

Path projectDir = Paths.get(RunConfiguration.getProjectDir())

Path reportDir =projectDir.resolve('Screenshots/Hitung Harga Properti/Positive Case/')

Files.createDirectories(reportDir)

String base_dir = 'Screenshots/Hitung Harga Properti/Positive Case/'

for (def rowNum = 1; rowNum <= findTestData('Positive Data').getRowNumbers();) {
	
	println('Perhitungan Aplikasi Web (Data Positif)')
	
	WebUI.setText(findTestObject('Object Repository/Input Penghasilan Total'), findTestData('Positive Data').getValue(1, rowNum))	
	
	WebUI.setText(findTestObject('Object Repository/Input Pengeluaran'), findTestData('Positive Data').getValue(2, rowNum))		
	
	WebUI.selectOptionByValue(findTestObject('Object Repository/Jangka Waktu'), findTestData('Positive Data').getValue(3, rowNum), true)
	
	WebUI.click(findTestObject('Object Repository/Button_Hitung Harga'))

	WebUI.getText(findTestObject('Object Repository/Hasil Hitung Properti'))
	
	WebUI.takeScreenshot((base_dir + 'Hasil Harga Properti (Data Positif)' + rowNum) + '.png')

	String Hasil_Hitung_1 = WebUI.getText(findTestObject('Object Repository/Hasil Hitung Properti'))

	println('++++ Hasil Hitung Harga Properti Apkikasi =' + Hasil_Hitung_1)

	println('Perhitungan Manual (Data Positif)')

	int PenghasilanTotal1 = findTestData('Positive Data').getValue(1, rowNum).toInteger()

	int Pengeluaran1 = findTestData('Positive Data').getValue(2, rowNum).toInteger()

	int JangkaWaktu1 = findTestData('Positive Data').getValue(3, rowNum).toInteger()

	Hitung_Harga_Properti_Manual =  (PenghasilanTotal1 - Pengeluaran1) * (JangkaWaktu1*12)/3

	String Hasil_Hitung_Manual1 = Hitung_Harga_Properti_Manual.toString()

	println('++++ Hasil Hitung Harga Properti Manual =' + Hasil_Hitung_Manual1)

	if (Hasil_Hitung_1 == Hasil_Hitung_Manual1){
	
		println ('Hasil Sesuai')
	}
	rowNum++
}
