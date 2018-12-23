package com.blankchn.test.feignclient.interceptor;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.util.*;

/**
 * @author BlankCHN
 * @date 2018-12-14 21:10
 */
@Component
public class FeignRequestInterceptor implements RequestInterceptor {

    @Autowired
    private ObjectMapper objectMapper;

    @Override
    public void apply(RequestTemplate requestTemplate) {
        if (requestTemplate.method().equals("GET") && requestTemplate.body() != null) {
            try {
                JsonNode jsonNode = objectMapper.readTree(requestTemplate.body());
                requestTemplate.body(null);

                Map<String, Collection<String>> queries = new HashMap<>();
                buildQuery(jsonNode, "", queries);
                requestTemplate.queries(queries);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void buildQuery(JsonNode jsonNode, String path, Map<String, Collection<String>> queries) {

        if (!jsonNode.isContainerNode()) {
            if (jsonNode.isNull()) {
                return;
            }

            Collection<String> values = queries.get(path);
            if (null == values) {
                values = new ArrayList<>();
                queries.put(path, values);
            }
            values.add(jsonNode.asText());
            return;
        }
        if (jsonNode.isArray()) {
            Iterator<JsonNode> it = jsonNode.elements();
            while (it.hasNext()) {
                buildQuery(it.next(), path, queries);
            }
        } else {
            Iterator<Map.Entry<String, JsonNode>> it = jsonNode.fields();
            while (it.hasNext()) {
                Map.Entry<String, JsonNode> entry = it.next();
                if (StringUtils.hasText(path)) {
                    buildQuery(entry.getValue(), path + "." + entry.getKey(), queries);
                } else {
                    buildQuery(entry.getValue(), entry.getKey(), queries);
                }
            }
        }

    }
}
