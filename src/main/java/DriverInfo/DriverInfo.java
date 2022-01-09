package DriverInfo;

/**
 * Project: newMotorDepot
 * Author: Zhanarbek Abdurasulov
 * Date: 8/1/22
 */
public class DriverInfo {
    private int id=0;
    private String driverId="";
    private String name="";
    private String bus="";

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDriverId() {
        return driverId;
    }

    public void setDriverId(String driverId) {
        this.driverId = driverId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBus() {
        return bus;
    }

    public void setBus(String bus) {
        this.bus = bus;
    }
}
