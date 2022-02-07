package com.varchenko.inventory_service.entity;

import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Objects;

@Entity
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Table(name = "inventory")
public class Inventory {

    @Id
    @Column(name = "uniq_id")
    private String uniqId;

    @Column(name = "availability")
    private int availability;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Inventory that = (Inventory) o;
        return uniqId != null && Objects.equals(uniqId, that.uniqId);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
