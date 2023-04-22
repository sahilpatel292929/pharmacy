package com.ets.SecurePharmacy.master.dao;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ets.SecurePharmacy.master.model.User;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User,Integer> {
    User findByUserName(String username);

    List<User> findByTenantIsNotNull();

    List<User> findByCreateUser(User currentUser);

    @Query(nativeQuery = true,value = "SELECT u.* from `User` u where u.tenant_id = ?1 and u.tenant_user_id is NULL")
    User getTenantUserDetails(Long id);

    List<User> findByTenantUser(User currentUser);
} 