package vn.elca.training.dom;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;


/**
 * QTaskAudit is a Querydsl query type for TaskAudit
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QTaskAudit extends EntityPathBase<TaskAudit> {

    private static final long serialVersionUID = -591107063L;

    public static final QTaskAudit taskAudit = new QTaskAudit("taskAudit");

    public final EnumPath<TaskAudit.AuditType> auditType = createEnum("auditType", TaskAudit.AuditType.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath message = createString("message");

    public final NumberPath<Long> projectId = createNumber("projectId", Long.class);

    public final EnumPath<TaskAudit.Status> status = createEnum("status", TaskAudit.Status.class);

    public final DateTimePath<java.util.Date> taskDeadline = createDateTime("taskDeadline", java.util.Date.class);

    public final StringPath taskName = createString("taskName");

    public QTaskAudit(String variable) {
        super(TaskAudit.class, forVariable(variable));
    }

    public QTaskAudit(Path<? extends TaskAudit> path) {
        super(path.getType(), path.getMetadata());
    }

    public QTaskAudit(PathMetadata<?> metadata) {
        super(TaskAudit.class, metadata);
    }

}

