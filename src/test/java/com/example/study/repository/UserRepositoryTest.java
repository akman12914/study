package com.example.study.repository;


import com.example.study.UserApplicationTests;
import com.example.study.model.entity.User;
import org.apache.catalina.Store;
import org.junit.Assert;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDateTime;
import java.util.Optional;

// JPA 테스트 관련 컴포넌트만 Import
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)    // 실제 db 사용
@DisplayName("ItemRepositoryTest 테스트")
public class UserRepositoryTest extends UserApplicationTests{

    //Dependency Injection
    @Autowired
    private UserRepository userRepository;

    @Test
    public void create(){

        User user = new User();
        user.setAccount("TestUser02");
        user.setCreatedAt(LocalDateTime.now());
        user.setCreatedBy("admin");

        User newUser = userRepository.save(user);
        System.out.println("newUser : "+newUser);
    }

    @Test
    public void read(){
        Optional<User> user = userRepository.findById(5L);

        user.ifPresent(selectUser ->{
            System.out.println("user: " + selectUser);
        });
    }

    @Test
    public void update(){
        Optional<User> user = userRepository.findById(5L);

        user.ifPresent(selectUser ->{
            selectUser.setAccount("PPPP");
            selectUser.setUpdatedAt(LocalDateTime.now());


            userRepository.save(selectUser);
        });
    }

    @Test
    public void delete(){
        Optional<User> user = userRepository.findById(5L);

        Assert.assertTrue(user.isPresent());

        user.ifPresent((selectUser->{
            userRepository.delete(selectUser);
        }));

        Optional<User> deleteUser = userRepository.findById(5L);

        Assert.assertFalse(deleteUser.isPresent());
    }


}