package aveh.soql.statements;

import aveh.soql.Condition;
import aveh.soql.Query;

public class SoqlFrom extends SoqlAbstract{
    public SoqlFrom(Query query) {
        super(query);
    }

    public SoqlWhere WHERE(Condition...conditions) {
        for (Condition crit : conditions) {
            query.criteria.add(crit);
        }
        SoqlWhere where = new SoqlWhere(query);
        return where;
    }
}
