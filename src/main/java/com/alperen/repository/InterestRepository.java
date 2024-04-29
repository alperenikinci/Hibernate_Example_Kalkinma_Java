package com.alperen.repository;

import com.alperen.entity.Interest;

public class InterestRepository extends MyFactoryRepository<Interest,Long> {
    public InterestRepository() {
        super(new Interest());
    }
}
