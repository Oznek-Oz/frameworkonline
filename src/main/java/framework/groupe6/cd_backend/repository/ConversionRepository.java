package framework.groupe6.cd_backend.repository;

import framework.groupe6.cd_backend.entites.Conversion;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ConversionRepository  extends JpaRepository<Conversion, Long> {
    List<Conversion> findTop5ByOrderByDateConversionAsc();
    List<Conversion> findByUserID(String userID);
}
