package info.chandika.wiremockutility.controller;

import com.github.tomakehurst.wiremock.matching.MatchesJsonPathPattern;
import info.chandika.wiremockutility.model.JsonPathValidationRequest;
import info.chandika.wiremockutility.model.JsonPathValidationResponse;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author : chandika
 * @since : 2023-08-15(Tue) 10:18
 **/
@RestController
@RequestMapping("api/validation")
public class ValidationController {
    @PostMapping("json-path")
    public JsonPathValidationResponse validateJsonPath(@RequestBody JsonPathValidationRequest request) {
        var matcher = new MatchesJsonPathPattern(request.path());
        return new JsonPathValidationResponse(matcher.match(request.value()).isExactMatch(), matcher.getExpressionResult(request.value()));
    }
}
