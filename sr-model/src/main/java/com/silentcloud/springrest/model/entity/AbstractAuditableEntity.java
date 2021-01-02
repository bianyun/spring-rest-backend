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

import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

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

    public void setId(Long id) {
        super.setId(id);
    }
}
