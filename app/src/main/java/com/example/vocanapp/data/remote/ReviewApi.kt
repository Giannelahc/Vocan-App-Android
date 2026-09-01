package com.example.vocanapp.data.remote

import com.example.vocanapp.data.model.review.CompleteReviewRequest
import com.example.vocanapp.data.model.review.ReviewDetailResponse
import com.example.vocanapp.data.model.review.ReviewHomeResponse
import com.example.vocanapp.data.model.review.ReviewResultResponse
import com.example.vocanapp.data.model.review.ReviewSummaryPaginatedResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface ReviewApi {

    @GET("reviews")
    suspend fun getReviewsSummary(
        @Query("page") page: Int? = 1,
        @Query("page_size") pageSize: Int? = 50,
        @Query("status") status: String? = null
    ): ReviewSummaryPaginatedResponse

    @GET("reviews/next")
    suspend fun getPendingReviewsSummary(
        @Query("page") page: Int? = 1,
        @Query("page_size") pageSize: Int? = 50
    ): ReviewSummaryPaginatedResponse

    @GET("reviews/{review_id}")
    suspend fun getReviewDetail(
        @Path("review_id") reviewId: Int
    ): ReviewDetailResponse

    @GET("reviews/home")
    suspend fun getReviewHomeData(): ReviewHomeResponse

    @POST("reviews/{reviewId}/complete")
    suspend fun completeReview(
        @Path("reviewId") reviewId: Int,
        @Body request: List<CompleteReviewRequest>
    ): ReviewResultResponse

}