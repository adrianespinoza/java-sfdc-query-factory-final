package aveh.soql.statements;

import aveh.soql.Query;

public class SoqlOffset implements Statement {
    private Query query;

    public SoqlOffset(Query query) {
        this.query = query;
    }

    @Override
    public String toSoql() {
        return this.query.toSoql();
    }
}
