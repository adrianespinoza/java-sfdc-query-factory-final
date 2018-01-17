package aveh.soql.statements;

import aveh.soql.Query;

public class SoqlGroupBy implements Statement {
    private Query query;

    public SoqlGroupBy(Query query) {
        this.query = query;
    }
    @Override
    public String toSoql() {
        return query.toSoql();
    }

}
