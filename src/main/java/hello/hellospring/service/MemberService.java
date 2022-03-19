package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

public class MemberService {
    private final MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    /**
     * 회원가입
     */
    public long join(Member member){
        //같은 이름의 회원 x
        validateMemberJoin(member);
        memberRepository.save(member);
        return member.getId();
    }

    private void validateMemberJoin(Member member) {
        memberRepository.findByName(member.getName()).ifPresent(m-> {
            throw new IllegalArgumentException("이미 존재하는 회원입니다.");
        });
    }

    /**
     * 전체 회원 조회
     */
    public List<Member> findMember(){
        return memberRepository.findAll();
    }

    public Optional<Member> findOne(Long memLongId){
        return memberRepository.findById(memLongId);
    }

}
