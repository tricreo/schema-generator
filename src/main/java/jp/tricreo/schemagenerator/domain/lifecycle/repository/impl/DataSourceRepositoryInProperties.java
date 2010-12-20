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
package jp.tricreo.schemagenerator.domain.lifecycle.repository.impl;

import java.util.Collection;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;

import jp.tricreo.schemagenerator.domain.lifecycle.repository.DataSourceRespository;
import jp.tricreo.schemagenerator.domain.model.DataSource;
import jp.tricreo.schemagenerator.exception.FileNotFoundRuntimeException;
import jp.tricreo.schemagenerator.exception.IORuntimeException;

import com.google.common.collect.Maps;

import org.apache.commons.lang.Validate;


/**
 * {@link DataSource}のためのリポジトリの実装クラス。
 * 
 * <p>
 * エンティティである{@link DataSource}の永続化を担うリポジトリ。
 * この実装では、プロパティファイルから{@link DataSource}を読み込みます。
 * </p>

 * 
 * @author junichi
 */
public class DataSourceRepositoryInProperties extends
		AbstractRepositoryInProperties implements DataSourceRespository {
	
	private final String fileName;
	

	/**
	 * インスタンスを生成する。
	 * 
	 * @param fileName
	 *            プロパティファイル名
	 */
	public DataSourceRepositoryInProperties(String fileName) {
		Validate.notNull(fileName);
		this.fileName = fileName;
	}
	
	/**
	 * {@inheritDoc}
	 * 
	 * @throws FileNotFoundRuntimeException ファイルが見つからない場合
	 * @throws IORuntimeException 
	 * プロパティファイルを読み込み時にエラーが発生した場合、 
	 * もしくはストリームをクローズできなかった場合。
	 */
	@Override
	public Collection<DataSource> findAll() {
		Properties properties = loadProperties(fileName);
		Map<String, DataSource> dataSources = parseProperties(properties);
		return dataSources.values();
	}
	
	@Override
	public DataSource findById(String identity) {
		Validate.notNull(identity);
		Properties properties = loadProperties(fileName);
		Map<String, DataSource> dataSources = parseProperties(properties);
		DataSource dataSource = dataSources.get(identity);
		return dataSource;
	}
	
	/**
	 * {@link DataSource}を管理する{@link Map}から指定した識別子の{@link DataSource}を取得する。
	 * 
	 * <p>{@link DataSource}を管理する{@link Map}に指定した識別子の{@link DataSource}がなければ作成して返す。</p>
	 * 
	 * @param dataSourceMap {@link DataSource}を管理する{@link Map}
	 * @param identity 識別子
	 * @return {@link DataSource}
	 */
	private DataSource getDataSoruceFromMap(
			Map<String, DataSource> dataSourceMap, String identity) {
		DataSource dataSource = null;
		if (dataSourceMap.containsKey(identity) == false) {
			dataSource = new DataSource(identity);
			dataSourceMap.put(identity, dataSource);
		} else {
			dataSource = dataSourceMap.get(identity);
		}
		return dataSource;
	}
	
	/**
	 * {@link Properties}を解析し、識別子をキーにデータソース情報を値に持つ{@link Map}に格納する。
	 * 
	 * @param properties {@link Properties}
	 * @return 識別子をキーにデータソース情報を値に持つ{@link Map}
	 */
	private Map<String, DataSource> parseProperties(Properties properties) {
		Validate.notNull(properties);
		Map<String, DataSource> dataSourceMap = Maps.newHashMap();
		for (Entry<Object, Object> propertiesEntry : properties.entrySet()) {
			String key = (String) propertiesEntry.getKey();
			if (key.startsWith("dataSources.") == false) {
				continue;
			}
			String value = (String) propertiesEntry.getValue();
			String[] split = key.split("\\.");
			String identity = split[1];
			DataSource dataSource =
					getDataSoruceFromMap(dataSourceMap, identity);
			String propertyName = split[2];
			setProperty(dataSource, propertyName, value);
		}
		return dataSourceMap;
	}
	
	/**
	 * {@link DataSource}のプロパティに値を設定する。
	 * 
	 * @param dataSource {@link DataSource}
	 * @param propertyName プロパティ名
	 * @param value 値
	 */
	private void setProperty(DataSource dataSource, String propertyName,
			String value) {
		if (propertyName.equals("driverClassName")) {
			dataSource.setDriverClassName(value);
		} else if (propertyName.equals("url")) {
			dataSource.setUrl(value);
		} else if (propertyName.equals("userName")) {
			dataSource.setUserName(value);
		} else if (propertyName.equals("password")) {
			dataSource.setPassword(value);
		}
	}
	
	@Override
	public void store(DataSource dataSource) {
		// 読み込みのみなので実装しない
		throw new UnsupportedOperationException("store");
	}
	
}
