package com.daniel.sipos.demoproject.entities;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QMessage is a Querydsl query type for Message
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QMessage extends EntityPathBase<Message> {

    private static final long serialVersionUID = -390468604L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QMessage message1 = new QMessage("message1");

    public final QUserTable fromUserTable;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final DateTimePath<java.time.LocalDateTime> insertionDateTime = createDateTime("insertionDateTime", java.time.LocalDateTime.class);

    public final StringPath message = createString("message");

    public final QUserTable toUserTable;

    public QMessage(String variable) {
        this(Message.class, forVariable(variable), INITS);
    }

    public QMessage(Path<? extends Message> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QMessage(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QMessage(PathMetadata metadata, PathInits inits) {
        this(Message.class, metadata, inits);
    }

    public QMessage(Class<? extends Message> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.fromUserTable = inits.isInitialized("fromUserTable") ? new QUserTable(forProperty("fromUserTable")) : null;
        this.toUserTable = inits.isInitialized("toUserTable") ? new QUserTable(forProperty("toUserTable")) : null;
    }

}

