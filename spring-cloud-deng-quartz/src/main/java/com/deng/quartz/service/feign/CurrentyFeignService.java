package com.deng.quartz.service.feign;

import com.deng.commons.forms.synclog.BaseSyncLogForm;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(value = "deng-currency")
@Service
public interface CurrentyFeignService {

    @PostMapping(value = "/synclogfeigncontroller/saveOrUpdateSyncLog",consumes = MediaType.APPLICATION_JSON_VALUE)
    void saveOrUpdateSyncLog(@RequestBody BaseSyncLogForm form);

}
