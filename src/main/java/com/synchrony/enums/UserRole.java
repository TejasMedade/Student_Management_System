/**
 * UserRole enum represents different roles a user can have in the system.
 * It is used for managing access control and permissions.
 *
 * @author Tejas_Medade
 */
package com.synchrony.enums;

/**
 * Enum representing different roles a user can have in the system.
 * This is typically used for managing user access and permissions.
 */
public enum UserRole {
    /**
     * The role of an admin user, with full privileges to manage students and admins.
     */
    ROLE_ADMIN,

    /**
     * The role of a student user, with limited privileges to access their own information.
     */
    ROLE_STUDENT
}
