package shop.model;

public final class Address {
    private final String street;
    private final String city;
    private final String postalCode;
    public Address(String street, String city, String postalCode) {
        if (blank(street) || blank(city) || blank(postalCode)) throw new IllegalArgumentException("Address values are required");
        this.street = street;
        this.city = city;
        this.postalCode = postalCode;
    }
    private static boolean blank(String value) { return value == null || value.isBlank(); }
    public String getStreet() { return street; }
    public String getCity() { return city; }
    public String getPostalCode() { return postalCode; }
    public String toString() { return street + ", " + city + " " + postalCode; }
}
