package dev.aquashdw.community.repository;

import dev.aquashdw.community.entity.AreaEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface AreaRepository extends CrudRepository<AreaEntity, Long> {
//    @Query("select a from AreaEntity a"
//    + "order by"
//    +"abs(a.latitude - :latitude)"
//    + "+abs(a.longitude - :longitude)")
    @Query(nativeQuery = true,
    value = "select * from area a order by"
    +" abs(a.latitude - :latitude) + abs(a.longitude - :longitude)")
    List<AreaEntity> getNearLocation(Double latitude, Double longitude);
}
