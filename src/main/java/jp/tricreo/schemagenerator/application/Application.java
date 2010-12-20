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
package jp.tricreo.schemagenerator.application;

import java.io.File;

import jp.tricreo.schemagenerator.domain.lifecycle.repository.impl.ActionsRepositoryInProperties;
import jp.tricreo.schemagenerator.domain.lifecycle.repository.impl.DataSourceRepositoryInProperties;
import jp.tricreo.schemagenerator.domain.model.Actions;


/**
 * アプリケーションクラス。
 * 
 * <p>ドメイン層のオブジェクトを使って、スキーマ作成を実現するアプリケーションクラス。</p>
 * 
 * @author junichi
 */
public final class Application {
	
	/**
	 * プログラムのエントリポイント。
	 * 
	 * @param args プログラム引数
	 */
	public static void main(String[] args) {
		if (args.length != 1) {
			System.out.println("引数に設定ファイルへのパスを指定してください");
			return;
		}
		new Application(args[0]).start();
	}
	

	private final String configPath;
	

	Application(String configPath) {
		this.configPath = new File(configPath).getPath();
	}
	
	/**
	 * アプリケーションを開始する。
	 */
	public void start() {
		DataSourceRepositoryInProperties dataSourceRespository =
				new DataSourceRepositoryInProperties(configPath);
		ActionsRepositoryInProperties actionsRepositoryInProperties =
				new ActionsRepositoryInProperties(configPath,
						dataSourceRespository);
		for (Actions actions : actionsRepositoryInProperties.findAll()) {
			actions.execute();
		}
	}
}
