package com.lucatic.grupo2.app.pay.clientfeign;

import com.lucatic.grupo2.app.pay.models.dto.BoolResponseWithError;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "eventmanager")
public interface CheckUserEventFeignClient {

    @GetMapping("/eventmanager/checkExist/{idUser}/{idEvent}")
    BoolResponseWithError checkUserEvent(@PathVariable Long idUser, @PathVariable Long idEvent);

    @GetMapping("/eventmanager/getUser/{idUser}")
    String getNameUser(@PathVariable Long idUser);
}
