package com.ets.SecurePharmacy.service;

import java.util.List;

import com.ets.SecurePharmacy.DTO.PurchaseReportDto;
import com.ets.SecurePharmacy.DTO.PurchaseReportFilterDTO;
import com.ets.SecurePharmacy.tenant.model.PurchaseEntryEntityDraft;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.ets.SecurePharmacy.exception.RecordNotFoundException;
import com.ets.SecurePharmacy.tenant.model.PurchaseEntryEntity;

@Service
public interface PurchaseEntryService {

	public List<PurchaseEntryEntity> getAllAccounts();

	public PurchaseEntryEntity getPurchaseById(Long id) throws RecordNotFoundException;

	public PurchaseEntryEntity createOrUpdateAccounts(PurchaseEntryEntity entity);

	public String getPurchaseDetailByInvoice(String invoice);
	public List<PurchaseEntryEntity> getLastPuchaseDetails(Long id);
	
	public Page<PurchaseEntryEntity> getAllPurchaseOrderWithPagination(int offset, int pageSize);

	public Page<PurchaseEntryEntity> getSearch(String query, Pageable pageable);

	Page<PurchaseReportDto> getPurchaseReport(PurchaseReportFilterDTO filter, Pageable pageable);
	
	public String getPurchaseDetailByInvoiceAndSupplier(String invoice, Long id);

    PurchaseEntryEntityDraft createOrUpdateDraft(PurchaseEntryEntityDraft pkgObj);

	List<PurchaseEntryEntityDraft> getDraftDetails();

	Object deleteDraftById(Long id);
}
