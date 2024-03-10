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

Path reportDir =projectDir.resolve('Screenshots/Hitung Harga Properti/Negative Case/')

Files.createDirectories(reportDir)

String base_dir = 'Screenshots/Hitung Harga Properti/Negative Case/'

println('1. Perhitungan Aplikasi Web (Penghasilan Total > Pengeluaran)')

WebUI.refresh()
	
WebUI.setText(findTestObject('Object Repository/Input Penghasilan Total'), findTestData('Negative Data').getValue(1, 1))	
	
WebUI.setText(findTestObject('Object Repository/Input Pengeluaran'), findTestData('Negative Data').getValue(2, 1))		
	
WebUI.selectOptionByValue(findTestObject('Object Repository/Jangka Waktu'), findTestData('Negative Data').getValue(3, 1), true)
	
WebUI.takeScreenshot(base_dir + 'Hasil Hitung Negatif Data Ke1.png')

println('Penghasilan Total Kurang Dari Pengeluaran')

println('-----------------------------------------------------------------------------------------------------------------------')

WebUI.refresh()

println('2. Perhitungan Aplikasi Web (Penghasilan Total > Pengeluaran) Dengan Nilai Koma')

WebUI.setText(findTestObject('Object Repository/Input Penghasilan Total'), findTestData('Negative Data').getValue(1, 2))

WebUI.setText(findTestObject('Object Repository/Input Pengeluaran'), findTestData('Negative Data').getValue(2, 2))

WebUI.selectOptionByValue(findTestObject('Object Repository/Jangka Waktu'), findTestData('Negative Data').getValue(3, 2), true)

WebUI.click(findTestObject('Object Repository/Button_Hitung Harga'))

WebUI.takeScreenshot(base_dir + 'Hasil Hitung Negatif Data Ke2.png')

String Hasil_Hitung_2 = WebUI.getText(findTestObject('Object Repository/Hasil Hitung Properti'))
	
println('++++ Hasil Hitung Harga Properti Dengan Nilai Koma=' + Hasil_Hitung_2)

println(' -> Tidak muncul validasi penghasilan total kurang dari pengeluaran')

println(' -> Hasil hitung bernilai minus (-)')
	
