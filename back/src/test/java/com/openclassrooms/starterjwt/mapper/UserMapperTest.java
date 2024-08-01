package com.openclassrooms.starterjwt.mapper;

import com.openclassrooms.starterjwt.dto.UserDto;
import com.openclassrooms.starterjwt.models.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class UserMapperTest {

    @Autowired
    private UserMapper userMapper;

    @Test
    public void testToEntity() {
        UserDto userDto = new UserDto();
        userDto.setId(1L);
        userDto.setEmail("test@example.com");
        userDto.setFirstName("John");
        userDto.setLastName("Doe");
        userDto.setPassword("password");
        userDto.setAdmin(false);

        User user = userMapper.toEntity(userDto);

        assertNotNull(user);
        assertEquals(userDto.getId(), user.getId());
        assertEquals(userDto.getEmail(), user.getEmail());
        assertEquals(userDto.getFirstName(), user.getFirstName());
        assertEquals(userDto.getLastName(), user.getLastName());
        assertEquals(userDto.isAdmin(), user.isAdmin());
        assertEquals(userDto.getPassword(), user.getPassword());
    }

    @Test
    public void testToDto() {
        User user = new User();
        user.setId(1L);
        user.setEmail("test@example.com");
        user.setFirstName("John");
        user.setLastName("Doe");
        user.setPassword("password");
        user.setAdmin(false);

        UserDto userDto = userMapper.toDto(user);

        assertNotNull(userDto);
        assertEquals(user.getId(), userDto.getId());
        assertEquals(user.getEmail(), userDto.getEmail());
        assertEquals(user.getFirstName(), userDto.getFirstName());
        assertEquals(user.getLastName(), userDto.getLastName());
        assertEquals(user.isAdmin(), userDto.isAdmin());
        assertEquals(user.getPassword(), userDto.getPassword());
    }

    @Test
    public void testToEntityList() {
        UserDto userDto1 = new UserDto();
        userDto1.setId(1L);
        userDto1.setEmail("test1@example.com");
        userDto1.setFirstName("John");
        userDto1.setLastName("Doe");
        userDto1.setPassword("password");
        userDto1.setAdmin(false);

        UserDto userDto2 = new UserDto();
        userDto2.setId(2L);
        userDto2.setEmail("test2@example.com");
        userDto2.setFirstName("Jane");
        userDto2.setLastName("Doe");
        userDto2.setPassword("password");
        userDto2.setAdmin(false);

        List<UserDto> dtoList = Arrays.asList(userDto1, userDto2);
        List<User> userList = userMapper.toEntity(dtoList);

        assertNotNull(userList);
        assertEquals(2, userList.size());

        User user1 = userList.get(0);
        assertEquals(userDto1.getId(), user1.getId());
        assertEquals(userDto1.getEmail(), user1.getEmail());
        assertEquals(userDto1.getFirstName(), user1.getFirstName());
        assertEquals(userDto1.getLastName(), user1.getLastName());
        assertEquals(userDto1.isAdmin(), user1.isAdmin());
        assertEquals(userDto1.getPassword(), user1.getPassword());

        User user2 = userList.get(1);
        assertEquals(userDto2.getId(), user2.getId());
        assertEquals(userDto2.getEmail(), user2.getEmail());
        assertEquals(userDto2.getFirstName(), user2.getFirstName());
        assertEquals(userDto2.getLastName(), user2.getLastName());
        assertEquals(userDto2.isAdmin(), user2.isAdmin());
        assertEquals(userDto2.getPassword(), user2.getPassword());
    }

    @Test
    public void testToDtoList() {
        User user1 = new User();
        user1.setId(1L);
        user1.setEmail("test1@example.com");
        user1.setFirstName("John");
        user1.setLastName("Doe");
        user1.setPassword("password");
        user1.setAdmin(false);

        User user2 = new User();
        user2.setId(2L);
        user2.setEmail("test2@example.com");
        user2.setFirstName("Jane");
        user2.setLastName("Doe");
        user2.setPassword("password");
        user2.setAdmin(false);

        List<User> userList = Arrays.asList(user1, user2);
        List<UserDto> dtoList = userMapper.toDto(userList);

        assertNotNull(dtoList);
        assertEquals(2, dtoList.size());

        UserDto userDto1 = dtoList.get(0);
        assertEquals(user1.getId(), userDto1.getId());
        assertEquals(user1.getEmail(), userDto1.getEmail());
        assertEquals(user1.getFirstName(), userDto1.getFirstName());
        assertEquals(user1.getLastName(), userDto1.getLastName());
        assertEquals(user1.isAdmin(), userDto1.isAdmin());
        assertEquals(user1.getPassword(), userDto1.getPassword());

        UserDto userDto2 = dtoList.get(1);
        assertEquals(user2.getId(), userDto2.getId());
        assertEquals(user2.getEmail(), userDto2.getEmail());
        assertEquals(user2.getFirstName(), userDto2.getFirstName());
        assertEquals(user2.getLastName(), userDto2.getLastName());
        assertEquals(user2.isAdmin(), userDto2.isAdmin());
        assertEquals(user2.getPassword(), userDto2.getPassword());
    }
}
