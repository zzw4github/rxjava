package me.zzw.app.rxjava;

import rx.Observable;
import rx.functions.Action1;

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

    public static void main(String... args){
        Main.hello("zzw");
    }
}
