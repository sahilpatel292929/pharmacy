package com.ets.SecurePharmacy.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.ets.SecurePharmacy.tenant.model.RouteCreationEntity;

@Service
public interface RouteCreationService {
	public List<RouteCreationEntity> getAllRoutes();

	public Page<RouteCreationEntity> getAllRouteWithPagination(int offset,int pageSize);
}
