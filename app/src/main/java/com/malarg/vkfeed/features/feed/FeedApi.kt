package com.malarg.vkfeed.features.feed

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface FeedApi {

    @GET("wall.get")
    fun getWall(
        @Query("access_token") accessToken: String, @Query("offset") offset: Int, @Query("count") count: Int, @Query(
            "v"
        ) v: String = "5.52"
    ): Call<Wall>

    data class Wall(
        val response: Response? = null
    )

    data class Response(
        val count: Int,
        val groups: List<Group>,
        val items: List<Post>,
        val profiles: List<Any>
    )

    data class Post(
        val attachments: List<Attachment>?,
        val can_pin: Int,
        val comments: Comments,
        val date: Long,
        val from_id: Int,
        val id: Long,
        val likes: Likes,
        val marked_as_ads: Int,
        val owner_id: Int,
        val post_source: PostSource,
        val post_type: String,
        val reposts: Reposts,
        val text: String,
        val views: Views
    )

    data class Comments(
        val can_post: Int,
        val count: Int
    )

    data class Reposts(
        val count: Int,
        val user_reposted: Int
    )

    data class Attachment(
        val photo: Photo?,
        val link: Link?,
        val type: String
    )

    data class Photo(
        val access_key: String,
        val album_id: Int,
        val date: Int,
        val height: Int,
        val id: Int,
        val owner_id: Int,
        val photo_1280: String,
        val photo_130: String,
        val photo_604: String,
        val photo_75: String,
        val photo_807: String,
        val text: String,
        val user_id: Int,
        val width: Int
    )

    data class Link(
        val caption: String,
        val description: String,
        val is_external: Int,
        val photo: LinkPhoto,
        val title: String,
        val url: String
    )

    data class LinkPhoto(
        val album_id: Int,
        val date: Int,
        val height: Int,
        val id: Int,
        val owner_id: Int,
        val photo_130: String?,
        val photo_604: String?,
        val photo_75: String?,
        val text: String,
        val width: Int
    )

    data class Likes(
        val can_like: Int,
        val can_publish: Int,
        val count: Int,
        val user_likes: Int
    )

    data class Views(
        val count: Int
    )

    data class PostSource(
        val type: String
    )

    data class Group(
        val id: Int,
        val is_admin: Int,
        val is_closed: Int,
        val is_member: Int,
        val name: String,
        val photo_100: String,
        val photo_200: String,
        val photo_50: String,
        val screen_name: String,
        val type: String
    )
}