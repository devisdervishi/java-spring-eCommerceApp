package devis.springboot.app.controllers;

import devis.springboot.app.entity.Member;
import devis.springboot.app.services.MemberService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

@WebMvcTest(AdminMemberControllerTestinng.class)
class AdminMemberControllerTestinng {

    @MockBean
    MemberService memberService;

    @Autowired
    MockMvc mockMvc;
    Member testMember;
    @BeforeEach
    void setUp(){
        Member testMember
                =Member.builder().birthdate(new Date(2004,10,15))
                .username("devis").password("passwordtest").role("ADMIN").build();
    }
    @Test
    @WithMockUser(roles = {"ADMIN"},username = "devis",password = "devi12345")
    void saveMeember() throws Exception {

        Member member
                =Member.builder().birthdate(new Date(2004,10,15))
                .id(1l).username("devis").password("passwordtest").role("ADMIN").build();
        Mockito.when(memberService.saveMember(testMember)).thenReturn(member);
        mockMvc.perform(MockMvcRequestBuilders.post("http://localhost:8080/app/admin/member")
                .with(SecurityMockMvcRequestPostProcessors.csrf())
                .contentType(MediaType.APPLICATION_JSON)
                .content(" {\n" +
                        " \"username\":\"devis\",\n" +
                        " \"password\":passwordtest,\n" +
                        " \"birthdate\":\"2004-10-15T07:00:00.000+00:00\"\n" +
                        " \"role\":ADMIN,\n" +
                        " }")).andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    @DisplayName("When get person by id endpoint is hit right data is returned")
    void getPersonnById() throws Exception {
        Member member
                =Member.builder().birthdate(new Date(2004,10,15))
                .id(1l).username("devis").password("passwordtest").role("ADMIN").build();
        Mockito.when(memberService.getMember(1l)).thenReturn(member);
        mockMvc.perform(MockMvcRequestBuilders.get("http://localhost:8080/app/admin/member/1")
                        .contentType(MediaType.APPLICATION_JSON)).andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.username")
                        .value(member.getUsername()));
    }

}