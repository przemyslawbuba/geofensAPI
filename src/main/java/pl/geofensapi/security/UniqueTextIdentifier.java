package pl.geofensapi.security;

import java.util.UUID;
public class UniqueTextIdentifier {
    public static String getUniqueId() {

        return UUID.randomUUID().toString() + UUID.randomUUID().toString() + UUID.randomUUID().toString();
    }
}