package jp.tricreo.schemagenerator.infrastructure.utils;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import java.io.Closeable;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import org.junit.Test;

/**
 * {@link CloseableUtil}のためのテスト。
 * 
 * @version $Id$
 * @author junichi
 */
public class CloseableUtilTest {
	
	/**
	 * Closeableをcloseできること
	 * 
	 * @throws IOException closeに失敗した時
	 */
	@Test
	public void test01_Closeableをcloseできること() throws IOException {
		Closeable closeable = mock(Closeable.class);
		CloseableUtil.close(closeable);
		verify(closeable).close();
	}
	
	/**
	 * Connectionをcloseできること
	 * 
	 * @throws SQLException closeに失敗した時 
	 */
	@Test
	public void test02_Connectionをcloseできること() throws SQLException {
		Connection connection = mock(Connection.class);
		CloseableUtil.close(connection);
		verify(connection).close();
	}
	
	/**
	 * Statementをcloseできること。
	 *
	 * @throws SQLException closeに失敗した時
	 */
	@Test
	public void test03_Statementをcloseできること() throws SQLException {
		Statement statement = mock(Statement.class);
		CloseableUtil.close(statement);
		verify(statement).close();
	}
	
}
