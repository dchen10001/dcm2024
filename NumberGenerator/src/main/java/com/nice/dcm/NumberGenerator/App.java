package com.nice.dcm.NumberGenerator;

import java.util.concurrent.ThreadLocalRandom;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
    	ThreadLocalRandom.current().ints(0, 100).distinct().limit(500).forEach(System.out::println);
    }
}
