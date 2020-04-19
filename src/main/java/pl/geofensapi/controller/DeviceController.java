package pl.geofensapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import pl.geofensapi.entity.Device;
import pl.geofensapi.entity.request.AddDeviceRequest;
import pl.geofensapi.repository.DeviceRepository;

import java.util.List;

@RestController
public class DeviceController {

    private DeviceRepository deviceRepository;

    @Autowired
    public DeviceController(DeviceRepository deviceRepository) {
        this.deviceRepository = deviceRepository;
    }

    @RequestMapping(method = RequestMethod.GET)
    public List<Device> findAllDevice() {
        return deviceRepository.findAll();
    }

    @RequestMapping(method = RequestMethod.POST)
    public void addDevice(@RequestBody AddDeviceRequest addDeviceRequest){
        Device device = new Device();
        device.setName(addDeviceRequest.getName());
        deviceRepository.save(device);
    }

}
