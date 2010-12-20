/*
 * Copyright 2010 TRICREO, Inc. (http://tricreo.jp/)
 * Copyright 2007-2010 Jiemamy Project and the Others.
 * Created on 2010/12/14
 *
 * This file is part of Jiemamy.
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

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Map.Entry;

import jp.tricreo.schemagenerator.domain.model.Entity;
import jp.tricreo.schemagenerator.domain.model.ValueObject;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;

/**
 * {@link Entity}や{@link ValueObject}の集合を複製するユーティリティクラス。
 * 
 * @version $Id$
 * @author daisuke
 * @author j5ik2o
 */
public final class CloneUtil {
	
	/**
	 * 指定した {@link Collection} の要素を全て{@link Object#clone() クローン}し、
	 * それらを要素とする新しい {@link ArrayList} を返す。
	 * 
	 * @param <E> 要素の型
	 * @param collection 元となる集合
	 * @return {@link ArrayList}
	 */
	public static <E extends Entity<E, ?>>ArrayList<E> cloneEntityArrayList(
			Collection<E> collection) {
		ArrayList<E> cloneCollection =
				Lists.newArrayListWithExpectedSize(collection.size());
		for (E element : collection) {
			// Entity(可変オブジェクト)はcloneする。
			E cloneElement = element.clone();
			cloneCollection.add(cloneElement);
		}
		return cloneCollection;
	}
	
	/**
	 * 指定した {@link Map} の値({@link Map#values()})を全て{@link Object#clone() クローン}し、
	 * それらを値とする新しい {@link HashMap} を返す。
	 * 
	 * @param <K> キーの型
	 * @param <V> 値の型
	 * @param map 元となる写像
	 * @return {@link HashMap}
	 */
	public static <K, V extends Entity<V, ?>>HashMap<K, V> cloneEntityHashMap(
			Map<K, V> map) {
		HashMap<K, V> cloneMap = Maps.newHashMapWithExpectedSize(map.size());
		for (Entry<K, V> element : map.entrySet()) {
			V cloneValue = element.getValue().clone();
			cloneMap.put(element.getKey(), cloneValue);
		}
		return cloneMap;
	}
	
	/**
	 * 指定した {@link Collection} の要素を全て{@link Object#clone() クローン}し、
	 * それらを要素とする新しい {@link HashSet} を返す。
	 * 
	 * @param <E> 要素の型
	 * @param collection 元となる集合
	 * @return {@link HashSet}
	 */
	public static <E extends Entity<E, ?>>HashSet<E> cloneEntityHashSet(
			Collection<E> collection) {
		HashSet<E> cloneCollection =
				Sets.newHashSetWithExpectedSize(collection.size());
		for (E element : collection) {
			E cloneElement = element.clone();
			cloneCollection.add(cloneElement);
		}
		return cloneCollection;
	}
	
	/**
	 * 指定した {@link Collection} の要素を全て{@link Object#clone() クローン}し、
	 * それらを要素とする新しい {@link LinkedHashSet} を返す。
	 * 
	 * @param <E> 要素の型
	 * @param collection 元となる集合
	 * @return {@link LinkedHashSet}
	 */
	public static <E extends Entity<E, ?>>LinkedHashSet<E>
			cloneEntityLinkedHashSet(Collection<E> collection) {
		LinkedHashSet<E> cloneCollection = Sets.newLinkedHashSet();
		for (E element : collection) {
			E cloneElement = element.clone();
			cloneCollection.add(cloneElement);
		}
		return cloneCollection;
	}
	
	/**
	 * 指定した {@link Collection} の要素を要素とする新しい {@link ArrayList} を返す。
	 * 
	 * @param <E> 要素の型
	 * @param collection 元となる集合
	 * @return {@link HashSet}
	 */
	public static <E>ArrayList<E> cloneValueArrayList(Collection<E> collection) {
		// VO(不変オブジェクト)はcloneしない。
		return Lists.newArrayList(collection);
	}
	
	/**
	 * 指定した {@link Map} の要素を要素とする新しい {@link HashMap} を返す。
	 * 
	 * @param <K> キーの型
	 * @param <V> 値の型
	 * @param map 元となる写像
	 * @return {@link HashMap}
	 */
	public static <K, V>HashMap<K, V> cloneValueHashMap(Map<K, V> map) {
		return Maps.newHashMap(map);
	}
	
	/**
	 * 指定した {@link Collection} の要素を要素とする新しい {@link HashSet} を返す。
	 * 
	 * @param <E> 要素の型
	 * @param collection 元となる集合
	 * @return {@link HashSet}
	 */
	public static <E>HashSet<E> cloneValueHashSet(Collection<E> collection) {
		return Sets.newHashSet(collection);
	}
	
	/**
	 * 指定した {@link Collection} の要素を要素とする新しい {@link LinkedHashSet} を返す。
	 * 
	 * @param <E> 要素の型
	 * @param collection 元となる集合
	 * @return {@link HashSet}
	 */
	public static <E>LinkedHashSet<E> cloneValueLinkedHashSet(
			Collection<E> collection) {
		return Sets.newLinkedHashSet(collection);
	}
	
	private CloneUtil() {
	}
}
