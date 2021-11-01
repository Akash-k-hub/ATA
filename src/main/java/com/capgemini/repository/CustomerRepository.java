package com.capgemini.repository;

import java.time.LocalDate;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.capgemini.model.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer> {
	
	  @Query(value = "select s from Customer s Where s.firstName = :name")
	  public List<Customer> realAllName(@Param("name") String firstName);
	    
	  Customer findByEmailAndPassword(String email, String Password);
	  
	  @Query(value ="Select s from Customer s Where s.email = :email1 AND s.password = :password1 ") 
	  public Customer readByEmailAndpassword(@Param("email1") String email1, @Param("password1") String password1);

	  @Query(value = "Select a From Customer a where a.email=:email")
		public Customer readByEmail(@Param("email") String email);
	  
	  @Transactional
	  @Modifying
	  @Query(value = "Update Customer c set c.firstName =:firstname, c.lastName =:lastname, c.dateOfBirth=:DOB, c.mobileNo=:mobileno, c.email=:email, c.password=:password where c.customerId=:id ")
	  public int updateCustomer(@Param("firstname") String firstName, @Param("lastname") String lastName, @Param("DOB") LocalDate dateOfBirth, @Param("mobileno") long mobileNo, @Param("email") String email, @Param("password") String password, @Param("id") int customerId );
	  
//	  @Transactional
//		@Modifying
//		@Query(value = "Update Customer d set d.firstName = :firstName where d.customerId = :customerId")
//		int updateCustomerFirstName(@Param("customerId")int customerId, @Param("firstName") String firstName);
//
//	  @Transactional
//		@Modifying
//		@Query(value = "Update Customer d set d.lastName = :lastName where d.customerId = :customerId")
//		int updateCustomerlastName(@Param("customerId")int customerId, @Param("lastName") String lastName);
//
//
//	  @Transactional
//		@Modifying
//		@Query(value = "Update Customer d set d.mobileNo = :mobileNo where d.customerId = :customerId")
//		int updateCustomerMobileNo(@Param("customerId")int customerId, @Param("mobileNo") long mobileNo);


}
