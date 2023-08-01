package com.gongnaixiao.susu.common.datasource.annotation;

import com.gongnaixiao.susu.common.datasource.DynamicDataSourceAutoConfiguration;
import org.springframework.context.annotation.Import;
import java.lang.annotation.*;

@Target({ ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@Import(DynamicDataSourceAutoConfiguration.class)
public @interface EnableDynamicDataSource {

}
