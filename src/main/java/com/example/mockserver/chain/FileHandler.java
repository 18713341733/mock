package com.example.mockserver.chain;

import cn.hutool.core.io.FileUtil;
import com.example.mockserver.model.MockContext;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;

public class FileHandler extends AbstractHandler<MockContext,String>{
    @Override
    protected boolean preHandle(MockContext mockContext) {
        // 判断是否是文件
        return FileUtil.isFile(mockContext.getFilePath());
    }

    @Override
    protected String onHandle(MockContext mockContext) throws Exception {
        // 是文件，则处理文件
        return FileUtils.readFileToString(new File(mockContext.getFilePath()),"utf-8");
    }
}
