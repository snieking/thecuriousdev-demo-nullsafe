package org.thecuriousdev.demo.nullsafe;

import com.google.common.base.Preconditions;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ImmutablePerson {

    private static final String PARAM_NAME = "name";
    private static final String PARAM_CITY = "city";

    private final String name;
    private final String city;
    private final List<String> favoriteDishes;
    private final Website website;

    private ImmutablePerson(Builder builder) {
        Preconditions.checkArgument(builder.name != null, PARAM_NAME);
        Preconditions.checkArgument(builder.city != null, PARAM_CITY);
        this.name = builder.name;
        this.city = builder.city;
        this.favoriteDishes = builder.favoriteDishes;
        this.website = builder.website;
    }

    public String getName() {
        return name;
    }

    public String getCity() {
        return city;
    }

    public Optional<List<String>> getFavoriteDishes() {
        return favoriteDishes != null ?
                Optional.ofNullable(new ArrayList<>(favoriteDishes)) :
                Optional.empty();
    }

    public Optional<Website> getWebsite() {
        return Optional.ofNullable(website);
    }

    public Builder toBuilder() {
        return new Builder(this);
    }

    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder {

        private String name;
        private String city;
        private List<String> favoriteDishes;
        private Website website;

        public Builder() {
            // Empty constructor
        }

        public Builder(ImmutablePerson person) {
            this.name = person.name;
            this.city = person.city;
            this.favoriteDishes = person.favoriteDishes != null ? new ArrayList<>(person.favoriteDishes) : null;

        }

        public Builder withName(String name) {
            this.name = name;
            return this;
        }

        public Builder withCity(String city) {
            this.city = city;
            return this;
        }

        public Builder withFavoriteDishes(List<String> dishes) {
            this.favoriteDishes = dishes != null ? new ArrayList<>(dishes) : null;
            return this;
        }

        public Builder withWebsite(Website website) {
            this.website = website;
            return this;
        }

        public ImmutablePerson build() {
            Preconditions.checkState(name != null, PARAM_NAME);
            Preconditions.checkState(city != null, PARAM_CITY);
            return new ImmutablePerson(this);
        }
    }
}
