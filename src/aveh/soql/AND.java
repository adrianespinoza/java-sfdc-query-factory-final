package aveh.soql;

public class AND extends LogicalOperator{

    public AND(Condition...criterias) {
        super("AND", criterias);
    }
}
