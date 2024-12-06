package api.dto

import utils.SerializationUtils

data class TodosRequest(
    val id: ULong,
    val text: String,
    val completed: Boolean
) {
    override fun toString() = SerializationUtils.toJson(this)
}
