package jpaboook.jpashop.service;

import jpaboook.jpashop.domain.item.Book;
import jpaboook.jpashop.domain.item.Item;
import jpaboook.jpashop.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ItemService {

    private final ItemRepository itemRepository;

    @Transactional
    public void saveItem(Item item){
        itemRepository.save(item);
    }

    @Transactional
    public void updateItem(Long itemId, String name ,int price, int stockQuantity){
        // 변경감지를 통해 commit
        Item findItem = itemRepository.findOne(itemId);
        findItem.setName(name);
        findItem.setPrice(price);
        findItem.setStockQuantity(stockQuantity);
    }

    public List<Item> findItem (){
        return itemRepository.findAll();
    }

    public Item findOne (Long itemId){
        return itemRepository.findOne(itemId);
    }
}
