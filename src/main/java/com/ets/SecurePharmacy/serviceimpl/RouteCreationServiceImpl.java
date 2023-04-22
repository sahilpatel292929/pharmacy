package com.ets.SecurePharmacy.serviceimpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.ets.SecurePharmacy.tenant.dao.RouteCreationRepository;
import com.ets.SecurePharmacy.exception.UnableToProcessException;
import com.ets.SecurePharmacy.tenant.model.RouteCreationEntity;
import com.ets.SecurePharmacy.service.RouteCreationService;

@Service
public class RouteCreationServiceImpl implements RouteCreationService{

	@Autowired
	RouteCreationRepository routeRepo;
	
	public List<RouteCreationEntity> getAllRoutes() {
		List<RouteCreationEntity> routeList; 
		try
		{
			routeList=(List<RouteCreationEntity>) routeRepo.findAll();
		}
		catch(Exception e){
			throw new UnableToProcessException("Unable to process at this moment, Try Again! After some time");
		}
		if(routeList!=null) {
			return routeList;
		}
		else {
			return new ArrayList<RouteCreationEntity>();
		}
	}

	@Override
	public Page<RouteCreationEntity> getAllRouteWithPagination(int offset, int pageSize) {
		
		Page<RouteCreationEntity> result ;
		try {
			result = routeRepo.findAll(PageRequest.of(offset, pageSize));
		}
		catch(Exception e){
			throw new UnableToProcessException("Unable to process at this moment, Try Again! After some time");
		}

		return result;
		}
}
