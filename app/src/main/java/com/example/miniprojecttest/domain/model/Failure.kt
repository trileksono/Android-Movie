package com.example.miniprojecttest.domain.model

sealed class Failure {
    object NetworkConnection : Failure()
    data class ServerError(val message: String) : Failure()

    /** * Extend this class for feature specific failures.*/
    abstract class FeatureFailure : Failure()
}

class RoomFailure() {
    class DeleteFailure : Failure.FeatureFailure()
    class InsertFailure : Failure.FeatureFailure()
}
