package com.example.mockserver.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;



@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MockResponse {
    private long id;
    private String uri;
    private String response_content;
    private String params;

    public MockResponse(String uri, String response_content,String params) {
        this.uri = uri;
        this.response_content = response_content;
        this.params = params;
    }

    public MockResponse(String response_content,String params) {
        this.response_content = response_content;
        this.params = params;
    }


}