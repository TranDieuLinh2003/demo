package com.demo.component.province.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
@AllArgsConstructor
@Table(name = "province")
public class Province implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    //mã tỉnh
    @Column(name = "code")
    private String code;

    //tên
    @Column(name = "name")
    private String name;

    //năm thành lập
    @Column(name = "founded_year")
    private Integer foundedYear;

    //diện tích
    @Column(name = "acreage")
    private Integer acreage;

    //dân số
    @Column(name = "number_of_people")
    private Integer numberOfPeople;

//    @OneToMany()
//    @JoinColumn(name = "id_district")
//    private Set<District> district;
}
