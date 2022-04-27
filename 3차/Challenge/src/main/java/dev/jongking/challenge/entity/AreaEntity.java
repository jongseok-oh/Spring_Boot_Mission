package dev.jongking.challenge.entity;

import javax.persistence.*;

@Entity
@Table(name = "area")
public class AreaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "region1")
    private String regionFirst;

    @Column(name = "region2")
    private String regionSecond;

    @Column(name = "region3")
    private String regionThird;

    private Double latitude;
    private Double longitude;

    public AreaEntity() {
    }

    public AreaEntity(Long id, String regionFirst, String regionSecond, String regionThird, Double latitude, Double longitude) {
        this.id = id;
        this.regionFirst = regionFirst;
        this.regionSecond = regionSecond;
        this.regionThird = regionThird;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRegionFirst() {
        return regionFirst;
    }

    public void setRegionFirst(String regionFirst) {
        this.regionFirst = regionFirst;
    }

    public String getRegionSecond() {
        return regionSecond;
    }

    public void setRegionSecond(String regionSecond) {
        this.regionSecond = regionSecond;
    }

    public String getRegionThird() {
        return regionThird;
    }

    public void setRegionThird(String regionThird) {
        this.regionThird = regionThird;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }
}
