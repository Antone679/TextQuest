package com.quest.servlets;

import static org.mockito.Mockito.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class RestartServletTest {

    private RestartServlet restartServlet;
    @Mock
    private HttpServletRequest req;
    @Mock
    private HttpServletResponse resp;
    @Mock
    private HttpSession session;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        restartServlet = new RestartServlet();
    }
    @Test
    public void testDoPost() throws IOException {
        when(req.getSession(false)).thenReturn(session);

        restartServlet.doPost(req, resp);

        verify(session).invalidate();
        verify(resp).sendRedirect("/welcome");
    }

}