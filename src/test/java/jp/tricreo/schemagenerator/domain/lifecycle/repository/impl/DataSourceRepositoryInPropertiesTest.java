package jp.tricreo.schemagenerator.domain.lifecycle.repository.impl;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;

import java.io.File;
import java.net.URISyntaxException;

import jp.tricreo.schemagenerator.domain.lifecycle.repository.DataSourceRespository;
import jp.tricreo.schemagenerator.domain.model.DataSource;

import org.junit.Test;

/**
 * {@link DataSourceRepositoryInProperties}のためのテスト。
 * 
 * @author junichi
 */
public class DataSourceRepositoryInPropertiesTest {
	
	private String getConfigFilePath() throws URISyntaxException {
		File file =
				new File(DataSourceRepositoryInPropertiesTest.class
					.getResource("config.properties").toURI());
		return file.getPath();
	}
	
	/**
	 * リポジトリからすべてのデータソースが読み込めること
	 * @throws URISyntaxException URIが不正の場合
	 */
	@Test
	public void test01_リポジトリからすべてのデータソースが読み込めること() throws URISyntaxException {
		DataSourceRespository repository =
				new DataSourceRepositoryInProperties(getConfigFilePath());
		for (DataSource dataSource : repository.findAll()) {
			if (dataSource.getIdentity().equals("test1")) {
				assertThat(dataSource, is(notNullValue()));
				assertThat(dataSource.getIdentity(), is("test1"));
				assertThat(dataSource.getDriverClassName(), is("org.h2.Driver"));
				assertThat(dataSource.getUrl(), is("jdbc:h2:./test1"));
				assertThat(dataSource.getUserName(), is("sa"));
				assertThat(dataSource.getPassword(), is(""));
			} else if (dataSource.getIdentity().equals("test2")) {
				assertThat(dataSource, is(notNullValue()));
				assertThat(dataSource.getIdentity(), is("test2"));
				assertThat(dataSource.getDriverClassName(), is("org.h2.Driver"));
				assertThat(dataSource.getUrl(), is("jdbc:h2:./test2"));
				assertThat(dataSource.getUserName(), is("sa"));
				assertThat(dataSource.getPassword(), is(""));
			}
		}
		
	}
	
	/**
	 * リポジトリから指定したデータソースが読み込めること
	 * @throws URISyntaxException URIが不正の場合
	 */
	@Test
	public void test02_リポジトリから指定したデータソースが読み込めること() throws URISyntaxException {
		DataSourceRespository repository =
				new DataSourceRepositoryInProperties(getConfigFilePath());
		DataSource dataSource = repository.findById("test1");
		assertThat(dataSource, is(notNullValue()));
		assertThat(dataSource.getIdentity(), is("test1"));
		assertThat(dataSource.getDriverClassName(), is("org.h2.Driver"));
		assertThat(dataSource.getUrl(), is("jdbc:h2:./test1"));
		assertThat(dataSource.getUserName(), is("sa"));
		assertThat(dataSource.getPassword(), is(""));
	}
	
}
