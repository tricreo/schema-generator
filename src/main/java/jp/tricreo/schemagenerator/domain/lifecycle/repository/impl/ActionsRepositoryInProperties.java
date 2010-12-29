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

import java.util.List;
import java.util.Properties;

import jp.tricreo.schemagenerator.domain.lifecycle.factory.ActionFactory;
import jp.tricreo.schemagenerator.domain.lifecycle.factory.ActionsFactory;
import jp.tricreo.schemagenerator.domain.lifecycle.factory.impl.ActionFactoryImpl;
import jp.tricreo.schemagenerator.domain.lifecycle.factory.impl.ActionsFactoryImpl;
import jp.tricreo.schemagenerator.domain.lifecycle.repository.ActionsRepository;
import jp.tricreo.schemagenerator.domain.lifecycle.repository.DataSourceRepository;
import jp.tricreo.schemagenerator.domain.model.Action;
import jp.tricreo.schemagenerator.domain.model.Actions;
import jp.tricreo.schemagenerator.domain.model.DataSource;
import jp.tricreo.schemagenerator.domain.model.impl.DataSourceConnectServiceImpl;

import org.apache.commons.lang.Validate;

import com.google.common.collect.Lists;

/**
 * {@link Actions}のためのリポジトリの実装クラス。
 * 
 * <p>
 * エンティティである{@link Actions}の永続化を担うリポジトリ。
 * この実装では、プロパティファイルから{@link Actions}を読み込みます。
 * </p>
 * 
 * @author junichi
 */
public class ActionsRepositoryInProperties extends
		AbstractRepositoryInProperties implements ActionsRepository {
	
	private final String fileName;
	
	private final DataSourceRepository dataSourceRepository;
	
	private final ActionFactory actionFactory = new ActionFactoryImpl();
	
	private final ActionsFactory actionsFactory = new ActionsFactoryImpl();
	

	/**
	 * インスタンスを生成する。
	 * 
	 * @param fileName ファイル名
	 * @param dataSourceRepository {@link DataSourceRepository}
	 */
	public ActionsRepositoryInProperties(String fileName,
			DataSourceRepository dataSourceRepository) {
		Validate.notNull(fileName);
		Validate.notNull(dataSourceRepository);
		this.fileName = fileName;
		this.dataSourceRepository = dataSourceRepository;
	}
	
	@Override
	public List<Actions> findAll() {
		List<Actions> result = Lists.newArrayList();
		Properties loadProperties = loadProperties(fileName);
		String sequence = loadProperties.getProperty("actions.sequence");
		String[] identities = sequence.split(",");
		for (String identitiy : identities) {
			identitiy = identitiy.trim();
			Actions actions = internalFindById(loadProperties, identitiy);
			result.add(actions);
		}
		return result;
	}
	
	@Override
	public Actions findById(String identitiy) {
		Properties loadProperties = loadProperties(fileName);
		return internalFindById(loadProperties, identitiy);
	}
	
	/**
	 * {@link Properties}から指定された識別子の{@link Actions}を検索する。
	 * 
	 * @param properties {@link Properties}
	 * @param identitiy 識別子
	 * @return {@link Actions}
	 */
	private Actions internalFindById(Properties properties, String identitiy) {
		String dataSourceId =
				properties.getProperty(String.format("actions.%s.dataSource",
						identitiy));
		DataSource dataSource = dataSourceRepository.findById(dataSourceId);
		int actionSize =
				Integer.valueOf(properties.getProperty(String.format(
						"actions.%s.action.size", identitiy)));
		List<Action<?>> actionItems = Lists.newArrayList();
		for (int index = 1; index <= actionSize; index++) {
			String command =
					properties.getProperty(String.format(
							"actions.%s.action.%d", identitiy, index));
			Action<?> action = actionFactory.newAction(command);
			actionItems.add(action);
		}
		Actions actions =
				actionsFactory.newActions(identitiy, dataSource,
						new DataSourceConnectServiceImpl(), actionItems);
		return actions;
	}
	
	@Override
	public void store(Actions entity) {
		throw new UnsupportedOperationException("store");
	}
	
}
