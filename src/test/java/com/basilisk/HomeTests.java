package com.basilisk;

import com.basilisk.backend.presenters.HomePresenter;
import org.junit.Before;
import org.springframework.beans.factory.annotation.Autowired;

public class HomeTests extends Tests {

    @Autowired
    private HomePresenter homePresenter;

    @Before
    public void setUp() {

    }
}
