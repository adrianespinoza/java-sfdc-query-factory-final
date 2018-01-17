package aveh.soql;

import org.apache.commons.lang3.StringUtils;

import aveh.soql.output.Output;

public class AggregateFunction extends Condition {
    private String fieldName;
    private String function;

    private AggregateFunction(String function) {
        this.function = function;
    }

    private AggregateFunction(String function, String fieldName) {
        this.function = function;
        this.fieldName = QueryUtils.addPrefix(fieldName);
    }

    public static AggregateFunction Count() {
        AggregateFunction aggFunction = new AggregateFunction("COUNT");
        return aggFunction;
    }

    public static AggregateFunction Count(String fieldName) {
        AggregateFunction aggFunction = new AggregateFunction("COUNT", fieldName);
        return aggFunction;
    }

    @Override
    public void write(Output out) {
        if (StringUtils.isNotBlank(fieldName)) {
            out.print(function + "( " + fieldName + " )");
        } else {
            out.print(function + "()");
        }
    }

}
