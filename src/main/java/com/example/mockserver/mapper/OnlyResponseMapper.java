package com.example.mockserver.mapper;


import com.example.mockserver.entity.MockOnlyResponse;
import org.springframework.stereotype.Repository;
import java.util.List;

import org.apache.ibatis.annotations.Select;


@Repository
public interface OnlyResponseMapper {
    @Select("select r.response_content,r.params from response r where uri = #{uri}")
    List<MockOnlyResponse> findByUri(String uri);
}
