package aveh.soql;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import aveh.soql.output.Output;
import aveh.soql.output.Outputable;

public abstract class LogicalOperator extends Condition {
    private String operator;
    private List<Condition> criterias;

    protected LogicalOperator(String operator, Condition...nextCriterias) {
        this.operator = operator;
        this.criterias = new ArrayList<Condition>();
        for (int i = 0; i < nextCriterias.length; i++) {
            this.criterias.add(nextCriterias[i]);
        }
    }

    public void write(Output out) {
        appendList(out, criterias, operator);
    }

    private void appendList(Output out, Collection collection, String seperator) {
        Iterator i = collection.iterator();
        boolean hasNext = i.hasNext();
        boolean hasElements = false;

        if(hasNext) {
            out.print("( ");
            hasElements = true;
        }
        while (hasNext) {
            Outputable curr = (Outputable) i.next();
            hasNext = i.hasNext();
            curr.write(out);
            if (hasNext) {
                out.print(' ')
                .print(seperator)
                .print(' ');
            }
        }
        if (hasElements) {
            out.print(" )");
        }
    }
}
