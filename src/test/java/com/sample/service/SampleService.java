package com.sample.service;

import static spark.Spark.get;
import static spark.Spark.port;

public class SampleService {

    public static void main(String[] args) {
        port(4999);
        get("/test", (req, res) -> "Crewe\ttrue\r\nGlasgow\tfalse");
    }
}
