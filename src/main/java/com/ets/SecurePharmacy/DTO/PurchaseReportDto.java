package com.ets.SecurePharmacy.DTO;

import lombok.Data;

import java.time.LocalDate;

@Data
public class PurchaseReportDto {

    private LocalDate date;

    private String supplierName;

    private String billNo;

    private String entryNo;

    private LocalDate entryDate;

    private String subTotal;

    private String discount;

    private String gst;

    private String billValue;
}
