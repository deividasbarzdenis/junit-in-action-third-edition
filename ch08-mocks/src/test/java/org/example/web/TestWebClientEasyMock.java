package org.example.web;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.InputStream;

import static org.easymock.EasyMock.expect;
import static org.easymock.classextension.EasyMock.replay;
import static org.easymock.classextension.EasyMock.verify;
import static org.easymock.classextension.EasyMock.createMock;
import static org.junit.jupiter.api.Assertions.*;

class TestWebClientEasyMock {

    private ConnectionFactory factory;
    private InputStream stream;

    @BeforeEach
    public void setUp() {
        factory = createMock("factory", ConnectionFactory.class);
        stream = createMock("stream", InputStream.class);
    }

    @Test
    void testGetConnectionOk() throws Exception {
        expect(factory.getData()).andReturn(stream);
        expect(stream.read()).andReturn(new Integer((byte) 'W'));
        expect(stream.read()).andReturn(new Integer((byte) 'o'));
        expect(stream.read()).andReturn(new Integer((byte) 'r'));
        expect(stream.read()).andReturn(new Integer((byte) 'k'));
        expect(stream.read()).andReturn(new Integer((byte) 's'));
        expect(stream.read()).andReturn(new Integer((byte) '!'));
        expect(stream.read()).andReturn(-1);
        stream.close();

        replay(factory);
        replay(stream);

        WebClient2 client = new WebClient2();
        String workingContent = client.getContent(factory);

        assertEquals("Works!", workingContent);
    }

    @Test
    void testGetContentCannotCloseInputStream() throws Exception {
        expect(factory.getData()).andReturn(stream);
        expect(stream.read()).andReturn(-1);
        stream.close();

        replay(factory);
        replay(stream);
        WebClient2 client = new WebClient2();
        String workingContent = client.getContent(factory);

        assertNull(workingContent);
    }

    @AfterEach
    void tearDown() {
        verify(factory);
        verify(stream);
    }
}
