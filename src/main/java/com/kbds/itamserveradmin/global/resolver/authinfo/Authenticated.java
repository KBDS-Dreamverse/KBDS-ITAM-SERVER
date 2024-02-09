package com.kbds.itamserveradmin.global.resolver.authinfo;



import java.lang.annotation.*;

@Target({ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Authenticated {
}
