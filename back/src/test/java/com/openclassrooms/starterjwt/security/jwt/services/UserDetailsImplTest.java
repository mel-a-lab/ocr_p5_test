package com.openclassrooms.starterjwt.security.jwt.services;

import com.openclassrooms.starterjwt.security.services.UserDetailsImpl;
import org.junit.jupiter.api.Test;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;
import java.util.HashSet;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class UserDetailsImplTest {

    @Test
    void testGetAuthorities() {
        UserDetailsImpl userDetails = UserDetailsImpl.builder()
                .id(1L)
                .username("user")
                .password("password")
                .build();

        Collection<? extends GrantedAuthority> authorities = userDetails.getAuthorities();
        assertThat(authorities).isEmpty();
    }

    @Test
    void testIsAccountNonExpired() {
        UserDetailsImpl userDetails = UserDetailsImpl.builder()
                .id(1L)
                .username("user")
                .password("password")
                .build();

        assertTrue(userDetails.isAccountNonExpired());
    }

    @Test
    void testIsAccountNonLocked() {
        UserDetailsImpl userDetails = UserDetailsImpl.builder()
                .id(1L)
                .username("user")
                .password("password")
                .build();

        assertTrue(userDetails.isAccountNonLocked());
    }

    @Test
    void testIsCredentialsNonExpired() {
        UserDetailsImpl userDetails = UserDetailsImpl.builder()
                .id(1L)
                .username("user")
                .password("password")
                .build();

        assertTrue(userDetails.isCredentialsNonExpired());
    }

    @Test
    void testIsEnabled() {
        UserDetailsImpl userDetails = UserDetailsImpl.builder()
                .id(1L)
                .username("user")
                .password("password")
                .build();

        assertTrue(userDetails.isEnabled());
    }

    @Test
    void testEquals() {
        UserDetailsImpl userDetails1 = UserDetailsImpl.builder()
                .id(1L)
                .username("user")
                .password("password")
                .build();

        UserDetailsImpl userDetails2 = UserDetailsImpl.builder()
                .id(1L)
                .username("user")
                .password("password")
                .build();

        UserDetailsImpl userDetails3 = UserDetailsImpl.builder()
                .id(2L)
                .username("user")
                .password("password")
                .build();

        assertEquals(userDetails1, userDetails2);
        assertNotEquals(userDetails1, userDetails3);
    }

    @Test
    void testBuilderAndGetters() {
        Long id = 1L;
        String username = "user";
        String firstName = "First";
        String lastName = "Last";
        Boolean admin = true;
        String password = "password";

        UserDetailsImpl userDetails = UserDetailsImpl.builder()
                .id(id)
                .username(username)
                .firstName(firstName)
                .lastName(lastName)
                .admin(admin)
                .password(password)
                .build();

        assertEquals(id, userDetails.getId());
        assertEquals(username, userDetails.getUsername());
        assertEquals(firstName, userDetails.getFirstName());
        assertEquals(lastName, userDetails.getLastName());
        assertEquals(admin, userDetails.getAdmin());
        assertEquals(password, userDetails.getPassword());
    }
}
