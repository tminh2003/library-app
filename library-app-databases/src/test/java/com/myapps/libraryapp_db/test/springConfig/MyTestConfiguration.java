package com.myapps.libraryapp_db.test.springConfig;

import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.myapps.libraryapp_db.test.util.MyObject;

@EnableTransactionManagement
@TestConfiguration
public class MyTestConfiguration {
	
	@Bean
	public MyObject myObject() {
		return new MyObject();
	}

}
