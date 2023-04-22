package com.ets.SecurePharmacy.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.ets.SecurePharmacy.DTO.SalesInvoiceDTO;
import com.ets.SecurePharmacy.exception.RecordNotFoundException;
import com.ets.SecurePharmacy.tenant.model.SalesInvoiceDraftEntity;
import com.ets.SecurePharmacy.tenant.model.SalesInvoiceEntity;

@Service
public interface SalesInvoiceService {

	 List<SalesInvoiceEntity> getAlStockInvoice();

	 SalesInvoiceEntity getStockInvoiceById(Long id) throws RecordNotFoundException;
	 SalesInvoiceDTO createOrUpdateStockInvoice(SalesInvoiceDTO entity);
	
	 Page<SalesInvoiceEntity> getAllSalesInvoiceWithPagination(int offset,int pageSize);
	 Page<SalesInvoiceEntity> getSearch(String query, Pageable pageable);
	
	 List<SalesInvoiceDraftEntity> getAlStockInvoiceDraft();
	 SalesInvoiceDraftEntity createOrUpdateStockInvoiceDraft(SalesInvoiceDTO entity);
	 Object deleteDraftById(Long id);
}
