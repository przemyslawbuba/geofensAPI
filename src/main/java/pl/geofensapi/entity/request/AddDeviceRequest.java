package pl.geofensapi.entity.request;

public class AddDeviceRequest {

    private String name;
    private String trackedObjectId;
    private Long positionIntervalInMinutes;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
}
