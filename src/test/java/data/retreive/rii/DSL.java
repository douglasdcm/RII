package data.retreive.rii;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class DSL {
	
	private WebDriver driver;
	
	public DSL(WebDriver driver) {
		this.driver = driver;
	}

	/********* TextField e TextArea ************/
	
	public void escrever(By by, String texto){
		driver.findElement(by).clear();
		driver.findElement(by).sendKeys(texto);
	}

	public void escrever(String id_campo, String texto){
		escrever(By.id(id_campo), texto);
	}
	
	public String obterValorCampo(String id_campo) {
		return driver.findElement(By.id(id_campo)).getAttribute("value");
	}
	
	/********* Radio e Check ************/
	
	public void clicarRadio(String id) {
		driver.findElement(By.id(id)).click();
	}
	
	public boolean isRadioMarcado(String id){
		return driver.findElement(By.id(id)).isSelected();
	}
	
	public void clicarCheck(String id) {
		driver.findElement(By.id(id)).click();
	}
	
	public boolean isCheckMarcado(String id){
		return driver.findElement(By.id(id)).isSelected();
	}
	
	/********* Combo ************/
	
	public void selecionarCombo(String id, String valor) {
		WebElement element = driver.findElement(By.id(id));
		Select combo = new Select(element);
		combo.selectByVisibleText(valor);
	}
	
	public void deselecionarCombo(String id, String valor) {
		WebElement element = driver.findElement(By.id(id));
		Select combo = new Select(element);
		combo.deselectByVisibleText(valor);
	}

	public String obterValorCombo(String id) {
		WebElement element = driver.findElement(By.id(id));
		Select combo = new Select(element);
		return combo.getFirstSelectedOption().getText();
	}
	
	public List<String> obterValoresCombo(String id) {
		WebElement element = driver.findElement(By.id("elementosForm:esportes"));
		Select combo = new Select(element);
		List<WebElement> allSelectedOptions = combo.getAllSelectedOptions();
		List<String> valores = new ArrayList<String>();
		for(WebElement opcao: allSelectedOptions) {
			valores.add(opcao.getText());
		}
		return valores;
	}
	
	public int obterQuantidadeOpcoesCombo(String id){
		WebElement element = driver.findElement(By.id(id));
		Select combo = new Select(element);
		List<WebElement> options = combo.getOptions();
		return options.size();
	}
	
	public boolean verificarOpcaoCombo(String id, String opcao){
		WebElement element = driver.findElement(By.id(id));
		Select combo = new Select(element);
		List<WebElement> options = combo.getOptions();
		for(WebElement option: options) {
			if(option.getText().equals(opcao)){
				return true;
			}
		}
		return false;
	}
	
	/********* Botao ************/
	
	public void clickButton(String id) {
		driver.findElement(By.id(id)).click();
	}
	
	public void clickButtonByCss(String cssSelector) {
		driver.findElement(By.cssSelector(cssSelector)).click();
	}
	
	public int fetchCollectionByCss(String cssSelector) {
		return driver.findElements(By.cssSelector(cssSelector)).size();
	}
	
	public int fetchCollectionByClass(String className) {
		return driver.findElements(By.className(className)).size();
	}
	
	public List<WebElement> fetchCollectionByClassList(String className) {
		return driver.findElements(By.className(className));
	}
	
	public void clickButtonByClass(String className) {
		driver.findElement(By.className(className)).click();
	}
	
	public String fetchValue(String id) {
		return driver.findElement(By.id(id)).getAttribute("text");
	}
	
	public String fetchValueByClass(String className) {
		return driver.findElement(By.className(className)).getText();
	}
	
	public String fetchValueByClass(String className, int index) {
		return driver.findElements(By.className(className)).get(index).getAttribute("text");
	}
	
	public String fetchValueByCss(String cssSelector) {
		return driver.findElement(By.cssSelector(cssSelector)).getAttribute("title");
	}
}
