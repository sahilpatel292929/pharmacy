package com.ets.SecurePharmacy.serviceimpl;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ets.SecurePharmacy.DTO.SaleReportSupplierWiseDTO;
import com.ets.SecurePharmacy.tenant.dao.SalesInvoiceRepository;
import com.ets.SecurePharmacy.service.SaleReportsService;
@Service
public class SaleReportsServiceImpl implements SaleReportsService {

	@Autowired
	SalesInvoiceRepository salesInvoiceRepository;
	@Override
	public List<SaleReportSupplierWiseDTO> getSaleReport(LocalDate startDate, LocalDate endDate, String itemName,
			String groupName, String mfgName, String supplierName) {
		// TODO Auto-generated method stub
		
		if(startDate !=null & endDate!=null ) {
			
			
		}
		return null;
	}

}
