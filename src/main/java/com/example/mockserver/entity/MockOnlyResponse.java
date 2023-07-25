package com.example.mockserver.entity;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.Map;


@Data
@NoArgsConstructor
public class MockOnlyResponse {
    private String response_content;
    private String params;

    public MockOnlyResponse(String response_content,String params) {
        this.response_content = response_content;
        this.params = params;
    }

    public Map<String, Integer> getMapParams() {
        // 将接口拿到的数据库中的请求体，设置为字典
        return getMap(this.params);

    }

    // 将字符串直接转成字典
    public Map<String, Integer> getMap(String jsonString) {
//        String jsonString = "{\"name=book\":1,\"classification=Language\":5}";

        try {
            ObjectMapper objectMapper = new ObjectMapper();
            Map<String, Integer> dictionary = objectMapper.readValue(jsonString, Map.class);

            return dictionary;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;

    }

}
