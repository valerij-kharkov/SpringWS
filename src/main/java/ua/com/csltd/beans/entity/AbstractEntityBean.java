package ua.com.csltd.beans.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.SQLException;

/**
 * User: makiyov
 * Date: 20.06.12
 * Time: 16:54
 */
public class AbstractEntityBean implements Serializable {
    protected BigDecimal id;
    protected BigDecimal queueId;
    protected BigDecimal branchId;
    protected BigDecimal actionTypeId;

    public BigDecimal getId() {
        return id;
    }

    public void setId(BigDecimal id) {
        this.id = id;
    }

    public BigDecimal getBranchId() {
        return branchId;
    }

    public void setBranchId(BigDecimal branchId) {
        this.branchId = branchId;
    }

    public BigDecimal getQueueId() {
        return queueId;
    }

    public void setQueueId(BigDecimal queueId) {
        this.queueId = queueId;
    }

    public BigDecimal getActionTypeId() {
        return actionTypeId;
    }

    public void setActionTypeId(BigDecimal actionTypeId) {
        this.actionTypeId = actionTypeId;
    }

    public static String getStringByObject(Object obj) throws SQLException {
        return "";
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("AbstractEntityBean");
        sb.append("{id=").append(id);
        sb.append(", queueId=").append(queueId);
        sb.append(", branchId=").append(branchId);
        sb.append(", actionTypeId=").append(actionTypeId);
        sb.append('}');
        return sb.toString();
    }

    protected <T> T nvl(T t1, T t2) {
        return t1 != null ? t1 : t2;
    }
}
