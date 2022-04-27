package dev.jongking.challenge.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "shop")
public class ShopEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(
            targetEntity = AreaEntity.class,
            fetch = FetchType.LAZY
    )
    @JoinColumn(name = "shop_area_id")
    private AreaEntity shopLocation;

    @OneToOne(
            targetEntity = UserEntity.class,
            fetch = FetchType.LAZY
    )
    @JoinColumn(name = "owner_id")
    private UserEntity shopOwner;

    @OneToMany(
            targetEntity = ShopPostEntity.class,
            fetch = FetchType.LAZY
    )
    private List<ShopPostEntity> shopPostList;

    @OneToMany(
            targetEntity = ShopPostReviewEntity.class,
            fetch = FetchType.LAZY
    )
    private List<ShopPostReviewEntity> shopPostReviewList;


    public ShopEntity() {
    }

    public ShopEntity(Long id, AreaEntity shopLocation, UserEntity shopOwner) {
        this.id = id;
        this.shopLocation = shopLocation;
        this.shopOwner = shopOwner;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public AreaEntity getShopLocation() {
        return shopLocation;
    }

    public void setShopLocation(AreaEntity shopLocation) {
        this.shopLocation = shopLocation;
    }

    public UserEntity getShopOwner() {
        return shopOwner;
    }

    public void setShopOwner(UserEntity shopOwner) {
        this.shopOwner = shopOwner;
    }

    public List<ShopPostEntity> getShopPostList() {
        return shopPostList;
    }

    public void setShopPostList(List<ShopPostEntity> shopPostList) {
        this.shopPostList = shopPostList;
    }

    public List<ShopPostReviewEntity> getShopPostReviewList() {
        return shopPostReviewList;
    }

    public void setShopPostReviewList(List<ShopPostReviewEntity> shopPostReviewList) {
        this.shopPostReviewList = shopPostReviewList;
    }
}
