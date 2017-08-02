package io.github.eugenius.urlmatcher;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;

public class UrlMatcher extends TypeSafeMatcher<URL> implements CustomisableUrlMatcher<URL> {

    private List<Matcher<String>> protocolMatchers = new ArrayList<Matcher<String>>();
    private List<Matcher<String>> userInfoMatchers = new ArrayList<Matcher<String>>();
    private List<Matcher<String>> hostMatchers = new ArrayList<Matcher<String>>();
    private List<Matcher<Integer>> portMatchers = new ArrayList<Matcher<Integer>>();
    private List<Matcher<String>> authorityMatchers = new ArrayList<Matcher<String>>();
    private List<Matcher<String>> pathMatchers = new ArrayList<Matcher<String>>();
    private List<Matcher<String>> queryMatchers = new ArrayList<Matcher<String>>();
    private List<Matcher<String>> refMatchers = new ArrayList<Matcher<String>>();

    protected boolean matchesSafely(URL actual) {
        if (!areMatchersMatching(protocolMatchers, actual.getProtocol())) return false;
        if (!areMatchersMatching(userInfoMatchers, actual.getUserInfo())) return false;
        if (!areMatchersMatching(hostMatchers, actual.getHost())) return false;
        if (!areMatchersMatching(portMatchers, actual.getPort())) return false;
        if (!areMatchersMatching(authorityMatchers, actual.getAuthority())) return false;
        if (!areMatchersMatching(pathMatchers, actual.getPath())) return false;
        if (!areMatchersMatching(queryMatchers, actual.getQuery())) return false;
        if (!areMatchersMatching(refMatchers, actual.getRef())) return false;

        return true;
    }

    private <T> boolean areMatchersMatching(List<Matcher<T>> matchers, T value) {
        if (!matchers.isEmpty()) {
            for (Matcher<T> matcher : matchers) {
                if (!matcher.matches(value)) {
                    return false;
                }
            }
        }
        return true;
    }

    public void describeTo(Description description) {
        description.appendText("url");
        describeMatchers(description, protocolMatchers, "protocol");
        describeMatchers(description, userInfoMatchers, "user info");
        describeMatchers(description, hostMatchers, "host");
        describeMatchers(description, portMatchers, "port");
        describeMatchers(description, authorityMatchers, "authority");
        describeMatchers(description, pathMatchers, "path");
        describeMatchers(description, queryMatchers, "query");
        describeMatchers(description, refMatchers, "ref");
    }

    private <T> void describeMatchers(Description description, List<Matcher<T>> matchers, String name) {
        if (!matchers.isEmpty()) {
            description.appendText(" with " + name);
            for (Matcher<T> matcher : matchers) {
                description.appendText(" ").appendDescriptionOf(matcher);
            }
        }
    }

    public CustomisableUrlMatcher<URL> with() {
        return this;
    }

    private CustomisableUrlMatcher<URL> addMatcher(Matcher<String> matcher, List<Matcher<String>> matchers) {
        matchers.add(matcher);
        return this;
    }

    public CustomisableUrlMatcher<URL> protocol(String protocol) {
        return protocol(equalTo(protocol));
    }

    public CustomisableUrlMatcher<URL> protocol(Matcher<String> matcher) {
        return addMatcher(matcher, protocolMatchers);
    }

    public CustomisableUrlMatcher<URL> host(String host) {
        return host(equalTo(host));
    }

    public CustomisableUrlMatcher<URL> host(Matcher<String> matcher) {
        return addMatcher(matcher, hostMatchers);
    }
}
