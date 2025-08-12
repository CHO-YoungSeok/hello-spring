package zerothStone.hello_spring.service;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import zerothStone.hello_spring.domain.Member;
import zerothStone.hello_spring.repository.MemberRepository;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@Transactional
public class MemberServiceIntegrationTest {
    @Autowired MemberService memberService;
    @Autowired MemberRepository memberRepository;

    @Test
    void 회원가입() throws Exception {
        //given
        Member member = new Member();
        member.setName("springTestMember777");

        //when
        Long savedId = memberService.join(member);

        //thenx
        Member findMember = memberService.findOne(savedId).get();
        assertEquals(member.getName(), findMember.getName());
    }

    @Test
    void 중복_회원_예외() {
        //Given
        Member member1 = new Member();
        Member member2 = new Member();
        member1.setName("springTestMember777");
        member2.setName("springTestMember777");
        memberService.join(member1);

        //When
        IllegalStateException e = assertThrows(IllegalStateException.class,
                () -> memberService.join(member2));
        assertThat(e.getMessage()).isEqualTo("already exists");

        //Then
    }
}
