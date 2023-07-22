package com.demo.component.commune.entity;

import com.demo.component.district.entity.DistrictDto;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Data
@Getter
@Setter
public class CommuneDto {
    private String code;
    private String name;
    private Integer foundedYear;
    private Integer acreage;
    private Integer numberOfPeople;
    private String idDistrict;
    private String idProvince;
}
