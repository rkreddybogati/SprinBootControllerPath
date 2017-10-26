package com.rk.springboot;

import java.lang.annotation.*;

/**
 * Created by srk on 26/10/17.
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface SpringRestService123   {
    String baseUri();
}
