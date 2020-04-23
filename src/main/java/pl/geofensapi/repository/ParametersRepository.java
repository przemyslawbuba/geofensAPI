package pl.geofensapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.geofensapi.entity.Device;
import pl.geofensapi.entity.Parameters;

@Repository
public interface ParametersRepository  extends JpaRepository<Parameters, Long> {

}
