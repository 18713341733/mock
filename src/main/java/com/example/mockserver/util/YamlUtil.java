package com.example.mockserver.util;

import com.example.mockserver.model.MockDataInfo;
import org.yaml.snakeyaml.Yaml;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class YamlUtil {
    // 这个工具的作用就是，读取yaml文件，将yaml文件里的内容转成一个实体类
    // 穿参path，yam文件的路径
    // 穿参Class<T> cls，要被转成的实体类
    public static <T> T readForObject(String path,Class<T> cls){
        try {
            Yaml yaml = new Yaml();
            // loadAs传参1是文件的流，传参2是要转换成哪个类的对象
            T t = yaml.loadAs(new FileInputStream(path), cls);
            return t;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            throw new IllegalArgumentException(e);
        }
    }

    public static void main(String[] args) {
        MockDataInfo mockDataInfo = readForObject("/Users/zhaohui/IdeaProjects/mock-server/src/main/resources/mock_data/get_user/a", MockDataInfo.class);
        System.out.println("mockDataInfo = " + mockDataInfo);
    }
}
