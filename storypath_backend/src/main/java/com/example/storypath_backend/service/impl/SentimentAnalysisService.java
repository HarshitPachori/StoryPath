package com.example.storypath_backend.service.impl;

import com.example.storypath_backend.payload.Sentiment;
import edu.stanford.nlp.ling.CoreAnnotations;
import edu.stanford.nlp.neural.rnn.RNNCoreAnnotations;
import edu.stanford.nlp.pipeline.Annotation;
import edu.stanford.nlp.pipeline.StanfordCoreNLP;
import edu.stanford.nlp.sentiment.SentimentCoreAnnotations;
import edu.stanford.nlp.trees.Tree;
import edu.stanford.nlp.util.CoreMap;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Properties;

@Service
@RequiredArgsConstructor
public class SentimentAnalysisService {

    private static StanfordCoreNLP pipeline;

    public static void init() {
        Properties properties = new Properties();
        // properties.setProperty("annotators", "tokenize, ssplit, parse, sentiment");
        properties.setProperty("annotators", "tokenize, ssplit, pos, lemma,  parse, sentiment");
        pipeline = new StanfordCoreNLP(properties);
    }

    public Sentiment doSentimentAnalysis(String content) {
        init();
        int sentimentValue = 0;
        // String sentimentName = "";
        Annotation annotation = pipeline.process(content);
        for (CoreMap sentence : annotation.get(CoreAnnotations.SentencesAnnotation.class)) {
            Tree tree = sentence.get(SentimentCoreAnnotations.SentimentAnnotatedTree.class);
            sentimentValue = RNNCoreAnnotations.getPredictedClass(tree);
            // sentimentName = sentence.get(SentimentCoreAnnotations.SentimentClass.class);
        }
        return setSentimentValue(sentimentValue);
    }

    private Sentiment setSentimentValue(int sentimentValue) {
        return switch (sentimentValue) {
            case 4 -> Sentiment.HIGHLY_POSITIVE;
            case 3 -> Sentiment.POSITIVE;
            case 2 -> Sentiment.NEUTRAL;
            case 1 -> Sentiment.NEGATIVE;
            case 0 -> Sentiment.HIGHLY_NEGATIVE;
            default -> null;
        };
    }

}
