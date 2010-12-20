package jp.tricreo.schemagenerator.domain.model.impl;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import jp.tricreo.schemagenerator.domain.model.Action;
import jp.tricreo.schemagenerator.domain.model.ActionContext;
import jp.tricreo.schemagenerator.domain.model.Actions;
import jp.tricreo.schemagenerator.domain.model.DataSource;
import jp.tricreo.schemagenerator.domain.model.DataSourceConnectService;

import com.google.common.collect.Lists;

import org.junit.Test;

/**
 * {@link ActionsImpl}のためのテスト。
 * 
 * @version $Id$
 * @author junichi
 */
public class ActionsImplTest {
	
	/**
	 * コンストラクタで与えたパラメータをゲッターで取得できること。
	 */
	@Test
	public void test01_コンストラクタで与えたパラメータをゲッターで取得できること() {
		String identity = "test1";
		DataSource dataSource = spy(new DataSource(identity));
		DataSourceConnectService dataSourceConnectService =
				mock(DataSourceConnectService.class);
		
		List<Action<?>> actionItems = Lists.newArrayList();
		actionItems.add(mock(Action.class));
		actionItems.add(mock(Action.class));
		
		Actions actions =
				new ActionsImpl(identity, dataSource, dataSourceConnectService,
						actionItems);
		
		assertThat(actions.getIdentity(), is(identity));
		assertThat(actions.getDataSource(), is(equalTo(dataSource)));
		assertThat(actions.getActions(), is(notNullValue()));
		assertThat(actions.getActions().isEmpty(), is(false));
		assertThat(actions.getActions().size(), is(2));
	}
	
	/**
	 * 識別子で等価であること。
	 */
	@Test
	public void test02_識別子で等価であること() {
		String identity = "test1";
		List<Action<?>> actionItems = Lists.newArrayList();
		actionItems.add(mock(Action.class));
		
		assertThat(new ActionsImpl(identity, mock(DataSource.class),
				mock(DataSourceConnectService.class), actionItems),
				is(equalTo(new ActionsImpl(identity, mock(DataSource.class),
						mock(DataSourceConnectService.class), actionItems))));
	}
	
	/**
	 * アクションを実行できること。
	 * 
	 * @throws SQLException SQLの発行に失敗した場合
	 */
	@Test
	public void test03_アクションを実行できること() throws SQLException {
		DataSourceConnectService dataSourceConnectService =
				mock(DataSourceConnectService.class);
		DataSource dataSource = mock(DataSource.class);
		Connection connection = mock(Connection.class);
		when(dataSourceConnectService.connect(eq(dataSource))).thenReturn(
				connection);
		
		Action<?> action = mock(Action.class);
		doNothing().when(action).execute(any(ActionContext.class));
		
		List<Action<?>> actionItems = Lists.newArrayList();
		actionItems.add(action);
		actionItems.add(action);
		
		ActionsImpl actions =
				new ActionsImpl("test1", dataSource, dataSourceConnectService,
						actionItems);
		
		actions.execute();
		
		verify(dataSourceConnectService).connect(dataSource);
		verify(action, times(2)).execute(any(ActionContext.class));
		verify(connection).close();
	}
	
	/**
	 * cloneしたインスタンスと等価であること
	 */
	@Test
	public void test04_cloneしたインスタンスと等価であること() {
		DataSourceConnectService dataSourceConnectService =
				mock(DataSourceConnectService.class);
		DataSource dataSource = mock(DataSource.class);
		
		List<Action<?>> actionItems = Lists.newArrayList();
		Action<?> action = mock(Action.class);
		actionItems.add(action);
		actionItems.add(action);
		
		ActionsImpl actions =
				new ActionsImpl("test1", dataSource, dataSourceConnectService,
						actionItems);
		ActionsImpl clone = actions.clone();
		assertThat(clone, is(equalTo(actions)));
	}
	
	/**
	 * 生成に失敗すること。(識別子にnullを指定した場合)
	 */
	@Test(expected = IllegalArgumentException.class)
	public void test05_生成に失敗すること_識別子にnullを指定した場合() {
		DataSource dataSource = mock(DataSource.class);
		DataSourceConnectService dataSourceConnectService =
				mock(DataSourceConnectService.class);
		
		List<Action<?>> actionItems = Lists.newArrayList();
		actionItems.add(mock(Action.class));
		actionItems.add(mock(Action.class));
		
		new ActionsImpl(null, dataSource, dataSourceConnectService, actionItems);
	}
	
	/**
	 * 生成に失敗すること。(識別子にnullを指定した場合)
	 */
	@Test(expected = IllegalArgumentException.class)
	public void test06_生成に失敗すること_dataSourceにnullを指定した場合() {
		DataSourceConnectService dataSourceConnectService =
				mock(DataSourceConnectService.class);
		
		List<Action<?>> actionItems = Lists.newArrayList();
		actionItems.add(mock(Action.class));
		actionItems.add(mock(Action.class));
		
		new ActionsImpl("test1", null, dataSourceConnectService, actionItems);
	}
	
	/**
	 * 生成に失敗すること。(actionItemsにnullを指定した場合)
	 */
	@Test(expected = IllegalArgumentException.class)
	public void test07_生成に失敗すること_actionItemsにnullを指定した場合() {
		DataSource dataSource = mock(DataSource.class);
		DataSourceConnectService dataSourceConnectService =
				mock(DataSourceConnectService.class);
		
		new ActionsImpl("test1", dataSource, dataSourceConnectService, null);
	}
	
	/**
	 * 生成に失敗すること。(dataSourceConnectServiceにnullを指定した場合)
	 */
	@Test(expected = IllegalArgumentException.class)
	public void test07_生成に失敗すること_dataSourceConnectServiceにnullを指定した場合() {
		DataSource dataSource = mock(DataSource.class);
		
		List<Action<?>> actionItems = Lists.newArrayList();
		actionItems.add(mock(Action.class));
		actionItems.add(mock(Action.class));
		
		new ActionsImpl("test1", dataSource, null, actionItems);
	}
	
}
