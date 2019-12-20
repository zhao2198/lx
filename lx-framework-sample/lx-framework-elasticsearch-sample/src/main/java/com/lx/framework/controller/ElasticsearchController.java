package com.lx.framework.controller;


import com.lx.framework.bean.Item;
import com.lx.framework.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

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


    @PostMapping("/update")
    public void update(){
        Item item = new Item(1000l, "苹果XSMax", " 手机",
                "小米", 3499.00, "http://image.baidu.com/13123.jpg");
        itemRepository.save(item);
    }



    @PostMapping("/batch")
    public void insertList() {
        List<Item> list = new ArrayList<>();
        list.add(new Item(2L, "坚果手机R1", " 手机", "锤子", 3699.00, "http://image.baidu.com/13123.jpg"));
        list.add(new Item(3L, "华为META10", " 手机", "华为", 4499.00, "http://image.baidu.com/13123.jpg"));
        // 接收对象集合，实现批量新增
        itemRepository.saveAll(list);
    }


    @GetMapping("/all")
    public void testQueryAll(){
        // 查找所有
        //Iterable<Item> list = this.itemRepository.findAll();
        // 对某字段排序查找所有 Sort.by("price").descending() 降序
        // Sort.by("price").ascending():升序
        Iterable<Item> list = this.itemRepository.findAll(Sort.by("price").ascending());

        for (Item item:list){
            System.out.println(item);
        }
    }




}
