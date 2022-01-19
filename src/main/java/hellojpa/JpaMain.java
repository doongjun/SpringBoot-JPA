package hellojpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

public class JpaMain {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {
            /**
             * 생성
             */
//            Member member = new Member();
//            member.setId(2L);
//            member.setName("HelloB");
//            em.persist(member);

            /**
             * 검색
             */
            //단건
            Member findMember = em.find(Member.class, 1L);
//            System.out.println("findMember.id : " + findMember.getId());
//            System.out.println("findMember.name : " + findMember.getName());

            //다건
            List<Member> result = em.createQuery("select m from Member as m", Member.class)
                    .setFirstResult(5)
                    .setMaxResults(8)
                    .getResultList();
            for(Member member : result) {
                System.out.println("member.id : " + member.getId());
                System.out.println("member.name : " + member.getName());
            }

            /**
             * 수정
             */
//            findMember.setName("Hello World");

            /**
             * 삭제
             */
//            em.remove(findMember);

            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }

        emf.close();
    }
}
