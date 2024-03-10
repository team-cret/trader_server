package traderV0.trader.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QCompany is a Querydsl query type for Company
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QCompany extends EntityPathBase<Company> {

    private static final long serialVersionUID = -584320788L;

    public static final QCompany company = new QCompany("company");

    public final NumberPath<Integer> beforePrice = createNumber("beforePrice", Integer.class);

    public final NumberPath<Integer> endPrice = createNumber("endPrice", Integer.class);

    public final NumberPath<Integer> highPrice = createNumber("highPrice", Integer.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final NumberPath<Integer> lowPrice = createNumber("lowPrice", Integer.class);

    public final StringPath name = createString("name");

    public final NumberPath<Double> priceVariance = createNumber("priceVariance", Double.class);

    public final NumberPath<Integer> startPrice = createNumber("startPrice", Integer.class);

    public final NumberPath<Integer> stockPrice = createNumber("stockPrice", Integer.class);

    public QCompany(String variable) {
        super(Company.class, forVariable(variable));
    }

    public QCompany(Path<? extends Company> path) {
        super(path.getType(), path.getMetadata());
    }

    public QCompany(PathMetadata metadata) {
        super(Company.class, metadata);
    }

}

