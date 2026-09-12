package com.example.vocanapp.data.mapper

import com.example.vocanapp.data.model.review.CompleteReviewRequest
import com.example.vocanapp.data.model.review.ExerciseResponse
import com.example.vocanapp.data.model.review.ReviewDetailResponse
import com.example.vocanapp.data.model.review.ReviewHomeResponse
import com.example.vocanapp.data.model.review.ReviewNotificationSummaryResponse
import com.example.vocanapp.data.model.review.ReviewResultResponse
import com.example.vocanapp.data.model.review.ReviewStatisticsResponse
import com.example.vocanapp.data.model.review.ReviewSummaryPaginatedResponse
import com.example.vocanapp.data.model.review.ReviewSummaryResponse
import com.example.vocanapp.domain.model.CompleteReview
import com.example.vocanapp.domain.model.Exercise
import com.example.vocanapp.domain.model.ReviewDetail
import com.example.vocanapp.domain.model.ReviewHome
import com.example.vocanapp.domain.model.ReviewNotificationSummary
import com.example.vocanapp.domain.model.ReviewResult
import com.example.vocanapp.domain.model.ReviewStatistics
import com.example.vocanapp.domain.model.ReviewStatus
import com.example.vocanapp.domain.model.ReviewSummary
import com.example.vocanapp.domain.model.ReviewSummaryPaginated
import java.time.Instant

fun ReviewSummaryPaginatedResponse.toDomain(): ReviewSummaryPaginated =
    ReviewSummaryPaginated(
        items = items.map { it.toDomain() },
        page = page,
        pageSize = pageSize,
        total = total
    )

fun ReviewHomeResponse.toDomain(): ReviewHome =
    ReviewHome(
        statistics = statistics.toDomain(),
        pendingReviews = pendingReviews.map { it.toDomain() }
    )

fun ReviewStatisticsResponse.toDomain(): ReviewStatistics =
    ReviewStatistics(
        successRate = successRate,
        reviewsToReview = reviewsToReview,
        overdueReviews = overdueReviews
    )

fun ReviewSummaryResponse.toDomain(): ReviewSummary =
    ReviewSummary(
        id = id,
        word = word,
        exerciseCount = exercises,
        nextReviewAt = nextReviewAt.let {
            Instant.parse(it)
        },
        completedAt = completedAt?.let {
            Instant.parse(it)
        },
        reviewLevel = reviewLevel
    )

fun ReviewDetailResponse.toDomain(): ReviewDetail =
    ReviewDetail(
        id = id,
        exercises = exercises.map { it.toDomain() },
    )

fun ReviewResultResponse.toDomain(): ReviewResult =
    ReviewResult(
        id = id,
        status = ReviewStatus.valueOf(status),
        nextReviewAt = nextReviewAt.let {
            Instant.parse(it)
        }
    )

fun ExerciseResponse.toDomain(): Exercise =
    Exercise(
        id = id,
        type = type,
        question = question,
        options = options,
        correctAnswer = correctAnswer
    )

fun CompleteReview.toRequest(): CompleteReviewRequest =
    CompleteReviewRequest(
        id = id,
        answer = answer
    )

fun ReviewNotificationSummaryResponse.toDomain(): ReviewNotificationSummary =
    ReviewNotificationSummary(
        totalPending = totalPending,
        todayReviews = todayReviews,
        overdueReviews = overdueReviews,
        userName = userName
    )
