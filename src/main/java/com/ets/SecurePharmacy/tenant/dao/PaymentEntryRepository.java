package com.ets.SecurePharmacy.tenant.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ets.SecurePharmacy.tenant.model.PaymentEntryEntity;

@Repository
public interface PaymentEntryRepository extends JpaRepository<PaymentEntryEntity, Long> {

	@Query("SELECT i FROM PaymentEntryEntity i WHERE CONCAT(i.paymentDate) LIKE %?1%")
	public List<PaymentEntryEntity> search(String keyword);

	Page<PaymentEntryEntity> findAllByPaymentDateContains(String name, Pageable pageable);
}
