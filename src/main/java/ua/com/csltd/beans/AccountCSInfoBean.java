package ua.com.csltd.beans;

import java.math.BigDecimal;

/**
 * Created with IntelliJ IDEA.
 * User: makiyov
 * Date: 19.12.13
 * Time: 16:10
 */
public class AccountCSInfoBean {

    private String accountNo;
    private BigDecimal currencyId;
    private String flexAccountNo;
    private BigDecimal openingBalance;
    private BigDecimal todayTurnoverDebit;
    private BigDecimal todayTurnoverCredit;
    private BigDecimal currBalance;
    private BigDecimal availableBalance;
    private BigDecimal blockedAmount;
    private BigDecimal limit;

    public String getAccountNo() {
        return accountNo;
    }

    public void setAccountNo(String accountNo) {
        this.accountNo = accountNo;
    }

    public BigDecimal getCurrencyId() {
        return currencyId;
    }

    public void setCurrencyId(BigDecimal currencyId) {
        this.currencyId = currencyId;
    }

    public String getFlexAccountNo() {
        return flexAccountNo;
    }

    public void setFlexAccountNo(String flexAccountNo) {
        this.flexAccountNo = flexAccountNo;
    }

    public BigDecimal getOpeningBalance() {
        return openingBalance;
    }

    public void setOpeningBalance(BigDecimal openingBalance) {
        this.openingBalance = openingBalance;
    }

    public BigDecimal getTodayTurnoverDebit() {
        return todayTurnoverDebit;
    }

    public void setTodayTurnoverDebit(BigDecimal todayTurnoverDebit) {
        this.todayTurnoverDebit = todayTurnoverDebit;
    }

    public BigDecimal getTodayTurnoverCredit() {
        return todayTurnoverCredit;
    }

    public void setTodayTurnoverCredit(BigDecimal todayTurnoverCredit) {
        this.todayTurnoverCredit = todayTurnoverCredit;
    }

    public BigDecimal getCurrBalance() {
        return currBalance;
    }

    public void setCurrBalance(BigDecimal currBalance) {
        this.currBalance = currBalance;
    }

    public BigDecimal getAvailableBalance() {
        return availableBalance;
    }

    public void setAvailableBalance(BigDecimal availableBalance) {
        this.availableBalance = availableBalance;
    }

    public BigDecimal getBlockedAmount() {
        return blockedAmount;
    }

    public void setBlockedAmount(BigDecimal blockedAmount) {
        this.blockedAmount = blockedAmount;
    }

    public BigDecimal getLimit() {
        return limit;
    }

    public void setLimit(BigDecimal limit) {
        this.limit = limit;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("AccountCSInfoBean{");
        sb.append("accountNo='").append(accountNo).append('\'');
        sb.append(", currencyId=").append(currencyId);
        sb.append(", flexAccountNo='").append(flexAccountNo).append('\'');
        sb.append(", openingBalance=").append(openingBalance);
        sb.append(", todayTurnoverDebit=").append(todayTurnoverDebit);
        sb.append(", todayTurnoverCredit=").append(todayTurnoverCredit);
        sb.append(", currBalance=").append(currBalance);
        sb.append(", availableBalance=").append(availableBalance);
        sb.append(", blockedAmount=").append(blockedAmount);
        sb.append(", limit=").append(limit);
        sb.append('}');
        return sb.toString();
    }
}
