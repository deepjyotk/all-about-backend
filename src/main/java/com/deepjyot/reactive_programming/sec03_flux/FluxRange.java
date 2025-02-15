package com.deepjyot.reactive_programming.sec03_flux;

import com.deepjyot.reactive_programming.common.Util;
import reactor.core.publisher.Flux;

public class FluxRange {
    public static void main(String[] args) {

        Flux.range(3, 10)
                .subscribe(Util.subscriber());

        // assignment - generate 10 random first names
        Flux.range(1, 10)
                .map(i -> Util.faker().name().firstName())
                .subscribe(Util.subscriber());

    }
}
