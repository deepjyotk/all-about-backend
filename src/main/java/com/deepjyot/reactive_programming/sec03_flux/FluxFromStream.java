package com.deepjyot.reactive_programming.sec03_flux;

import com.deepjyot.reactive_programming.common.Util;
import reactor.core.publisher.Flux;

import java.util.List;

public class FluxFromStream {
    public static void main(String[] args) {
        var list = List.of(1,2,3,4);
        var stream = list.stream();

        //providing supplier of stream
        var flux = Flux.fromStream(list::stream);

        flux.subscribe(Util.subscriber("sub1"));
        flux.subscribe(Util.subscriber("sub2"));

    }
}