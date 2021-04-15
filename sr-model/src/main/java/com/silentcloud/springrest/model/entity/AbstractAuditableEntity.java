package com.silentcloud.springrest.model.entity;

import com.silentcloud.springrest.model.entity.sys.User;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.AbstractPersistable;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.EntityListeners;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

/**
 * 可审计实体抽象父类
 *
 * @author bianyun
 */
@EntityListeners(AuditingEntityListener.class)
@MappedSuperclass
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public abstract class AbstractAuditableEntity extends AbstractPersistable<Long> {

    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @CreatedBy
    @ManyToOne
    private User createdBy;

    @CreatedDate
    private LocalDateTime createdTime;

    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @LastModifiedBy
    @ManyToOne
    private User lastModifiedBy;

    @LastModifiedDate
    private LocalDateTime lastModifiedTime;

    @Override
    public void setId(Long id) {
        super.setId(id);
    }
}
