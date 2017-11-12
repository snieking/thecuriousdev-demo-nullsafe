package org.thecuriousdev.demo.nullsafe;

import org.junit.Test;

import static org.junit.Assert.*;

public class NullSafeTest {

    @Test(expected = IllegalStateException.class)
    public void cannotBuildWithNull() {
        ImmutablePerson.builder()
                .withName(null)
                .withCity("Stockholm")
                .build();
    }

    @Test
    public void buildAndVerifyOptionals() {
        String url = "thecuriousdev.org";
        String rssFeedUrl = "thecuriousdev.org/rss";

        ImmutablePerson p1 = ImmutablePerson.builder()
                .withName("Viktor")
                .withCity("Stockholm")
                .build();

        assertNotNull(p1.getName());
        assertNotNull(p1.getCity());
        assertFalse(p1.getFavoriteDishes().isPresent());
        assertFalse(p1.getWebsite().isPresent());

        Website w1 = Website.builder()
                .withUrl(url)
                .build();

        ImmutablePerson p2 = p1.toBuilder()
                .withWebsite(w1)
                .build();

        assertTrue(p2.getWebsite().isPresent());
        assertEquals(url, p2.getWebsite().map(Website::getUrl).orElse(null));

        Website w2 = w1.toBuilder()
                .withRssFeedUrl(rssFeedUrl)
                .build();

        ImmutablePerson p3 = p2.toBuilder()
                .withWebsite(w2)
                .build();

        assertEquals(rssFeedUrl, p3.getWebsite().flatMap(Website::getRssFeedUrl).orElse(null));
    }
}
