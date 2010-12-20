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

import jp.tricreo.schemagenerator.domain.model.Action;
import jp.tricreo.schemagenerator.domain.model.ActionContext;

import org.apache.commons.lang.Validate;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;


/**
 * メッセージを出力するためのアクションクラス。
 * 
 * <p>当該クラスはImmutableであること。</p>
 * 
 * @author junichi
 */
public final class EchoActionImpl implements Action<EchoActionImpl> {
	
	private final String text;
	

	/**
	 * インスタンスを生成する。
	 * 
	 * @param text 出力したい文字列
	 */
	public EchoActionImpl(String text) {
		Validate.notNull(text);
		this.text = text;
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
		EchoActionImpl other = (EchoActionImpl) obj;
		return sameValueAs(other);
	}
	
	@Override
	public void execute(ActionContext actionContext) {
		Validate.notNull(actionContext);
		actionContext.getLogger().info(text);
	}
	
	/**
	 * 出力したい文字列を取得する。
	 * 
	 * @return 出力したい文字列
	 */
	public String getText() {
		return text;
	}
	
	@Override
	public int hashCode() {
		return new HashCodeBuilder().append(text).toHashCode();
	}
	
	@Override
	public boolean sameValueAs(EchoActionImpl other) {
		return new EqualsBuilder().append(text, other.text).isEquals();
	}
	
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this,
				ToStringStyle.MULTI_LINE_STYLE);
	}
}
