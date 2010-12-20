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

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import jp.tricreo.schemagenerator.exception.FileNotFoundRuntimeException;
import jp.tricreo.schemagenerator.exception.IORuntimeException;
import jp.tricreo.schemagenerator.infrastructure.utils.CloseableUtil;

import org.apache.commons.lang.Validate;


/**
 * リポジトリ(プロパティファイル版)の抽象クラス。
 * 
 * @author junichi
 */
public abstract class AbstractRepositoryInProperties {
	
	/**
	 * プロパティファイルを読み込む。
	 * 
	 * @param filePath ファイルパス
	 * @return {@link Properties}
	 */
	protected Properties loadProperties(String filePath) {
		Validate.notNull(filePath);
		FileInputStream fis = null;
		Properties result = new Properties();
		try {
			fis = new FileInputStream(filePath);
			result.load(fis);
		} catch (FileNotFoundException e) {
			throw new FileNotFoundRuntimeException(e);
		} catch (IOException e) {
			throw new IORuntimeException(e);
		} finally {
			CloseableUtil.close(fis);
		}
		return result;
	}
}
