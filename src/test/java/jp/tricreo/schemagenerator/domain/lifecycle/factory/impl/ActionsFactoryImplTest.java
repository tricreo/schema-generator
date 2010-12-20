package jp.tricreo.schemagenerator.domain.lifecycle.factory.impl;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;

import java.util.List;

import jp.tricreo.schemagenerator.domain.model.Action;
import jp.tricreo.schemagenerator.domain.model.Actions;
import jp.tricreo.schemagenerator.domain.model.DataSource;
import jp.tricreo.schemagenerator.domain.model.DataSourceConnectService;

import com.google.common.collect.Lists;

import org.junit.Test;

/**
 * {@link ActionFactoryImpl}のためのテスト。
 * 
 * @version $Id$
 * @author junichi
 */
public class ActionsFactoryImplTest {
	
	/**
	 * Actionsを生成できること。
	 */
	@Test
	public void test01_Actionsを生成できること() {
		ActionsFactoryImpl actionsFactoryImpl = new ActionsFactoryImpl();
		DataSource dataSource = mock(DataSource.class);
		DataSourceConnectService dataSourceConnectService =
				mock(DataSourceConnectService.class);
		List<Action<?>> actions = Lists.newArrayList();
		Actions newActions =
				actionsFactoryImpl.newActions("test1", dataSource,
						dataSourceConnectService, actions);
		assertThat(newActions, is(notNullValue()));
		assertThat(newActions.getActions(), is(equalTo(actions)));
		assertThat(newActions.getDataSource(), is(equalTo(dataSource)));
	}
	
}
