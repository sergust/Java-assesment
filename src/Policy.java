public class Policy extends Event{
    private String policyId;
    private String policyStatus;

    public Policy(String time, String machineName, String eventType, String policyId, String policyStatus) {
        super(time, machineName, eventType);
        this.policyId = policyId;
        this.policyStatus = policyStatus;
    }

    public String getPolicyId() {
        return policyId;
    }

    public String getPolicyStatus() {
        return policyStatus;
    }
}