package com.ets.SecurePharmacy.tenant.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.ets.SecurePharmacy.tenant.model.GroupCreationEntity;

@Repository
public interface GroupCreationRepository extends JpaRepository<GroupCreationEntity, Long> {

	List<GroupCreationEntity> findByGroupName(String gname);

	@Query("SELECT i FROM GroupCreationEntity i WHERE CONCAT(i.groupName) LIKE %?1%")
	public List<GroupCreationEntity> search(String keyword);

	Page<GroupCreationEntity> findAllByGroupNameContains(String name, Pageable pageable);
}
