package com.deepjyot.reactive_programming.sec03_flux;

import com.deepjyot.reactive_programming.sec01.subscriber.SubscriberImpl;
import com.deepjyot.reactive_programming.sec03_flux.helper.NameGenerator;

public class FluxVsList {
    public static void main(String[] args) {

//        var list = NameGenerator.getNamesList(10);
//        System.out.println(list);

        var subscriber = new SubscriberImpl();
        NameGenerator.getNamesFlux(10)
                .subscribe(subscriber);

        subscriber.getSubscription().request(10);
        subscriber.getSubscription().cancel();
    }

}
