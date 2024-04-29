package com.alperen.repository;

import com.alperen.entity.User;
import com.alperen.utility.HibernateUtility;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserRepository extends MyFactoryRepository<User,Long> {
    public UserRepository() {
        super(new User());
    }
}
