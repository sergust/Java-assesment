public class SoftwareUpdate extends Event{
    private String softwareUpdateId;
    private String softwareUpdateStatus;

    public SoftwareUpdate(String time, String machineName, String eventType, String softwareUpdateId, String softwareUpdateStatus) {
        super(time, machineName, eventType);
        this.softwareUpdateId = softwareUpdateId;
        this.softwareUpdateStatus = softwareUpdateStatus;
    }

    public String getSoftwareUpdateId() {
        return softwareUpdateId;
    }

    public String getSoftwareUpdateStatus() {
        return softwareUpdateStatus;
    }
}
