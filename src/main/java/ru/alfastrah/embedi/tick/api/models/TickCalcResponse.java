package ru.alfastrah.embedi.tick.api.models;

import java.math.BigDecimal;
import java.util.UUID;

public class TickCalcResponse {

    private UUID id;
    private BigDecimal premium;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public BigDecimal getPremium() {
        return premium;
    }

    public void setPremium(BigDecimal premium) {
        this.premium = premium;
    }

}
