package io.github.eugenius.urlmatcher;

import org.hamcrest.Matcher;

import java.net.URL;

/**
 * Entry point for the URL matchers.
 */
public class Matchers {

	public static CustomisableUrlMatcher<String> isAnUrl() {
		return new StringUrlMatcher();
	}

	public static CustomisableUrlMatcher<URL> hasProtocol(String protocol) {
		return new UrlMatcher().with().protocol(protocol);
	}

	public static CustomisableUrlMatcher<URL> hasProtocol(Matcher<String> matcher) {
		return new UrlMatcher().with().protocol(matcher);
	}
}
