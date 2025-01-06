/**
 * ProfileStatus enum represents the status of a user's profile.
 * It is used to indicate whether a user's profile is active or inactive.
 *
 * @author Tejas_Medade
 */
package com.synchrony.enums;

/**
 * Enum representing the status of a profile, indicating whether the profile is currently active or inactive.
 * This is commonly used in managing user access and profile visibility.
 */
public enum ProfileStatus {
    /**
     * The profile is active and the user has full access to the system.
     */
    ACTIVE,

    /**
     * The profile is inactive and the user does not have access to the system.
     */
    INACTIVE
}
