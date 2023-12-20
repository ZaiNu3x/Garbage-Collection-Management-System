package group.boysoverflowers.location_based_garbage_management_system.models;

public class Bin {

    private Integer id;
    private String city;
    private String barangay;
    private String street;
    private String schedule;

    public Bin(int id, String city, String barangay, String street, String schedule) {
        this.id = id;
        this.city = city;
        this.barangay = barangay;
        this.street = street;
        this.schedule = schedule;
    }

    public Bin() {
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getBarangay() {
        return barangay;
    }

    public void setBarangay(String barangay) {
        this.barangay = barangay;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String  getSchedule() {
        return schedule;
    }

    public void setSchedule(String schedule) {
        this.schedule = schedule;
    }
}
