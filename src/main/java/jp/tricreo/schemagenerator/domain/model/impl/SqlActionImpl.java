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
package jp.tricreo.schemagenerator.domain.model.impl;

import java.sql.SQLException;
import java.sql.Statement;

import jp.tricreo.schemagenerator.domain.model.Action;
import jp.tricreo.schemagenerator.domain.model.ActionContext;
import jp.tricreo.schemagenerator.exception.SQLRuntimeException;
import jp.tricreo.schemagenerator.infrastructure.utils.CloseableUtil;

import org.apache.commons.lang.Validate;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;


/**
 * SQLを実行するアクションクラス。
 * 
 * <p>当該クラスはImmutableであること。</p>
 * 
 * @author junichi
 */
public final class SqlActionImpl implements Action<SqlActionImpl> {
	
	private final String sql;
	

	/**
	 * インスタンスを生成する。
	 * 
	 * @param sql 実行するSQL
	 */
	public SqlActionImpl(String sql) {
		Validate.notNull(sql);
		this.sql = sql;
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
		SqlActionImpl other = (SqlActionImpl) obj;
		return sameValueAs(other);
	}
	
	@Override
	public void execute(ActionContext actionContext) {
		Validate.notNull(actionContext);
		Statement statement = null;
		try {
			statement = actionContext.getConnection().createStatement();
			if (statement.executeUpdate(sql) > 0) {
				actionContext.getLogger().debug("sql = " + sql);
			}
		} catch (SQLException e) {
			throw new SQLRuntimeException(e);
		} finally {
			CloseableUtil.close(statement);
		}
	}
	
	/**
	 * SQLを取得する。
	 * 
	 * @return SQL
	 */
	public String getSql() {
		return sql;
	}
	
	@Override
	public int hashCode() {
		return new HashCodeBuilder().append(sql).toHashCode();
	}
	
	@Override
	public boolean sameValueAs(SqlActionImpl other) {
		return new EqualsBuilder().append(sql, other.sql).isEquals();
	}
	
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this,
				ToStringStyle.MULTI_LINE_STYLE);
	}
	
}
