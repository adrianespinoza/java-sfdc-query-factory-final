package aveh.soql;

public enum Order {
    ASC("ASC"),
    DESC("DESC");

    private String code;

    private Order(String order) {
        code = order;
    }

    public String getCode() {
        return code;
    }
}
