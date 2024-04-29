package com.alperen;

import com.alperen.entity.Interest;
import com.alperen.entity.Name;
import com.alperen.entity.User;
import com.alperen.repository.InterestRepository;
import com.alperen.repository.UserRepository;
import com.alperen.utility.enums.EGender;


import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        UserRepository userRepository = new UserRepository();
        InterestRepository interestRepository = new InterestRepository();

        Name name = Name.builder().name("Alperen").surname("İkinci").build();
        User user = User.builder()
                .username("alp")
                .password("12345678")
                .name(name)
                .gender(EGender.MALE)
                .build();
        userRepository.save(user);
        Interest interest1 = Interest.builder().content("kitap okumak").user(user).build();
        Interest interest2 = Interest.builder().content("Oyun oynamak").user(user).build();


        interestRepository.save(interest1);
        interestRepository.save(interest2);
        user.getInterests().addAll(List.of(interest1,interest2));
        userRepository.update(user);
       user.getInterests().add(Interest.builder().content("asdf").user(user).build());
       userRepository.update(user);
//        user = userRepository.findById(1L).get();
        //SELECT * FROM tbl_interests where user_id = ?; ? = user.getId()

//        Name name2 = Name.builder().name("Muhammet").surname("Kaya").build();
//        User user2 = User.builder()
//                .username("mhmt")
//                .password("321")
//                .name(name2)
//                .gender(EGender.MALE)
//                .interests(List.of("Coding","Fitness","Reading"))
//                .build();

//        userRepository.saveAll(List.of(user));

//        System.out.println(userRepository.findAll());
//        userRepository.deleteByID(1L);
//        System.out.println(userRepository.findById(1L));


    }


}