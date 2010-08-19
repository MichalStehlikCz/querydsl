/*
 * Copyright (c) 2010 Mysema Ltd.
 * All rights reserved.
 *
 */
package com.mysema.query;

import static org.junit.Assert.assertEquals;

import java.sql.SQLException;

import javax.annotation.Nullable;

import org.junit.AfterClass;
import org.junit.Rule;
import org.junit.rules.MethodRule;
import org.junit.runner.RunWith;

import com.mysema.query.sql.SQLQuery;
import com.mysema.query.sql.SQLQueryImpl;
import com.mysema.query.sql.SQLSubQuery;
import com.mysema.query.sql.SQLTemplates;
import com.mysema.query.sql.dml.SQLDeleteClause;
import com.mysema.query.sql.dml.SQLInsertClause;
import com.mysema.query.sql.dml.SQLMergeClause;
import com.mysema.query.sql.dml.SQLUpdateClause;
import com.mysema.query.types.EntityPath;
import com.mysema.testutil.FilteringTestRunner;
import com.mysema.testutil.LabelRule;

@RunWith(FilteringTestRunner.class)
public abstract class AbstractBaseTest {

    @AfterClass
    public static void tearDownAfterClass() throws SQLException {
        Connections.close();
    }

    protected SQLTemplates templates;

    @Nullable
    protected String expectedQuery;
    
    @Rule
    public static MethodRule labelRule = new LabelRule();
    
    protected SQLUpdateClause update(EntityPath<?> e){
        return new SQLUpdateClause(Connections.getConnection(), templates, e);
    }

    protected SQLInsertClause insert(EntityPath<?> e){
        return new SQLInsertClause(Connections.getConnection(), templates, e);
    }

    protected SQLDeleteClause delete(EntityPath<?> e){
        return new SQLDeleteClause(Connections.getConnection(), templates, e);
    }

    protected SQLMergeClause merge(EntityPath<?> e){
        return new SQLMergeClause(Connections.getConnection(), templates, e);
    }

    protected SQLQuery query() {
        return new SQLQueryImpl(Connections.getConnection(), templates) {
            @Override
            protected String buildQueryString(boolean countRow) {
                String rv = super.buildQueryString(countRow);
                if (expectedQuery != null) {
                    assertEquals(expectedQuery, rv.replace('\n', ' '));
                    expectedQuery = null;
                }
                System.out.println(rv);
                return rv;
            }
        };
    }

    protected SQLSubQuery sq(){
        return new SQLSubQuery();
    }

}
