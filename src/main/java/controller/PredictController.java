package controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import service.PredictService;

/**
 * REST controller exposing endpoints to retrieve solar forecast and weather summaries for a given location.
 */
@RestController
@RequestMapping("/api/predict")
public class PredictController {

    private final PredictService predictService;

    public PredictController(PredictService predictService) {
        this.predictService = predictService;
    }


}
