package com.caesar_84.vcardconverter.core;

import ezvcard.Ezvcard;
import ezvcard.property.Telephone;

import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.stream.Collectors;

public class VcardParser {
    private final Map<String, Set<String>> contacts = new TreeMap<>();

    public Map<String, Set<String>> getContacts(File file) throws IOException {
        var cards = Ezvcard.parse(file).all();
        cards.forEach(vcard -> contacts.put(vcard.getFormattedName().getValue(), vcard.getTelephoneNumbers().stream()
                .map(Telephone::getText)
                .collect(Collectors.toSet())));

        return contacts;
    }
}
