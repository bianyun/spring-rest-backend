package com.silentcloud.springrest.model.entity.lib;

import com.silentcloud.springrest.model.entity.AbstractAuditableEntity;
import com.silentcloud.springrest.model.enums.Country;
import com.silentcloud.springrest.model.enums.Gender;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.util.List;

@Entity
@Table(name = "lib_author")
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class Author extends AbstractAuditableEntity {

    @Column(nullable = false)
    private String name;

    private Gender gender;

    private Country country;

    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @ManyToMany(mappedBy = "authors")
    private List<Book> books;
}

