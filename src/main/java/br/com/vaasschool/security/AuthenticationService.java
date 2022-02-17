package br.com.vaasschool.security;

import br.com.vaasschool.repository.UserRespository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService implements UserDetailsService {

    private final UserRespository userRespository;

    public AuthenticationService(UserRespository userRespository) {
        this.userRespository = userRespository;
    }

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        return userRespository.findByEmail(userName)
                .orElseThrow(() -> new UsernameNotFoundException(String.format("User name %s not found", userName)));
    }
}
