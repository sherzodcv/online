package my.com.repository;

import my.com.entity.Printer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface PrinterRepository extends JpaRepository<Printer, UUID> {
}
