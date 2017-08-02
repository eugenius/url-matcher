package io.github.eugenius.urlmatcher;

import org.junit.Test;

import java.net.MalformedURLException;
import java.net.URL;

import static io.github.eugenius.urlmatcher.Matchers.hasProtocol;
import static io.github.eugenius.urlmatcher.Matchers.isAnUrl;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

public class MatcherAssertTest {

    @Test
    public void validUrl() {
        assertThat("http://test.com", isAnUrl());
    }

    @Test
    public void invalidUrl() {
        assertThat("qqq", not(isAnUrl()));
    }

    @Test
    public void testProtocol() {
        assertThat("http://test.com", isAnUrl().with().protocol("http"));
    }

    @Test
    public void testProtocolNot() {
        assertThat("http://test.com", not(isAnUrl().with().protocol("ftp")));
    }

    @Test
    public void testProtocolMatcherEqualTo() {
        assertThat("http://test.com", isAnUrl().with().protocol(equalTo("http")));
    }

    @Test
    public void testProtocolMatcherNotEqualTo() {
        assertThat("http://test.com", isAnUrl().with().protocol(not(equalTo("ftp"))));
    }

    @Test
    public void testNotProtocolMatcherNotEqualTo() {
        assertThat("http://test.com", not(isAnUrl().with().protocol(not(equalTo("http")))));
    }

    @Test
    public void testUrlProtocol() throws MalformedURLException {
        assertThat(new URL("http://test.com"), hasProtocol("http"));
    }

    @Test
    public void testUrlProtocolNot() throws MalformedURLException {
        assertThat(new URL("http://test.com"), not(hasProtocol("ftp")));
    }

    @Test
    public void testUrlProtocolMatcherEqualTo() throws MalformedURLException {
        assertThat(new URL("http://test.com"), hasProtocol(equalTo("http")));
    }

    @Test
    public void testUrlProtocolMatcherNotEqualTo() throws MalformedURLException {
        assertThat(new URL("http://test.com"), hasProtocol(not(equalTo("ftp"))));
    }

    @Test
    public void testNotUrlProtocolMatcherNotEqualTo() throws MalformedURLException {
        assertThat(new URL("http://test.com"), not(hasProtocol(not(equalTo("http")))));
    }

    @Test
    public void testHost() {
        assertThat("http://test.com", isAnUrl().with().host("test.com"));
    }
}