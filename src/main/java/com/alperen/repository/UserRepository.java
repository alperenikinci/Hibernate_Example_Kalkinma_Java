package com.alperen.repository;

import com.alperen.entity.Interest;
import com.alperen.entity.User;
import com.alperen.utility.HibernateUtility;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.List;


public class UserRepository extends MyFactoryRepository<User,Long> {

    private EntityManager entityManager;
    public UserRepository() { //Constructor related/based Dependency Injection
        super(new User());
        this.entityManager = HibernateUtility.getSessionFactory().createEntityManager();
    }

    //NATIVE QUERY
    public List<Interest> findUsersInterests(User user){
//        List<Interest> interestList = new ArrayList<>();
        String sql = "SELECT i.*\n" +
                "FROM tbl_interest i\n" +
                "JOIN tbl_user u ON i.userid = u.id\n" +
                "WHERE u.id = :userId";
        TypedQuery<Interest> interestTypedQuery = (TypedQuery<Interest>) entityManager.createNativeQuery(sql, Interest.class);
        interestTypedQuery.setParameter("userId",user.getId());

//        interestList = interestTypedQuery.getResultList();
        return interestTypedQuery.getResultList();
    }
}
