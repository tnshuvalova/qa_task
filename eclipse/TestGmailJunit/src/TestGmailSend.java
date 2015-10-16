import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.*;
import org.sikuli.script.App;
import org.sikuli.script.FindFailed;
import org.sikuli.script.Pattern;
import org.sikuli.script.Region;
import org.sikuli.script.Screen;


public class TestGmailSend {
	private static WebDriver driver;
	private static Screen screen; 
	
	@BeforeClass
	public static void setUp(){
		driver = new FirefoxDriver();
		driver.manage().window().maximize();
		driver.get("https://www.google.com");	
		screen = new Screen();
	}
	
	@AfterClass
	public static void tearDown(){
		driver.close();
	}
//	@Test
//	public void testTorrent(){
//		App torrent = new App("C:\\Users\\Shuvalova\\AppData\\Roaming\\uTorrent\\uTorrent.exe");
//		torrent.open();
//		try {
//			Thread.sleep(13000);
//		} catch (InterruptedException e) {
//			e.printStackTrace();
//		}
//		torrent.close();
//	}
	
	@Test
	public void testGmailLogin() throws FindFailed{
		Pattern gmail = new Pattern("imgs\\gmail.png");
		Pattern mail = new Pattern("imgs\\mail.png");
		Pattern next = new Pattern("imgs\\next.png");
		Pattern pass = new Pattern("imgs\\pass.png");
		Pattern submit = new Pattern("imgs\\submit.png");
		Pattern email_area = new Pattern("imgs\\email_area.png");
		Pattern password_area = new Pattern("imgs\\password_area.png");

		screen.click(gmail);
		Region email_region = screen.wait(email_area);
		email_region.paste(mail, "tshuvalova@cogniance.com");
		email_region.click(next);

		Region password_region = screen.wait(password_area);
		password_region.paste(pass, "hr$53DVX");
		password_region.click(submit);

		try {
			Thread.sleep(15000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testSendMail() throws FindFailed{
		Pattern compose_area = new Pattern("imgs\\compose_area.png");
		Pattern compose = new Pattern("imgs\\compose.png");
		Region compose_region = screen.wait(compose_area);
		compose_region.click(compose);
		
		Pattern email_fields_area = new Pattern("imgs\\to_area.png");
		Pattern to = new Pattern("imgs\\to.png").targetOffset(20,0);
		Pattern message = new Pattern("imgs\\to_area.png").targetOffset(0,60); 
		Pattern subject = new Pattern("imgs\\subject.png").targetOffset(40,0);
		screen.wait(to);
		Pattern send = new Pattern("imgs\\send.png");
		screen.click(to);
		screen.paste("tshuvalova@cogniance.com");
		screen.click(subject);
		screen.paste("Look at you!");
		screen.click(message);
		screen.paste("Say cheeeeeeeeese!");
		screen.click(send);
		
	}
}