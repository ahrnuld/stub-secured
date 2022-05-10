package io.swagger.model.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Manufacturer {

    @Id
    @GeneratedValue
    private UUID id;
    private String name;
    private String homePage;
    private String phone;

    @OneToMany(mappedBy = "manufacturer")
    private List<Product> products = new ArrayList<>();
}
