package com.kiyotatabangers.unittesting.spike;

import org.json.JSONException;
import org.junit.Test;
import org.skyscreamer.jsonassert.JSONAssert;

public class JsonAssertTest {

    String actualResponse = "{\"id\":1,\"name\":\"Ball\",\"price\":10,\"quantity\":100}";

    @Test
    public void jsonAssert_StrictTrue_ExactMatchExceptForSpaces() throws JSONException {

        // json レスポンスの中身がすべて正しいかを assert する
        // String expectedResponse = "{\"id\":1,\"name\":\"Ball\"}";
        // java.lang.AssertionError:
        // Unexpected: price
        // ;
        // Unexpected: quantity
        String expectedResponse = "{\"id\":1,\"name\":\"Ball\",\"price\":10,\"quantity\":100}";
        JSONAssert.assertEquals(expectedResponse, actualResponse, true);
    }

    @Test
    public void jsonAssert_StrictFalse() throws JSONException {

        // json レスポンスの中身で指定したものが正しいかを assert する
        // String expectedResponse = "{\"id\":1,\"name\":\"Ball\", \"price\":11}"; // これはエラーになる　
        String expectedResponse = "{\"id\":1,\"name\":\"Ball\", \"price\":10}";
        JSONAssert.assertEquals(expectedResponse, actualResponse, false);
    }

    @Test
    public void jsonAssert_WithoutEscapeCharacters() throws JSONException {

        // name:\"Ball 2\" などスペースを含む場合は、エスケープして表現する
        String expectedResponse = "{id:1,name:Ball,price:10}";
        JSONAssert.assertEquals(expectedResponse, actualResponse, false);
    }
}
