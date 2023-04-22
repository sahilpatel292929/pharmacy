package com.ets.SecurePharmacy.DTO;

import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class PurchaseReportFilterDTO {

    private LocalDate startDate;

    private LocalDate endDate;

    private List<Long> itemIds;

    private List<Long> supplierIds;

}
