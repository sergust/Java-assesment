import java.sql.Time;

public class Event {
    private String eventTime;
    private String machineName;
    private String eventType;

    public Event(String time, String machineName, String eventType) {
        this.eventTime = time;
        this.machineName = machineName;
        this.eventType = eventType;
    }

    public String getEventTime() {
        return eventTime;
    }

    public String getMachineName() {
        return machineName;
    }

    public String getEventType() {
        return eventType;
    }

    public void printEvent() {
        System.out.println(getEventTime() + " " + getMachineName() + " " + getEventType());
    }
}
