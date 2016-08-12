package ua.com.csltd.beans.entity;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by valeriy_solyanik
 * on 08.08.2016.
 */
public class PdfFileEntity extends AbstractEntityBean {
  private String fileName;
  private BigDecimal fileSize;
  private BigDecimal pdfGroupId;
  private String descr;
  private BigDecimal isInactive;
  private List<PdfGroupEntity> pdfgroups;


  public String getFileName() {
    return fileName;
  }

  public void setFileName(String fileName) {
    this.fileName = fileName;
  }

  public BigDecimal getFileSize() {
    return fileSize;
  }

  public void setFileSize(BigDecimal fileSize) {
    this.fileSize = fileSize;
  }

  public BigDecimal getPdfGroupId() {
    return pdfGroupId;
  }

  public void setPdfGroupId(BigDecimal pdfGroupId) {
    this.pdfGroupId = pdfGroupId;
  }

  public String getDescr() {
    return descr;
  }

  public void setDescr(String descr) {
    this.descr = descr;
  }

  public BigDecimal getIsInactive() {
    return isInactive;
  }

  public void setIsInactive(BigDecimal isInactive) {
    this.isInactive = isInactive;
  }

  public List<PdfGroupEntity> getPdfgroups() {
    return pdfgroups;
  }

  public void setPdfgroups(List<PdfGroupEntity> pdfgroups) {
    this.pdfgroups = pdfgroups;
  }

  @Override
  public String toString() {
    return "PdfFileEntity{" +
        "fileName='" + fileName + '\'' +
        ", fileSize=" + fileSize +
        ", pdfGroupId=" + pdfGroupId +
        ", descr='" + descr + '\'' +
        ", isInactive=" + isInactive +
        ", pdfgroups=" + pdfgroups +
        '}';
  }
}
