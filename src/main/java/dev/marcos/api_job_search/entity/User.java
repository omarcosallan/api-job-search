package dev.marcos.api_job_search.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false, length = 150)
    private String name;

    @Column(unique = true, nullable = false, length = 150)
    private String email;

    @Column(nullable = false)
    private String password;

    @OneToOne(mappedBy = "owner")
    private Company company;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if (company != null) return List.of(new SimpleGrantedAuthority("ROLE_OWNER"));
        return List.of();
    }

    @Override
    public String getUsername() {
        return email;
    }
}
