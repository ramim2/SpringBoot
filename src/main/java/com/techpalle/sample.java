package com.techpalle;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@SpringBootApplication
@RestController
@RequestMapping

public class sample {
	private final CustomerRepository customerRepository;

	public sample(CustomerRepository customerRepository) {
		this.customerRepository = customerRepository;
	}

	public static void main(String[] args) {

		SpringApplication.run(sample.class,args);
	}
	record NewCustomerRequest(Integer Id,String name,String email,Integer age){}
	@GetMapping("/")
	public List<Customer> customers(){
		return customerRepository.findAll();

	}
	@PostMapping
	public void addCustomer(@RequestBody NewCustomerRequest newCustomerRequest){
	Customer customer=new Customer();
	customer.setId(newCustomerRequest.Id);  //
	customer.setName(newCustomerRequest.name);
	customer.setEmail(newCustomerRequest.email);
	customer.setAge(newCustomerRequest.age);
	customerRepository.save(customer);
	}
	@DeleteMapping("{customerId}")
	public void deleteCustomer(@PathVariable("customerId")Integer id ){

		customerRepository.deleteById(id);
	}

}