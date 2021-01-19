public class Inventory extends Event{
    private String inventoryType;
    private String inventoryStatus;

    public Inventory(String time, String machineName, String eventType, String inventoryType, String inventoryStatus) {
        super(time, machineName, eventType);
        this.inventoryType = inventoryType;
        this.inventoryStatus = inventoryStatus;
    }

    public String getInventoryType() {
        return inventoryType;
    }

    public String getInventoryStatus() {
        return inventoryStatus;
    }
}