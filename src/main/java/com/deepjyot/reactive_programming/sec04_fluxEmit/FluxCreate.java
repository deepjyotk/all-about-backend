package com.deepjyot.reactive_programming.sec04_fluxEmit;

import com.deepjyot.reactive_programming.common.Util;
import reactor.core.publisher.Flux;

public class FluxCreate {

    public static void main(String[] args) {
        Flux.create(fluxSink -> {
            fluxSink.next(1);
            fluxSink.next(2);
            fluxSink.complete();;
        }).subscribe(Util.subscriber());
    }
}
