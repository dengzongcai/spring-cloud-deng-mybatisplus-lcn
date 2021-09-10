package com.deng.quartz.config;

import com.codingapi.txlcn.common.exception.TxClientException;
import com.codingapi.txlcn.common.runner.TxLcnApplicationRunner;
import com.codingapi.txlcn.common.util.ApplicationInformation;
import com.codingapi.txlcn.common.util.id.ModIdProvider;
import com.codingapi.txlcn.logger.TxLoggerConfiguration;
import com.codingapi.txlcn.tc.config.TxClientConfig;
import com.codingapi.txlcn.tracing.TracingAutoConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.*;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 事务消费者(即事务发起者) 需加这个配置类
 *
 * @Auther ZongCai
 * @Date 2021/9/9
 */
@Configuration
@ComponentScan(
        excludeFilters = @ComponentScan.Filter(
                type = FilterType.ASPECTJ, pattern = "com.codingapi.txlcn.tc.core.transaction.txc..*"
        )
)
@Import({TxLoggerConfiguration.class, TracingAutoConfiguration.class})
public class TCAutoConfiguration {

    private static final String REDIS_TM_LIST = "tm.instances";

    @Bean
    public ApplicationRunner txLcnApplicationRunner(ApplicationContext applicationContext) {
        return new TxLcnApplicationRunner(applicationContext);
    }

    @Bean
    @ConditionalOnMissingBean
    public ModIdProvider modIdProvider(ConfigurableEnvironment environment,
                                       @Autowired(required = false) ServerProperties serverProperties) {
        return () -> ApplicationInformation.modId(environment, serverProperties);
    }

    @Bean
    @ConfigurationProperties(prefix = "tx-lcn.client")
    public TxClientConfig txClientConfig(@Autowired StringRedisTemplate stringRedisTemplate) throws TxClientException {
        TxClientConfig txClientConfig = new TxClientConfig();
        List<String> managerAddress = stringRedisTemplate.opsForHash().entries(REDIS_TM_LIST).entrySet().stream()
                .map(entry -> entry.getKey().toString()).collect(Collectors.toList());
        if (CollectionUtils.isEmpty(managerAddress)) {
            throw new TxClientException("在redis没有找到可用的tx-manager地址");
        }
        txClientConfig.setManagerAddress(managerAddress);
        return txClientConfig;
    }
}