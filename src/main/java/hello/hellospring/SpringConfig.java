package hello.hellospring;

import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;
import hello.hellospring.service.MemberService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


// 스프링 빈에 서비스랑 리포지토리 등록해줌
@Configuration
public class SpringConfig {
    @Bean
    public MemberService memberService(){
        return new MemberService(memberRepository()); //스프링 빈에 등록되어 있는 맴버 리포지토리를 맴버 서비스에 등록
    }
    @Bean
    public MemberRepository memberRepository(){
    return new MemoryMemberRepository();
    }

}
