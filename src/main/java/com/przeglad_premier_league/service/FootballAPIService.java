package com.przeglad_premier_league.service;

import com.przeglad_premier_league.config.WebClientConfig;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;


@Slf4j
@Service
public class FootballAPIService {

    @Autowired
    private WebClientConfig webClientConfig;

    @Value("${ppl.fdapi.key}")
    private String apiKey;
    @Value("${ppl.season.id}")
    private String seasonId;

    public JSONArray callActualSeasonAndParseItToDatabase() {
        log.info("Data downloading");
        Mono<String> response = webClientConfig.webClient()
                .get().uri("/league-tables?key="+apiKey+"&season_id="+seasonId)
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(String.class);
        JSONObject json = new JSONObject(response.block());
        log.info("Data downloading complete");
        return json.getJSONObject("data").getJSONArray("specific_tables").getJSONObject(0).getJSONArray("table");
    }

    public String getPeriodOfSeason(){
        log.info("Data downloading");
        Mono<String> response = webClientConfig.webClient()
                .get().uri("/league-tables?key="+apiKey+"&season_id="+seasonId)
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(String.class);
        JSONObject json = new JSONObject(response.block());
        Object object = json.getJSONObject("data").getJSONArray("specific_tables").getJSONObject(0).get("round");
        log.info("Data downloading complete");
        log.info("I have downloaded the data for the season {}", object.toString());
        return object.toString();
    }
}
