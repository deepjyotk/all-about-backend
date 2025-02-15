package com.deepjyot.reactive_programming.sec02;

import com.deepjyot.reactive_programming.common.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Mono;

import java.util.List;

/*
    To delay the execution using supplier / callable
    - use supplier when there is some computation required!
    - here we see the we find the sum (Computationally intensive task), and it took time for it!
 */
public class Lec05MonoFromSupplier {

    private static final Logger log = LoggerFactory.getLogger(Lec05MonoFromSupplier.class);

    public static void main(String[] args) {

        var list = List.of(1, 2, 3);



        Mono.fromSupplier(() -> sum(list));
//                .subscribe(Util.subscriber());

    }

    private static int sum(List<Integer> list) {
        log.info("finding the sum of {}", list);
        return list.stream().mapToInt(a -> a).sum();
    }

}
