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
package jp.tricreo.schemagenerator.domain.model;

import java.sql.Connection;

import org.apache.commons.lang.Validate;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.slf4j.Logger;

/**
 * {@link Action}のためのコンテキスト。
 * 
 * <p>
 * {@link Connection}や{@link Logger}は可変オブジェクトであるため、
 * このオブジェクトも可変オブジェクトである。あまりイケテナイけど、可変
 * にする部分は、できるだけ局所化するとよい。
 * </p>
 * 
 * @author junichi
 */
public final class ActionContext implements ValueObject<ActionContext> {
	
	private final Connection connection;
	
	private final Logger logger;
	

	/**
	 * インスタンスを生成する。
	 * 
	 * @param logger {@link Logger}
	 * @param connection {@link Connection}
	 */
	public ActionContext(Logger logger, Connection connection) {
		Validate.notNull(logger);
		Validate.notNull(connection);
		this.logger = logger;
		this.connection = connection;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		ActionContext other = (ActionContext) obj;
		return sameValueAs(other);
	}
	
	/**
	 * {@link Connection}を取得する。
	 * 
	 * <p>便宜上、内部の参照をそのまま返す。</p>
	 * 
	 * @return {@link Connection}
	 */
	public Connection getConnection() {
		return connection;
	}
	
	/**
	 * {@link Logger}を取得する。
	 * 
	 * <p>便宜上、内部の参照をそのまま返す。</p>
	 * 
	 * @return {@link Logger}
	 */
	public Logger getLogger() {
		return logger;
	}
	
	@Override
	public int hashCode() {
		return new HashCodeBuilder().append(connection).append(logger)
			.toHashCode();
	}
	
	@Override
	public boolean sameValueAs(ActionContext other) {
		return new EqualsBuilder().append(connection, other.connection)
			.append(logger, other.logger).isEquals();
	}
	
}
