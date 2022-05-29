package org.example.web;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.IOException;
import java.io.InputStream;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TestWebClientMockito {

    @Mock
    private ConnectionFactory factory;
    @Mock
    private InputStream mockStream;

    @Test
    void testGetContentOk() throws Exception {
        when(factory.getData()).thenReturn(mockStream);
        when(mockStream.read())
                .thenReturn((int) 'W')
                .thenReturn((int) 'o')
                .thenReturn((int) 'r')
                .thenReturn((int) 'k')
                .thenReturn((int) 's')
                .thenReturn((int) '!')
                .thenReturn(-1);

        WebClient2 client = new WebClient2();
        String workingContent = client.getContent(factory);
        assertEquals("Works!", workingContent);
    }

    @Test
    void testGetContentCannotCloseInputStream()
            throws Exception {
        when(factory.getData()).thenReturn(mockStream);
        when(mockStream.read()).thenReturn(-1);
        doThrow(new IOException("cannot close")).when(mockStream).close();

        WebClient2 client = new WebClient2();
        String workingContent = client.getContent(factory);
        assertNull(workingContent);
    }
}
