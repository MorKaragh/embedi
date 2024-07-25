package ru.alfastrah.embedi.prodstream;

import java.math.BigDecimal;

public class TickStreamCalcResult {

    private BigDecimal premium;
    private String calcId;

    public String getCalcId() {
        return calcId;
    }

    public void setCalcId(String calcId) {
        this.calcId = calcId;
    }

    public BigDecimal getPremium() {
        return premium;
    }

    public void setPremium(BigDecimal premium) {
        this.premium = premium;
    }

}
