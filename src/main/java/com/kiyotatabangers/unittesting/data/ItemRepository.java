package com.kiyotatabangers.unittesting.data;

import com.kiyotatabangers.unittesting.model.Item;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends JpaRepository<Item, Integer> {

}
