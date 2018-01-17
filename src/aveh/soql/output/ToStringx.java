package aveh.soql.output;

public class ToStringx {
    public static String toString(Outputable outputable) {
        Output out = new Output("    ");
        outputable.write(out);
        return out.toString();
    }
}
