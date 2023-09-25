package TestRevele;
import java.time.Duration;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class ETmoney 
{    
	int i =0 ;
	public WebDriver driver;
	
	@BeforeTest
    public void Setup ()
      { 
		
  // System.setProperty("webdriver.chrome.driver", "C:\\Users\\BALA G\\Downloads\\chromedriver_win32\\chromedriver.exe"); 
		 
		WebDriverManager.chromedriver().setup();
		
		 driver = new ChromeDriver();
   
 
      }
	
	@Test
	
	void testCookieManipulation()	
	
	{
		 
		
		driver.get("https://www.etmoney.com/mutual-funds/filter/long-term-funds-return");
        
        driver.manage().window().maximize();
           
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        
     // Step 2: Verify if “Long-Term Historic Returns” is selected 
        
        WebElement returnsCheckbox = driver.findElement(By.xpath("//input[@value='long_term']"));
        
        if (returnsCheckbox.isSelected()) 
        {
            System.out.println("Task 2: Long-Term Historic Returns is selected");
        } 
        else 
        {
            System.out.println("Task 2: Long-Term Historic Returns is not selected");
        }
        
        

     // Step 3: Click on Investment Type “SIP”
        WebElement sipCheckbox = driver.findElement(By.xpath("//input[@value='sip']"));
        sipCheckbox.click();
        
     // Step 4: Click on “Hybrid” under Category
        WebElement categoryCheckbox = driver.findElement(By.xpath("//input[@value='HYBRID']"));
        categoryCheckbox.click();
      
     // Step 5: Verify if “All” is checked by default
        
        List<WebElement>checkbox = driver.findElements(By.xpath("//input[@value='HYBRID']"));
        
          for (int i =0 ; i<checkbox.size() ; i++)
      	  
          { 
        	  checkbox.get(i).click() ;
        	  
          }
        if (checkbox.get(1).isSelected()) 
        {
            System.out.println(" All is checked by default");
        } 
        else 
        {
            System.out.println(" All is not checked by default");
        } 
        
     // Step 6: Click on the “Aggressive Hybrid” checkbox to select it
        
     // allredy selected in step 5  
        
      
     // Step 7: Verify if “All” checkbox is unchecked automatically
        
        for (int i =0 ; i<checkbox.size() ; i++)
        	  
        { 
      	  checkbox.get(i).click() ;
      	  
        }
        
        
        if (checkbox.get(i).isSelected()) 
        {
            System.out.println( "All is unchecked automatically");
        } else 
        {
            System.out.println(
 "All is not unchecked automatically");
        }  
        
        
     // Step 8: Click on 1Y to sort it on the basis of 1yr return
        WebElement return1YHeader = driver.findElement(By.id("returns-1y"));
        return1YHeader.click(); 
       
        
     // Step 9: Get all the returns and verify if it is sorted in descending order
        List<WebElement> returnElements = driver.findElements(By.xpath("//table[@id='el-mutual-funds']/tbody/tr/td[8]"));
        boolean isSorted = true;

        for (int i = 0; i < returnElements.size() - 1; i++) {
            String currentReturn = returnElements.get(i).getText();
            String nextReturn = returnElements.get(i + 1).getText();
            
            // Convert percentage strings to double for comparison
            double currentReturnPercentage = Double.parseDouble(currentReturn.replace("%", ""));
            double nextReturnPercentage = Double.parseDouble(nextReturn.replace("%", ""));
            
            if (currentReturnPercentage < nextReturnPercentage) {
                isSorted = false;
                break;
            }
        }

        if (isSorted) {
            System.out.println(" Returns are sorted in descending order");
        } else {
            System.out.println(" Returns are not sorted in descending order");
        }

	}
	
	@AfterTest
	public void tearDown()
{ 
	        driver.quit();
	    }
		
             		
}
	
	
	
		

