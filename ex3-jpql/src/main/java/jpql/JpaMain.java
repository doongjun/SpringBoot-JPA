package jpql;

import javax.persistence.*;
import java.util.List;

public class JpaMain {

    public static void main(String[] args) {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {

            Member member = new Member();
            member.setUsername("member1");
            member.setAge(10);
            em.persist(member);

            em.flush();
            em.clear();

            List<Object[]> objectList = em.createQuery("select m.username, m.age from Member m")
                    .getResultList();
            Object[] object = objectList.get(0);
            System.out.println("username = " + object[0]);
            System.out.println("age = " + object[1]);

            List<MemberDTO> dtoList = em.createQuery("select new jpql.MemberDTO(m.username, m.age) from Member m", MemberDTO.class)
                    .getResultList();

            MemberDTO memberDTO = dtoList.get(0);
            System.out.println("MemberDTO.name = " + memberDTO.getUsername());
            System.out.println("MemberDTO.age = " + memberDTO.getAge());

            tx.commit();
        } catch (Exception e) {
            e.printStackTrace();
            tx.rollback();
        } finally {
            em.close();
        }

        emf.close();
    }
}
