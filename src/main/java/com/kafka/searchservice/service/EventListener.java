package com.kafka.searchservice.service;

import blackfriday.protobuf.EdaMessage;
import com.google.protobuf.InvalidProtocolBufferException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class EventListener {

    @Autowired
    SearchService searchService;

    @KafkaListener(topics = "product_tag_added")
    public void consumeTagAdded(byte[] message) throws InvalidProtocolBufferException {
        var object = EdaMessage.ProductTag.parseFrom(message);

        log.info("[product_tag_added] consumed: {}", object);

        searchService.addTagCache(object.getProductId(), object.getTagsList());
    }

    @KafkaListener(topics = "product_tags_removed")
    public void consumeTagRemoved(byte[] message) throws InvalidProtocolBufferException {
        var object = EdaMessage.ProductTag.parseFrom(message);

        log.info("[product_tags_removed] consumed: {}", object);

        searchService.removeTagCache(object.getProductId(), object.getTagsList());
    }
}
