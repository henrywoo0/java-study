package basic.collection.hashset;

import basic.collection.Member;

import java.util.HashSet;
import java.util.Iterator;

public class MemberHashSet {
    private HashSet<Member> hashSet;

    public MemberHashSet() {
        hashSet = new HashSet<>();
    }

    public void addMember(Member member) {
        hashSet.add(member);
    }

    public boolean removeMember(int memberId) {
        Iterator<Member> ir = hashSet.iterator();

        while(ir.hasNext()) {
            Member member = ir.next(); // 회원을 하나씩 가져와서
            int tempId = member.getMemberId(); // 아이디 비교
            if(tempId == memberId) { // 같은 아이디인 경우
                hashSet.remove(member); // 해당 멤버 삭제
                return true;
            }
        }

        System.out.println(memberId + "에 해당하는 멤버를 찾을 수 없습니다.");
        return false;
    }
}
