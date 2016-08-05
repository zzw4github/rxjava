package me.zzw.app.rxjava;

import rx.Observable;
import rx.Observer;
import rx.functions.Action1;
import rx.observables.SyncOnSubscribe;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by infosea on 2016-08-05.
 */
public class Main {
    public static void hello(String... names) {
        Observable.from(names).subscribe(new Action1<String>() {
            @Override
            public void call(String s) {
                System.out.println("Hello " + s + "!");
            }

        });

    }
//Creating an Observable from an Existing Data Structure
    public static void from() {
        Observable<String> observer1 = Observable.from(new String[]{"a", "b", "c"});
        List list = new ArrayList<Integer>();
        list.add(5);
        list.add(6);
        list.add(7);
        list.add(8);

        observer1.subscribe(new Action1<String>() {
            @Override
            public void call(String s) {
                System.out.println(s);
            }
        });

        Observable<Integer> observer2 = Observable.from(list);

        Observable<String> observer3 = Observable.just("one object");

    }

//    Creating an Observable via the create( ) method
    /**
     * This example shows a custom Observable that blocks
     * when subscribed to (does not spawn an extra thread).
     */
    public static void  customObservableBlocking() {
         Observable.create(new SyncOnSubscribe<String, String>() {
            @Override
            protected String generateState() {
                return null;
            }

            @Override
            protected String next(String s, Observer<? super String> observer) {
                return null;
            }
        });
//        return Observable.create { aSubscriber ->
//                50.times { i ->
//            if (!aSubscriber.unsubscribed) {
//                aSubscriber.onNext("value_${i}")
//            }
//        }
//            // after sending all values we complete the sequence
//            if (!aSubscriber.unsubscribed) {
//                aSubscriber.onCompleted()
//            }
//        }
    }

    // To see output:
//    customObservableBlocking().subscribe { println(it) }

    public static void main(String... args){
        Main.hello("zzw");
        Main.from();
    }
}
