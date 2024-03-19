package com.lucatic.grupo2.app.pay.clientfeign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "checkUserEvent")
public interface CheckUserEventFeignClient {

    @PostMapping("/checkuserevent/{iduser}/{idevent}")
    Boolean checkUserEvent(@PathVariable Long iduser, @PathVariable Long idevent);

    @GetMapping("/getUser")
    String getNameUser(@RequestParam Long idUser);
}
