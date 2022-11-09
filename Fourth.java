package lambda3;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class Fourth {
	public String username = "shijus1995";
	public String accesskey = "rSGOa2NjyRrlBjqLGvkoYdyqRpTgCnF30kCmTqHyVWaf6z3hRm";
	public RemoteWebDriver driver = null;
	public String gridURL = "@hub.lambdatest.com/wd/hub";
	boolean status = false;
	 
	@BeforeMethod
	@Parameters(value={"browser","version","platform"})
	public void setUp(String browser, String version, String platform) throws Exception {
	    DesiredCapabilities capabilities = new DesiredCapabilities();
	     capabilities.setCapability("browserName", browser);
	     capabilities.setCapability("version", version);
	     capabilities.setCapability("platform", platform);
	     capabilities.setCapability("build", "ParallelTestNG");
	     capabilities.setCapability("name", "ParallelTestNG");
	     capabilities.setCapability("network", true); 
	     capabilities.setCapability("visual", true); 
	     capabilities.setCapability("video", true); 
	     capabilities.setCapability("console", true); 
	     try {
	         driver = new RemoteWebDriver(new URL("https://" + username + ":" + accesskey + gridURL), capabilities);
	     } catch (MalformedURLException e) {
	         System.out.println("Invalid grid URL");
	     } catch (Exception e) {
	         System.out.println(e.getMessage());
	     }
	 }
	@Test
	public void launchBrowser()  {
		driver.manage().deleteAllCookies();
		driver.manage().window().maximize();
		
		WebDriverWait wait=new WebDriverWait(driver,20);
		driver.get("https://www.lambdatest.com/");
		  JavascriptExecutor j = (JavascriptExecutor)driver;
		  wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div[1]/footer/div[1]/div/div/div[2]/div/div[2]/ul/li[15]/a")));
		  
	      if (j.executeScript("return document.readyState").toString().equals("complete")){
	         System.out.println("Page has loaded");
	       
	         WebElement target=driver.findElement(By.xpath("//*[@id=\"__next\"]/div[1]/section[7]/div/div/div/div/a"));
	         Actions actions=new Actions(driver);
	         actions.moveToElement(target).perform();
	         String hyperlink = target.getAttribute("href");
	         
	         
	         String currentHandle= driver.getWindowHandle();
	         
		     
	         ((JavascriptExecutor)driver).executeScript("window.open()");
	         
	       
	         Set<String> handles=driver.getWindowHandles();
	         for(String actual: handles)
	         {
	             
	          if(!actual.equalsIgnoreCase(currentHandle))
	          {
	          
	              driver.switchTo().window(actual);
	              
	            driver.get(hyperlink);
	            System.out.println("opened window handles are"+"=>> "+currentHandle+" , "+actual);
	            String actualUrl=driver.getCurrentUrl();
	           Assert.assertEquals(actualUrl,hyperlink, currentHandle);
	            System.out.println("URL is the same as the expected URL");
	            
	            JavascriptExecutor js=(JavascriptExecutor)driver;
	        	js.executeScript("window.scrollBy(200,6900)");
	            driver.findElement(By.cssSelector(".sm\\:w-9\\/12 > div:nth-child(4) > div:nth-child(2) > div:nth-child(4) > a:nth-child(4)")).click();
	            
	             String actualTitle=driver.getTitle();
	           
	           try{
	        	   actualTitle.contains("TestingWhiz LambdaTest | LambdaTest");
				   System.out.println("url contains TestingWhiz LambdaTest | LambdaTest ");
				
				} catch
					( NoSuchElementException e){
					System.out.println("url is invalid and not contain TestingWhiz LambdaTest | LambdaTest");}
					
					driver.close();
					driver.switchTo().window(currentHandle);
					ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
					System.out.println("No. of tabs: " + tabs.size());
					String link1 = ("https://www.lambdatest.com/blog");
					driver.get(link1);
	           driver.findElement(By.partialLinkText("Community")).click();
	          
	           String url1=driver.getCurrentUrl();
	           Assert.assertEquals(url1, "https://community.lambdatest.com/");
	           System.out.println("url is same as=>> https://community.lambdatest.com/");
	           System.out.println("TEST IS COMPLETE");
	      
	          }}}}
	
	
	
	@AfterMethod
	    public void tearDown() throws Exception {
	       if (driver != null) {
	            driver.quit();
	        }
	    }
	}


