package jp.tricreo.schemagenerator.domain.lifecycle.repository.impl;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;

import java.io.File;
import java.net.URISyntaxException;
import java.util.ListIterator;

import jp.tricreo.schemagenerator.domain.lifecycle.repository.DataSourceRepository;
import jp.tricreo.schemagenerator.domain.model.Actions;
import jp.tricreo.schemagenerator.domain.model.DataSource;

import org.junit.Before;
import org.junit.Test;

/**
 * {@link ActionsRepositoryInProperties}のためのテスト。
 * 
 * @version $Id$
 * @author junichi
 */
public class ActionsRepositoryInPropertiesTest {
	
	private ActionsRepositoryInProperties repository;
	
	private DataSource dataSource;
	

	/**
	 * テストメソッド毎に行う初期化処理
	 * @throws URISyntaxException URIが不正の場合
	 */
	@Before
	public void setUp() throws URISyntaxException {
		String identity = "test1";
		dataSource = spy(new DataSource("test1"));
		
		DataSourceRepository dataSourceRepository =
				mock(DataSourceRepository.class);
		when(dataSourceRepository.findById(identity)).thenReturn(dataSource);
		
		File file =
				new File(ActionsRepositoryInPropertiesTest.class.getResource(
						"config.properties").toURI());
		repository =
				new ActionsRepositoryInProperties(file.getPath(),
						dataSourceRepository);
	}
	
	/**
	 * リポジトリからすべてのアクションズが読み込めること
	 */
	@Test
	public void test01_リポジトリからすべてのアクションズが読み込めること() {
		ListIterator<Actions> listIterator =
				repository.findAll().listIterator();
		String identity = "test1";
		
		Actions next = listIterator.next();
		assertThat(next.getIdentity(), is("employee_def"));
		assertThat(next.getDataSource(), is(equalTo(dataSource)));
		assertThat(next.getDataSource().getIdentity(), is(identity));
		assertThat(next.getActions(), is(notNullValue()));
		assertThat(next.getActions().isEmpty(), is(false));
		assertThat(next.getActions().size(), is(6));
		
		next = listIterator.next();
		assertThat(next.getIdentity(), is("employee_data"));
		assertThat(next.getDataSource(), is(equalTo(dataSource)));
		assertThat(next.getDataSource().getIdentity(), is(identity));
		assertThat(next.getActions(), is(notNullValue()));
		assertThat(next.getActions().isEmpty(), is(false));
		assertThat(next.getActions().size(), is(4));
	}
	
	/**
	 * リポジトリから指定したアクションズが読み込めること
	 */
	@Test
	public void test02_リポジトリから指定したアクションズが読み込めること() {
		Actions actions = repository.findById("employee_def");
		assertThat(actions.getIdentity(), is("employee_def"));
		assertThat(actions.getDataSource(), is(dataSource));
		assertThat(actions.getDataSource().getIdentity(), is("test1"));
		assertThat(actions.getActions(), is(notNullValue()));
		assertThat(actions.getActions().isEmpty(), is(false));
		assertThat(actions.getActions().size(), is(6));
	}
}
