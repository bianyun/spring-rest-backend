package com.silentcloud.springrest.model.entity.lib;

import com.silentcloud.springrest.model.entity.AbstractAuditableEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "lib_book")
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class Book extends AbstractAuditableEntity {

    @Column(nullable = false, unique = true)
    private String isbn;

    @Column(nullable = false)
    private String title;

    private Long wordCount;

//    private MonetaryAmount unitPrice;

    private Long unitPrice;

    private String translaters;

    private LocalDate publishDate;

    @ManyToOne(optional = false)
    private Publisher publisher;

    @EqualsAndHashCode.Exclude
    @ManyToMany(cascade = {CascadeType.MERGE, CascadeType.REFRESH})
    @JoinTable(name = "lib_book_author",
            joinColumns = @JoinColumn(name = "book_id"),
            inverseJoinColumns = @JoinColumn(name = "author_id"))
    private List<Author> authors;

}
