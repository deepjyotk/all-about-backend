package com.deepjyot.reactive_programming.sec03_flux;

import com.deepjyot.reactive_programming.common.Util;
import reactor.core.publisher.Flux;

public class FluxJust {

    public static void main(String[] args) {

        Flux.just(1, 2, 3, "sam")
                .subscribe(Util.subscriber());

    }
}
