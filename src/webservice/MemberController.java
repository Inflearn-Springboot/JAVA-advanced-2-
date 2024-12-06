package webservice;

import io.member.Member;
import io.member.MemberRepository;
import was.httpserver.HttpResponse;
import was.httpserver.servlet.annotation.Mapping;

import java.util.List;

public class MemberController {
    private final MemberRepository memberRepository;

    public MemberController(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Mapping("/")
    public void home(HttpResponse response){
        String str = "<html><body>" +
                "<h1>Member Manager</h1>" +
                "<ul" +
                "<li><a href='/members'>Member List</a></li>" +
                "<li><a href='/add-member-form'>Add New Member</a></li>" +
                "</ul>";

        response.writeBody(str);
    }

    @Mapping("/members")
    public void members(HttpResponse response){
        List<Member> members = memberRepository.findAll();

        StringBuilder page = new StringBuilder();
        page.append("<html><body>");
        page.append("<h1>Member List</h1>");
        page.append("<ul>");
        page.append("</ul>");
        page.append("<a href='/'>Back to Home</a>");
        page.append("</body></html");
        response.writeBody(page.toString());
    }
}
