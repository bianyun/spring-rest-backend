package com.silentcloud.springrest.model.entity.sys;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.springframework.data.jpa.domain.AbstractPersistable;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Table(name = "sys_api_perm")
public class ApiPerm extends AbstractPersistable<Long> {

    @Column(unique = true, nullable = false)
    private String name;

    @Column(unique = true, nullable = false)
    private String value;

    @EqualsAndHashCode.Exclude
    @ManyToOne(fetch = FetchType.LAZY)
    private ApiPerm parent;

    @EqualsAndHashCode.Exclude
    @OneToMany(mappedBy = "parent", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private List<ApiPerm> children = new ArrayList<>();

    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @ManyToMany(mappedBy = "apiPerms")
    private Set<Menu> menus = new HashSet<>();

    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @ManyToMany(mappedBy = "apiPerms")
    private Set<Button> buttons = new HashSet<>();

    public void addChild(ApiPerm child) {
        children.add(child);
        child.setParent(this);
    }

    public void removeChild(ApiPerm child) {
        children.remove(child);
        child.setParent(null);
    }
}
