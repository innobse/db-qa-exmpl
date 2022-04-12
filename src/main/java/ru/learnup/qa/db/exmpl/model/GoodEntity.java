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
public class GoodEntity {

    private Integer id;
    private String name;
    private String description;

    public GoodEntity(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public String toString() {
        return String.format("%s (%d)\n%s\n", name, id, description);
    }

}
