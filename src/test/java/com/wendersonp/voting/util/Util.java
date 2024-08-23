package com.wendersonp.voting.util;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;
import java.util.function.Supplier;

public class Util {

    public static final Random RANDOM = new Random();

    public static <T> Set<T> generateSet(Integer size, Supplier<T> supplier) {
        Set<T> set = new HashSet<>();
        for(int i = 0; i < size; i++) {
            set.add(supplier.get());
        }
        return set;
    }


}
