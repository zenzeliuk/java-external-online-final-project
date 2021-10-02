package com.epam.rd.java.basic.controller.command.auth;

import com.epam.rd.java.basic.controller.Page;
import com.epam.rd.java.basic.controller.util.PathPageManager;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;


public class SingInCommandGETTest {

    private HttpServletRequest request;
    private HttpServletResponse response;
    private SingInCommandGET singInCommandGET;

    @Before
    public void setUp() {
        request = mock(HttpServletRequest.class);
        response = mock(HttpServletResponse.class);
        singInCommandGET = new SingInCommandGET();
    }

    @Test
    public void execute() {
        Page expected = new Page(PathPageManager.getProperty("page.sign-in")).setDispatchType(Page.DispatchType.FORWARD);
        assertEquals(expected, singInCommandGET.execute(request, response));
    }


    @After
    public void tearDown() {
        response = null;
        request = null;
        singInCommandGET = null;
    }
}