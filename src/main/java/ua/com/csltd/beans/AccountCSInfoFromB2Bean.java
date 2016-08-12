package ua.com.csltd.beans;

import java.math.BigDecimal;

/**
 * Created by valeriy_solyanik
 * on 11.07.2016.
 */
public class AccountCSInfoFromB2Bean extends AccountCSInfoBean {
  private BigDecimal id;
  private BigDecimal siteId;
  private BigDecimal summaBegin;
  private BigDecimal summaNow;
  private BigDecimal debet;
  private BigDecimal credit;
  private BigDecimal accountLimit;

  public BigDecimal getId() {
    return id;
  }

  public void setId(BigDecimal id) {
    this.id = id;
  }

  public BigDecimal getSiteId() {
    return siteId;
  }

  public void setSiteId(BigDecimal siteId) {
    this.siteId = siteId;
  }

  public BigDecimal getSummaBegin() {
    return summaBegin;
  }

  public void setSummaBegin(BigDecimal summaBegin) {
    this.summaBegin = summaBegin;
  }

  public BigDecimal getSummaNow() {
    return summaNow;
  }

  public void setSummaNow(BigDecimal summaNow) {
    this.summaNow = summaNow;
  }

  public BigDecimal getDebet() {
    return debet;
  }

  public void setDebet(BigDecimal debet) {
    this.debet = debet;
  }

  public BigDecimal getCredit() {
    return credit;
  }

  public void setCredit(BigDecimal credit) {
    this.credit = credit;
  }

  public BigDecimal getAccountLimit() {
    return accountLimit;
  }

  public void setAccountLimit(BigDecimal accountLimit) {
    this.accountLimit = accountLimit;
  }
}
