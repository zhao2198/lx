
package com.lx.core.common.repository;

import java.util.List;
import java.util.Map;

public interface BaseRepository<T> {

	T get(Object id);

	int save(T t);

	int save(Map<String, Object> map);

	int saveBatch(List<T> list);

	int update(T t);

	int update(Map<String, Object> map);

	int delete(Object id);

	int delete(Map<String, Object> map);

	int deleteBatch(Object[] id);

	T queryObject(Object id);

	List<T> queryList(Map<String, Object> map);

	List<T> queryList(T t);

	List<T> queryAll();

}
