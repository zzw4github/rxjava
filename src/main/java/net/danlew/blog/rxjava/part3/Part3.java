package net.danlew.blog.rxjava.part3;

import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;
import rx.Observable;
import rx.Scheduler;
import rx.Subscriber;
import rx.Subscription;
import rx.functions.Action0;
import rx.schedulers.Schedulers;
import rx.subjects.Subject;

import java.util.concurrent.TimeUnit;

/**
 * Created by Administrator on 2016-08-05.
 */
public class Part3 {
    Logger logger = Logger.getLogger(Part3.class);
   // error handling
    @Test
    public void exam01() {
        Observable.just("hello world")
                .map(s -> potentialException(s))
                .map(s -> anotherPotentialException(s))
                .subscribe(new Subscriber<String>() {
                    @Override
                    public void onCompleted() {
                        logger.info("success");
                    }
                    @Override
                    public void onError(Throwable e) {
                        logger.error("Ouch");
                    }
                    @Override
                    public void onNext(String s) {
                        logger.info(s);

                    }
                });
    }

    private String anotherPotentialException(String s) {
        return "AnotherPotentialException" + s;
    }

    private String potentialException(String s) {
        return "potentialException" + s;
    }

    class Service {
        public Observable<String> getWords(String url) {
           return  Observable.from(new String[]{"url1", "url2", "url3"});
        }
    }
   Service  myObservableServices = null;
    @Before
    public void setUp() {
        myObservableServices = new Service();
    }
    @Test
    public void exam02() {
        myObservableServices.getWords("this is url")
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.io())
                .subscribe(s -> logger.info(s));
    }

    @Test
    public void exam03() {
        Subscription subscription = Observable.just("hello world")
                .subscribe(s -> logger.info(s));
        subscription.unsubscribe();
        logger.info("unSubscribed = " + subscription.isUnsubscribed());
    }
}
