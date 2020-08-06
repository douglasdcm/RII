package data.retreive.rii;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class PageModel {
	
	private DSL dsl;
	
	public PageModel(WebDriver driver) {
		dsl = new DSL(driver);
	}
	
	public void selectLetter(String index){
		dsl.clickButtonByCss("#listaLetras > li:nth-child("+index+") > a");
	}
	
	public int selectLetterCollection(){
		return dsl.fetchCollectionByCss("#listaLetras > li");
	}
	
	public void selectCompany(String index){
		dsl.clickButtonByCss("#listaClientes > li:nth-child("+index+") > a > span.logo > img");
	}
	
	public void selectPage(String index){
		dsl.clickButtonByCss("#paginationClientes > div > a:nth-child("+index+")");
	}
	
	public int selectCompanyCollection(){
		return dsl.fetchCollectionByCss("#listaClientes > li > a > span.logo > img");
	}
	
	public void selectPosition(String index){
		dsl.clickButtonByCss("#lista-vagas-de-empresas > li:nth-child("+index+") > header > div.informacoes-header > h2 > .link-detalhes-vaga");
	}
	
	public int selectPositionCollection(){
		return dsl.fetchCollectionByClass("link-detalhes-vaga");
	}
	
	public List<WebElement> selectPositionCollectionList(){
		return dsl.fetchCollectionByClassList("link-detalhes-vaga");
	}
	
	public int selectPageCollection(){
		return dsl.fetchCollectionByCss("#paginationClientes > div > a");
	}
	
	public String retreiveDataFromCompany(String index){
		return dsl.fetchValueByCss("#listaClientes > li:nth-child("+index+") > a > span.logo > img");
	}
	
	public String retreiveDataFromPosition(){
		return dsl.fetchValueByClass("texto");
	}
}
