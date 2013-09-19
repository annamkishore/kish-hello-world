package kish.spring.jpadata.repo;

import kish.spring.jpadata.entities.Customer;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;


public interface CustomerRepository extends JpaRepository<Customer, Long> {

	/**
	 * Returns a page of {@link Customer}s with the given lastname.
	 * 
	 * @param lastname
	 * @param pageable
	 * @return
	 */
	public Page<Customer> findByLastname(String lastname, Pageable pageable);
}
