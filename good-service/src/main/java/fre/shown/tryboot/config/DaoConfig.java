package fre.shown.tryboot.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author Radon Freedom
 * created at 2019.04.19 上午9:34
 */


@Configuration
@MapperScan("fre.shown.tryboot.dao")
public class DaoConfig {
}
