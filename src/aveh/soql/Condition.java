package aveh.soql;

import aveh.soql.output.Output;
import aveh.soql.output.Outputable;
import aveh.soql.output.ToStringx;

public abstract class Condition implements Outputable {
    protected String quote(String s) {
        if (s == null) {
            return "null";
        }
        StringBuffer str = new StringBuffer();
        str.append('\'');
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '\\' || s.charAt(i) == '\"' || s.charAt(i) == '\'') {
                str.append('\\');
            }
            str.append(s.charAt(i));
        }
        str.append('\'');
        return str.toString();
    }

    public String toString() {
        return ToStringx.toString(this);
    }

    public abstract void write(Output out);
}
