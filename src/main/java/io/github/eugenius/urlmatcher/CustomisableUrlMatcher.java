package io.github.eugenius.urlmatcher;

import org.hamcrest.Matcher;

/**
 * {@link Matcher} implementation where URL parts can be matched with custom matchers.
 */
public interface CustomisableUrlMatcher<T> extends Matcher<T> {
    CustomisableUrlMatcher<T> with();

    CustomisableUrlMatcher<T> protocol(String protocol);

    CustomisableUrlMatcher<T> protocol(Matcher<String> matcher);

    CustomisableUrlMatcher<T> host(String host);

    CustomisableUrlMatcher<T> host(Matcher<String> matcher);
}