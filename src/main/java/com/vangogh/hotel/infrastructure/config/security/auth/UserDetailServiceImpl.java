package com.vangogh.hotel.infrastructure.config.security.auth;

import com.vangogh.hotel.domain.models.entities.Role;
import com.vangogh.hotel.domain.models.entities.Usuario;
import com.vangogh.hotel.infrastructure.config.security.jwt.JwtUtils;
import com.vangogh.hotel.infrastructure.persistence.crud.IRoleCrud;
import com.vangogh.hotel.infrastructure.persistence.crud.IUsuarioCrud;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class UserDetailServiceImpl implements UserDetailsService {

    private final JwtUtils jwtUtils;
    private final PasswordEncoder passwordEncoder;
    private final IUsuarioCrud userRepository;
    private final IRoleCrud roleRepository;

    @Override
    public UserDetails loadUserByUsername(String username) {

        Usuario usuario = userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("El usuario " + username + " no existe."));

        List<SimpleGrantedAuthority> authorityList = new ArrayList<>();

        usuario.getRoles().forEach(role -> authorityList.add(new SimpleGrantedAuthority("ROLE_".concat(role.getDescripcion().name()))));



        return new User(usuario.getUsername(), usuario.getPassword(), usuario.isEnabled(), usuario.isAccountNoExpired(), usuario.isCredentialNoExpired(), usuario.isAccountNoLocked(), authorityList);
    }

    public AuthResponse createUser(AuthCreateUserRequest createRoleRequest) {

        String username = createRoleRequest.username();
        String password = createRoleRequest.password();
        List<String> rolesRequest = createRoleRequest.roleRequest().roleListName();

        Set<Role> roleList = new HashSet<>(roleRepository.findRolesByDescripcionIn(rolesRequest));

        if (roleList.isEmpty()) {
            throw new IllegalArgumentException("The roles specified does not exist.");
        }

        Usuario usuario = Usuario.builder().username(username).password(passwordEncoder.encode(password)).roles(roleList).isEnabled(true).accountNoLocked(true).accountNoExpired(true).credentialNoExpired(true).build();

        Usuario userSaved = userRepository.save(usuario);

        ArrayList<SimpleGrantedAuthority> authorities = new ArrayList<>();

        userSaved.getRoles().forEach(role -> authorities.add(new SimpleGrantedAuthority("ROLE_".concat(role.getDescripcion().name()))));

        //SecurityContext securityContextHolder = SecurityContextHolder.getContext();
        Authentication authentication = new UsernamePasswordAuthenticationToken(userSaved, null, authorities);

        String accessToken = jwtUtils.createToken(authentication);

        return new AuthResponse(username, "User created successfully", accessToken, true);
    }

    public AuthResponse loginUser(AuthLoginRequest authLoginRequest) {

        String username = authLoginRequest.username();
        String password = authLoginRequest.password();

        Authentication authentication = this.authenticate(username, password);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        String accessToken = jwtUtils.createToken(authentication);
        return new AuthResponse(username, "User loged succesfully", accessToken, true);
    }

    public Authentication authenticate(String username, String password) {
        UserDetails userDetails = this.loadUserByUsername(username);

        if (userDetails == null) {
            throw new BadCredentialsException("Invalid username or password");
        }

        if (!passwordEncoder.matches(password, userDetails.getPassword())) {
            throw new BadCredentialsException("Incorrect Password");
        }

        return new UsernamePasswordAuthenticationToken(username, password, userDetails.getAuthorities());
    }

    public String getNombre(String username) {
        return userRepository.findByUsername(username)
                .map(value -> value.getEmpleado().getNombres()).orElse("User not found");
    }
}