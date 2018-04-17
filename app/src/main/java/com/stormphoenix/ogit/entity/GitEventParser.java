package com.stormphoenix.ogit.entity;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.stormphoenix.ogit.entity.github.GitCommitComment;
import com.stormphoenix.ogit.entity.github.GitEvent;
import com.stormphoenix.ogit.entity.github.payload.GitCommitCommentPayload;
import com.stormphoenix.ogit.entity.github.payload.GitCreatePayload;
import com.stormphoenix.ogit.entity.github.payload.GitIssueCommentPayload;
import com.stormphoenix.ogit.entity.github.payload.GitIssuePayload;
import com.stormphoenix.ogit.entity.github.payload.GitMemberPayload;
import com.stormphoenix.ogit.entity.github.payload.GitPayload;
import com.stormphoenix.ogit.entity.github.payload.GitPullRequestPayload;
import com.stormphoenix.ogit.entity.github.payload.GitPushPayload;
import com.stormphoenix.ogit.entity.github.payload.GitReleasePayload;

import java.lang.reflect.Type;

/**
 * Created by wanlei on 18-3-4.
 * <p>
 * GitEventParser 用于将GitPayload根据GitEvent的类型进行转化
 * 转化成GitMemberPayload或者GitIssue
 */

public class GitEventParser implements JsonDeserializer<GitEvent> {
    private Gson gson;

    public GitEventParser() {
        this.gson = new GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES).create();
    }

    @Override
    public GitEvent deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        GitEvent event = gson.fromJson(json, GitEvent.class);
        if (event == null || !json.isJsonObject()) {
            return event;
        } else {
            // 从原生json数据中获取 payload这个字段
            JsonElement rawPayload = json.getAsJsonObject().get("payload");
            // 若没有 payload这个数据项，则不用转型
            if (rawPayload == null || !rawPayload.isJsonObject()) {
                return event;
            }
            // 判断事件类型，如果为空，则停止转换
            if (event.getType() == null || event.getType().length() == 0) {
                return event;
            }
            // 关键，根据type类型来确定Payload类型
            Class payloadClass = null;
            if (event.getType().equals(GitEvent.Companion.getGIT_MEMBER_EVENT())) {
                payloadClass = GitMemberPayload.class;
            } else if (event.getType().equals(GitEvent.Companion.getGIT_ISSUES_EVENT())) {
                payloadClass = GitIssuePayload.class;
            } else if (event.getType().equals(GitEvent.Companion.getGIT_PUSH_EVENT())) {
                payloadClass = GitPushPayload.class;
            } else if (event.getType().equals(GitEvent.Companion.getGIT_RELEASE_EVENT())) {
                payloadClass = GitReleasePayload.class;
            } else if (event.getType().equals(GitEvent.Companion.getGIT_CREATE_EVENT())) {
                payloadClass = GitCreatePayload.class;
            } else if (event.getType().equals(GitEvent.Companion.getGIT_ISSUE_COMMENT_EVENT())) {
                payloadClass = GitIssueCommentPayload.class;
            } else if (event.getType().equals(GitEvent.Companion.getGIT_PULL_REQUEST_EVENT())) {
                payloadClass = GitPullRequestPayload.class;
            } else if (event.getType().equals(GitEvent.Companion.getGIT_COMMIT_COMMENT_EVENT())) {
                payloadClass = GitCommitCommentPayload.class;
            } else {
                payloadClass = GitPayload.class;
            }
            // 确认类型后，进行反序列化
            GitPayload payload = (GitPayload) context.deserialize(rawPayload, payloadClass);
            event.setPayload(payload);
            return event;
        }
    }
}
