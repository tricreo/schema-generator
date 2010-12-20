package jp.tricreo.schemagenerator.domain.model.impl;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import java.sql.Connection;

import jp.tricreo.schemagenerator.domain.model.ActionContext;
import jp.tricreo.schemagenerator.domain.model.impl.EchoActionImpl;

import org.junit.Test;
import org.slf4j.Logger;


/**
 * {@link EchoActionImpl}のためのテスト。
 * 
 * @version $Id$
 * @author junichi
 */
public class EchoActionImplTest {
	
	private String text = "DEPTを作成します";
	

	/**
	 * 値として等価であること
	 */
	@Test
	public void test01_値として等価であること() {
		assertThat(new EchoActionImpl(text),
				is(equalTo(new EchoActionImpl(text))));
	}
	
	/**
	 * アクションを実行できること
	 */
	@Test
	public void test02_アクションを実行できること() {
		Logger logger = mock(Logger.class);
		Connection connection = mock(Connection.class);
		doNothing().when(logger).info(text);
		
		ActionContext actionContext = new ActionContext(logger, connection);
		
		EchoActionImpl echoActionImpl = new EchoActionImpl(text);
		echoActionImpl.execute(actionContext);
		
//		verify(actionContext, never()).getConnection();
//		verify(actionContext).getLogger();
		verify(logger).info(text);
	}
}
