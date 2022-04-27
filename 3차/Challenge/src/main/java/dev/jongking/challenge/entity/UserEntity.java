package dev.jongking.challenge.entity;

import javax.persistence.*;

@Entity
@Table(name = "user_table")
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_id")
    private String writer;

    private String password;

    @ManyToOne(
            targetEntity = AreaEntity.class,
            fetch = FetchType.LAZY
    )
    @JoinColumn(name = "location_id")
    private AreaEntity address;

    @Column(name = "is_shop_owner")
    private Boolean isShopOwner;

    public UserEntity() {
    }

    public UserEntity(Long id, String writer, String password, AreaEntity address, Boolean isShopOwner) {
        this.id = id;
        this.writer = writer;
        this.password = password;
        this.address = address;
        this.isShopOwner = isShopOwner;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getWriter() {
        return writer;
    }

    public void setWriter(String writer) {
        this.writer = writer;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public AreaEntity getAddress() {
        return address;
    }

    public void setAddress(AreaEntity address) {
        this.address = address;
    }

    public Boolean getShopOwner() {
        return isShopOwner;
    }

    public void setShopOwner(Boolean shopOwner) {
        isShopOwner = shopOwner;
    }
}
