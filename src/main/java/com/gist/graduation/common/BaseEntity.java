package com.gist.graduation.common;


import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;

@MappedSuperclass
@Getter
@EntityListeners(AuditingEntityListener.class)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public abstract class BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @CreatedDate
    @Column(name = "created_at",
            columnDefinition = "timestamp null default null")
    private LocalDateTime createdAt;

    @LastModifiedDate
    @Column(name = "updated_at",
            columnDefinition = "timestamp null default null")
    private LocalDateTime updatedAt;

    @Getter(value = AccessLevel.PROTECTED)
    @Column(name = "deleted_at",
            columnDefinition = "timestamp null default null")
    private LocalDateTime deletedAt;

    protected void softDelete(LocalDateTime deletedAt) {
        this.deletedAt = deletedAt;
    }

    protected boolean isSoftDeleted() {
        return null != deletedAt;
    }

    public void delete() {
        softDelete(LocalDateTime.now());
    }

    public boolean isDeleted() {
        return isSoftDeleted();
    }

    protected void undoDelete() {
        this.deletedAt = null;
    }

    public void clearData() {
        delete();
    }


}
