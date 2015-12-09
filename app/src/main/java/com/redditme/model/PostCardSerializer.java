package com.redditme.model;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import java.lang.reflect.Type;

/**
 * Created by Johannes on 08-12-2015.
 */
public class PostCardSerializer implements JsonSerializer<PostCard> {
    @Override
    public JsonElement serialize(PostCard postCard, Type typeOfSrc, JsonSerializationContext context) {
        JsonObject result = new JsonObject();
        result.add("title", new JsonPrimitive(postCard.getTitle()));
        result.add("description", new JsonPrimitive(postCard.getDescription()));
        result.add("thumbnail", new JsonPrimitive(postCard.getThumbnailURL()));
        result.add("commentsCount", new JsonPrimitive(postCard.getCommentsCount()));
        return result;
    }
}
