package com.deepjyot.reactive_programming.sec04_fluxEmit;

import com.deepjyot.reactive_programming.common.Util;
import com.deepjyot.reactive_programming.sec04_fluxEmit.helper.NameGenerator2;
import reactor.core.publisher.Flux;

public class FluxCreateRefactor {
    public static void main(String[] args) {

        var generator = new NameGenerator2();
        var flux = Flux.create(generator);
        flux.subscribe(Util.subscriber());

        // somewhere else!
        for (int i = 0; i < 10; i++) {
            generator.generate();
        }

    }
}
