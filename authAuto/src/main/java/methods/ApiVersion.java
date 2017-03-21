package methods;

public enum ApiVersion {

    API_2_2_2("2.2.2", "json"), API_2_2_1("2.2.1","json");

    private String version;

    private String format;

    ApiVersion(String version, String format) {
        this.version = version;
        this.format = format;
    }

    public String getVersion() {
        return version;
    }

    public String getFormat() {
        return format;
    }

}
