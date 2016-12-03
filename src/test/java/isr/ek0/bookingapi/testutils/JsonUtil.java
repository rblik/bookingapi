package isr.ek0.bookingapi.testutils;

public class JsonUtil {
    public static final String bookingJson = "{\"date\":\"2016-12-09\",\"time\":\"15:00\"}";
    public static final String bookingJsonNotValidToLate = "{\"date\":\"2016-12-09\",\"time\":\"23:30\"}";
    public static final String bookingJsonNotValid = "{\"time\":\"15:00\"}";
    public static final String restaurantJson = "{\"name\":\"Fine Eat\",\"location\":{\"x\":\"10.0\",\"y\":\"-40.0\"},\"menu\":[{\"description\":\"Porridge\",\"preparingTime\":30,\"price\":10.0},{\"description\":\"Soup\",\"preparingTime\":45,\"price\":15.0},{\"description\":\"Grecha\",\"preparingTime\":15,\"price\":20.0}],\"openTime\":\"06:00\",\"closeTime\":\"22:00\"}";
    public static final String restaurantJsonNotValid = "{\"location\":{\"x\":\"10.0\",\"y\":\"-40.0\"},\"menu\":[{\"description\":\"Porridge\",\"preparingTime\":30,\"price\":10.0},{\"description\":\"Soup\",\"preparingTime\":45,\"price\":15.0},{\"description\":\"Grecha\",\"preparingTime\":15,\"price\":20.0}],\"openTime\":\"06:00\",\"closeTime\":\"22:00\"}";
    public static final String userJson = "{\"email\":\"user@ofice.com\",\"name\":\"NewUser\",\"password\":\"password\"}";
    public static final String userJsonNotValid = "{\"email\":\"user@ofice.com\",\"password\":\"password\"}";
    public static final String userJsonForUpdate = "{\"email\":\"user@yandex.com\",\"name\":\"User1_updated\",\"password\":\"password\"}";
    public static final String newMeal = "{\"description\":\"Salad\",\"preparingTime\":\"10\",\"price\":\"10.0\"}";
    public static final String newMealNotValid = "{\"preparingTime\":\"10\",\"price\":\"10.0\"}";
}
