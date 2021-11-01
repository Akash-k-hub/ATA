package com.capgemini.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.capgemini.model.Admin;

@Repository
public interface AdminRepository extends JpaRepository<Admin, Integer> {
	// public List<Admin> readAdminsByAdminName(String firstName);

	@Query(value = "Select a From Admin a where a.firstName=:name")
	public List<Admin> readAllName(@Param("name") String firstName);

	Admin findByEmailAndPassword(String email, String password);

	@Query(value = "Select s From Admin s where s.email=:email1 AND s.password=:password1")
	public Admin readByEmailAndPassword(@Param("email1") String email1, @Param("password1") String password1);

	@Query(value = "Select a From Admin a where a.email=:email")
	public Admin readByEmail(@Param("email") String email);

	@Transactional
	@Modifying
	@Query(value = "Update Admin a set a.firstName =:firstname, a.lastName =:lastname, a.dateOfBirth=:DOB, a.mobileNo=:mobileno, a.email=:email, a.password=:password where a.adminId=:id ")
	public int updateCustomer(@Param("firstname") String firstName, @Param("lastname") String lastName,
			@Param("DOB") LocalDate dateOfBirth, @Param("mobileno") long mobileNo, @Param("email") String email,
			@Param("password") String password, @Param("id") int adminId);

}
