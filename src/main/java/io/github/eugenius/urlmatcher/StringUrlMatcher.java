package io.github.eugenius.urlmatcher;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;

import java.net.MalformedURLException;
import java.net.URL;

public class StringUrlMatcher extends TypeSafeMatcher<String> implements CustomisableUrlMatcher<String> {

    private UrlMatcher urlMatcher = new UrlMatcher();

    protected boolean matchesSafely(String actual) {
        try {
            return urlMatcher.matchesSafely(new URL(actual));
        } catch (MalformedURLException e) {
            return false;
        }
    }

    public void describeTo(Description description) {
        urlMatcher.describeTo(description);
    }

    public CustomisableUrlMatcher<String> with() {
        return this;
    }

    public CustomisableUrlMatcher<String> protocol(String protocol) {
        urlMatcher.protocol(protocol);
        return this;
    }

    public CustomisableUrlMatcher<String> protocol(Matcher<String> matcher) {
        urlMatcher.protocol(matcher);
        return this;
    }

    public CustomisableUrlMatcher<String> host(String host) {
        urlMatcher.host(host);
        return this;
    }

    public CustomisableUrlMatcher<String> host(Matcher<String> matcher) {
        urlMatcher.host(matcher);
        return this;
    }
}
