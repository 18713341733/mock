package com.example.mockserver.service;

import com.example.mockserver.entity.MockOnlyResponse;



import java.util.List;

public interface OnlyResponseService {
    List<MockOnlyResponse> findByUri(String uri);
}
