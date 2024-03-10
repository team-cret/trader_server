package traderV0.trader.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QUserInfo is a Querydsl query type for UserInfo
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QUserInfo extends EntityPathBase<UserInfo> {

    private static final long serialVersionUID = -601114486L;

    public static final QUserInfo userInfo = new QUserInfo("userInfo");

    public final StringPath httpSession = createString("httpSession");

    public final StringPath id = createString("id");

    public final NumberPath<Integer> money = createNumber("money", Integer.class);

    public final StringPath name = createString("name");

    public final StringPath password = createString("password");

    public final DateTimePath<java.time.LocalDateTime> sessionDate = createDateTime("sessionDate", java.time.LocalDateTime.class);

    public final EnumPath<traderV0.trader.constant.UserState> state = createEnum("state", traderV0.trader.constant.UserState.class);

    public final StringPath webSession = createString("webSession");

    public QUserInfo(String variable) {
        super(UserInfo.class, forVariable(variable));
    }

    public QUserInfo(Path<? extends UserInfo> path) {
        super(path.getType(), path.getMetadata());
    }

    public QUserInfo(PathMetadata metadata) {
        super(UserInfo.class, metadata);
    }

}

