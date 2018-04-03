package com.cly.customview.view;

import android.app.Activity;
import android.view.View;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Created by admin on 2018/3/25.
 */

public class Butterknife {
    public static void bind(Activity activity){
        Class<? extends Activity> aClass = activity.getClass();
        Field[] declaredFields = aClass.getDeclaredFields();
        for (Field field :
                 declaredFields) {
             field.setAccessible(true); //允许暴力反射
           // String name = field.getName();

            BindView annotation = field.getAnnotation(BindView.class);

             if(annotation !=null){
                 int value = annotation.value();
                 try {
                     View viewById = activity.findViewById(value);
                     field.set(activity,viewById);
                 } catch (IllegalAccessException e) {
                     e.printStackTrace();
                 }
             }

            bindClick(activity,aClass);
        }


    }

    private static void bindClick(final Activity activity, Class<? extends Activity> aClass) {

        Method[] declaredMethods = aClass.getDeclaredMethods();
        for (final Method method :
                declaredMethods) {
             method.setAccessible(true);
            OnClick annotation = method.getAnnotation(OnClick.class);
              if(annotation !=null){
                  int[] i = annotation.value();
                  for (int j :
                          i) {
                      final View viewById = activity.findViewById(j);
                      viewById.setOnClickListener(new View.OnClickListener() {
                          @Override
                          public void onClick(View view) {
                              try {
                                  method.invoke(activity,viewById);
                              } catch (IllegalAccessException e) {
                                  e.printStackTrace();
                              } catch (InvocationTargetException e) {
                                  e.printStackTrace();
                              }
                          }
                      });
                  }

              }
        }

    }
}
