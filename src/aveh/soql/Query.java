package aveh.soql;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import aveh.soql.output.Output;
import aveh.soql.output.Outputable;
import aveh.soql.output.ToStringx;
import aveh.soql.statements.SoqlSelect;

public class Query implements Outputable {
    public List<Condition> criteria;
    public String table;
    public String[] columns;

    public String orderByColumn;
    public String order;
    public String ordersNull;
    public int limit = -1;
    public int offset = -1;

    private List<Condition> aggregateFunctions;

    public String[] groupByColumns;

    public Query() {
        init();
    }

    public static SoqlSelect SELECT(String columns) {
        columns = StringUtils.deleteWhitespace(columns);
        String[] tempColumns = StringUtils.split(columns, ",");
        return SELECT(tempColumns);
    }

    public static SoqlSelect SELECT(String... column) {
        Query builder = new Query();
        QueryUtils.addPrefix(column);
        builder.columns = column;
        SoqlSelect select = new SoqlSelect(builder);
        return select;
    }

    public static SoqlSelect SELECT(Condition...aggregateFunctions) {
        return SELECT("", aggregateFunctions);
    }

    public static SoqlSelect SELECT(String summaryField, Condition...aggregateFunctions) {
        String[] columns = StringUtils.split(summaryField, ",");
        return SELECT(columns, aggregateFunctions);
    }

    public static SoqlSelect SELECT(String[] summaryColumns, Condition...aggregateFunctions) {
        Query builder = new Query();
        QueryUtils.addPrefix(summaryColumns);
        builder.columns = summaryColumns;
        for (Condition condition : aggregateFunctions) {
            builder.aggregateFunctions.add(condition);
        }
        SoqlSelect select = new SoqlSelect(builder);
        return select;
    }

    public String toSoql() {
        return ToStringx.toString(this);
    }

    private void init() {
        //columns = new ArrayList<String>();
        criteria = new ArrayList<Condition>();
        aggregateFunctions = new ArrayList<Condition>();
    }

    @Override
    public void write(Output out) {
        out.println("SELECT");
        // Add columns to select
        if (columns != null && columns.length > 0) {
            out.indent();
            appendColumns(out, columns, ",");
            out.unindent();
        }

        // or add functions
        if (aggregateFunctions != null && aggregateFunctions.size() > 0) {
            out.indent();
            if (columns != null && columns.length > 0) {
                out.print(", ");
            }
            appendList(out, aggregateFunctions, ",");
            out.unindent();
        }

        // Add tables to select from
        out.println("FROM");
        out.indent();
        appendString(out, table);
        out.unindent();

        // Add criteria
        if (criteria.size() > 0) {
            out.println("WHERE");
            out.indent();
            appendList(out, criteria, "AND");
            out.unindent();
        }

        if (groupByColumns != null && groupByColumns.length > 0) {
            out.println("GROUP BY");
            out.indent();
            appendColumns(out, groupByColumns, ",");
            out.unindent();
        }

        if (StringUtils.isNotBlank(orderByColumn)) {
            List<String> orderExpresion = new ArrayList<String>();
            orderExpresion.add(orderByColumn);
            out.println("ORDER BY");
            out.indent();
            if (StringUtils.isNotBlank(order)) {
                orderExpresion.add(order);
            }
            if (StringUtils.isNotBlank(ordersNull)) {
                orderExpresion.add(ordersNull);
            }
            appendColumns(out, orderExpresion.toArray(new String[0]), "");
            out.unindent();
        }

        if (limit > -1) {
            out.println("LIMIT");
            out.indent();
            appendString(out, String.valueOf(limit));
            out.unindent();
        }

        if (offset > -1) {
            out.println("OFFSET");
            out.indent();
            appendString(out, String.valueOf(offset));
            out.unindent();
        }
    }

    private void appendList(Output out, Collection collection, String seperator) {
        Iterator i = collection.iterator();
        boolean hasNext = i.hasNext();

        while (hasNext) {
            Outputable curr = (Outputable) i.next();
            hasNext = i.hasNext();
            curr.write(out);
            out.print(' ');
            if (hasNext) {
                out.print(seperator);
            }
            out.println();
        }
    }

    private void appendList2(Output out, Collection collection, String seperator) {
        Iterator i = collection.iterator();
        boolean hasNext = i.hasNext();

        while (hasNext) {
            String curr = (String) i.next();
            hasNext = i.hasNext();
            out.print(curr);
            out.print(' ');
            if (hasNext) {
                out.print(seperator);
            }
            out.println();
        }
    }

    public void appendColumns(Output out, String[] collection, String seperator) {
        int collectionSize = collection.length;
        for (int i = 0; i < collectionSize; i++) {
            out.print(collection[i]);
            if (i < (collectionSize - 1)) {
                out.print(seperator);
            }
            out.print(' ');
            //out.println(); for rows
        }
        out.println();// for columns
    }

    public void appendString(Output out, String value) {
        appendString(out, value, null);
    }

    public void appendString(Output out, String value, String separator) {
        out.print(value);
        if (StringUtils.isNotBlank(separator)) {
            out.print(",");
        }
        out.print(' ');
        out.println();
    }
}
