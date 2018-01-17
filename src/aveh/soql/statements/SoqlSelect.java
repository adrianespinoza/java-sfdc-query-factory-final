package aveh.soql.statements;

import aveh.soql.Query;
import aveh.soql.QueryUtils;

public class SoqlSelect implements Statement{
    private Query query;

    public SoqlSelect(Query query) {
        this.query = query;
    }

    public SoqlFrom FROM(String entity) {
        this.query.table = QueryUtils.addPrefix(entity);
        SoqlFrom from = new SoqlFrom(query);
        return from;
    }

    @Override
    public String toSoql() {
        return query.toSoql();
    }
}
