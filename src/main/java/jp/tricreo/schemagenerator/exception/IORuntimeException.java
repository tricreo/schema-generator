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
package jp.tricreo.schemagenerator.exception;

import java.io.IOException;

/**
 * {@link IOException}に対応する実行時例外。
 * 
 * @author junichi
 */
@SuppressWarnings("serial")
public class IORuntimeException extends RuntimeException {
	
	/**
	 * インスタンスを生成する。
	 * 
	 */
	public IORuntimeException() {
	}
	
	/**
	 * インスタンスを生成する。
	 * 
	 * @param message メッセージ
	 */
	public IORuntimeException(String message) {
		super(message);
	}
	
	/**
	 * インスタンスを生成する。
	 * 
	 * @param message メッセージ
	 * @param cause 原因
	 */
	public IORuntimeException(String message, Throwable cause) {
		super(message, cause);
	}
	
	/**
	 * インスタンスを生成する。
	 * 
	 * @param cause 原因
	 */
	public IORuntimeException(Throwable cause) {
		super(cause);
	}
	
}
