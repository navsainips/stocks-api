package com.stocks.api.stocksapi.dto;


import lombok.*;

import javax.persistence.*;

@Entity // This tells Hibernate to make a table out of this class
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Stock {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private long id;

    public String quarter;

    public String stock;

    public String date;

    public String open;

    public String high;

    public String low;

    public String close;

    public String volume;

    public String percentChangePrice;

    public String percentChangeVolumeOverLastWk;

    public String previousWeeksVolume;

    public String nextWeeksOpen;

    public String nextWeeksClose;

    public String percentChangeNextWeeksPrice;

    public String daysToNextDividend;

    public String percentReturnNextDividend;

    public String userId;

    public Stock(String quarter, String stock, String date, String open, String high, String low, String close, String volume, String percentChangePrice, String percentChangeVolumeOverLastWk, String previousWeeksVolume, String nextWeeksOpen, String nextWeeksClose, String percentChangeNextWeeksPrice, String daysToNextDividend, String percentReturnNextDividend, String userId) {
        this.quarter = quarter;
        this.stock = stock;
        this.date = date;
        this.open = open;
        this.high = high;
        this.low = low;
        this.close = close;
        this.volume = volume;
        this.percentChangePrice = percentChangePrice;
        this.percentChangeVolumeOverLastWk = percentChangeVolumeOverLastWk;
        this.previousWeeksVolume = previousWeeksVolume;
        this.nextWeeksOpen = nextWeeksOpen;
        this.nextWeeksClose = nextWeeksClose;
        this.percentChangeNextWeeksPrice = percentChangeNextWeeksPrice;
        this.daysToNextDividend = daysToNextDividend;
        this.percentReturnNextDividend = percentReturnNextDividend;

    }
}
