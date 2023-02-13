package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.springframework.stereotype.Repository;

import java.util.*;
//@Repository
public class MemoryMemberRepository implements MemberRepository {
private static Map<Long,Member> store = new HashMap<>(); //저장소
private static  long sequence = 0L; //임의의 키 값 생성
    @Override
    public Member save(Member member) {
        member.setId(++sequence);
        store.put(member.getId(), member); //스토어에 저장
        return member; //저장된 결과 반환
    }

    @Override
    public Optional<Member> findById(Long id) {
        return Optional.ofNullable(store.get(id)); //Optional .. null이 반환될수있는 상환에 쓰는게 좋다.
    }

    @Override
    public Optional<Member> findByName(String name) {
       return store.values().stream()
                .filter(member -> member.getName().equals(name))
                .findAny();//stream.filter 안에서 돌리기, 멤머안에서 돌리고 findAny =네임이랑 같은거 뭐라도 있을시 반환
    }

    @Override
    public List<Member> findAll() {
        return new ArrayList<>(store.values()); //store에 있는 값
    }

    public void clearStore(){
        store.clear();

    }
}
