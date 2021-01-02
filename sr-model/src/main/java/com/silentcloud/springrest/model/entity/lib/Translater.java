package com.silentcloud.springrest.model.entity.lib;

import com.silentcloud.springrest.model.entity.AbstractAuditableEntity;
import com.silentcloud.springrest.model.enums.CountryCode;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.util.List;

@Entity
@Table(name = "lib_translater")
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class Translater extends AbstractAuditableEntity {

    @Column(nullable = false)
    private String name;

    private CountryCode countryCode;

    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @ManyToMany(mappedBy = "translaters")
    private List<Book> books;
}
