package jp.tricreo.schemagenerator.domain.model.impl;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import jp.tricreo.schemagenerator.domain.model.ActionContext;
import jp.tricreo.schemagenerator.domain.model.impl.SqlActionImpl;

import org.junit.Test;
import org.slf4j.Logger;


/**
 * {@link SqlActionImpl}のためのテスト。
 * 
 * @version $Id$
 * @author junichi
 */
public class SqlActionImplTest {
	
	private String sql =
			"CREATE TABLE DEPT (DEPT_ID INTEGER, DEPT_NAME VARCHAR(32))";
	

	/**
	 * 値として等価であること
	 */
	@Test
	public void test01_識別子で等価であること() {
		assertThat(new SqlActionImpl(sql), is(equalTo(new SqlActionImpl(sql))));
	}
	
	/**
	 * アクションを実行できること
	 * 
	 * @throws SQLException SQLの発行に失敗した場合
	 */
	@Test
	public void test02_アクションを実行できること() throws SQLException {
		Logger logger = mock(Logger.class);
		Connection connection = mock(Connection.class);
		Statement statement = mock(Statement.class);
		// finalクラスの場合はmockにできない
		ActionContext actionContext = new ActionContext(logger, connection);
		
		//when(actionContext.getLogger()).thenReturn(logger);
		doNothing().when(logger).debug(anyString());
		//when(actionContext.getConnection()).thenReturn(connection);
		when(connection.createStatement()).thenReturn(statement);
		when(statement.executeUpdate(sql)).thenReturn(1);
		
		SqlActionImpl sqlActionImpl = new SqlActionImpl(sql);
		sqlActionImpl.execute(actionContext);
		
		//verify(actionContext).getConnection();
		verify(connection).createStatement();
		verify(statement).executeUpdate(sql);
		verify(logger).debug(anyString());
		verify(statement).close();
	}
}
