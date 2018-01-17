package aveh.soql.statements;

import aveh.soql.Query;

public class SoqlOrderBy implements Statement {
    private Query query;

    public SoqlOrderBy(Query query) {
        this.query = query;
    }

    public SoqlLimit LIMIT(int numberOfRows) {
        this.query.limit = numberOfRows;
        SoqlLimit limit = new SoqlLimit(this.query);
        return limit;
    }

    @Override
    public String toSoql() {
        return query.toSoql();
    }

}
