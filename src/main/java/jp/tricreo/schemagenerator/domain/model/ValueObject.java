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
 * バリューオブジェクトを表すインターフェイス。
 * 
 * <p>
 * バリューオブジェクトは原則的にImmutableであること。可変にせざるを得ない場合できる限り小さくすること。
 * このインターフェイスを実装するクラスでは、{@link Object#equals(Object)}は、
 * 属性のみでオブジェクトの等価判定を行うこと。
 * {@link Object#hashCode()}も一般契約に基づき実装すること。
 * </p>
 * 
 * @param <T> バリューオブジェクトの型
 * 
 * @author junichi
 */
public interface ValueObject<T extends ValueObject<T>> {
	
	/**
	 * 指定されたバリューオブジェクトとこのバリューオブジェクトの等価を判定する。
	 * 
	 * <p>{@link Object#equals}のタイプセーフ版</p>
	 * 
	 * @param other バリューオブジェクト
	 * @return 等価ならtrue
	 */
	boolean sameValueAs(T other);
	
}
