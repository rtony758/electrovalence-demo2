package com.tony.electrovalencedemo2.repository;

import com.tony.electrovalencedemo2.domain.Electrovalence;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;


@Mapper
public interface ElectrovalenceMapper {

    List<Electrovalence> selectById(Integer id);
}
