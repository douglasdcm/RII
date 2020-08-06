package data.retreive.rii;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

public class GetVagasData {
	
	private WebDriver driver;
	private PageModel page;
	private int countPage=1;
	
	@After
	public void tearDown(){
		//driver.quit();
	}
	
	@Before
	public void initialize(){
		System.setProperty("webdriver.gecko.driver", "./src/test/resources/geckodriver.exe");
		
		/*
		//Set Firefox Headless mode as TRUE
		FirefoxOptions options = new FirefoxOptions();
		options.setHeadless(true);		
		driver = new FirefoxDriver(options);
		*/
		
		driver = new FirefoxDriver();
		driver.manage().window().setSize(new Dimension(1200, 765));
		//driver.get("https://www.vagas.com.br/empresas-contratando");
		driver.get("https://www.vagas.com.br/empresas-contratando?page="+countPage+"");
		page = new PageModel(driver);
	}
	
	@Test
	public void getDataFromVagasDotCom() throws InterruptedException{
		
		//int countPage;
		int countCompany;
		int countPosition;
		int pageSize;
		int companySize;
		int positionSize;
		String companyName;
		String companyUrl;
		String letterUrl;

		letterUrl = driver.getCurrentUrl();
				
		//for each page
		pageSize= page.selectPageCollection();
		//while(countPage < pageSize) {
		while(countPage < 59) {
		
			Thread.sleep(800);
			countCompany=0;			
			//for each company
			companySize = page.selectCompanyCollection();
			while(countCompany < companySize) {
			
				companyName = page.retreiveDataFromCompany(Integer.toString(countCompany+1));
				page.selectCompany(Integer.toString(countCompany+1));
				companyUrl = driver.getCurrentUrl();
				
				Thread.sleep(800);
				countPosition=0;
				//for each position
				positionSize = page.selectPositionCollection();
				while(countPosition < positionSize) {
					
					//get the list of elements
					List<WebElement> lpositions = page.selectPositionCollectionList();
					//click the nth element
					lpositions.get(countPosition).click();
					String positionUrl = driver.getCurrentUrl();
					String positionDescription;
					
					try {							
						positionDescription = page.retreiveDataFromPosition();
					}catch(Exception e) {
						break;
					}
					
					Thread.sleep(800);
					
					//back to previous page
					driver.get(companyUrl);
					
					//save data to database
					String sql = "INSERT INTO positions (compurl, compname, posurl, posname) VALUES (?, ?, ?, ?)";
					
					Connect c = new Connect();
			        try { 
			        	Connection conn = c.connect("database");
			        	Thread.sleep(800);
			            PreparedStatement pstmt = conn.prepareStatement(sql);
			            pstmt.setString(1, companyUrl);
			            pstmt.setString(2, companyName);
			            pstmt.setString(3, positionUrl);
			            pstmt.setString(4, positionDescription);
			            pstmt.executeUpdate();
			            Thread.sleep(800);
			            c.close(conn);
			        } catch (SQLException e) {
			            System.out.println(e.getMessage());
			        }
					
					countPosition++;
				}
				
				//back to previous page
				driver.get(letterUrl);
				countCompany++;
			
			}
			
			countPage++;
			//if (countPage < pageSize - 1) {					
				//page.selectPage(Integer.toString(countPage+3));
				driver.get("https://www.vagas.com.br/empresas-contratando?page="+countPage+"");
				letterUrl = driver.getCurrentUrl();
			//}
		}
		
	}
}
