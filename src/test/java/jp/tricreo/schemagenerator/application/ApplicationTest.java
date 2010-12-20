package jp.tricreo.schemagenerator.application;

import java.io.File;
import java.net.URISyntaxException;

import org.junit.Test;

/**
 * {@link Application}のためのテスト。
 * 
 * @version $Id$
 * @author junichi
 */
public class ApplicationTest {
	
	/**
	 * アプリケーションが開始できること
	 */
	@Test
	public void test01_アプリケーションが開始できること() throws URISyntaxException {
		File file =
				new File(ApplicationTest.class.getResource("config.properties")
					.toURI());
		Application application = new Application(file.getPath());
		application.start();
	}
	
}
