package com.app.fuzzy.repository;

import com.app.fuzzy.entity.fuzzySearch;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface fuzzySearchRepository extends MongoRepository<fuzzySearch,Integer> {
}
