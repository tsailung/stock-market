/*
 * Copyright 2012-2014 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.metapro.simple;

import com.alibaba.fastjson.JSON;
import com.metapro.stock.Constants;
import com.metapro.stock.StockMarketApplication;
import com.metapro.stock.dto.MQTransferDataDto;
import com.metapro.stock.mq.MQService;
import com.metapro.stock.mq.impl.MQServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.annotation.MapperScan;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.Date;
import java.util.Random;
import java.util.UUID;
import java.util.concurrent.CountDownLatch;

/**
 * Tests for {@link StockMarketApplication}.
 *
 * @author Dave Syer
 * @author Phillip Webb
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@MybatisTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class StockMarketApplicationTests {

//	@Autowired
//	GatewayServiceImpl gatewayServiceImpl;
//
//	@Test
//	public void testRequestStockstar(){
//		gatewayServiceImpl.requestStockstar();
//	}

    @Resource
    MQService mqService;

    @Test
    public void mqSend() {
        try {
            CountDownLatch countDownLatch = new CountDownLatch(1);

            MQTransferDataDto transferData = new MQTransferDataDto();
            transferData.setCount((int)(Math.random() * 100));
            transferData.setData(UUID.randomUUID().toString());
            transferData.setSendTime(new Date());
            mqService.send(JSON.toJSONString(transferData), Constants.IMMEDIATELY_EVENT_ROUTE_KEY);
            transferData = new MQTransferDataDto();
            transferData.setCount((int)(Math.random() * 100));
            transferData.setData(UUID.randomUUID().toString());
            transferData.setSendTime(new Date());
            mqService.sendWithDelay(JSON.toJSONString(transferData), Constants.DELAY_EVENT_ROUTE_KEY, 5);
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    @Configuration
	@Import({MQServiceImpl.class})
    @ComponentScan(basePackages = {"com.metapro.stock.mq", "com.metapro.stock.config"})
    @MapperScan(basePackages = "com.metapro.stock")
    public static class TestApplication {

        public static void main(String[] args) throws Exception {
            SpringApplication.run(StockMarketApplication.class, args);
        }

    }

}
