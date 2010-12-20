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
package jp.tricreo.schemagenerator.infrastructure.utils;

import java.io.Closeable;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import jp.tricreo.schemagenerator.exception.IORuntimeException;


/**
 * {@code Closeable}のためのユーティリティ。
 * 
 * @author junichi
 */
public final class CloseableUtil {
	
	/**
	 * {@link Closeable}をクローズする。
	 * 
	 * @param closeable {@link Closeable}
	 */
	public static void close(Closeable closeable) {
		if (closeable == null) {
			return;
		}
		try {
			closeable.close();
		} catch (IOException e) {
			Throwable cause = e.getCause();
			if (cause != null && cause instanceof SQLException) {
				throw new IORuntimeException(cause);
			} else {
				throw new IORuntimeException(e);
			}
		}
	}
	
	/**
	 * {@link Connection}をクローズする。
	 * 
	 * @param connection {@link Connection}
	 */
	public static void close(final Connection connection) {
		if (connection == null) {
			return;
		}
		close(new Closeable() {
			
			@Override
			public void close() throws IOException {
				try {
					if (connection.isClosed() == false) {
						connection.close();
					}
				} catch (SQLException e) {
					throw new IOException(e);
				}
			}
		});
	}
	
	/**
	 * {@link Statement}をクローズする。
	 * 
	 * @param statement {@link Statement}
	 */
	public static void close(final Statement statement) {
		if (statement == null) {
			return;
		}
		close(new Closeable() {
			
			@Override
			public void close() throws IOException {
				try {
					if (statement.isClosed() == false) {
						statement.close();
					}
				} catch (SQLException e) {
					throw new IOException(e);
				}
			}
		});
	}
	
	private CloseableUtil() {
	}
	
}
