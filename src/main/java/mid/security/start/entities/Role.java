package mid.security.start.entities;

import java.util.Set;

public enum Role {
    ROLE_USER(
            Set.of(
                    Permission.USER_READ,
                    Permission.USER_WRITE,
                    Permission.USER_DELETE
            )
    ),
    ROLE_ADMIN(
            Set.of(
                    Permission.USER_READ,
                    Permission.USER_WRITE,
                    Permission.USER_DELETE,
                    Permission.ADMIN_READ,
                    Permission.ADMIN_WRITE
                    )
    );

    private final Set<Permission> permissions;

    Role(Set<Permission> permissions) {
        this.permissions = permissions;
    }

    public Set<Permission> getPermissions() {
        return permissions;
    }
}
