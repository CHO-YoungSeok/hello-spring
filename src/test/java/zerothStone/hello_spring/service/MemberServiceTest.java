package zerothStone.hello_spring.service;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import zerothStone.hello_spring.domain.Member;
import zerothStone.hello_spring.repository.MemberRepository;
import zerothStone.hello_spring.repository.MemoryMemberRepository;
import zerothStone.hello_spring.service.MemberService;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class MemberServiceTest {
    MemberService memberService;
    MemoryMemberRepository memberRepository;

    @BeforeEach
    void beforeEach() {
        memberRepository = new MemoryMemberRepository();
        memberService = new MemberService(memberRepository);
    }

    @AfterEach
    void afterEach() {
        memberRepository.clearStore();
    }

    @Test
    void 회원가입() throws Exception {
        //given
        Member member = new Member();
        member.setName("hello");

        //when
        Long savedId = memberService.join(member);

        //then
        Member findMember = memberService.findOne(savedId).get();
        assertEquals(member.getName(), findMember.getName());
    }

    @Test
    void 중복_회원_예외() {
        //Given
        Member member1 = new Member();
        Member member2 = new Member();
        member1.setName("hello");
        member2.setName("hello");
        memberService.join(member1);

        //When
        IllegalStateException e = assertThrows(IllegalStateException.class,
                () -> memberService.join(member2));
        assertThat(e.getMessage()).isEqualTo("already exists");

        //Then
    }

    @Test
    void findMembers() {

    }

    @Test
    void findOne() {

    }
}