package com.basilisk;

import com.basilisk.backend.presenters.LoginPresenter;
import org.junit.jupiter.api.BeforeAll;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public class LoginTests extends Tests {

    @Autowired
    private LoginPresenter loginPresenter;

    @BeforeAll
    public static void setUp() {

    }
}
