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

import jp.tricreo.schemagenerator.domain.model.Action;

/**
 * {@link Action}のためのファクトリ。
 * 
 * <p>設定ファイル上のアクションの文字列表現から{@link Action}を生成するファクトリ。</p>
 * 
 * @author junichi
 */
public interface ActionFactory {
	
	/**
	 * 新しい{@link Action}を生成する。
	 * 
	 * @param command 設定ファイル上のアクションの文字列表現
	 * @return 新しい{@link Action}
	 */
	Action<?> newAction(String command);
}
