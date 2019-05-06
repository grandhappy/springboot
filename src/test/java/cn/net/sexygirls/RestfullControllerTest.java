package cn.net.sexygirls;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

/**
 * @Description:测试Controller
 * @Author: zule
 * @Date: 2019/5/5
 */
//SpringBoot1.4版本之前用的是SpringJUnit4ClassRunner.class
//用SpringRunner来运行,SpringJUnit4ClassRunner 和 SpringRunner 区别是什么？SpringRunner is an alias for the SpringJUnit4ClassRunner
@RunWith(SpringRunner.class)
//SpringBoot1.4版本之前用的是@SpringApplicationConfiguration(classes = Application.class)
//@SpringBootTest是SpringBoot的一个用于测试的注解，通过SpringApplication在测试中创建ApplicationContext。
@SpringBootTest(classes = App.class)
//测试环境使用，用来表示测试环境使用的ApplicationContext将是WebApplicationContext类型的
//@WebAppConfiguration
//方法1：@AutoConfigureMockMvc是用于自动配置MockMvc;方法2：手动配置MockMvc
//是用于自动配置MockMvc
@AutoConfigureMockMvc
public class RestfullControllerTest {

    @Autowired
    private MockMvc mockMvc;
    /*@MockBean
    private XXXDao xxxtDao;*/

    /*
    @Autowired
    private WebApplicationContext webApplicationContext;
    @Before
    public void setUp(){
        //指定WebApplicationContext，将会从该上下文获取相应的控制器并得到相应的MockMvc；
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }*/

    @Test
    public void getHelloWorld() throws Exception{
        /**
         * 1、mockMvc.perform执行一个请求。
         * 2、MockMvcRequestBuilders.get("XXX")构造一个请求。
         * 3、ResultActions.param添加请求传值
         * 4、ResultActions.accept(MediaType.TEXT_HTML_VALUE))设置返回类型
         * 5、ResultActions.andExpect添加执行完成后的断言。
         * 6、ResultActions.andDo添加一个结果处理器，表示要对结果做点什么事情
         *   比如此处使用MockMvcResultHandlers.print()输出整个响应结果信息。
         * 5、ResultActions.andReturn表示执行完成后返回相应的结果。
         */
        MvcResult mvcResult= mockMvc.perform(MockMvcRequestBuilders.get("/index")
                //.param("name","lvgang")
                .accept(MediaType.TEXT_PLAIN_VALUE))
                .andExpect(MockMvcResultMatchers.status().isOk())             //等同于Assert.assertEquals(200,status);
                .andExpect(MockMvcResultMatchers.content().string("hello world"))    //等同于 Assert.assertEquals("hello lvgang",content);
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
        int status=mvcResult.getResponse().getStatus();                 //得到返回代码
        String content=mvcResult.getResponse().getContentAsString();    //得到返回结果

        Assert.assertEquals(200,status);                        //断言，判断返回代码是否正确
        Assert.assertEquals("hello world",content);            //断言，判断返回的值是否正确
    }
}
