package aveh.soql;

import java.util.Collection;
import java.util.Iterator;

import aveh.soql.output.Output;

public class SetCondition extends Condition{
    private String operator;
    private String column;
    private String value;
    private Query subSelect;

    private SetCondition(String operator, String column, Collection values) {
        this.operator = operator;
        this.column = QueryUtils.addPrefix(column);
        StringBuffer v = new StringBuffer();
        Iterator i = values.iterator();
        boolean hasNext = i.hasNext();
        while (hasNext) {
            Object curr = i.next();
            hasNext = i.hasNext();
            if (curr instanceof Number) {
                v.append(curr);
            } else {
                v.append(quote(curr.toString()));
            }
            if (hasNext) v.append(',');
        }
        this.value = v.toString();
    }

    private SetCondition(String operator, String column, String[] values) {
        this.operator = operator;
        this.column = QueryUtils.addPrefix(column);
        StringBuffer v = new StringBuffer();
        for (int i = 0; i < values.length; i++) {
            v.append(quote(values[i]));
            if (i < values.length - 1) v.append(',');
        }
        this.value = v.toString();
    }

    private SetCondition(String operator, String column, int[] values) {
        this.operator = operator;
        this.column = QueryUtils.addPrefix(column);
        StringBuffer v = new StringBuffer();
        for (int i = 0; i < values.length; i++) {
            v.append(values[i]);
            if (i < values.length - 1) v.append(',');
        }
        this.value = v.toString();
    }

    private SetCondition(String operator, String column, float[] values) {
        this.operator = operator;
        this.column = QueryUtils.addPrefix(column);
        StringBuffer v = new StringBuffer();
        for (int i = 0; i < values.length; i++) {
            v.append(values[i]);
            if (i < values.length - 1) v.append(',');
        }
        this.value = v.toString();
    }

    private SetCondition(String operator, String column, Query subSelect) {
        this.operator = operator;
        this.column = QueryUtils.addPrefix(column);
        this.subSelect = subSelect;
    }

    private SetCondition(String operator, String column, String subSelect) {
        this.operator = operator;
        this.column = QueryUtils.addPrefix(column);
        this.value = subSelect;
    }

    /**IN*/
    public static SetCondition in(String column, Collection values) {
        SetCondition setCond = new SetCondition(Operator.IN.getOperatorCode(), column, values);
        return setCond;
    }

    public static SetCondition in(String column, String[] values) {
        SetCondition setCond = new SetCondition(Operator.IN.getOperatorCode(), column, values);
        return setCond;
    }

    public static SetCondition in(String column, int[] values) {
        SetCondition setCond = new SetCondition(Operator.IN.getOperatorCode(), column, values);
        return setCond;
    }

    public static SetCondition in(String column, float[] values) {
        SetCondition setCond = new SetCondition(Operator.IN.getOperatorCode(), column, values);
        return setCond;
    }

    public static SetCondition in(String column, Query subSelect) {
        SetCondition setCond = new SetCondition(Operator.IN.getOperatorCode(), column, subSelect);
        return setCond;
    }

    public static SetCondition in(String column, String subSelect) {
        SetCondition setCond = new SetCondition(Operator.IN.getOperatorCode(), column, subSelect);
        return setCond;
    }

    /**NOT IN*/
    public static SetCondition notIn(String column, Collection values) {
        SetCondition setCond = new SetCondition(Operator.NO_IN.getOperatorCode(), column, values);
        return setCond;
    }

    public static SetCondition notIn(String column, String[] values) {
        SetCondition setCond = new SetCondition(Operator.NO_IN.getOperatorCode(), column, values);
        return setCond;
    }

    public static SetCondition notIn(String column, int[] values) {
        SetCondition setCond = new SetCondition(Operator.NO_IN.getOperatorCode(), column, values);
        return setCond;
    }

    public static SetCondition notIn(String column, float[] values) {
        SetCondition setCond = new SetCondition(Operator.NO_IN.getOperatorCode(), column, values);
        return setCond;
    }

    public static SetCondition notIn(String column, Query subSelect) {
        SetCondition setCond = new SetCondition(Operator.NO_IN.getOperatorCode(), column, subSelect);
        return setCond;
    }

    public static SetCondition notIn(String column, String subSelect) {
        SetCondition setCond = new SetCondition(Operator.NO_IN.getOperatorCode(), column, subSelect);
        return setCond;
    }

    /***/
    public static SetCondition excludes(String column, Collection values) {
        SetCondition setCond = new SetCondition(Operator.EXCLUDES.getOperatorCode(), column, values);
        return setCond;
    }

    public static SetCondition excludes(String column, String[] values) {
        SetCondition setCond = new SetCondition(Operator.EXCLUDES.getOperatorCode(), column, values);
        return setCond;
    }

    public static SetCondition includes(String column, Collection values) {
        SetCondition setCond = new SetCondition(Operator.INCLUDES.getOperatorCode(), column, values);
        return setCond;
    }

    public static SetCondition includes(String column, String[] values) {
        SetCondition setCond = new SetCondition(Operator.INCLUDES.getOperatorCode(), column, values);
        return setCond;
    }

    public String getColumn() {
        return column;
    }

    @Override
    public void write(Output out) {
        out.print(column);
        out.println(" " + this.operator + " (");

        out.indent();
        if (subSelect != null) {
            subSelect.write(out);
        } else {
            out.print(value);
        }
        out.unindent();

        out.println();
        out.print(")");
    }
}
