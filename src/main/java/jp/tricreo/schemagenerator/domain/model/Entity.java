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

/**
 * エンティティを表すインターフェイス。
 * 
 * <p>
 * {@link #getIdentity()}で識別子を返すこと。
 * このインターフェイスを実装するクラスでは、{@link Object#equals(Object)}は、
 * この識別子だけでオブジェクトの等価判定を行うこと。
 * {@link Object#hashCode()}も一般契約に基づき実装すること。
 * また、エンティティは原則的に可変オブジェクトで、防御的コピーを可能にするため
 * {@link Cloneable}を実装しなければならない。
 * </p>
 * 
 * @param <T> エンティティの型
 * @param <ID> 識別子
 * 
 * @version $Id$
 * @author junichi
 */
public interface Entity<T extends Entity<T, ID>, ID> extends Cloneable {
	
	/**
	 * クローンを生成する。
	 * 
	 * @return クローン
	 */
	T clone();
	
	/**
	 * 識別子を取得する。
	 * 
	 * @return 識別子
	 */
	ID getIdentity();
	
	/**
	 * 指定されたエンティティとこのエンティティが同じ識別子かどうかを判定する。
	 * 
	 * <p>{@link Object#equals}のタイプセーフ版</p>
	 * 
	 * @param other エンティティ
	 * @return 同じ識別子ならtrue
	 */
	boolean sameIdentityAs(T other);
	
}
