package pl.geofensapi.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;
import java.util.List;

@Entity
@ApiModel(description = "Device")
public class Device {

    @Id
    @GeneratedValue
    private long id;
    private String name;
    private String token;
    private String trackedObjectId;
    private Long positionIntervalInMinutes;

    @OneToMany(mappedBy = "device", fetch = FetchType.EAGER)
    @JsonManagedReference
    private List<Parameters> parameters;


    @ApiModelProperty(notes = "The database generated device ID")
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getTrackedObjectId() {
        return trackedObjectId;
    }

    public void setTrackedObjectId(String trackedObjectId) {
        this.trackedObjectId = trackedObjectId;
    }

    public Long getPositionIntervalInMinutes() {
        return positionIntervalInMinutes;
    }

    public void setPositionIntervalInMinutes(Long positionIntervalInMinutes) {
        this.positionIntervalInMinutes = positionIntervalInMinutes;
    }

    public List<Parameters> getParameters() {
        return parameters;
    }

    public void setParameters(List<Parameters> parameters) {
        this.parameters = parameters;
    }
}
