package com.sample.beans;

import org.springframework.stereotype.Service;

@Service
public class SpringTestData implements ITestData {

    @Override
    public Object[][] getData() {
        return new Object[][] {
            {"Machynlleth", true},
            {"Popleton", false}
        };
    }

}
