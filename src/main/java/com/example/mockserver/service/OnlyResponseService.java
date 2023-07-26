package com.example.mockserver.service;

import com.example.mockserver.entity.MockOnlyResponse;
import com.example.mockserver.entity.MockResponse;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;


import java.util.List;

public interface OnlyResponseService {
    List<MockOnlyResponse> findByUri(String uri);

    List<MockResponse> findAll();

    // 增
    int addResponse(MockResponse mockResponse);

    int updateResponse(MockResponse mockResponse);

    int deleteResponse(long id);
}
