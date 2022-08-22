package hello.servlet.domain.member;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

class MemberRepositoryTest {

    MemberRepository memberRepository = MemberRepository.getInstance();

    @AfterEach // 각각의 테스트 실행 후, 저장소를 초기화
    void afterEach() {
        memberRepository.clearStore();
    }

    @Test
    void save() {
        // given
        Member member = new Member("choi", 20);

        // when
        Member savedMember = memberRepository.save(member);

        // then
        Member findMember = memberRepository.findById(savedMember.getId());
        assertThat(findMember).isEqualTo(savedMember);
    }

    @Test
    void findAll() {

        // given
        Member member1 = new Member("choi", 20);
        Member member2 = new Member("kim", 25);
        Member member3 = new Member("park", 30);

        memberRepository.save(member1);
        memberRepository.save(member2);
        memberRepository.save(member3);

        // when
        List<Member> memberList = memberRepository.findAll();

        // then
        assertThat(memberList.size()).isEqualTo(3);
        assertThat(memberList).contains(member1, member2, member3);
        
    }
}