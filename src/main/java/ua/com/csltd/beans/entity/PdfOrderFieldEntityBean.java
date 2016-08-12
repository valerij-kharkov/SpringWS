package ua.com.csltd.beans.entity;

import java.math.BigDecimal;
import java.sql.SQLException;

/**
 * Created with IntelliJ IDEA.
 * User: makiyov
 * Date: 01.04.13
 * Time: 13:11
 */
public class PdfOrderFieldEntityBean extends AbstractDocumentEntityBean implements Comparable<PdfOrderFieldEntityBean> {
    private String name;
    private String value;
    private BigDecimal fieldNo;

    public PdfOrderFieldEntityBean() {
    }

    public PdfOrderFieldEntityBean(Object[] arrayAttr) throws SQLException {
        this.id = (BigDecimal) arrayAttr[0];
        this.name = getStringByObject(arrayAttr[1]);
        this.value = getStringByObject(arrayAttr[2]);
        this.fieldNo = (BigDecimal) arrayAttr[3];
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public BigDecimal getFieldNo() {
        return fieldNo;
    }

    public void setFieldNo(BigDecimal fieldNo) {
        this.fieldNo = fieldNo;
    }

    @Override
    public int compareTo(PdfOrderFieldEntityBean o) {
        if (fieldNo == null || o == null || o.getFieldNo() == null) {
            throw new RuntimeException("Not valid field data [this fieldNo=" + fieldNo + ", compare field=" + o + "]");
        }
        return fieldNo.compareTo(o.getFieldNo());
    }

    @Override
    public BigDecimal getTableId() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    protected Object[] getIfobsDSArea() {
        return new Object[0];  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    protected Object[] getDSArea() {
        return new Object[0];  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public String toString() {
        return "PdfOrderFieldEntityBean{" +
                "name='" + name + '\'' +
                ", value='" + value + '\'' +
                ", fieldNo=" + fieldNo +
                "} " + super.toString();
    }
}
