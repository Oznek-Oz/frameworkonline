package framework.groupe6.cd_backend.repository;

import framework.groupe6.cd_backend.entites.Devise;
import org.springframework.data.jpa.repository.JpaRepository;

public interface  DeviseRepository extends JpaRepository<Devise, Long> {
}
