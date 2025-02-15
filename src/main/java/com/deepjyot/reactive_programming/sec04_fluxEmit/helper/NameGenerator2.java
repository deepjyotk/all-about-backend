package com.deepjyot.reactive_programming.sec04_fluxEmit.helper;

import com.deepjyot.reactive_programming.common.Util;
import reactor.core.publisher.FluxSink;

import java.util.function.Consumer;

public class NameGenerator2 implements Consumer<FluxSink<String>> {

    private FluxSink<String> sink;

    @Override
    public void accept(FluxSink<String> stringFluxSink) {
        this.sink = stringFluxSink;
    }

    public void generate(){
        this.sink.next(Util.faker().name().firstName());
    }
}