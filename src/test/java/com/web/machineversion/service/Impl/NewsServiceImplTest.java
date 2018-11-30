package com.web.machineversion.service.Impl;

import com.web.machineversion.model.OV.Result;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class NewsServiceImplTest {

    @Autowired
    private NewsServiceImpl newsService;

    @Test
    public void getMatterInfoList() {
        Result result = newsService.getMatterInfoList();
        System.out.print(result);

    }

    @Test
    public void getArticleInfo() {

    }

    @Test
    public void getNewsInfoList() {
        Result result = newsService.getNewsInfoList("academic");
        System.out.print(result);
    }

    @Test
    public void getNewsType() {
    }

    @Test
    public void addNewNews() {
    }

    @Test
    public void modifyNews() {
    }

    @Test
    public void deleteNews() {
    }
}