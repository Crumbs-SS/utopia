package com.ss.utopia.repo;

import com.ss.utopia.entity.User;
import com.ss.utopia.entity.UserRole;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class UserRepositoryTest {


    @Autowired UserRepository userRepository;
    @Autowired UserRoleRepository userRoleRepository;

    User user;
    UserRole userRole;

    @BeforeEach
    void createInstances(){
        user = new User("TEST",
                "USER",
                "TEST-USER",
                "TESTUSER@GMAIL.COM",
                "TESTING",
                "111"
        );


        user.setUserRole(userRoleRepository.getOne(1));
        userRepository.save(user);
    }

    @AfterEach
    void deleteInstances(){
        userRepository.delete(user);
    }


    @Test
    void authenticateUser() {
        assertNull(userRepository.authenticateUser("hello", "notpassword"));
        assertEquals(user, userRepository.authenticateUser("TEST-USER", "TESTING"));
    }

    @Test
    void getTravellers() {
        List<User> users = userRepository.getTravellers();

        Integer[] expectedRoles = new Integer[users.size()];
        Arrays.fill(expectedRoles, 2);


        Integer[] roles = users.stream()
                .map(user -> user.getUserRole().getId())
                .collect(Collectors.toList())
                .toArray(new Integer[users.size()]);

        assertArrayEquals(expectedRoles, roles);
    }

    @Test
    void getEmployees() {
        List<User> users = userRepository.getEmployees();

        Integer[] expectedRoles = new Integer[users.size()];
        Arrays.fill(expectedRoles, 3);


        Integer[] roles = users.stream()
                .map(user -> user.getUserRole().getId())
                .collect(Collectors.toList())
                .toArray(new Integer[users.size()]);

        assertArrayEquals(expectedRoles, roles);
    }


}