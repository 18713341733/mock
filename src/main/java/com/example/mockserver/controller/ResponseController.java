package com.example.mockserver.controller;
import java.util.Map;
import com.example.mockserver.entity.MockOnlyResponse;
import com.example.mockserver.entity.MockResponse;
import com.example.mockserver.service.OnlyResponseService;
import com.example.mockserver.util.ArrayUtil;
import com.example.mockserver.util.ReplaceRandomUtil;
import lombok.extern.slf4j.Slf4j;


import com.example.mockserver.model.MockContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@Slf4j
public class ResponseController {
    @Autowired
    private OnlyResponseService onlyResponseService;

    @Autowired
    private HttpServletRequest request;


    @PostMapping("/addResponse")
    public String addResponse(@RequestBody MockResponse mockResponse) throws IOException {
        onlyResponseService.addResponse(mockResponse);
        return "新增成功";

    }

    @PostMapping("/deleteResponse")
    public String deleteResponse(@RequestBody MockResponse mockResponse) throws IOException {
        onlyResponseService.deleteResponse(mockResponse.getId());
        return "删除成功";
    }

    @PostMapping("/updateResponse")
    public String updateResponse(@RequestBody MockResponse mockResponse) throws IOException {
        onlyResponseService.updateResponse(mockResponse);
        return "更新成功";
    }

    @GetMapping(value ="/allResponse",produces = MediaType.APPLICATION_JSON_VALUE)
    public List<MockResponse> allResponse() throws IOException {
        List<MockResponse> all = onlyResponseService.findAll();
        return all;
    }



//    @RequestMapping("/**")
    @GetMapping(value = "/**", produces = MediaType.APPLICATION_JSON_VALUE)
    public String doMock() throws IOException {

        log.info("请求的URI---------:"+request.getRequestURI());
        log.info("请求IP---------:"+request.getRemoteAddr());
        log.info("请求的参数---------:"+request.getParameterMap());




        // 将获取的用户数据 ip 参数 URI ，存储到 mockContext 这个类里
        MockContext mockContext = MockContext.builder()
                .requestIp(request.getRemoteAddr()) // 获取ip
                .requestParams(getParams(request.getParameterMap())) // 请求体
                .requestURI(request.getRequestURI()) // 获取请求的URI
                .requestMethod(request.getMethod()) //  请求方式
                .build();

        String response  = getResponse(mockContext);
        return response;

    }

    // 获取用户的传参，value是一个数组。这里为了将来处理方便，我们将这数组转成一个字符串。
    // 我们默认，这个数据的长度是1，那我们只需要取出来数组的第一个值就可以了。
    public Map<String,String> getParams(Map<String,String[]> parameterMap){
        Map<String,String> params = parameterMap.entrySet().stream().collect(Collectors.toMap(e -> e.getKey(), e -> ArrayUtil.getFirst(e.getValue())));
        return params;

    }


    // get 查询mock的返回结果
    public String getResponse(MockContext mockContext){

        // [name=zhangsan, classification=Language]
        List<String> userParamStringList = mockContext.getParamStringList();
        System.out.println(userParamStringList);
        Integer totalNum = 0;
        String response = "";



        // 开始遍历
        System.out.println("开始遍历");
        System.out.println(mockContext.getRequestURI());
        List<MockOnlyResponse> mockOnlyResponseList = onlyResponseService.findByUri(mockContext.getRequestURI());
        System.out.println(mockOnlyResponseList);
        for (MockOnlyResponse mockOnlyResponse : mockOnlyResponseList) {
            Integer num = 0;
            for(String str:userParamStringList){
                // 字典
                Map<String, Integer> mapParams = mockOnlyResponse.getMapParams();
                if (mapParams.containsKey(str)) {
                    num += mapParams.get(str);
                }
            }
            if(num>totalNum){
                totalNum = num;
                response = mockOnlyResponse.getResponse_content();
            }
        }
        System.out.println(">>>>>>>>>.");
        System.out.println(response);
        System.out.println(">>>>>>>>>.");
        // 随机数字/字符串处理
        response = ReplaceRandomUtil.replaceRandomFields(response);
        return response;


    }

}

