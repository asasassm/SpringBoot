package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;

public class MemoryMemberRepositoryTest {

    MemoryMemberRepository repository = new MemoryMemberRepository();
    @AfterEach
    public void afterEach(){ //테스트 하나 마다 저장소 비움
        repository.clearStore();
    }
    @Test
    public void save() {
        Member member = new Member();
        member.setName("spring");

        repository.save(member);//저장소에 멤머 저장
        Member result = repository.findById(member.getId()).get();// result에 get으로 꺼내기
      //  System.out.println("result = "+(result == member)); //검증하려고 출력 해봄

        Assertions.assertEquals(member,result);
        // 비교하는 거 (,)안의 맴버 값을 기대하고 result값과 비교해서 기대한 값이 맞는지
        assertThat(member).isEqualTo(result);
        //똑같이 비교  알트 엔터해서 Asssert 임포트

    }

    @Test
    public void findByName() {
        //given
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);
        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);
        //when
        Member result = repository.findByName("spring1").get();
        //then
        assertThat(result).isEqualTo(member1);
    }

    @Test
    public void findAll(){

        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member(); //Shift + f6 으로 변수 이름 한변에 변경
        member2.setName("spring1");
        repository.save(member2);

        List<Member> result = repository.findAll();
        assertThat(result.size()).isEqualTo(2);

    }
}
