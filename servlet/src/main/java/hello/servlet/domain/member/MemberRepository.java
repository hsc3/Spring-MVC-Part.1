package hello.servlet.domain.member;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 동시성 문제가 고려되어 있지 않음, 실무에서는 ConcurrentHashMap, AtomicLong 사용 고려
 */
public class MemberRepository {

    // static 변수는 클래스 객체가 여러 개 존재해도, 오직 하나의 static 변수를 가지고 공유한다.
    // 여기서는 MemberRepository 타입의 객체가 싱글톤으로 관리되므로, static 키워드 없이도 1개의 변수를 갖게 된다.
    private static Map<Long, Member> store = new HashMap<>();
    private static long sequence = 0L; // member id

    private static final MemberRepository instance = new MemberRepository(); // 싱글톤

    private MemberRepository() {}
    public static MemberRepository getInstance() {
        return instance;
    }

    // 회원 생성
    public Member save(Member member) {
        member.setId(++sequence);
        store.put(member.getId(), member);
        return member;
    }
    
    // id를 기준으로 회원 조회
    public Member findById(Long id) {
        return store.get(id); // key 값 기준으로 map 조회
    }

    // 모든 회원 조회
    public List<Member> findAll() {
        return new ArrayList<>(store.values());
    }
    
    // 회원 저장소 초기화
    public void clearStore() {
        store.clear();
    }
}