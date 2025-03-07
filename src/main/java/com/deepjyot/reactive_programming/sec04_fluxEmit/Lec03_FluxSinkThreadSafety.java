package com.deepjyot.reactive_programming.sec04_fluxEmit;

import com.deepjyot.reactive_programming.common.Util;
import com.deepjyot.reactive_programming.sec04_fluxEmit.helper.NameGenerator2;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;

import java.util.ArrayList;

/*
    FluxSink is thread safe!
    This is just a demo.
    It does NOT mean we should write code like this!
 */
public class Lec03_FluxSinkThreadSafety {

    private static final Logger log = LoggerFactory.getLogger(Lec03_FluxSinkThreadSafety.class);

    public static void main(String[] args) {

        demo2();

    }

    // arraylist is not thread safe
    private static void demo1(){
        var list = new ArrayList<Integer>();
        Runnable runnable = () -> {
            for (int i = 0; i < 1000; i++) {
                list.add(i);
            }
        };
        for (int i = 0; i < 10; i++) {
            Thread.ofPlatform().start(runnable);
        }
        Util.sleepSeconds(3);
        log.info("list size: {}", list.size());
    }

    // arraylist is not thread safe.
    // flux sink is thread safe. we get all the 10000 items safely into array list
    private static void demo2(){
        var list = new ArrayList<String>();
        var generator = new NameGenerator2();
        var flux = Flux.create(generator);
        flux.subscribe(list::add);

        Runnable runnable = () -> {
            for (int i = 0; i < 1000; i++) {
                System.out.println(Thread.currentThread().getName() + " is printing: "+i) ;
                generator.generate();
            }
        };
        for (int i = 0; i < 10; i++) {
            Thread.ofPlatform().start(runnable).setName("t"+i);
        }
        Util.sleepSeconds(3);
        log.info("list size: {}", list.size());
    }

}