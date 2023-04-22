package com.ets.SecurePharmacy.master.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.ets.SecurePharmacy.master.model.Role;


@Repository
public interface RoleRepository extends CrudRepository<Role, String> {

    Role findByRoleName(String role);
}
