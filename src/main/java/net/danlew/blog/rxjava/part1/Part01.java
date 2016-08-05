package net.danlew.blog.rxjava.part1;


import org.junit.Test;
import rx.Observable;
import rx.Subscriber;
import rx.functions.Action1;
import rx.functions.Action2;
import rx.functions.Func1;

/**
 * Created by Administrator on 2016-08-05.
 */
public class Part01 {
//    Hello World
    @Test
    public  void exam01() {
        Observable<String> observable = Observable.create(
                new Observable.OnSubscribe<String>() {
                    public void call(Subscriber<? super String> subscriber) {
                        subscriber.onNext("hello world");
                    }
                }
        );

        Subscriber<String> stringSubscriber = new Subscriber<String>() {
            public void onCompleted() {
            }
            public void onError(Throwable e) {
            }
            public void onNext(String s) {
                System.out.println(s);
            }
        };
        observable.subscribe(stringSubscriber);
    }

//    Simpler code
@Test
    public  void exam02() {
        Observable<String> observable = Observable.just("hello world");
        Action1<String> onNextAction = new Action1<String>() {
            public void call(String s) {
                System.out.println(s);
            }
        };
        observable.subscribe(onNextAction);
    }
//    just call
@Test
    public  void exam03() {
        Observable.just("hello world")
                .subscribe(new Action1<String>() {
                    public void call(String s) {
                        System.out.println(s);
                    }
                });
    }

//    lambda
@Test
    public  void exam04() {
        Observable.just("hello world").subscribe(s -> System.out.println(s) );
    }

//    map
@Test
    public  void exam05() {
        Observable.just("hello world")
                .map(s -> s + "dan")
                .subscribe(s -> System.out.println(s));
    }

//    more on map
@Test
    public  void exam06() {
        Observable.just("hello world")
                .map(new Func1<String, Integer>() {
                    @Override
                    public Integer call(String s) {
                        return s.hashCode();
                    }
                }).subscribe(i -> System.out.println(i));
    }

//    lambda
@Test
    public  void exam07() {
        Observable.just("hello world")
                .map(s -> s.hashCode())
                .map(i -> Integer.toString(i))
                .subscribe(s -> System.out.println(s));
    }

//    another lambda
@Test
    public  void exam08() {
        Observable.just("hello world")
                .map(s -> s + " zzw")
                .map(s -> s.hashCode())
                .map(i -> Integer.toString(i))
                .subscribe(s -> System.out.println(s));}
}
