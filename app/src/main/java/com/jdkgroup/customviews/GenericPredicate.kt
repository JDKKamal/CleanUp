package com.jdkgroup.customviews

import java.util.ArrayList
import java.util.function.*

/**
 * Created by kamlesh on 8/30/2017.
 */

class GenericPredicate<T> : Predicate<T> {
    internal var varc1: T? = null

    override fun test(varc: T): Boolean {
        return if (varc1 == varc) {
            true
        } else false
    }

    internal inner class LoopConsumer<T> : Consumer<T> {
        override fun accept(ctask: T) {
            println("Processing Task " + ctask)
        }
    }

    /*public void OutputLoopConsumer() {
        List<Integer> myList = new ArrayList<>();
        LoopConsumer<Integer> mcons = new LoopConsumer<Integer>();

        myList.add(100);
        myList.add(200);
        myList.add(300);

        myList.forEach(mcons);
    }*/
}