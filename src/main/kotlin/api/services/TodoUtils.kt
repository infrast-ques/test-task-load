package api.services

import api.dto.TodosRequest
import data.Consts.TODOS_URL
import org.apache.http.entity.ContentType.APPLICATION_JSON
import org.apache.jmeter.protocol.http.util.HTTPConstants
import us.abstracta.jmeter.javadsl.JmeterDsl
import us.abstracta.jmeter.javadsl.core.preprocessors.DslJsr223PreProcessor
import us.abstracta.jmeter.javadsl.http.DslHttpSampler
import utils.stringUtils
import kotlin.random.Random
import kotlin.random.Random.Default.nextBoolean
import kotlin.random.nextULong

object TodoUtils {

    private fun todoRequestData() = TodosRequest(
        id = Random.nextULong(),
        text = stringUtils.nextAlphabetic(8000, 10000),
        completed = nextBoolean()
    )

    private fun DslJsr223PreProcessor.PreProcessorVars.postTodosRequest(): String {
        return todoRequestData().toString()
    }

    fun postTodosSampler(): DslHttpSampler =
        JmeterDsl.httpSampler("POST /todos", "$TODOS_URL/todos")
            .method(HTTPConstants.POST)
            .contentType(APPLICATION_JSON)
            .body { context -> context.postTodosRequest() }

}
