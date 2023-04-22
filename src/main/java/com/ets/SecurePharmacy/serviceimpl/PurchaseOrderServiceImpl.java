package com.ets.SecurePharmacy.serviceimpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ets.SecurePharmacy.tenant.dao.PurchaseOrderReposiotory;

import com.ets.SecurePharmacy.exception.RecordNotFoundException;
import com.ets.SecurePharmacy.exception.UnableToProcessException;
import com.ets.SecurePharmacy.tenant.model.PurchaseOrderEntity;
import com.ets.SecurePharmacy.service.PurchaseOrderEntryService;

@Service
public class PurchaseOrderServiceImpl implements PurchaseOrderEntryService {

	@Autowired
	PurchaseOrderReposiotory poRepo;

	@Override
	@Transactional
	public List<PurchaseOrderEntity> getAll() {
		List<PurchaseOrderEntity> result; 
		try {
			result = (List<PurchaseOrderEntity>) poRepo.findAll();
		}
		catch(Exception e){
			throw new UnableToProcessException("Unable to process at this moment, Try Again! After some time");
		}
		
		if (result.size() > 0) {
			return result;
		} else {
			return new ArrayList<PurchaseOrderEntity>();
		}
	}

	@Override
	@Transactional
	public PurchaseOrderEntity getById(Long id) throws RecordNotFoundException {
		Optional<PurchaseOrderEntity> poDetails;
		try {
			 poDetails= poRepo.findById(id);
		}
		catch(Exception e){
			throw new UnableToProcessException("Unable to process at this moment, Try Again! After some time");
		}
		
		
		if (poDetails.isPresent()) {
			return poDetails.get();
		} else {
			throw new RecordNotFoundException("No Purchase Order record exist for given id");
		}
	}

	@Override
	@Transactional
	public PurchaseOrderEntity createOrUpdate(PurchaseOrderEntity entity) {
		
		try {
			entity = poRepo.save(entity);
		}
		catch(Exception e){
			throw new UnableToProcessException("Unable to process at this moment, Try Again! After some time");
		}
		
		
		return entity;
	}

	public List<PurchaseOrderEntity> findByCriteria(Long itemsId, Long supplierDetailsId) {
		
		
		return poRepo.findAll(new Specification<PurchaseOrderEntity>() {
			/**
			* 
			*/
			private static final long serialVersionUID = 1L;

			@Override
			public Predicate toPredicate(Root<PurchaseOrderEntity> root, CriteriaQuery<?> query,
					CriteriaBuilder criteriaBuilder) {
				List<Predicate> predicates = new ArrayList<>();
				if (itemsId != null) {
					predicates
							.add(criteriaBuilder.and(criteriaBuilder.like(root.get("items_id"), "%" + itemsId + "%")));
				}
				if (supplierDetailsId != null) {
					predicates.add(criteriaBuilder
							.and(criteriaBuilder.equal(root.get("supplier_details_id"), supplierDetailsId)));
				}
				return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
			}
		});
	}

	@Override
	public Page<PurchaseOrderEntity> getAllPurchaseOrderWithPagination(int offset, int pageSize) {
		Page<PurchaseOrderEntity> result ;
		try {
			result = poRepo.findAll(PageRequest.of(offset, pageSize));
		}
		catch(Exception e){
			throw new UnableToProcessException("Unable to process at this moment, Try Again! After some time");
		}
		
		return result;

	}

	@Override
	public Page<PurchaseOrderEntity> getSearch(String query, Pageable pageable) {
		Page<PurchaseOrderEntity> result ; 
		
		try{
			result = poRepo.findAllBySupplierNameContains(query, pageable);
		}
		catch(Exception e){
			throw new UnableToProcessException("Unable to process at this moment, Try Again! After some time");
		}
		
		return result;
	}

}
