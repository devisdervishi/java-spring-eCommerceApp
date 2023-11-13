package devis.springboot.app.config;

import devis.springboot.app.entity.Member;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public class MemberDetails implements UserDetails {

    private Member member;
    public MemberDetails(Member member){
        this.member=member;

    }
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        if (member.getRole().equals("USER")) return Arrays.asList(new SimpleGrantedAuthority("ROLE_USER"));
        else if (member.getRole().equals("ADMIN")) return  Arrays.asList(new SimpleGrantedAuthority("ROLE_ADMIN"));
        return null;

    }

    @Override
    public String getPassword() {
        return member.getPassword();
    }

    @Override
    public String getUsername() {
        return member.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
