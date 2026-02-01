package com.jd.darkincognito.education;

import java.util.ArrayList;
import java.util.List;

public class ContentProvider {

    public static List<OnionLink> getSafeOnionLinks() {
        List<OnionLink> list = new ArrayList<>();

        list.add(new OnionLink(
                "DuckDuckGo (Tor Search)",
                "Private Tor search engine. Best way to find .onion sites.",
                "https://duckduckgo.com"
        ));

        list.add(new OnionLink(
                "Ahmia (Tor Index)",
                "Search engine for Tor hidden services.",
                "https://ahmia.fi"
        ));

        list.add(new OnionLink(
                "Tor Project",
                "Official Tor documentation and downloads.",
                "https://www.torproject.org"
        ));

        list.add(new OnionLink(
                "Whonix Wiki",
                "Tor OS and privacy documentation.",
                "https://www.whonix.org"
        ));

        list.add(new OnionLink(
                "SecureDrop",
                "Whistleblower & journalist secure communication.",
                "https://securedrop.org"
        ));

        return list;
    }
}
