package edu.byu.cs.tweeter.server.service;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ServiceImplTest {

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void validateTokenPass() {
        ServiceImpl service = new ServiceImpl();
        service.validateToken("token");
    }

    @Test
    void validateTokenFail() {
        ServiceImpl service = new ServiceImpl();
        try {
            service.validateToken("wrong");
            fail("Should have thrown exception.");
        } catch (RuntimeException e) {
            assert true;
        }
    }

    @Test
    void validateTokenMissing() {
        ServiceImpl service = new ServiceImpl();
        try {
            service.validateToken(null);
            fail("Should have thrown exception.");
        } catch (RuntimeException e) {
            assert true;
        }
    }

    @Test
    void validateTokenEmpty() {
        ServiceImpl service = new ServiceImpl();
        try {
            service.validateToken("");
            fail("Should have thrown exception.");
        } catch (RuntimeException e) {
            assert true;
        }
    }

    @Test
    void validateTokenExpired() {
        ServiceImpl service = new ServiceImpl();
        try {
            service.validateToken("old");
            fail("Should have thrown exception.");
        } catch (RuntimeException e) {
            assert true;
        }
    }
}