package pl.geofensapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.geofensapi.entity.Device;

@Repository
public interface DeviceRepository extends JpaRepository<Device, Long> {



}
