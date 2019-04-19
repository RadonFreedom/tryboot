package fre.shown.tryboot.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author Radon Freedom
 * created at 2019.04.19 下午7:39
 */


@Configuration
@MapperScan("fre.shown.tryboot.dao")
public class DaoConfig {
}
