package com.example.springwebservice.domain.item;

import com.example.springwebservice.domain.item.Item;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends JpaRepository<Item, Long> {
}
