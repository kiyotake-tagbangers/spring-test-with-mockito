package com.kiyotatabangers.unittesting.business;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class SomeBusinessMockTest {

    @Test
    public void calculateSumUsingDataService_basic() {
        SomeBusinessImpl business = new SomeBusinessImpl();

        // Mockito を使用
        SomeDataService dataServiceMock = mock(SomeDataService.class);
        when(dataServiceMock.retrieveAllData()).thenReturn(new int[]{1, 2, 3});

        business.setSomeDataService(dataServiceMock);
        int actualResult = business.calculateSumUsingDataService();
        int expectedResult = 6;
        assertEquals(expectedResult, actualResult);
    }

    @Test
    public void calculateSumUsingDataService_empty() {
        SomeBusinessImpl business = new SomeBusinessImpl();

        SomeDataService emptyDataService = mock(SomeDataService.class);
        when(emptyDataService.retrieveAllData()).thenReturn(new int[]{});

        business.setSomeDataService(emptyDataService);
        int actualResult = business.calculateSumUsingDataService();
        int expectedResult = 0;
        assertEquals(expectedResult, actualResult);
    }

    @Test
    public void calculateSumUsingDataService_oneValue() {
        SomeBusinessImpl business = new SomeBusinessImpl();

        SomeDataService oneElementDataService = mock(SomeDataService.class);
        when(oneElementDataService.retrieveAllData()).thenReturn(new int[]{5});

        business.setSomeDataService(oneElementDataService);
        int actualResult = business.calculateSumUsingDataService();
        int expectedResult = 5;
        assertEquals(expectedResult, actualResult);
    }
}