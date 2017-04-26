package myprojects.automation.assignment5.utils;

/**
 * Help class to get passed parameters from environment for further usage in the automation project
 */
public class Properties {
    private static final String DEFAULT_BASE_URL = "http://prestashop-automation.qatestlab.com.ua/";
    private static final String DEFAULT_BASE_ADMIN_URL = "http://prestashop-automation.qatestlab.com.ua/admin147ajyvk0/";
    private static final String FIRST_NAME = "Vasya";
    private static final String SECOND_NAME = "Pupcin";
    private static final String EMAIL = "test@gmail.com";
    private static final String ADDRES = "TestAdress";
    private static final String INDEX = "12345";
    private static final String CITY = "TestCity";
    private Properties() {

    }

    /**
     *
     * @return Website frontend.
     */
    public static String getBaseUrl() {
        return System.getProperty(EnvironmentVariable.BASE_URL.toString(), DEFAULT_BASE_URL);
    }

    /**
     *
     * @return Website backend (ULR of the Admin Panel.)
     */
    public static String getBaseAdminUrl() {
        return System.getProperty(EnvironmentVariable.BASE_ADMIN_URL.toString(), DEFAULT_BASE_ADMIN_URL);
    }

    public static String getFirstName() {
        return FIRST_NAME;
    }

    public static String getSecondName() {
        return SECOND_NAME;
    }

    public static String getEmail() {
        return EMAIL;
    }

    public static String getAdress() {
        return ADDRES;
    }

    public static String getIndex() {
        return INDEX;
    }

    public static String getCity() {
        return CITY;
    }
}

/**
 * All parameters that are passed to automation project
 */
enum EnvironmentVariable {
    BASE_URL("env.url"),
    BASE_ADMIN_URL("env.admin.url");

    private String value;
    EnvironmentVariable(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return value;
    }
}