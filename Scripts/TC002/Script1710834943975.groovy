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

Path reportDir =projectDir.resolve('Screenshots/Hitung Harga Properti/')

Files.createDirectories(reportDir)

String base_dir = 'Screenshots/Hitung Harga Properti/'

println('1. Hitung Harga Properti (Data Positif)')

for (def rowNum = 1; rowNum <= 5; rowNum++) {
		
	WebUI.setText(findTestObject('Object Repository/Input Penghasilan Total'), findTestData('Daftar Harga').getValue(1, rowNum))
	
	WebUI.setText(findTestObject('Object Repository/Input Pengeluaran'), findTestData('Daftar Harga').getValue(2, rowNum))
	
	WebUI.selectOptionByValue(findTestObject('Object Repository/Jangka Waktu'), findTestData('Daftar Harga').getValue(3, rowNum), true)
	
	WebUI.click(findTestObject('Object Repository/Button_Hitung Harga'))

	WebUI.getText(findTestObject('Object Repository/Hasil Hitung Properti'))
	
	WebUI.delay(3)
	
	WebUI.takeFullPageScreenshot()
	
	WebUI.takeFullPageScreenshot(base_dir + 'Positive Case/Hasil Harga Properti (Data Positif)' + rowNum + '.png')

	String Hasil_Hitung_Positif = WebUI.getText(findTestObject('Object Repository/Hasil Hitung Properti'))
	
	String Hasil_Hitung_Web_Positif = Hasil_Hitung_Positif.replaceAll("Rp ", "").replace(".", "").trim()
	
	int PenghasilanTotal = findTestData('Daftar Harga').getValue(1, rowNum).toInteger()
	
	int Pengeluaran = findTestData('Daftar Harga').getValue(2, rowNum).toInteger()
	
	int JangkaWaktu = findTestData('Daftar Harga').getValue(3, rowNum).toInteger()
	
	Hitung_Harga_Properti_Manual =  (PenghasilanTotal - Pengeluaran)*(JangkaWaktu*12/3)
	
	String Hasil_Hitung_Manual = Hitung_Harga_Properti_Manual.toString()
	
	println(Hasil_Hitung_Web_Positif)
	
	println(Hasil_Hitung_Manual)
	
	if (Hasil_Hitung_Web_Positif == Hasil_Hitung_Manual){
	
		println('Hasil Sesuai')
		
	}
	else {
		println('Hasil Tidak Sesuai')
	}
	
	rowNum++
}

//Negative Case

println('2. Hitung Harga Properti (Data Negatif')

println('a. Perhitungan Harga Properti (Penghasilan Total > Pengeluaran)')

WebUI.refresh()
	
WebUI.setText(findTestObject('Object Repository/Input Penghasilan Total'), findTestData('Daftar Harga').getValue(1, 6))
	
WebUI.setText(findTestObject('Object Repository/Input Pengeluaran'), findTestData('Daftar Harga').getValue(2, 6))
	
WebUI.selectOptionByValue(findTestObject('Object Repository/Jangka Waktu'), findTestData('Daftar Harga').getValue(3, 6), true)

WebUI.delay(3)

WebUI.takeFullPageScreenshot()
	
WebUI.takeFullPageScreenshot(base_dir + 'Negative Case/Hasil Hitung Negatif Data Ke1.png')

print('Penghasilan Total Kurang Dari Pengeluaran')

WebUI.refresh()

println('b. Perhitungan Aplikasi Web (Penghasilan Total > Pengeluaran) Dengan Nilai Koma')

WebUI.setText(findTestObject('Object Repository/Input Penghasilan Total'), findTestData('Daftar Harga').getValue(1, 7))

WebUI.setText(findTestObject('Object Repository/Input Pengeluaran'), findTestData('Daftar Harga').getValue(2, 7))

WebUI.selectOptionByValue(findTestObject('Object Repository/Jangka Waktu'), findTestData('Daftar Harga').getValue(3, 7), true)

WebUI.click(findTestObject('Object Repository/Button_Hitung Harga'))

WebUI.delay(3)

WebUI.takeFullPageScreenshot()

WebUI.takeFullPageScreenshot(base_dir + 'Negative Case/Hasil Hitung Negatif Data Ke2.png')

String Hasil_Hitung_Negatif = WebUI.getText(findTestObject('Object Repository/Hasil Hitung Properti'))
	
println(Hasil_Hitung_Negatif)

println(' -> Tidak muncul validasi penghasilan total kurang dari pengeluaran')

println(' -> Hasil hitung bernilai minus (-)')


