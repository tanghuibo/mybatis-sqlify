package io.github.tanghuibo.mybatissqlify;

import io.github.tanghuibo.mybatissqlify.bean.SqlifyRequest;
import io.github.tanghuibo.mybatissqlify.bean.SqlifyResponse;
import io.github.tanghuibo.mybatissqlify.service.SqlifyService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

@SpringBootTest
class MybatisSqlifyApplicationTests {

	@Resource
	SqlifyService sqlifyService;

	@Test
	void contextLoads() {

		SqlifyRequest request = new SqlifyRequest();
		request.setMybatisSql("selct * from table1 where <if test=\"name != null\"> name = #{name} </if> and value = #{value}");
		Map<String, Object> map = new HashMap<>(2);
		map.put("name", "Bob");
		map.put("size", "18CM");
		request.setParam(map);
		SqlifyResponse process = sqlifyService.process(request);
		System.out.println(process.getParam());
		System.out.println(process.getSqlRenderBefore());
		System.out.println(process.getSqlRenderAfter());
	}

}
