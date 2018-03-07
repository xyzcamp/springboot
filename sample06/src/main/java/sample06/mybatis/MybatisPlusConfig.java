package sample06.mybatis;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.baomidou.mybatisplus.plugins.PaginationInterceptor;
import com.baomidou.mybatisplus.plugins.PerformanceInterceptor;

@Configuration
// 扫描dao或者是Mapper接口
//@MapperScan("com.example*")
public class MybatisPlusConfig {
	/**
	 * mybatis-plus 分页插件
	 */

	@Bean
	public PaginationInterceptor paginationInterceptor() {
		PaginationInterceptor page = new PaginationInterceptor();
		page.setDialectType("mysql");
		return page;
	}

	// @Bean
	// public PerformanceInterceptor performanceInterceptor() {
	// return new PerformanceInterceptor();
	// }

}