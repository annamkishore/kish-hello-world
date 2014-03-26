package kish.spring.jpadata.services.impl;

import java.util.List;

import kish.spring.jpadata.entities.Customer;
import kish.spring.jpadata.repo.CustomerRepository;
import kish.spring.jpadata.services.CustomerService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class CustomerServiceImpl implements CustomerService{

	@Autowired
	CustomerRepository customerRepo;
	
	@Transactional
	public void addCustomer(Customer customer) {
		customerRepo.save(customer);
	}

	@Override
	public List<Customer> listCustomers() {
		return customerRepo.findAll();
	}

	@Override
	public Page<Customer> listCustomersPage(Pageable pageable) {
		return customerRepo.findAll(pageable);
	}

	@Override
	public Customer findCustomerById(Long id) {
		return customerRepo.findOne(id);
	}
}
