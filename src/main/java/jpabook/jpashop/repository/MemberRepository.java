package jpabook.jpashop.repository;

import jpabook.jpashop.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository // springbean으로 자동 등록됨
@RequiredArgsConstructor // final 있는 필드만 가지고 생성자 만들어 줌
public class MemberRepository {

    //@PersistenceContext spring이 엔티티 매니저를 만들어 주입
    private final EntityManager em;

    public void save(Member member) {
        em.persist(member); // 영속성 컨텍스트에 member 엔티티 넣음
    }

    public Member findOne(Long id) {
        return em.find(Member.class, id);  // (타입,pk)
    }

    public List<Member> findAll() {
        return em.createQuery("select m from Member m", Member.class)   // from의 대상이 테이블이 아닌 엔티티
                .getResultList();
    }

    public List<Member> findByName(String name) {
        return em.createQuery("select m from Member m where m.name = :name", Member.class) // :name -> 데이터 추가될 곳
                .setParameter("name", name) // 데이터를 동적으로 바인딩
                .getResultList();
    }
}
