package com.ets.SecurePharmacy.serviceimpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.ets.SecurePharmacy.tenant.dao.PaymentModeRepository;
import com.ets.SecurePharmacy.exception.UnableToProcessException;
import com.ets.SecurePharmacy.tenant.model.PaymentModeEntity;
import com.ets.SecurePharmacy.service.PaymentModeService;

@Service("paymentModeService")
public class PaymentModeServiceImpl implements PaymentModeService {

	@Autowired
	PaymentModeRepository modeRepo;
	
	public List<PaymentModeEntity> getAllPaymentModes() {
		// TODO Auto-generated method stub
		List<PaymentModeEntity> result;
		try {
			result = (List<PaymentModeEntity>) modeRepo.findAll();
		}
		catch(Exception e){
			throw new UnableToProcessException("Unable to process at this moment, Try Again! After some time");
		}
		
		if(result.size() > 0) {
			return result;
		} else {
			return new ArrayList<PaymentModeEntity>();
		}
		
	}

	@Override
	public Page<PaymentModeEntity> getAllPaymentModeWithPagination(int offset, int pageSize) {
		Page<PaymentModeEntity> result;
		try {
			 result = modeRepo.findAll(PageRequest.of(offset, pageSize));
		}
		catch(Exception e){
			throw new UnableToProcessException("Unable to process at this moment, Try Again! After some time");
		}

		return result;
	}

}
