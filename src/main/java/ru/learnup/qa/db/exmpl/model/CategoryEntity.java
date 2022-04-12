package ru.learnup.qa.db.exmpl.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

/**
 * Description
 *
 * @author bse71
 * Created on 07.04.2022
 * @since
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "categories")
public class CategoryEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "categories_id_seq")
    @SequenceGenerator(name = "categories_id_seq", sequenceName = "categories_id_seq", allocationSize = 1)
    private Integer id;

    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "category", fetch = FetchType.EAGER)
    private List<GoodEntity> goods;

    public String toString() {
        return name;
    }

}
