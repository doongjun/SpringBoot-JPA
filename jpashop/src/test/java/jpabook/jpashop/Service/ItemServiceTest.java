package jpabook.jpashop.Service;

import jpabook.jpashop.domain.item.Book;
import jpabook.jpashop.exception.NotEnoughStockException;
import jpabook.jpashop.repository.ItemRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class ItemServiceTest {

    @Autowired
    ItemService itemService;
    @Autowired
    ItemRepository itemRepository;
    @Autowired
    EntityManager em;

    @Test
    public void 상품등록 () throws Exception {
        //given
        Book book = new Book();
        book.setName("총균쇠");

        //when
        Long savedId = itemService.saveItem(book);

        //then
        assertEquals(book, itemService.findOne(savedId));
    }

    @Test(expected = NotEnoughStockException.class)
    public void 감소_재고_예외 () throws Exception {
        //given
        Book book = new Book();
        book.setName("총균쇠");
        book.addStock(1);

        //when
        book.removeStock(2);    //예외 발생

        //then
        fail("예외가 발생해야 한다.");
    }


}
