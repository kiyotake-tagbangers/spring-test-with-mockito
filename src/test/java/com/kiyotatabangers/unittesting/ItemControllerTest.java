package com.kiyotatabangers.unittesting;

import com.kiyotatabangers.unittesting.business.ItemBusinessService;
import com.kiyotatabangers.unittesting.model.Item;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@WebMvcTest(ItemController.class)
public class ItemControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ItemBusinessService businessService;

    @Test
    public void dummyItem_basic() throws Exception {

        RequestBuilder request = MockMvcRequestBuilders
                .get("/dummy-item")
                .accept(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(request)
                .andExpect(status().isOk())

                // stringだと厳密に文字列として一致している必要がある
                // .andExpect(content().string("{\"id\":1,\"name\":\"Ball\",\"price\":10,\"quantity\":100}"))

                // jsonだと追加でスペースが入っていても値を評価するため問題ない
                .andExpect(content().json("{\"id\":   1,\"name\":\"Ball\",\"price\":10,\"quantity\":100}"))
                .andReturn();
    }

    @Test
    public void itemFromBusinessService_basic() throws Exception {

        // MockBean を登録しているので値を上書きできる(独立してテストを実行できる)
        when(businessService.retreiveHardcodedItem()).thenReturn(
                new Item(2, "Item2", 10, 10));

        RequestBuilder request = MockMvcRequestBuilders
                .get("/item-from-business-service")
                .accept(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(request)
                .andExpect(status().isOk())
                // .andExpect(content().json("{id: 1,name:Ball,price:10,quantity:100}"))
                .andExpect(content().json("{id:2,name:Item2,price:10,quantity:10}"))
                .andReturn();
    }

    @Test
    public void retrieveAllItems_basic() throws Exception {
        when(businessService.retrieveAllItems()).thenReturn(
                Arrays.asList(new Item(2, "Item2", 10, 10),
                        new Item(3, "Item3", 20, 15))
        );

        RequestBuilder request = MockMvcRequestBuilders
                .get("/all-items-from-database")
                .accept(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(content().json("[{id:2,name:Item2,price:10},{id:3,name:Item3,price:20}]"))
                .andReturn();
    }
}