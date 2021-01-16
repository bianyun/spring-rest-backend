package com.silentcloud.springrest.model.entity.lib;

import com.silentcloud.springrest.model.entity.AbstractAuditableEntity;
import com.silentcloud.springrest.model.enums.Country;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;

@Entity
@Table(name = "lib_publisher")
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class Publisher extends AbstractAuditableEntity {

    @Column(nullable = false)
    private String name;

    private String address;

    private String zipCode;

    private Country country;

    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @OneToMany(mappedBy = "publisher")
    private List<Book> books;
}
