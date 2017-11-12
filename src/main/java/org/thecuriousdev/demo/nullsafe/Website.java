package org.thecuriousdev.demo.nullsafe;

import com.google.common.base.Preconditions;

import java.util.Optional;

public class Website {

    private static final String PARAM_URL = "url";

    private final String url;
    private final String rssFeedUrl;

    private Website(Builder builder) {
        Preconditions.checkArgument(builder.url != null, PARAM_URL);
        this.url = builder.url;
        this.rssFeedUrl = builder.rssFeedUrl;
    }

    public String getUrl() {
        return url;
    }

    public Optional<String> getRssFeedUrl() {
        return Optional.ofNullable(rssFeedUrl);
    }

    public Builder toBuilder() {
        return new Builder(this);
    }

    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder {

        private String url;
        private String rssFeedUrl;

        public Builder() {
            // Empty constructor
        }

        public Builder(Website website) {
            this.url = website.url;
            this.rssFeedUrl = website.rssFeedUrl;
        }

        public Builder withUrl(String url) {
            this.url = url;
            return this;
        }

        public Builder withRssFeedUrl(String rssFeedUrl) {
            this.rssFeedUrl = rssFeedUrl;
            return this;
        }

        public Website build() {
            Preconditions.checkState(url != null, PARAM_URL);
            return new Website(this);
        }
    }
}
