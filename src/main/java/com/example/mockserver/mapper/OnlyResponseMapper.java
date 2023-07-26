package com.example.mockserver.mapper;


import com.example.mockserver.entity.MockOnlyResponse;
import com.example.mockserver.entity.MockResponse;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;
import java.util.List;

import org.apache.ibatis.annotations.Select;


@Repository
public interface OnlyResponseMapper {
    @Select("select r.response_content,r.params from response r where uri = #{uri}")
    List<MockOnlyResponse> findByUri(String uri);

    // 查询所有的
    @Select("select * from response")
    List<MockResponse> findAll();

    // 增
    @Insert("insert into response (uri,response_content,params) values (#{uri},#{response_content},#{params})")
    int addResponse(MockResponse mockResponse);

    // 改
    @Update("update response set uri = #{uri},response_content = #{response_content},params = #{params} where id = #{id}")
    int updateResponse(MockResponse mockResponse);

    // 删除
    @Delete("delete from response where id = #{id}")
    int deleteResponse(long id);

}
