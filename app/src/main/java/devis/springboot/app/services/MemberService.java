package devis.springboot.app.services;

import devis.springboot.app.entity.Member;
import devis.springboot.app.exceptions.EntityNotFoundException;
import devis.springboot.app.repositories.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class MemberService {

    @Autowired
    private MemberRepository repository;
    @Bean
     private BCryptPasswordEncoder passwordEncoderForService(){
        return new BCryptPasswordEncoder(11);
    };

    public Member saveMember(Member member){
    BCryptPasswordEncoder encoder=passwordEncoderForService();
    member.setPassword(encoder.encode(member.getPassword()));
        return repository.save(member);
    }

    public Member getMember(Long id) throws EntityNotFoundException {
        Optional<Member> member=repository.findById(id);
        if (member.isEmpty()) throw new EntityNotFoundException("Member doesn't exist");
        return member.get();
    }

    @Modifying
    @Transactional
    public Member updateMemberData(Member member, Long id ) throws EntityNotFoundException {
        Optional<Member> memberToBeUpdated=repository.findById(id);
        if (memberToBeUpdated.isEmpty()) throw new EntityNotFoundException("Member doesn't exist");
        if (member.getBirthdate()!=null&&!member.getBirthdate().equals(memberToBeUpdated.get().getBirthdate())){
            memberToBeUpdated.get().setBirthdate(member.getBirthdate());
        }
        if (member.getAddress()!=null&&!member.getAddress().equals(memberToBeUpdated.get().getAddress())){
            memberToBeUpdated.get().setAddress(member.getAddress());
        }
        if(member.getUsername()!=null&&!member.getUsername().equals(memberToBeUpdated.get().getUsername())){
            memberToBeUpdated.get().setUsername(member.getUsername());
        }
        return repository.save(memberToBeUpdated.get());
    }
    @Modifying
    @Transactional
    public String deleteMember(Long id) throws EntityNotFoundException {
        Optional<Member> member=repository.findById(id);
        if (member.isEmpty()) throw new EntityNotFoundException("Member doesn't exist");
        repository.deleteById(id);
        return "Success";
    }
}
