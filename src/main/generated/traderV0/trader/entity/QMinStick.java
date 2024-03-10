package traderV0.trader.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QMinStick is a Querydsl query type for MinStick
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QMinStick extends EntityPathBase<MinStick> {

    private static final long serialVersionUID = -1713488177L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QMinStick minStick = new QMinStick("minStick");

    public final QCompany company;

    public final NumberPath<Integer> endPrice = createNumber("endPrice", Integer.class);

    public final NumberPath<Integer> highPrice = createNumber("highPrice", Integer.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final NumberPath<Integer> lowPrice = createNumber("lowPrice", Integer.class);

    public final DateTimePath<java.time.LocalDateTime> minStickDate = createDateTime("minStickDate", java.time.LocalDateTime.class);

    public final NumberPath<Integer> startPrice = createNumber("startPrice", Integer.class);

    public QMinStick(String variable) {
        this(MinStick.class, forVariable(variable), INITS);
    }

    public QMinStick(Path<? extends MinStick> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QMinStick(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QMinStick(PathMetadata metadata, PathInits inits) {
        this(MinStick.class, metadata, inits);
    }

    public QMinStick(Class<? extends MinStick> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.company = inits.isInitialized("company") ? new QCompany(forProperty("company")) : null;
    }

}

