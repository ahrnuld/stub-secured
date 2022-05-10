package io.swagger.model.entity;

import lombok.*;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Data
public class Product {

    @Id
    @GeneratedValue
    private UUID id;

    private String name;

    @ManyToOne(cascade = CascadeType.ALL)
    private Manufacturer manufacturer;

}
