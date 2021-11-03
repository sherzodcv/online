package my.com.repository;

import my.com.entity.Laptop;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface LaptopRepository extends JpaRepository<Laptop, UUID> {

    List<Laptop> findAllByHddContainingOrRamContainingOrProtsessorContainingOrPrSpeedContainingOrScreenContainingOrModelContainingOrPriceContainingOrColorContaining(String search, String search1, String search2, String search3, String search4, String search5, String search6, String search7);
}
