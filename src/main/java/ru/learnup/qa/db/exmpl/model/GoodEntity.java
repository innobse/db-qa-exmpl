package ru.learnup.qa.db.exmpl.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

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
@Table(name = "goods")
@NamedQueries({
        @NamedQuery(name = "good_deleteById", query = "delete from GoodEntity good where good.id = :id")
})
public class GoodEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "goods_id_seq")
    @SequenceGenerator(name = "goods_id_seq", sequenceName = "goods_id_seq", allocationSize = 1)
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column
    private String description;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private CategoryEntity category;

    public GoodEntity(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public String toString() {
        return String.format("%s (%d -> %s)\n%s\n", name, id, category, description);
    }

}
