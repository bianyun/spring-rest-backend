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
@Table(name = "sys_menu")
public class Menu extends AbstractPersistable<Long> {

    @Column(unique = true, nullable = false)
    private String name;

    @Column(unique = true, nullable = false)
    private String value;

    @EqualsAndHashCode.Exclude
    @ManyToOne(fetch = FetchType.LAZY)
    private Menu parent;

    @EqualsAndHashCode.Exclude
    @OneToMany(mappedBy = "parent", cascade = CascadeType.ALL)
    private List<Menu> children = new ArrayList<>();

    @EqualsAndHashCode.Exclude
    @OneToMany(mappedBy = "parentMenu", cascade = CascadeType.REMOVE)
    private List<Button> buttons = new ArrayList<>();

    @EqualsAndHashCode.Exclude
    @ManyToMany(cascade = {CascadeType.MERGE})
    @JoinTable(name = "sys_menu_api_perm",
            joinColumns = @JoinColumn(name = "menu_id"),
            inverseJoinColumns = @JoinColumn(name = "api_perm_id"))
    private Set<ApiPerm> apiPerms = new HashSet<>();

    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @ManyToMany(mappedBy = "menus")
    private Set<Role> roles = new HashSet<>();

    // public void addChild(Menu child) {
    //     children.add(child);
    //     child.setParent(this);
    // }
    //
    // public void removeChild(Menu child) {
    //     children.remove(child);
    //     child.setParent(null);
    // }
    //
    // public void addButton(Button button) {
    //     buttons.add(button);
    //     button.setParentMenu(this);
    // }
    //
    // public void removeButton(Button button) {
    //     buttons.remove(button);
    //     button.setParentMenu(null);
    // }
    //
    // public void addApiPerm(ApiPerm apiPerm) {
    //     apiPerms.add(apiPerm);
    //     apiPerm.getMenus().add(this);
    // }
    //
    // public void removeApiPerm(ApiPerm apiPerm) {
    //     apiPerms.remove(apiPerm);
    //     apiPerm.getMenus().remove(this);
    // }
}
