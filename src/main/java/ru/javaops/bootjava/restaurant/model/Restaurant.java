package ru.javaops.bootjava.restaurant.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;
import ru.javaops.bootjava.common.model.BaseEntity;
import ru.javaops.bootjava.common.validation.NoHtml;

@Entity
@Table(name = "restaurant")
@Getter
@Setter
@NoArgsConstructor
public class Restaurant extends BaseEntity {
    @Column(name = "name", nullable = false)
    @NotBlank
    @Size(min = 2, max = 100)
    @NoHtml
    private String name;

    public Restaurant(Integer id, String name) {
        super(id);
        this.name = name;
    }
}

