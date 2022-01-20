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
             * 트랜잭션을 지원하는 쓰기지연
             */
            Member member1 = new Member(150L, "A");
            Member member2 = new Member(160L, "B");
            
            em.persist(member1);
            em.persist(member2);
            // 여기까지 INSERT SQL을 DB에 보내지 않는다.
            
            // 커밋하는 순간 DB에 INSERT SQL을 보낸다
            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }

        emf.close();
    }
}
