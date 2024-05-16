package com.example.iSale;

import com.example.iSale.api.ApiLand;
import com.example.iSale.domain.entity.ISaleEnroll;
import com.example.iSale.domain.repository.ISaleEnrollRepository;
import com.example.iSale.dto.response.ISaleFromLandResponse;
import com.example.iSale.global.utils.TokenInfo;
import jakarta.transaction.Transactional;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Transactional
public class iSaleEnrollScheduled {
    private final ApiLand apiLand;
    private final ISaleEnrollRepository iSaleEnrollRepository;

    @Scheduled(cron = "0 */5 * * * *")
    public void iSaleEnrollQualification() {

        List<UUID> idList = new ArrayList<>();
        List<ISaleEnroll> byQualTfIsNull = iSaleEnrollRepository.findAllByQualTFIsNull();

        if(!byQualTfIsNull.isEmpty()){

            for(ISaleEnroll iSaleEnroll : byQualTfIsNull) {
                idList.add(iSaleEnroll.getUserId());
            }

            // Map<UUID, Integer> allLandsByUserId = apiLand.getAllObjectsByUserId(idList);

            // 여기서 idList 로 Land 에 요청을 보내서
            // Map< UserId : 매물 개수 > 반환
            apiLand.getAllObjectsByUserId(idList);

            System.out.println(ApiLand.map);


            for(ISaleEnroll iSaleEnroll : byQualTfIsNull) {
                System.out.println(iSaleEnroll.getAge());
                int score = 0;

                // 나이에 따른 자격 점수 부여
                if (iSaleEnroll.getAge() < 19) {
                    score += 10;
                } else if (iSaleEnroll.getAge() < 20) {
                    score += 15;
                } else if (iSaleEnroll.getAge() < 30) {
                    score += 20;
                } else {
                    score += 25;
                }

                // 수입에 따른 자격 점수 부여
                if (iSaleEnroll.getIncome() > 60000000) {
                    score += 10;
                } else if (iSaleEnroll.getIncome() > 50000000) {
                    score += 15;
                } else if (iSaleEnroll.getIncome() > 40000000) {
                    score += 20;
                } else {
                    score += 25;
                }

                // 자녀 수에 따른 자격 점수 부여
                if (iSaleEnroll.getChild() < 1) {
                    score += 10;
                } else if (iSaleEnroll.getChild() < 2) {
                    score += 15;
                } else if (iSaleEnroll.getChild() < 3) {
                    score += 20;
                } else {
                    score += 25;
                }

                // 매물 여부에 따른 자격 점수 부여
                if(ApiLand.map.get(iSaleEnroll.getUserId()) == null){
                    score += 25;
                }

                System.out.println(score);

                // score 점수 75점 초과일 경우 true 부여
                iSaleEnroll.setQualTF(score > 75);
            }
        }
    }
}
