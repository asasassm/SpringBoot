package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
//@Service //멤머 콘트롤러에 인식시켜줌 서비스를
public class MemberService {
 // ctrl + shift + t 테스트 바로 만들기
    private final MemberRepository memberRepository;
   // @Autowired //멤머 리포지 토리가 필요하니 스프링 컨테이너에 있는 멤머 리포지토리를 주입
    public MemberService(MemberRepository memberRepository){
        this.memberRepository = memberRepository;
    }

    public Long join(Member member){
       //중복 회원 x //ctrl + alt + v 옥셔널 만들고 지워도 옥셔널로 반환
        validateDuplicateMember(member); //중복회원 검증
        //ifPresent= null이 아니면 동작 ctrl + alt + shift + T = Refacter this 에서 method

        memberRepository.save(member);
        return member.getId();
    }

    private void validateDuplicateMember(Member member) {
        memberRepository.findByName(member.getName())
                .ifPresent(m-> {throw new IllegalStateException("이미 존재하는 회원 입니다."); });
        //ifPresent = 값이 있으면
    }

    public List<Member> findMembers(){
        return memberRepository.findAll();
    }
    public Optional<Member> findOne(Long memberId){
        return memberRepository.findById(memberId);
    }
}
