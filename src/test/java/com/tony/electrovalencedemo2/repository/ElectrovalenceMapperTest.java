package com.tony.electrovalencedemo2.repository;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


@RunWith(SpringRunner.class)
@SpringBootTest
public class ElectrovalenceMapperTest {

    @Autowired
    private ElectrovalenceMapper electrovalenceMapper;

    @Test
    public void selectById() {
        System.out.println(electrovalenceMapper.selectById(1));
    }
}