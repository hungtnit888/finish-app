package com.example.base;

import org.junit.jupiter.api.BeforeEach;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.ActiveProfiles;

@ActiveProfiles("test")
public abstract class BaseTest {

    @BeforeEach
    public void initMocks() {
        MockitoAnnotations.openMocks(this);
    }
} 