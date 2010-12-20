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
package jp.tricreo.schemagenerator.domain.lifecycle.factory;

import java.util.List;

import jp.tricreo.schemagenerator.domain.model.Action;
import jp.tricreo.schemagenerator.domain.model.Actions;
import jp.tricreo.schemagenerator.domain.model.DataSource;
import jp.tricreo.schemagenerator.domain.model.DataSourceConnectService;
import jp.tricreo.schemagenerator.domain.model.impl.ActionsImpl;


/**
 * {@link ActionsImpl}のためのファクトリ。
 * 
 * @author junichi
 */
public interface ActionsFactory {
	
	/**
	 * 新しい{@link ActionsImpl}を生成する。
	 * 
	 * @param identity 識別子
	 * @param dataSource {@link DataSource}
	 * @param dataSourceConnectService {@link DataSourceConnectService}
	 * @param actions {@link Action}のリスト
	 * @return {@link Actions}
	 */
	Actions newActions(String identity, DataSource dataSource,
			DataSourceConnectService dataSourceConnectService,
			List<Action<?>> actions);
}
