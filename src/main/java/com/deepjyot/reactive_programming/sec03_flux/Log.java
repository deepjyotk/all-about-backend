package com.deepjyot.reactive_programming.sec03_flux;

import com.deepjyot.reactive_programming.common.Util;
import reactor.core.publisher.Flux;

// whatever subscriber request it has to go via log, and whatever publisher emits it has to go via log
public class Log {

    public static void main(String[] args) {


        Flux.range(1, 5)
                .log()
                .map(i -> Util.faker().name().firstName())
                .log()
                .subscribe(Util.subscriber());


    }
}
