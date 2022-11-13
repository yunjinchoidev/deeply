package com.server.deeply.common;

import com.querydsl.core.dml.DeleteClause;
import com.querydsl.core.dml.UpdateClause;
import com.querydsl.core.types.EntityPath;
import com.querydsl.jpa.impl.JPADeleteClause;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAUpdateClause;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;

public abstract class AbstractRepositoryImpl {
    @Autowired
    protected EntityManager em;

    protected <T> JPAQuery<T> query() {
        return new JPAQuery<>(em);
    }

    protected UpdateClause<JPAUpdateClause> update(EntityPath<?> path) {
        return new JPAUpdateClause(em, path);
    }

    protected DeleteClause<JPADeleteClause> delete(EntityPath<?> path) {
        return new JPADeleteClause(em, path);
    }
}