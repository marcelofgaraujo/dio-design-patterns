package dio.gof.service;

import dio.gof.model.Customer;

public interface CostumerService {
	
	Iterable<Customer> findAll();
	
	Customer getById(Long id);
	
	void create(Customer costumer);
	
	void update(Long id, Customer costumer);
	
	void delete(Long id);

}
