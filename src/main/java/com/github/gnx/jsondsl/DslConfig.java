package com.github.gnx.jsondsl;

/**
 * Created with IntelliJ IDEA.
 * Description: 
 * @author genx
 * @date 2020/3/22 16:39
 */
public class DslConfig {

    /**
     * 连接符 and
     */
    private String[] connectorAnd = {"and", "$and", "_and"};

    /**
     * 连接符 or
     */
    private String[] connectorOr = {"or", "$or", "_or"};

    /**
     * 连接符 not
     */
    private String[] connectorNot = {"not", "$not", "_not"};


    /**
     * 运算符 >
     */
    private String[] operatorGreaterThan = {">", "gt", "$gt", "_gt"};

    /**
     * 运算符 >=
     */
    private String[] operatorGreaterThanOrEqual = {">=", "gte", "$gte", "_gte"};

    /**
     * 运算符 <
     */
    private String[] operatorLessThan = {"<", "lt", "$lt", "_lt"};

    /**
     * 运算符 <=
     */
    private String[] operatorLessThanOrEqual = {"<=", "lte", "$lte", "_lte"};

    /**
     * 运算符 =
     * 一般不会出现
     */
    private String[] operatorEqual = {"="};

    /**
     * 运算符 =
     * 一般不会出现
     */
    private String[] operatorNotEqual = {"!="};

    /**
     * 运算符 in
     */
    private String[] operatorIn = {"in", "$in", "_in"};

    private String[] operatorNotIn = {"not int", "$not in", "_not in"};


    public String[] getConnectorAnd() {
        return connectorAnd;
    }

    public void setConnectorAnd(String[] connectorAnd) {
        this.connectorAnd = connectorAnd;
    }

    public String[] getConnectorOr() {
        return connectorOr;
    }

    public void setConnectorOr(String[] connectorOr) {
        this.connectorOr = connectorOr;
    }

    public String[] getConnectorNot() {
        return connectorNot;
    }

    public void setConnectorNot(String[] connectorNot) {
        this.connectorNot = connectorNot;
    }

    public String[] getOperatorGreaterThan() {
        return operatorGreaterThan;
    }

    public void setOperatorGreaterThan(String[] operatorGreaterThan) {
        this.operatorGreaterThan = operatorGreaterThan;
    }

    public String[] getOperatorGreaterThanOrEqual() {
        return operatorGreaterThanOrEqual;
    }

    public void setOperatorGreaterThanOrEqual(String[] operatorGreaterThanOrEqual) {
        this.operatorGreaterThanOrEqual = operatorGreaterThanOrEqual;
    }

    public String[] getOperatorLessThan() {
        return operatorLessThan;
    }

    public void setOperatorLessThan(String[] operatorLessThan) {
        this.operatorLessThan = operatorLessThan;
    }

    public String[] getOperatorLessThanOrEqual() {
        return operatorLessThanOrEqual;
    }

    public void setOperatorLessThanOrEqual(String[] operatorLessThanOrEqual) {
        this.operatorLessThanOrEqual = operatorLessThanOrEqual;
    }

    public String[] getOperatorEqual() {
        return operatorEqual;
    }

    public void setOperatorEqual(String[] operatorEqual) {
        this.operatorEqual = operatorEqual;
    }

    public String[] getOperatorIn() {
        return operatorIn;
    }

    public void setOperatorIn(String[] operatorIn) {
        this.operatorIn = operatorIn;
    }

    public String[] getOperatorNotEqual() {
        return operatorNotEqual;
    }

    public void setOperatorNotEqual(String[] operatorNotEqual) {
        this.operatorNotEqual = operatorNotEqual;
    }

    public String[] getOperatorNotIn() {
        return operatorNotIn;
    }

    public void setOperatorNotIn(String[] operatorNotIn) {
        this.operatorNotIn = operatorNotIn;
    }
}
