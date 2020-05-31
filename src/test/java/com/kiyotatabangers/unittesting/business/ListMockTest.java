package com.kiyotatabangers.unittesting.business;

import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

public class ListMockTest {

    List mock = mock(List.class);

    @Test
    public void size_basic() {
        when(mock.size()).thenReturn(5);
        assertEquals(5, mock.size());
    }

    @Test
    public void returnDifferentValues() {
        when(mock.size()).thenReturn(5).thenReturn(10);
        assertEquals(5, mock.size());
        assertEquals(10, mock.size());
    }

    @Test
    public void returnWithParameters() {
        when(mock.get(0)).thenReturn("Test");
        when(mock.get(1)).thenReturn("Test1");
        assertEquals("Test", mock.get(0));
        assertEquals("Test1", mock.get(1));
        assertEquals(null, mock.get(2));
    }

    @Test
    public void returnWithGenericParameters() {
        when(mock.get(anyInt())).thenReturn("Test");
        assertEquals("Test", mock.get(0));
        assertEquals("Test", mock.get(1));
    }
}
