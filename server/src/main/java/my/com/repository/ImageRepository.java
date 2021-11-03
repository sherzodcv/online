package my.com.repository;

import my.com.entity.Images;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface ImageRepository extends JpaRepository<Images, UUID> {


    boolean deleteByHashId(String hashId);

    Optional<Images> findByHashId(String hashId);
}
