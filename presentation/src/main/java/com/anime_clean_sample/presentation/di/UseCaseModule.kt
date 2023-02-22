package com.anime_clean_sample.presentation.di

import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
object UseCaseModule {

    /*@Singleton
    @Provides
    fun provideValidateUserFieldsUseCase(
        @Default dispatcher: CoroutineDispatcher
    ): ValidateUserFieldsUseCase = ValidateUserFieldsUseCase(dispatcher)

    @Singleton
    @Provides
    fun provideIsValidUserCredentialsUseCase(
        //repository: UserCredentialsRepository,
        @Default dispatcher: CoroutineDispatcher
    ): IsValidUserCredentialsUseCase =
        IsValidUserCredentialsUseCase(*//*repository, *//*dispatcher)

    @Singleton
    @Provides
    fun provideSaveUserUseCase(
        //repository: UserCredentialsRepository,
        @IO dispatcher: CoroutineDispatcher
    ): SaveUserUseCase = SaveUserUseCase(*//*repository, *//*dispatcher)*/

}