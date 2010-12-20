package jp.tricreo.schemagenerator.domain.model;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.spy;
import jp.tricreo.schemagenerator.domain.model.DataSource;

import org.junit.Test;

/**
 * {@link DataSource}のためのテスト。
 * 
 * @version $Id$
 * @author junichi
 */
public class DataSourceTest {
	
	/**
	 * コンストラクタで与えたパラメータをゲッターで取得できること
	 */
	@Test
	public void test01_コンストラクタで与えたパラメータをゲッターで取得できること() {
		DataSource dataSource = new DataSource("test1");
		dataSource.setDriverClassName("org.h2.Driver");
		dataSource.setUrl("jdbc:h2:./test1");
		dataSource.setUserName("sa");
		dataSource.setPassword("");
		assertThat(dataSource, is(notNullValue()));
		assertThat(dataSource.getIdentity(), is("test1"));
		assertThat(dataSource.getDriverClassName(), is("org.h2.Driver"));
		assertThat(dataSource.getUrl(), is("jdbc:h2:./test1"));
		assertThat(dataSource.getUserName(), is("sa"));
		assertThat(dataSource.getPassword(), is(""));
	}
	
	/**
	 * 識別子で等価であること
	 */
	@Test
	public void test02_識別子で等価であること() {
		assertThat(new DataSource("test1"),
				is(equalTo(new DataSource("test1"))));
	}
	
	/**
	 * cloneしたインスタンスと等価であること
	 */
	@Test
	public void test03_cloneしたインスタンスと等価であること() {
		DataSource dataSource = spy(new DataSource("test3"));
		
		dataSource.setDriverClassName("org.h2.Driver");
		dataSource.setUrl("jdbc:h2:./test1");
		dataSource.setUserName("sa");
		dataSource.setPassword("");
		
		DataSource clone = dataSource.clone();
		assertThat(dataSource, is(equalTo(clone)));
	}
	
}
