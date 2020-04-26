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
import org.springframework.web.servlet.tags.Param;
import pl.geofensapi.entity.Device;
import pl.geofensapi.entity.Parameters;
import pl.geofensapi.entity.request.AddDeviceRequest;
import pl.geofensapi.entity.request.AddParametersRepository;
import pl.geofensapi.repository.DeviceRepository;
import pl.geofensapi.repository.ParametersRepository;
import pl.geofensapi.security.UniqueTextIdentifier;

import java.util.List;
import java.util.Optional;


@RestController

public class DeviceController {

    @Autowired
    private DeviceRepository deviceRepository;
    private ParametersRepository parametersRepository;

    @Autowired
    public DeviceController(DeviceRepository deviceRepository, ParametersRepository parametersRepository) {

        this.deviceRepository = deviceRepository;
        this.parametersRepository = parametersRepository;
    }

    @RequestMapping(value = "/list", method= RequestMethod.GET)
    @ApiOperation(value = "Device list")
    public List<Device> findAllDevice() {
        return deviceRepository.findAll();
    }

    @PostMapping("/device")
    @ApiOperation(value = "Add a device")
    public void addDevices(@RequestBody AddDeviceRequest addDeviceRequest){
        Device device = new Device();
        device.setName(addDeviceRequest.getName());
        device.setToken(UniqueTextIdentifier.getUniqueId());
        device.setPositionIntervalInMinutes(addDeviceRequest.getPositionIntervalInMinutes());
        device.setTrackedObjectId(addDeviceRequest.getTrackedObjectId());
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


    @ApiOperation(value = "Get device")
    @RequestMapping(value="/device/{id}", method = RequestMethod.GET)
    public Device getDevice(@PathVariable(value = "id") Long id){
        Device device = new Device();
        Optional<Device> opti = deviceRepository.findById(id);
        device = deviceRepository.findByIdOrderById(id);
        device.setParameters(null);
        return device;
    }


    @ApiOperation(value = "Add parameters")
    @PostMapping("/addParameters/{id}")
    public boolean addParameters(@RequestBody AddParametersRepository addParametersRepository, @RequestHeader("Bearer") String token, @PathVariable(value = "id") Long id){


        Device device = new Device();
        device = deviceRepository.getOne(id);
        String deviceToken = device.getToken();

        if (device.getToken().equals(token)){
            Parameters parameters = new Parameters();
            parameters.setAcc(addParametersRepository.getAcc());
            parameters.setAlt(addParametersRepository.getAlt());
            parameters.setBea(addParametersRepository.getBea());
            parameters.setLat(addParametersRepository.getLat());
            parameters.setLongitude(addParametersRepository.getLongitude());
            parameters.setProv(addParametersRepository.getProv());
            parameters.setSpd(addParametersRepository.getSpd());
            parameters.setSat(addParametersRepository.getSat());
            parameters.setTime(addParametersRepository.getTime());
            parameters.setSerial(addParametersRepository.getTid());
            parameters.setTid(addParametersRepository.getTid());
            parameters.setPlat(addParametersRepository.getPlat());
            parameters.setPlatVer(addParametersRepository.getPlatVer());
            parameters.setBat(addParametersRepository.getBat());
            parameters.setDevice(device);
            parametersRepository.save(parameters);
            return true;
        }
        return false;
    }

    @ApiOperation(value = "Get device with parameters")
    @RequestMapping(value="/deviceParameters/{id}", method = RequestMethod.GET)
    public Device getDeviceParameters(@PathVariable(value = "id") Long id, @RequestHeader("Bearer") String token){

        Device device = new Device();
        Optional<Device> opti = deviceRepository.findById(id);
        device = deviceRepository.findByIdOrderById(id);
        String deviceToken = device.getToken();
        if (device.getToken().equals(token)){
            return device;
        } else
            return null;
    }
}
