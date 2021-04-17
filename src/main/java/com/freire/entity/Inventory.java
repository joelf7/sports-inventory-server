package com.freire.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@NamedQuery(name = "Inventory.findAll", query = "SELECT i FROM Inventory i")
@NamedQuery(name = "Inventory.getByName", query = "SELECT i from Inventory i where i.prodName = :prodName")
@NamedQuery(name = "Inventory.clearAll", query = "DELETE FROM Inventory")
public class Inventory implements Comparable<Inventory>, Serializable {

    @Id
    @GeneratedValue
    private Long id;
    @Column(unique = true)
    private String prodName;
    private String sport;
    private int quantity;
    private Date latestDate;

    @ManyToOne
    @JoinColumn(name = "id_store")
    private Store store;

    @PrePersist
    void createdAt() { this.latestDate = new Date(); }

    @PreUpdate
    void updatedAt() { this.latestDate = new Date(); }

    public Inventory(String prodName, String sport, int quantity) {
        this.prodName = prodName;
        this.sport = sport;
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "Inventory{" +
                "prodName='" + prodName + '\'' +
                ", sport='" + sport + '\'' +
                ", qty='" + quantity + '\'' +
                ", id='" + id + '\'' +
                '}';
    }

    @Override
    public int compareTo(Inventory o) {
        return latestDate.compareTo(o.latestDate);
    }

}
