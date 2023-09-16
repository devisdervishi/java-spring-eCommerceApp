package devis.springboot.app.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import lombok.*;
import org.springframework.data.annotation.PersistenceConstructor;
import org.springframework.stereotype.Component;

import java.util.Date;


@Entity
@Component
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class Member {
@SequenceGenerator(
            name = "member_id_sequence", sequenceName = "member_id_sequence", allocationSize = 1)
@Id
@GeneratedValue(generator = "member_id_sequence",strategy = GenerationType.SEQUENCE)
 private Long id;
@NotNull

 private String username;
@NotNull
 private String fullname;
@Email
 private String email;
 private String role;
 private String password;
 private String phone_no;
 private String gender;
 private String address;

 @Past
 private Date birthdate;


}
