package com.dartlinwave.platform.lawconnectplatform.iam.infrastructure.authorization.sfs.services;

import com.dartlinwave.platform.lawconnectplatform.iam.infrastructure.authorization.sfs.model.UserDetailsImpl;
import com.dartlinwave.platform.lawconnectplatform.iam.infrastructure.persistence.jpa.repositories.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * Service implementation of {@link UserDetailsService} for loading user-specific data.
 * <p>
 * Retrieves user details from the persistence layer and adapts them for Spring Security.
 * </p>
 */
@Service(value = "defaultUserDetailsService")
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    /**
     * Constructs a new {@code UserDetailsServiceImpl} with the given {@link UserRepository}.
     *
     * @param userRepository the repository used to access user data
     */
    public UserDetailsServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * Loads the user details for the given username.
     *
     * @param username the username identifying the user whose data is required
     * @return a fully populated {@link UserDetails} object
     * @throws UsernameNotFoundException if the user could not be found
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        var user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with username: " + username));

        return UserDetailsImpl.build(user);
    }
}