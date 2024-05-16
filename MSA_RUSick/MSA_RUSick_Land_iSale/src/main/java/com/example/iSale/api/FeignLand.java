package com.example.iSale.api;

import com.example.iSale.dto.response.ISaleFromLandResponse;
import com.example.iSale.global.utils.TokenInfo;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(value = "LAND", path = "/api/v1/lands")
public interface FeignLand {

    @PostMapping("owner/landCount")
    Map<UUID, Long> getLandsByUserId(@RequestBody List<UUID> userId);
}
