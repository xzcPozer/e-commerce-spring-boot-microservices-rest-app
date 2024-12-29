package com.sharf.ecommerce.customer;

import com.sharf.ecommerce.exception.AlreadyExistException;
import com.sharf.ecommerce.exception.CustomerNotFoundException;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static java.lang.String.format;

@Service
@RequiredArgsConstructor
public class CustomerService {
    private final CustomerRepository repository;
    private final CustomerMapper mapper;

    public Integer createCustomer(CustomerRequest request) {
        if(repository.existsByEmail(request.email())){
            throw new AlreadyExistException(request.email() + " already exist");
        }

        var customer = repository.save(mapper.toCustomer(request));
        return customer.getId();
    }

    public void updateCustomer(CustomerRequest request) {
        var customer = repository.findById(request.id())
                .orElseThrow(() -> new CustomerNotFoundException(
                        format("Cannot update customer:: No customer found with provided ID:: %s", request.id())
                ));
        mergeCustomer(customer, request);
        repository.save(customer);
    }

    private void mergeCustomer(Customer customer, CustomerRequest request) {
        if(StringUtils.isNotBlank(request.firstname())){
            customer.setFirstname(request.firstname());
        }
        if(StringUtils.isNotBlank(request.lastname())){
            customer.setLastname(request.lastname());
        }
        if(StringUtils.isNotBlank(request.email())){
            customer.setEmail(request.email());
        }
        if(request.street() != null){
            customer.setStreet(request.street());
        }
        if(request.zipCode() != null){
            customer.setZipCode(request.zipCode());
        }
    }

    public List<CustomerResponse> findAllCustomer() {
        return repository.findAll()
                .stream()
                .map(mapper::fromCustomer)
                .collect(Collectors.toList());
    }

    public Boolean existsById(Integer customerId) {
        return repository.findById(customerId)
                .isPresent();
    }

    public CustomerResponse findById(Integer customerId) {
        return repository.findById(customerId)
                .map(mapper::fromCustomer)
                .orElseThrow(() -> new CustomerNotFoundException(format("No customer found with the provided ID:: %s", customerId)));
    }

    public void deleteCustomer(Integer customerId) {
        repository.deleteById(customerId);
    }
}
