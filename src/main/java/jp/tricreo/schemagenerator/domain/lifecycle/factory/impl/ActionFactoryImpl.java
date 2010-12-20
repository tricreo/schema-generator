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
package jp.tricreo.schemagenerator.domain.lifecycle.factory.impl;

import jp.tricreo.schemagenerator.domain.lifecycle.factory.ActionFactory;
import jp.tricreo.schemagenerator.domain.model.Action;
import jp.tricreo.schemagenerator.domain.model.impl.EchoActionImpl;
import jp.tricreo.schemagenerator.domain.model.impl.SqlActionImpl;

import org.apache.commons.lang.Validate;

/**
 * {@link ActionFactory}の実装クラス。
 * 
 * 
 * @author junichi
 */
public class ActionFactoryImpl implements ActionFactory {
	
	static final String SQL = "SQL:";
	
	static final String ECHO = "ECHO:";
	

	/**
	 * {@inheritDoc}
	 * 
	 * @param command 先頭にSQL:かECHO:の文字列が含まれるアクションの文字列。
	 */
	@Override
	public Action<?> newAction(String command) {
		Validate.notNull(command);
		Validate.notEmpty(command);
		Action<?> action = null;
		if (command.startsWith(ECHO)) {
			action = new EchoActionImpl(command.substring(ECHO.length()));
		} else if (command.startsWith(SQL)) {
			action = new SqlActionImpl(command.substring(SQL.length()));
		}
		return action;
	}
	
}
