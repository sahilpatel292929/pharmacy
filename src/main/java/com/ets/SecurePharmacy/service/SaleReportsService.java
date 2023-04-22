package com.ets.SecurePharmacy.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Service;

import com.ets.SecurePharmacy.DTO.SaleReportSupplierWiseDTO;

@Service
public interface SaleReportsService {

	public List<SaleReportSupplierWiseDTO> getSaleReport(LocalDate startDate, LocalDate endDate, String itemName, String groupName, String mfgName, String supplierName);

}
