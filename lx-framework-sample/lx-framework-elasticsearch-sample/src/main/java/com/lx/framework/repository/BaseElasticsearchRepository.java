package com.lx.framework.repository;


import org.elasticsearch.index.query.QueryBuilder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.core.query.SearchQuery;
import org.springframework.data.elasticsearch.repository.ElasticsearchCrudRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.io.Serializable;
import java.util.Iterator;

@NoRepositoryBean
public interface BaseElasticsearchRepository<T,ID extends Serializable> extends ElasticsearchCrudRepository<T,ID> {

    <E extends T> E index(E entity);

    Iterator<T> search(QueryBuilder query);

    Page<T> search(QueryBuilder query, Pageable pageable);

    Page<T> search(SearchQuery query);

    Page<T> searchSimilar(T entity, String [] fields, Pageable pageable);

    void refresh();

    Class<T> getEntityClass();
}
