package com.example.iSale.api;

import com.example.iSale.dto.response.ISaleFromLandResponse;
import com.example.iSale.global.utils.TokenInfo;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ApiLand {
    public final FeignLand feignLand;
    public static Map<UUID, Long> map = new HashMap<>();

    @Async
    public void getAllObjectsByUserId(List<UUID> userId) {
        map = feignLand.getLandsByUserId(userId);
    }
}
