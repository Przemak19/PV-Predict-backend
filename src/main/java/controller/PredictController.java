package controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import service.PredictService;

@RestController
@RequestMapping("/api/predict")
public class PredictController {

    private final PredictService predictService;

    /**
     * Constructor for assigning services
     * @param predictService weather forecasting service
     */
    public PredictController(PredictService predictService) {
        this.predictService = predictService;
    }


}
