package pl.geofensapi.controller;


import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.web.bind.annotation.*;
import pl.geofensapi.entity.Device;
import pl.geofensapi.entity.request.AddDeviceRequest;
import pl.geofensapi.repository.DeviceRepository;

import java.util.List;


@RestController

public class DeviceController {

    @Autowired
    private DeviceRepository deviceRepository;

    @Autowired
    public DeviceController(DeviceRepository deviceRepository) {
        this.deviceRepository = deviceRepository;
    }

    @RequestMapping(value = "/list", method= RequestMethod.GET)
    @ApiOperation(value = "Device list")
    public List<Device> findAllDevice() {
        return deviceRepository.findAll();
    }

    @PostMapping("/device")
    @ApiOperation(value = "Add a device")
    public void addDevice(@RequestBody AddDeviceRequest addDeviceRequest){
        Device device = new Device();
        device.setName(addDeviceRequest.getName());
        deviceRepository.save(device);
    }

    @ApiOperation(value = "Delete a device")
    @RequestMapping(value="/delete/{id}", method = RequestMethod.DELETE, produces = "application/json")
    public ResponseEntity delete(@PathVariable(value = "id") Long id){
        Device device = new Device();
        device = deviceRepository.getOne(id);
        deviceRepository.delete(device);
        return new ResponseEntity("Product deleted successfully", HttpStatus.OK);
    }


}
