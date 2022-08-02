package dio.gof.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dio.gof.model.Customer;
import dio.gof.service.CostumerService;

@RestController
@RequestMapping("customers")
public class ClientRestController {
	
	@Autowired
	private CostumerService costumerService;
	
	@GetMapping
	public ResponseEntity<Iterable<Customer>> findAll() {
		return ResponseEntity.ok(costumerService.findAll());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Customer> getById(@PathVariable Long id) {
		return ResponseEntity.ok(costumerService.getById(id));
	}
	
	@PostMapping
	public ResponseEntity<Customer> create(@RequestBody Customer costumer) {
		costumerService.create(costumer);
		return ResponseEntity.ok(costumer);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Customer> update(@PathVariable Long id, @RequestBody Customer costumer) {
		costumerService.update(id, costumer);
		return ResponseEntity.ok(costumer);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		costumerService.delete(id);
		return ResponseEntity.ok().build();
	}

}
