package com.my.food_ordering.repository;

import com.my.food_ordering.model.Address;
import org.springframework.data.repository.CrudRepository;

public interface AddressRepository extends CrudRepository<Address, Integer> {
}
