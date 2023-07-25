package com.example.mockserver.service.imp;
import com.example.mockserver.entity.MockOnlyResponse;
import com.example.mockserver.mapper.OnlyResponseMapper;
import com.example.mockserver.service.OnlyResponseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ResponseServiceImpl implements OnlyResponseService {

    @Autowired
    private OnlyResponseMapper onlyMapper;

    @Override
    public List<MockOnlyResponse> findByUri(String uri) {
        return onlyMapper.findByUri(uri);
    }

}
