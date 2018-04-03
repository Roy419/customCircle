package com.cly.customview.view;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;



/**
 * Created by admin on 2018/3/25.
 */

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)

 public  @interface OnClick {
     int[] value();
}
