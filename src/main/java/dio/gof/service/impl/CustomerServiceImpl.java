package dio.gof.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dio.gof.model.Address;
import dio.gof.model.AddressRepository;
import dio.gof.model.Customer;
import dio.gof.service.CostumerService;
import dio.gof.service.ViaCepService;
import dio.gof.model.CustomerRepository;

@Service
public class CustomerServiceImpl implements CostumerService {
	
	// Singleton: inject Spring components with @Autowired
	@Autowired
	private CustomerRepository customerRepository;
	@Autowired
	private AddressRepository addressRepository;
	@Autowired
	private ViaCepService viaCepService;
	
	private void saveCustomerByCep(Customer customer) {
		String cep = customer.getAdress().getCep();
		Address address = addressRepository.findById(cep).orElseGet(() -> {
			Address newAddress = viaCepService.findCep(cep);
			addressRepository.save(newAddress);
			return newAddress;
		});
		customer.setAdress(address);
		customerRepository.save(customer);
	}
	
	// Strategy: implement the methods defined in interface
	
	// Facade: abstract integrations with subsystems, providing a simple interface

	@Override
	public Iterable<Customer> findAll() {
		return customerRepository.findAll();
	}

	@Override
	public Customer getById(Long id) {
		Optional<Customer> customer = customerRepository.findById(id);
		return customer.get();
	}

	@Override
	public void create(Customer customer) {
		saveCustomerByCep(customer);
	}

	@Override
	public void update(Long id, Customer customer) {
		Optional<Customer> customerDB = customerRepository.findById(id);
		if (customerDB.isPresent()) {
			saveCustomerByCep(customer);
		}
	}

	@Override
	public void delete(Long id) {
		customerRepository.deleteById(id);
		
	}

}
