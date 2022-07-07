package com.daniel.sipos.demoproject.entities;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QUserTable is a Querydsl query type for UserTable
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QUserTable extends EntityPathBase<UserTable> {

    private static final long serialVersionUID = 190637952L;

    public static final QUserTable userTable = new QUserTable("userTable");

    public final StringPath emailAddress = createString("emailAddress");

    public final StringPath fullName = createString("fullName");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public QUserTable(String variable) {
        super(UserTable.class, forVariable(variable));
    }

    public QUserTable(Path<? extends UserTable> path) {
        super(path.getType(), path.getMetadata());
    }

    public QUserTable(PathMetadata metadata) {
        super(UserTable.class, metadata);
    }

}

