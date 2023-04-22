package com.ets.SecurePharmacy.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.ets.SecurePharmacy.tenant.model.PaymentModeEntity;

@Service("paymentModeService")
public interface PaymentModeService {

	public List<PaymentModeEntity> getAllPaymentModes();
    public Page<PaymentModeEntity> getAllPaymentModeWithPagination(int offset,int pageSize);
}