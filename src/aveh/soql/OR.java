package aveh.soql;

public class OR extends LogicalOperator {

    public OR(Condition...criterias) {
        super("OR", criterias);
    }
}
