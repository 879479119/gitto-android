package com.stormphoenix.ogit.mvp.model.api;

import com.stormphoenix.ogit.entity.github.GitEvent;
import com.stormphoenix.ogit.entity.github.GitOrganization;
import com.stormphoenix.ogit.entity.github.GitUser;

import java.util.List;

import retrofit2.Response;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.Path;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by wanlei on 18-3-11.
 */

public interface OrganizationApi {
    @Headers("Cache-Control: public, max-age=600")
    @GET("/user/orgs?per_page=10?per_page=100")
    Observable<Response<List<GitOrganization>>> loadOwnerOrgs(@Query("page") int page);

    @Headers("Cache-Control: public, max-age=600")
    @GET("/users/{username}/orgs")
    Observable<List<GitOrganization>> loadOwnerOrgs(@Header("Authorization") String auth,
                                                    @Path("username") String username,
                                                    @Query("page") int pageId);

    @Headers("Cache-Control: public, max-age=600")
    @GET("/orgs/{org}/members")
    Observable<Response<List<GitUser>>> loadMembers(@Path("org") String org,
                                          @Query("page") int pageId);

    @GET("orgs/{org}")
    Observable<Response<GitOrganization>> loadOrganization(@Path("org") String org);

    @GET("orgs/{org}/members?per_page=1")
    Observable<Response<List<GitUser>>> loadMembersCount(@Path("org") String org);

    @GET("users/{username}/events/orgs/{org}?per_page=10")
    Observable<Response<List<GitEvent>>> loadOwnerOrgEvents(@Path("username") String username, @Path("org") String org, @Query("page") int page);
}