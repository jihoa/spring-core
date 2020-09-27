package hello.core;

import hello.core.member.Grade;
import hello.core.member.Member;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;

/**
* @date : 2020-09-26
* @see :
 * 순수한 자바 코드로만 개발한 로직을 테스트하기 위한 클래스
 * 어플케이션을 자바 main method로만 테스트하는 것은 한계가 있다.
 * 따라서 앞으로는 Junit을 사용한다.
**/

public class MemberApp {
    public static void main(String[] args) {
        AppConfig appConfig = new AppConfig();
        MemberService memberService = appConfig.memberService();

        Member member = new Member(1L, "memberA", Grade.VIP);
        memberService.join(member);

        Member findMember = memberService.findMember(1L);
        System.out.println("new member = " + member.getName());
        System.out.println("find member = " + findMember.getName());
    }
}
