package com.basilisk;

import com.basilisk.backend.presenters.MenuBarPresenter;
import org.junit.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public class MenuBarTests extends Tests {

    @Autowired
    private MenuBarPresenter menuBarPresenter;

    @Before
    public void setUp() {

    }
}

