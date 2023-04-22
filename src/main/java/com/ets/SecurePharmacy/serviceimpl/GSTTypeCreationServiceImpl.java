package com.ets.SecurePharmacy.serviceimpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.ets.SecurePharmacy.tenant.dao.GSTTypeCreationRepository;
import com.ets.SecurePharmacy.exception.UnableToProcessException;
import com.ets.SecurePharmacy.tenant.model.GSTTypeCreationEntity;
import com.ets.SecurePharmacy.service.GSTTypeCreationService;

@Service
public class GSTTypeCreationServiceImpl implements GSTTypeCreationService {

	@Autowired
	GSTTypeCreationRepository gstType;

	public List<GSTTypeCreationEntity> getAllGSTType() {
		// TODO Auto-generated method stub
		List<GSTTypeCreationEntity> getList ;
		try {
			getList= (List<GSTTypeCreationEntity>) gstType.findAll();
		}
		
		catch(Exception e){
			throw new UnableToProcessException("Unable to process at this moment, Try Again! After some time");
		}
		
		
		if (getList != null) {
			return getList;
		} else {
			return new ArrayList<GSTTypeCreationEntity>();
		}
	}

	@Override
	public Page<GSTTypeCreationEntity> getAllGSTTypeWithPagination(int offset, int pageSize) {
		Page<GSTTypeCreationEntity> result;
		try {
			result = gstType.findAll(PageRequest.of(offset, pageSize));
		}
		catch(Exception e){
			throw new UnableToProcessException("Unable to process at this moment, Try Again! After some time");
		}
		

		return result;
	}

}
