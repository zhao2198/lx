package com.lx.framework.controller;


import com.lx.framework.bean.Item;
import com.lx.framework.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ElasticsearchController {

    @Autowired
    ElasticsearchTemplate estemplate;

    @Autowired
    private ItemRepository itemRepository;


    /**
     * 创建索引
     */
    @PostMapping("/index")
    public void testCreateIndex() {
        estemplate.createIndex(Item.class);
    }

    @DeleteMapping("/index")
    public void deleteIndex() {
        estemplate.deleteIndex(Item.class);
    }

    @PostMapping("/insert")
    public void insert(){
        Item item = new Item(1000l,"小米手机7","手机","小米",1000d,"");
        itemRepository.save(item);
    }


}
