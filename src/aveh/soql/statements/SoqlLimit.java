package aveh.soql.statements;

import aveh.soql.Query;

public class SoqlLimit implements Statement {
    private Query query;

    public SoqlLimit(Query query) {
        this.query = query;
    }

    public SoqlOffset OFFSET(int numberOfRowsToSkip) {
        this.query.offset = numberOfRowsToSkip;
        SoqlOffset offset = new SoqlOffset(this.query);
        return offset;
    }

    @Override
    public String toSoql() {
        return query.toSoql();
    }
}
