package com.example.iSale.controller;

import com.example.iSale.dto.request.ISaleEnrollRequest;
import com.example.iSale.dto.request.ISaleRequest;
import com.example.iSale.dto.request.InterestISaleRequest;
import com.example.iSale.domain.entity.ISale;
import com.example.iSale.domain.entity.InterestISale;
import com.example.iSale.dto.response.ISaleDetailResponse;
import com.example.iSale.dto.response.ISaleResponse;
import com.example.iSale.dto.response.InterestISaleResponse;
import com.example.iSale.global.utils.JwtUtil;
import com.example.iSale.global.utils.TokenInfo;
import com.example.iSale.service.ISaleService;
import java.util.List;
import java.util.UUID;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/iSale")
@RequiredArgsConstructor
public class ISaleController {
    private final ISaleService iSaleService;
    private final JwtUtil jwtUtil;

    @PostMapping
    public void addISale(@RequestBody ISaleRequest req){
        iSaleService.save(req);
    }

    @GetMapping()
    public List<ISaleResponse> getAllISale(){
        return iSaleService.getAllISale();
    }

    @GetMapping("{id}")
    public ISaleDetailResponse getISale(@PathVariable String id){
        return iSaleService.getISaleById(id);
    }

    // 분양 신청
    @PostMapping("{id}/enroll")
    public void enrollISale(@PathVariable String id, @RequestBody ISaleEnrollRequest req
                    , @AuthenticationPrincipal TokenInfo tokenInfo){
        iSaleService.enrollSave(id, tokenInfo, req);
    }

    // 관심 분양

    // 관심 분양 리스트 출력
    @GetMapping("/interest")
    public List<InterestISaleResponse> getInterestISale(@AuthenticationPrincipal TokenInfo tokenInfo){
        return iSaleService.getInterestById(tokenInfo);
    }

    // 관심 분양 추가, 추가돼있다면 삭제 작업
    @GetMapping("{iSaleId}/interest")
    public Boolean getInterest(@PathVariable("iSaleId") String iSaleId, @AuthenticationPrincipal TokenInfo tokenInfo){
        return iSaleService.getInterest(iSaleId, tokenInfo);
    }


    @PostMapping("{id}/interest")
    public void addOrDeleteInterest(@PathVariable String id, @AuthenticationPrincipal TokenInfo tokenInfo){
        InterestISaleRequest req = new InterestISaleRequest(UUID.fromString(id), tokenInfo);
        iSaleService.addOrDeleteInterest(req);
    }
}
