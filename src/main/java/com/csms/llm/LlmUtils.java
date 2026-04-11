package com.csms.llm;

import dev.langchain4j.service.UserMessage;
import dev.langchain4j.service.V;
import org.springframework.stereotype.Service;

@Service
public interface LlmUtils {

    @UserMessage("You are a decision maker, find the intention of user query and get " +
            "only the top 3 matched service from the services {{services}}" +
            " with properties as service_id and service_name against the user query {{query}} with service_name property value translated into {{lang}}")
    public ServiceResponse getMatchedServices(@V("services") String services,@V("query") String query,@V("lang") String lang);

    @UserMessage("You are a translator, translate the user message {{message}} " +
            "into the language {{language}} and output only message, no need any explanation.")
    public String translate(@V("message") String message,@V("language") String language);

    @UserMessage("You are a professional translator, translate the following comma separated " +
            "sentences {{documentList}} into the language {{language}}. Instructions: 1. identify each sentence separately " +
            "2. Don't consider comma as part of sentence. 3. Avoid spaces,[,],/ before and after sentence.")
    public DocsResponse translateList(@V("documentList") String docs,@V("language") String lang);
/*    @UserMessage("extract a list of services from the user query {{query}}")
    public ServiceE[] extractInfo(@V("query") String query);*/
}
