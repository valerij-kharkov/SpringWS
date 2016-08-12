package ua.com.csltd.beans.entity;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;

/**
 * User: makiyov
 * Date: 18.04.12
 * Time: 17:23
 */
public abstract class AbstractDocumentEntityBean extends AbstractEntityBean {
    protected BigDecimal parentTable;
    protected BigDecimal parentId;
    protected String sig;
    protected String sigCert;
    protected BigDecimal sigType;
    protected byte[] ds = null;
    protected BigDecimal stateId;
    protected BigDecimal version;

    /**
     * Информация о записи из очереди
     * */

//    public abstract DirectDataTypes getType();



    public abstract BigDecimal getTableId();

    public String getDigitalSignature() {
        return getDs() != null ? new String(getDs()) : null;
    }

    public String getSigCert() {
        return sigCert;
    }

    public void setSigCert(String sigCert) {
        this.sigCert = sigCert;
    }

    public String getSig() {
        return sig;
    }

    public byte[] getSigByte() {
        return sig != null ? sig.getBytes() : null;
    }

    public void setSig(String sig) {
        this.sig = sig;
    }

    public BigDecimal getSigType() {
        return sigType;
    }

    public void setSigType(BigDecimal sigType) {
        this.sigType = sigType;
    }

    public byte[] getDs() {
        return ds;
    }

    public void setDs(byte[] ds) {
        this.ds = ds;
    }

    public BigDecimal getParentTable() {
        return parentTable;
    }

    public void setParentTable(BigDecimal parentTable) {
        this.parentTable = parentTable;
    }

    public BigDecimal getParentId() {
        return parentId;
    }

    public void setParentId(BigDecimal parentId) {
        this.parentId = parentId;
    }

    public BigDecimal getStateId() {
        return stateId;
    }

    public void setStateId(BigDecimal stateId) {
        this.stateId = stateId;
    }

    public BigDecimal getVersion() {
        return version;
    }

    public void setVersion(BigDecimal version) {
        this.version = version;
    }

    protected abstract Object[] getIfobsDSArea();

    protected abstract Object[] getDSArea();

    public String getIfobsDSString() {

        final Object[] fields = getIfobsDSArea();

        if (fields == null || fields.length == 0) {
            return null;
        }

        String result;
        final StringBuffer res = new StringBuffer();

        for (Object obj : fields) {

            res.append(getStringByObjectPassed(obj));
            res.append("");
        }

        result = res.toString();
        return result.substring(0, result.length() - 1);
    }

    public String getIITDSString() {

        return null;
    }

    public String getDSString() {

        final Object[] fields = getDSArea();

        if (fields == null) {
            return null;
        }

        final StringBuffer res = new StringBuffer();

        for (int i = 0; i < fields.length; i++) {
            if (i > 0) {
                res.append("");
            }
            res.append(getStringByObjectPassed(fields[i]));
        }
        return res.toString();
    }

    private String getStringByObjectPassed(final Object obj) {

        if (obj == null) {
            return "";
        }

        if (obj instanceof Timestamp) {
            return getDateStringFromTS((Timestamp) obj);
        }

        return obj.toString();
    }

    public static String getDateStringFromTS(final Timestamp date) {
        if (date != null) {
            try {
                return getTimestampAsString("", date);
            } catch (Exception ex) {
            }
        }
        return "";
    }

    public static String getTimestampAsString(final String format, final Timestamp date) throws Exception {
        if ((format == null) || (date == null)) {
            return null;
        }
        final DateFormat formatter = new SimpleDateFormat(format);
        final String dateNewFormat = formatter.format(date);
        return dateNewFormat;
    }

    @Override
    public String toString() {
        return "AbstractDocumentEntityBean{" +
                "parentTable=" + parentTable +
                ", parentId=" + parentId +
                ", sig='" + sig + '\'' +
                ", sigCert='" + sigCert + '\'' +
                ", sigType=" + sigType +
                ", ds=" + Arrays.toString(ds) +
                ", stateId=" + stateId +
                ", version=" + version +
                '}';
    }
}
