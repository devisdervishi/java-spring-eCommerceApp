package devis.springboot.app.services;

import devis.springboot.app.config.MemberDetails;
import devis.springboot.app.entity.Member;
import devis.springboot.app.repositories.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Optional;

public class MemberInfoDetailsService implements UserDetailsService {

    @Autowired
    MemberRepository repository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Member> member=repository.findByUsername(username);
        return member.map(MemberDetails::new).orElseThrow(()->new
                UsernameNotFoundException("User with username :"+username+"doesn't exist"));
    }
}
