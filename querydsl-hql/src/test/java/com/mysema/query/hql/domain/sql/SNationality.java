/*
 * Copyright (c) 2010 Mysema Ltd.
 * All rights reserved.
 *
 */
package com.mysema.query.hql.domain.sql;

import static com.mysema.query.types.path.PathMetadataFactory.forVariable;

import com.mysema.query.types.Expr;
import com.mysema.query.types.PathMetadata;
import com.mysema.query.types.custom.CSimple;
import com.mysema.query.types.path.BeanPath;
import com.mysema.query.types.path.EntityPathBase;
import com.mysema.query.types.path.PNumber;

/**
 * SNationality is a Querydsl query type for SNationality
 */
@SuppressWarnings("serial")
@com.mysema.query.sql.Table(value="NATIONALITY")
public class SNationality extends EntityPathBase<SNationality> {

    public final PNumber<Integer> calendarId = createNumber("CALENDAR_ID", Integer.class);

    public final PNumber<Long> id = createNumber("ID", Long.class);

    public SNationality(String variable) {
        super(SNationality.class, forVariable(variable));
    }

    public SNationality(BeanPath<? extends SNationality> entity) {
        super(entity.getType(),entity.getMetadata());
    }

    public SNationality(PathMetadata<?> metadata) {
        super(SNationality.class, metadata);
    }

    public Expr<Object[]> all() {
        return CSimple.create(Object[].class, "{0}.*", this);
    }

}

