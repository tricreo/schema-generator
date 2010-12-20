/*
 * Copyright 2010 TRICREO, Inc. (http://tricreo.jp/)
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

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import jp.tricreo.schemagenerator.domain.model.DataSource;
import jp.tricreo.schemagenerator.domain.model.DataSourceConnectService;
import jp.tricreo.schemagenerator.exception.ClassNotFoundRuntimeException;
import jp.tricreo.schemagenerator.exception.SQLRuntimeException;

import org.apache.commons.lang.Validate;


/**
 * {@link DataSourceConnectService}の実装クラス。
 * 
 * @author junichi
 */
public class DataSourceConnectServiceImpl implements DataSourceConnectService {
	
	@Override
	public Connection connect(DataSource dataSource) {
		Validate.notNull(dataSource);
		try {
			Class.forName(dataSource.getDriverClassName());
			Connection connection =
					DriverManager.getConnection(dataSource.getUrl(),
							dataSource.getUserName(), dataSource.getPassword());
			return connection;
		} catch (ClassNotFoundException e) {
			throw new ClassNotFoundRuntimeException(e);
		} catch (SQLException e) {
			throw new SQLRuntimeException(e);
		}
	}
	
}
