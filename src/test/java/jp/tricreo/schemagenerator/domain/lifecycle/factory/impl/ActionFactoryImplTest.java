package jp.tricreo.schemagenerator.domain.lifecycle.factory.impl;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertThat;
import jp.tricreo.schemagenerator.domain.model.Action;
import jp.tricreo.schemagenerator.domain.model.impl.EchoActionImpl;
import jp.tricreo.schemagenerator.domain.model.impl.SqlActionImpl;

import org.junit.Test;

/**
 * {@link ActionFactoryImpl}のためのテスト。
 * 
 * @version $Id$
 * @author junichi
 */
public class ActionFactoryImplTest {
	
	/**
	 * Actionを生成できること。
	 */
	@Test
	public void test01_Actionを生成できること() {
		ActionFactoryImpl actionFactoryImpl = new ActionFactoryImpl();
		Action<?> newAction =
				actionFactoryImpl.newAction(ActionFactoryImpl.ECHO);
		assertThat(newAction, is(notNullValue()));
		assertThat(newAction, is(EchoActionImpl.class));
		
		newAction = actionFactoryImpl.newAction(ActionFactoryImpl.SQL);
		assertThat(newAction, is(notNullValue()));
		assertThat(newAction, is(SqlActionImpl.class));
	}
	
	/**
	 * Actionの生成に失敗すること(nullを渡した場合)。
	 */
	@Test(expected = IllegalArgumentException.class)
	public void test02_Actionの生成に失敗すること_nullを渡した場合() {
		ActionFactoryImpl actionFactoryImpl = new ActionFactoryImpl();
		actionFactoryImpl.newAction(null);
	}
	
	/**
	 * Actionの生成に失敗すること(空文字列を渡した場合)。
	 */
	@Test(expected = IllegalArgumentException.class)
	public void test03_Actionの生成に失敗すること_空文字列を渡した場合() {
		ActionFactoryImpl actionFactoryImpl = new ActionFactoryImpl();
		actionFactoryImpl.newAction("");
	}
	
	/**
	 * Actionの生成に失敗すること(想定外の文字列を渡した場合)。
	 * <p>ECHO:かSQL:から始まらない文字列を受け取った場合</p>
	 */
	@Test
	public void test04_Actionの生成に失敗すること_想定外の文字列を渡した場合() {
		ActionFactoryImpl actionFactoryImpl = new ActionFactoryImpl();
		Action<?> newAction = actionFactoryImpl.newAction("TEST");
		assertThat(newAction, is(nullValue()));
	}
	
}
