package com.deng.quartz.service.feign;

import com.deng.commons.forms.useraccount.SaveOrUpdateUserAccountForm;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(value = "deng-account")
@Service
public interface AccountFeignService {

    @PostMapping(value = "/useraccountfeign/saveOrUpdateUserAccount",consumes = MediaType.APPLICATION_JSON_VALUE)
    void saveOrUpdateUserAccount(@RequestBody SaveOrUpdateUserAccountForm form);

}
