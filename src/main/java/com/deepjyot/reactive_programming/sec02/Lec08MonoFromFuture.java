package com.deepjyot.reactive_programming.sec02;

import com.deepjyot.reactive_programming.common.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Mono;

import java.util.concurrent.CompletableFuture;

/*
    If you have a CompletableFuture already, then we can convert that into a Mono
 */
public class Lec08MonoFromFuture {

    private static final Logger log = LoggerFactory.getLogger(Lec08MonoFromFuture.class);

    public static void main(String[] args) {

        //-- here we are not directly calling getName() (since CompleteTable future is not lazy and will execute it)
        //-- hence fromFuture provides that we can also use supplier (to learn supplier: https://www.youtube.com/watch?v=zBlyOYRw5Es)..now it became lazy!
        //means if no subscribe is called it will not execute!


        Mono.fromFuture(Lec08MonoFromFuture::getName)
                .subscribe(Util.subscriber());

        Util.sleepSeconds(1);
    }


    //
    private static CompletableFuture<String> getName(){
        return CompletableFuture.supplyAsync(() -> {
            log.info("generating name");
            return Util.faker().name().firstName();
        });
    }

}
