package aveh.high;

public class Validator {
    public static ValueComparer validate(String value) {
        ValueComparer valueComp = new ValueComparer();
        valueComp.valueTo = value;
        System.out.println("Validating the value with database: " + value);
        return valueComp;
    }
}
