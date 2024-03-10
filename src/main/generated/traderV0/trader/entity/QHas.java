package traderV0.trader.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QHas is a Querydsl query type for Has
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QHas extends EntityPathBase<Has> {

    private static final long serialVersionUID = -1015292279L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QHas has = new QHas("has");

    public final QCompany company;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final NumberPath<Integer> price = createNumber("price", Integer.class);

    public final NumberPath<Integer> quantity = createNumber("quantity", Integer.class);

    public final QUserInfo userInfo;

    public QHas(String variable) {
        this(Has.class, forVariable(variable), INITS);
    }

    public QHas(Path<? extends Has> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QHas(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QHas(PathMetadata metadata, PathInits inits) {
        this(Has.class, metadata, inits);
    }

    public QHas(Class<? extends Has> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.company = inits.isInitialized("company") ? new QCompany(forProperty("company")) : null;
        this.userInfo = inits.isInitialized("userInfo") ? new QUserInfo(forProperty("userInfo")) : null;
    }

}

