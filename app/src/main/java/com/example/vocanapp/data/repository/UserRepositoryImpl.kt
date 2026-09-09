package com.example.vocanapp.data.repository

import com.example.vocanapp.data.mapper.toDomain
import com.example.vocanapp.data.remote.UserApi
import com.example.vocanapp.domain.model.HomeInfo
import com.example.vocanapp.domain.model.UserInfo
import com.example.vocanapp.domain.repository.UserRepository
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val api: UserApi
) : UserRepository {

    override suspend fun getCurrentUser(): UserInfo {
        val user = api.getUserInfo()
        return user.toDomain()
    }

    override suspend fun getHomeInfo(): HomeInfo {
        val homeInfo = api.getHomeSummary()
        return homeInfo.toDomain()
    }

}