package com.dshop.backend.models;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "color")
public class Color {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;

    @Column
    private String value;

    @ManyToOne()
    @JoinColumn(name = "product_id")
    private Product product;

    @OneToMany(mappedBy = "color", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<Size> sizes = new ArrayList<>();
}