package com.github.matheusko.citiesapi.cities.entities;

import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import org.hibernate.annotations.TypeDefs;
import org.springframework.data.geo.Point;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity(name = "City")
@Table(name = "cidade")
@TypeDefs(value = {
        @TypeDef(name = "point", typeClass = PointType.class)
})
public class City {

    @Id
    private Long id;

    @Column(name = "nome")
    private String name;

    @Column(name = "uf")
    private int uf;

    @Column(name = "ibge")
    private Long ibge;

    // como string
//    @Column(name = "lat_lon")
//    private String geolocation;

    // como Point
    @Type(type = "point")
    @Column(name = "lat_lon", updatable = false, insertable = false)
    private Point geolocation;

    @Column(name = "cod_tom")
    private int cod_tom;

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getUf() {
        return uf;
    }

    public Point getGeolocation() {
        return geolocation;
    }

    public Long getIbge() {
        return ibge;
    }

    public int getCod_tom() {
        return cod_tom;
    }

    public City() {
    }
}
