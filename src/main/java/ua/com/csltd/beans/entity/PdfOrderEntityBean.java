package ua.com.csltd.beans.entity;

import oracle.sql.ARRAY;
import oracle.sql.BLOB;
import oracle.sql.CLOB;
import oracle.sql.STRUCT;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: makiyov
 * Date: 29.03.13
 * Time: 13:49
 */
public class PdfOrderEntityBean extends AbstractDocumentEntityBean {
  private BigDecimal pdfFileId;
  private BigDecimal fieldCount;
  private BigDecimal sourceImageSize;
  private BigDecimal needSendtob2;
  private Timestamp receiveDate;
  private BigDecimal reqSignLevel;
  private BigDecimal createUserId;
  private BigDecimal lastModifiedUserId;
  private Timestamp lastModifiedDate;
  private BigDecimal version;
  private String rejectComment;
  private Timestamp documentDate;
  private String user1Login;
  private String user2Login;
  private List<PdfOrderFieldEntityBean> pdfField_tab;
  private String absOrderId;
  private byte [] sourceImage;
  private String process_errormsg;
  private BigDecimal siteId;
  private BigDecimal streamId;
  private BigDecimal smsAuth;
  private String smsCodeHash;
  private BigDecimal smsCodeCount;
  private Timestamp smsCodeExpireDate;
  private String digipassPassword;
  private String sourceImageBlob;
  private List<PdfFileEntity> pdffiles;


  public PdfOrderEntityBean() {
  }

  public PdfOrderEntityBean(Object[] arrayAttr) throws SQLException {
    int i = 0;
    this.id = (BigDecimal) arrayAttr[i++];
    this.fieldCount = (BigDecimal) arrayAttr[i++];
    this.stateId = (BigDecimal) arrayAttr[i++];
    this.rejectComment = getStringByObject(arrayAttr[i++]);
    this.receiveDate = (Timestamp) arrayAttr[i++];
    this.createUserId = (BigDecimal) arrayAttr[i++];
    this.lastModifiedUserId = (BigDecimal) arrayAttr[i++];
    this.lastModifiedDate = (Timestamp) arrayAttr[i++];
    this.branchId = (BigDecimal) arrayAttr[i++];
    this.pdfField_tab = new ArrayList<PdfOrderFieldEntityBean>();
    ARRAY fieldArray = (ARRAY) arrayAttr[i++];
    if (fieldArray != null) {
      Object[] fields = (Object[]) fieldArray.getArray();
      if (fields != null) {
        for (Object field : fields) {
          Object[] fieldAttr = ((STRUCT) field).getAttributes();
          if (fieldAttr != null && fieldAttr.length >= 3) {
            this.pdfField_tab.add(new PdfOrderFieldEntityBean(fieldAttr));
          } else {
            throw new IllegalArgumentException("PdfField attr is null or size < 3");
          }
        }
      }
    }
    this.sig = getStringByObject(arrayAttr[i++]);
    this.sigType = (BigDecimal) arrayAttr[i++];
    this.sigCert = getStringByObject(arrayAttr[i++]);
    this.user1Login = getStringByObject(arrayAttr[i++]);
    this.user2Login = getStringByObject(arrayAttr[i++]);

//        this.absOrderId = (BigDecimal) arrayAttr[i++];
  }

  public String getAbsOrderId() {
    return absOrderId;
  }

  public void setAbsOrderId(String absOrderId) {
    this.absOrderId = absOrderId;
  }

  public BigDecimal getPdfFileId() {
    return pdfFileId;
  }

  public void setPdfFileId(BigDecimal pdfFileId) {
    this.pdfFileId = pdfFileId;
  }

  public BigDecimal getFieldCount() {
    return fieldCount;
  }

  public void setFieldCount(BigDecimal fieldCount) {
    this.fieldCount = fieldCount;
  }

  public BigDecimal getSourceImageSize() {
    return sourceImageSize;
  }

  public void setSourceImageSize(BigDecimal sourceImageSize) {
    this.sourceImageSize = sourceImageSize;
  }

  public BigDecimal getStateId() {
    return stateId;
  }

  public void setStateId(BigDecimal stateId) {
    this.stateId = stateId;
  }

  public BigDecimal getNeedSendtob2() {
    return needSendtob2;
  }

  public void setNeedSendtob2(BigDecimal needSendtob2) {
    this.needSendtob2 = needSendtob2;
  }

  public Timestamp getReceiveDate() {
    return receiveDate;
  }

  public void setReceiveDate(Timestamp receiveDate) {
    this.receiveDate = receiveDate;
  }

  public BigDecimal getReqSignLevel() {
    return reqSignLevel;
  }

  public void setReqSignLevel(BigDecimal reqSignLevel) {
    this.reqSignLevel = reqSignLevel;
  }

  public BigDecimal getCreateUserId() {
    return createUserId;
  }

  public void setCreateUserId(BigDecimal createUserId) {
    this.createUserId = createUserId;
  }

  public BigDecimal getLastModifiedUserId() {
    return lastModifiedUserId;
  }

  public void setLastModifiedUserId(BigDecimal lastModifiedUserId) {
    this.lastModifiedUserId = lastModifiedUserId;
  }

  public Timestamp getLastModifiedDate() {
    return lastModifiedDate;
  }

  public void setLastModifiedDate(Timestamp lastModifiedDate) {
    this.lastModifiedDate = lastModifiedDate;
  }

  public BigDecimal getVersion() {
    return version;
  }

  public void setVersion(BigDecimal version) {
    this.version = version;
  }

  public String getRejectComment() {
    return rejectComment;
  }

  public void setRejectComment(String rejectComment) {
    this.rejectComment = rejectComment;
  }

  public Timestamp getDocumentDate() {
    return documentDate;
  }

  public void setDocumentDate(Timestamp documentDate) {
    this.documentDate = documentDate;
  }

  public String getUser1Login() {
    return user1Login;
  }

  public void setUser1Login(String user1Login) {
    this.user1Login = user1Login;
  }

  public String getUser2Login() {
    return user2Login;
  }

  public void setUser2Login(String user2Login) {
    this.user2Login = user2Login;
  }

  public void setSigCertClob(CLOB sigCertClob) throws SQLException {
    super.setSigCert(sigCertClob != null ? sigCertClob.stringValue() : null);
  }

  public void setSourceImage(BLOB sourceImageBlob) throws SQLException {
    this.sourceImage = sourceImageBlob != null ? sourceImageBlob.getBytes() : null;
  }
  /*public void setsourceImageBlob(BLOB sourceImageBlob) throws SQLException {
    setSourceImage(sourceImageBlob != null ? sourceImageBlob.stringValue() : null);
  }*/
  public void setSigCert(CLOB sigCertClob) throws SQLException {
    super.setSigCert(sigCertClob != null ? sigCertClob.stringValue() : null);
  }

  @Override
  public BigDecimal getTableId() {
    return BigDecimal.ONE;
  }

  public List<PdfOrderFieldEntityBean> getPdfField_tab() {
    return pdfField_tab;
  }

  public void setPdfField_tab(List<PdfOrderFieldEntityBean> pdfField_tab) {
    this.pdfField_tab = pdfField_tab;
  }

  public byte[] getSourceImage() {
    return sourceImage;
  }

  public String getProcess_errormsg() {
    return process_errormsg;
  }

  public void setProcess_errormsg(String process_errormsg) {
    this.process_errormsg = process_errormsg;
  }

  public BigDecimal getSiteId() {
    return siteId;
  }

  public void setSiteId(BigDecimal siteId) {
    this.siteId = siteId;
  }

  public BigDecimal getStreamId() {
    return streamId;
  }

  public void setStreamId(BigDecimal streamId) {
    this.streamId = streamId;
  }

  public BigDecimal getSmsAuth() {
    return smsAuth;
  }

  public void setSmsAuth(BigDecimal smsAuth) {
    this.smsAuth = smsAuth;
  }

  public String getSmsCodeHash() {
    return smsCodeHash;
  }

  public void setSmsCodeHash(String smsCodeHash) {
    this.smsCodeHash = smsCodeHash;
  }

  public BigDecimal getSmsCodeCount() {
    return smsCodeCount;
  }

  public void setSmsCodeCount(BigDecimal smsCodeCount) {
    this.smsCodeCount = smsCodeCount;
  }

  public Timestamp getSmsCodeExpireDate() {
    return smsCodeExpireDate;
  }

  public void setSmsCodeExpireDate(Timestamp smsCodeExpireDate) {
    this.smsCodeExpireDate = smsCodeExpireDate;
  }

  public String getDigipassPassword() {
    return digipassPassword;
  }

  public void setDigipassPassword(String digipassPassword) {
    this.digipassPassword = digipassPassword;
  }

  public List<PdfFileEntity> getPdffiles() {
    return pdffiles;
  }

  public void setPdffiles(List<PdfFileEntity> pdffiles) {
    this.pdffiles = pdffiles;
  }

  @Override
  protected Object[] getIfobsDSArea() {
    if (pdfField_tab != null) {
      Collections.sort(pdfField_tab);
      Object[] data = new Object[pdfField_tab.size()];
      int i = 0;
      for (PdfOrderFieldEntityBean pdfOrderField : pdfField_tab) {
        data[i++] = pdfOrderField.getValue();
      }
      return data;
    } else {
      return new Object[0];
    }
  }

  @Override
  protected Object[] getDSArea() {
    return getIfobsDSArea();
  }


  @Override
  public String toString() {
    return "PdfOrderEntityBean{" +
        "pdfFileId=" + pdfFileId +
        ", fieldCount=" + fieldCount +
        ", sourceImageSize=" + sourceImageSize +
        ", needSendtob2=" + needSendtob2 +
        ", receiveDate=" + receiveDate +
        ", reqSignLevel=" + reqSignLevel +
        ", createUserId=" + createUserId +
        ", lastModifiedUserId=" + lastModifiedUserId +
        ", lastModifiedDate=" + lastModifiedDate +
        ", version=" + version +
        ", rejectComment='" + rejectComment + '\'' +
        ", documentDate=" + documentDate +
        ", user1Login='" + user1Login + '\'' +
        ", user2Login='" + user2Login + '\'' +
        ", pdfField_tab=" + pdfField_tab +
        ", absOrderId='" + absOrderId + '\'' +
        ", sourceImage='" + sourceImage + '\'' +
        ", process_errormsg='" + process_errormsg + '\'' +
        ", siteId=" + siteId +
        ", streamId=" + streamId +
        ", smsAuth=" + smsAuth +
        ", smsCodeHash='" + smsCodeHash + '\'' +
        ", smsCodeCount=" + smsCodeCount +
        ", smsCodeExpireDate=" + smsCodeExpireDate +
        ", digipassPassword='" + digipassPassword + '\'' +
        ", pdffiles=" + pdffiles +
        '}';
  }
}
