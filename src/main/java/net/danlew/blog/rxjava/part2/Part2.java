package net.danlew.blog.rxjava.part2;

import org.apache.log4j.Logger;
import org.junit.Test;
import rx.Observable;
import rx.functions.Func1;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016-08-05.
 */
public class Part2 {
    Logger logger = Logger.getLogger(Part2.class);
    public Observable<List<String>> query(String text) {
        List<String> list = new ArrayList<String>();
        list.add(text + " a");
        list.add(text + " b");
        list.add(text + " c");
        list.add(text + " d");
        list.add(text + " e");
        list.add(text + " f");
        list.add(text + " g");
        list.add(text + " h");
        list.add(text + " i");
        return   Observable.just(list);
    }

    Observable<String> getTitle(String url) {
        return Observable.just(url.substring(5));
    }

    Observable<String> saveTitle(String title) {
        logger.info(" save" + title);
        return Observable.just(title);
    }
    @Test
    public void exam01() {
        query(" has ")
                .subscribe(urls -> {
                    for (String url : urls){
                       logger.info(url);
                    }
                });
    }

//    glimmer of hope
    @Test
    public void exam02() {
        Observable.from(new String[]{"url1", "url2", "url3", "url4", "url5", "url6", "url7", "url8", "url9"})
                .subscribe(s -> logger.info(s));
    }

    @Test
    public void exam03() {
        query("has ")
                .subscribe(urls -> {
                    Observable.from(urls)
                            .subscribe(url -> logger.info(url));
                });
    }

//    better way
    @Test
    public void exam04() {
        query("has ")
                .flatMap(new Func1<List<String>, Observable<String>>() {
                    @Override
                    public Observable<String> call(List<String> urls) {
                        return Observable.from(urls);
                    }
                })
                .subscribe(url -> logger.info(url));
    }

//    better way lambda
    @Test
    public void exam05() {
        query("has")
                .flatMap(urls -> Observable.from(urls))
                .subscribe(url -> logger.info(url));
    }

    //    even better
    @Test
    public void exam06() {
        query("title")
                .flatMap(urls -> Observable.from(urls))
                .flatMap(new Func1<String, Observable<String>>() {
                    @Override
                    public Observable<String> call(String url) {
                        return getTitle(url);
                    }
                }).subscribe(s -> logger.info(s));
    }

//    even better lambda
    @Test
    public void exam07() {
        query("title")
                .flatMap(urls -> Observable.from(urls))
                .flatMap(url -> getTitle(url))
                .subscribe(url -> logger.info(url));
    }

//    operators galore filter
    @Test
    public void exam08() {
        query("title")
                .flatMap(urls -> Observable.from(urls))
                .flatMap(url -> getTitle(url))
                .filter(title -> title != null)
                .subscribe(title -> logger.info(title));
    }

//    show 5 result
    @Test
    public void exam09() {
        query("title")
                .flatMap(urls -> Observable.from(urls))
                .flatMap(url -> getTitle(url))
                .filter(title -> title != null)
                .take(5)
                .subscribe(title -> logger.info(title));
    }

//    save title
    @Test
    public void exam10() {
        query("title")
                .flatMap(urls -> Observable.from(urls))
                .flatMap(url -> getTitle(url))
                .filter(title -> title != null)
                .take(5)
                .doOnNext(title -> saveTitle(title))
                .subscribe(title -> logger.info(title));
    }

}
