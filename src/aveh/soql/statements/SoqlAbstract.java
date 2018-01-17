package aveh.soql.statements;

import org.apache.commons.lang3.StringUtils;

import aveh.soql.Order;
import aveh.soql.OrdersNull;
import aveh.soql.Query;
import aveh.soql.QueryUtils;

public abstract class SoqlAbstract implements Statement {
    protected Query query;

    protected SoqlAbstract(Query query) {
        this.query = query;
    }

    public SoqlGroupBy GROUPBY(String columns) {
        String[] groupByColumns = StringUtils.split(columns, ",");
        QueryUtils.addPrefix(groupByColumns);
        return GROUPBY(groupByColumns);
    }

    public SoqlGroupBy GROUPBY(String... columns) {
        this.query.groupByColumns = columns;
        SoqlGroupBy groupBy = new SoqlGroupBy(query);
        return groupBy;
    }

    public SoqlOrderBy ORDERBY(String column) {
        this.query.orderByColumn = QueryUtils.addPrefix(column);
        SoqlOrderBy orderby = new SoqlOrderBy(this.query);
        return orderby;
    }

    public SoqlOrderBy ORDERBY(String column, Order order) {
        this.query.orderByColumn = QueryUtils.addPrefix(column);
        this.query.order = order.getCode();
        SoqlOrderBy orderby = new SoqlOrderBy(this.query);
        return orderby;
    }

    public SoqlOrderBy ORDERBY(String column, OrdersNull ordersNull) {
        this.query.orderByColumn = QueryUtils.addPrefix(column);
        this.query.ordersNull = ordersNull.getCode();
        SoqlOrderBy orderby = new SoqlOrderBy(this.query);
        return orderby;
    }

    public SoqlOrderBy ORDERBY(String column, Order order, OrdersNull ordersNull) {
        this.query.orderByColumn = QueryUtils.addPrefix(column);
        this.query.order = order.getCode();
        this.query.ordersNull = ordersNull.getCode();
        SoqlOrderBy orderby = new SoqlOrderBy(this.query);
        return orderby;
    }

    public SoqlLimit LIMIT(int numberOfRows) {
        this.query.limit = numberOfRows;
        SoqlLimit limit = new SoqlLimit(this.query);
        return limit;
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
