package com.maeteno.study.utils;

import org.junit.Assert;
import org.junit.Test;

import java.util.List;

public class UtilsTest {

    @Test
    public void mock() {
        List<Integer> list = Utils.mock(10);
        Assert.assertNotNull(list);
    }
}