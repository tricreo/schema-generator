package jp.tricreo.schemagenerator.domain.model;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;

import java.sql.Connection;

import org.junit.Test;
import org.slf4j.Logger;

/**
 * {@link ActionContext}のためのテスト。
 * 
 * @version $Id$
 * @author junichi
 */
public class ActionContextTest {
	
	/**
	 * コンストラクタで指定した値がプロパティで取得できること。
	 */
	@Test
	public void test01_コンストラクタで指定した値がプロパティで取得できること() {
		Logger logger = mock(Logger.class);
		Connection connection = mock(Connection.class);
		ActionContext actionContext = new ActionContext(logger, connection);
		assertThat(actionContext, is(notNullValue()));
		assertThat(actionContext.getLogger(), is(logger));
		assertThat(actionContext.getConnection(), is(connection));
	}
	
	/**
	 * 生成に失敗すること(loggerがnullの時)。
	 */
	@Test(expected = IllegalArgumentException.class)
	public void test02_生成に失敗すること_loggerがnullの時() {
		new ActionContext(null, mock(Connection.class));
	}
	
	/**
	 * 生成に失敗すること(loggerがnullの時)。
	 */
	@Test(expected = IllegalArgumentException.class)
	public void test03_生成に失敗すること_connectionがnullの時() {
		new ActionContext(mock(Logger.class), null);
	}
	
	/**
	 * 異なるインスタンスで同値ならequalsがtrueを返すこと。
	 */
	@Test
	public void test04_異なるインスタンスで同値ならequalsがtrueを返すこと() {
		Logger logger = mock(Logger.class);
		Connection connection = mock(Connection.class);
		assertThat(new ActionContext(logger, connection),
				is(equalTo(new ActionContext(logger, connection))));
	}
	
	/**
	 * 異なるインスタンスで同値でないならequalsがfalseを返すこと。
	 */
	@Test
	public void test05_異なるインスタンスで同値でないならequalsがfalseを返すこと() {
		assertThat(
				new ActionContext(mock(Logger.class), mock(Connection.class)).equals(new ActionContext(
						mock(Logger.class), mock(Connection.class))), is(false));
	}
	
}
