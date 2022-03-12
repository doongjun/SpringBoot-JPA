package jpabook.jpashop.service;

import jpabook.jpashop.domain.item.Book;
import jpabook.jpashop.repository.ItemRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class ItemServiceTest {

    @Autowired ItemService itemService;
    @Autowired ItemRepository itemRepository;

    @Test
    public void 상품저장() throws Exception {
        //given
        Book book = new Book();
        book.setName("JPA 프로그래밍");
        book.setAuthor("김영한");
        book.setPrice(30000);
        book.addStock(2);

        //when
        Long savedId = itemService.saveItem(book);

        //then
        assertEquals(book, itemRepository.findOne(savedId));
    }

}