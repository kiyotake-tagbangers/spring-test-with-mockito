package com.kiyotatabangers.unittesting.spike;

import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author KIYOTA, Takeshi
 */
public class JsonPathTest {

    @Test
    public void learning() {

        String responseFromService = "[" +
                "{\"id\":10000, \"name\":\"Pencil\", \"quantity\":5}," +
                "{\"id\":10001, \"name\":\"Pen\", \"quantity\":15}," +
                "{\"id\":10002, \"name\":\"Eraser\", \"quantity\":10}" +
                "]";

        // @see https://github.com/json-path/JsonPath
        DocumentContext context = JsonPath.parse(responseFromService);

        int length = context.read("$.length()");
        assertThat(length).isEqualTo(3);

        // System.out.println(context.read("$..id").toString());
        // [10000,10001,10002]

        List<Integer> ids = context.read("$..id");
        assertThat(ids).containsExactly(10000,10001,10002);

        // System.out.println(context.read("$.[1]").toString());
        // {id=10001, name=Pen, quantity=15}

        // System.out.println(context.read("$.[0:2]").toString());
        // 0 to: 2. Input: [0:2]
        // [{"id":10000,"name":"Pencil","quantity":5},{"id":10001,"name":"Pen","quantity":15}]

        // System.out.println(context.read("$.[1:3]").toString());
        // 1 to: 3. Input: [1:3]
        // [{"id":10001,"name":"Pen","quantity":15},{"id":10002,"name":"Eraser","quantity":10}]

        System.out.println(context.read("$.[?(@.name=='Eraser')]").toString());
        // [{"id":10002,"name":"Eraser","quantity":10}]

        System.out.println(context.read("$.[?(@.quantity==5)]").toString());
        // [{"id":10000,"name":"Pencil","quantity":5}]
    }
}
