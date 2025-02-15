package com.deepjyot.reactive_programming.sec01.publisher;

import org.reactivestreams.Publisher;
import org.reactivestreams.Subscriber;


// provides a subscribe method.. and binds subscription and subsriber together.
//
public class PublisherImpl implements Publisher<String> {


//    remember publisher has 1 method called subscribe (it will receive subscriber instance), it's like
// mnemonic: subsriber can call this and it will get passed as parameter.
    @Override
    public void subscribe(Subscriber<? super String> subscriber) {
        var subscription = new SubscriptionImpl(subscriber);
        subscriber.onSubscribe(subscription);
    }

}
