package com.lucatic.grupo2.app.pay.clientfeign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "checkUserEvent")
public interface CheckUserEventFeignClient {

    @PostMapping("/checkuserevent/")
    public Boolean checkUserEvent(@RequestParam Long iduser, @RequestParam Long idevent);
}
