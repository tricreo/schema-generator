/*
 * Copyright 2010 the Sisioh Project ant the Others.
 * Created on 2010/08/03
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND,
 * either express or implied. See the License for the specific language
 * governing permissions and limitations under the License.
 */
package jp.tricreo.schemagenerator.domain.model.impl;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;

import java.sql.Connection;
import java.sql.SQLException;

import jp.tricreo.schemagenerator.domain.model.DataSource;
import jp.tricreo.schemagenerator.domain.model.DataSourceConnectService;
import jp.tricreo.schemagenerator.exception.ClassNotFoundRuntimeException;
import jp.tricreo.schemagenerator.exception.SQLRuntimeException;
import jp.tricreo.schemagenerator.infrastructure.utils.CloseableUtil;

import org.junit.Test;

/**
 * {@link DataSourceConnectServiceImpl}のためのテスト。
 * 
 * @version $Id$
 * @author junichi
 */
public class DataSourceConnectServiceImplTest {
	
	private DataSourceConnectService dataSourceConnectService =
			new DataSourceConnectServiceImpl();
	

	/**
	 * データソースから新しい接続を取得できること。
	 * 
	 * @throws SQLException 接続に失敗した場合
	 */
	@Test
	public void test01_データソースから新しい接続を取得できること() throws SQLException {
		DataSource dataSource = new DataSource("test1");
		dataSource.setDriverClassName("org.h2.Driver");
		dataSource.setUrl("jdbc:h2:./test1");
		dataSource.setUserName("sa");
		dataSource.setPassword("");
		
		Connection connect = null;
		try {
			connect = dataSourceConnectService.connect(dataSource);
			assertThat(connect, is(notNullValue()));
			assertThat(connect.isClosed(), is(false));
		} finally {
			CloseableUtil.close(connect);
		}
	}
	
	/**
	 * 接続に失敗すること。(不正なドライバークラス名を指定した場合)
	 * 
	 * @throws SQLException 接続に失敗した場合
	 */
	@Test(expected = ClassNotFoundRuntimeException.class)
	public void test02_接続に失敗すること_不正なドライバークラス名を指定した場合() throws SQLException {
		DataSource dataSource = new DataSource("test1");
		dataSource.setDriverClassName("dmmy");
		dataSource.setUrl("jdbc:h2:./test1");
		dataSource.setUserName("sa");
		dataSource.setPassword("");
		
		Connection connect = null;
		try {
			connect = dataSourceConnectService.connect(dataSource);
			assertThat(connect, is(notNullValue()));
			assertThat(connect.isClosed(), is(false));
		} finally {
			CloseableUtil.close(connect);
		}
	}
	
	/**
	 * 接続に失敗すること。(不正なユーザ名を指定した場合)
	 * 
	 * @throws SQLException 接続に失敗した場合
	 */
	@Test(expected = SQLRuntimeException.class)
	public void test03_接続に失敗すること_不正なユーザ名を指定した場合() throws SQLException {
		DataSource dataSource = new DataSource("test1");
		dataSource.setDriverClassName("org.h2.Driver");
		dataSource.setUrl("jdbc:h2:./test1");
		dataSource.setUserName("");
		dataSource.setPassword("");
		
		Connection connect = null;
		try {
			connect = dataSourceConnectService.connect(dataSource);
			assertThat(connect, is(notNullValue()));
			assertThat(connect.isClosed(), is(false));
		} finally {
			CloseableUtil.close(connect);
		}
	}
}
