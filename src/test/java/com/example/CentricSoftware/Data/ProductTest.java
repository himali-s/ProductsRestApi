package com.example.CentricSoftware.Data;

import org.junit.jupiter.api.Test;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Calendar;
import java.util.TimeZone;

import static org.junit.jupiter.api.Assertions.*;

class ProductTest {

    @Test
    void getCreatedAt() {
        Product p = new Product();
        Date current = new Date(System.currentTimeMillis());
        SimpleDateFormat sdf;
        sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssXXX");
        sdf.setTimeZone(TimeZone.getTimeZone("UTC"));
        String text = sdf.format(current);
        assertTrue(p.getCreatedAt().equals(text));
    }
}