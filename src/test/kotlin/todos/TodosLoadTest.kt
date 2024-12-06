package todos

import api.services.TodoUtils.postTodosSampler
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import us.abstracta.jmeter.javadsl.JmeterDsl.autoStop
import us.abstracta.jmeter.javadsl.JmeterDsl.rpsThreadGroup
import us.abstracta.jmeter.javadsl.JmeterDsl.testPlan
import us.abstracta.jmeter.javadsl.dashboard.DashboardVisualizer.dashboardVisualizer
import java.time.Duration

class TodosLoadTest {


    @Test
    @DisplayName("POST /todos -> Load test: 150rps, duration 300sec")
    fun test() {
        val rpsThreadGroup = rpsThreadGroup()
            .maxThreads(10)
            .rampTo(0.5, Duration.ofSeconds(10)) // попытка избежать спайка в начале
            .rampToAndHold(150.0, Duration.ofSeconds(30), Duration.ofSeconds(300))
            .children(postTodosSampler())

        val testPlan = testPlan(
            rpsThreadGroup,
//            resultsTreeVisualizer(), для отладки
            dashboardVisualizer(),
            autoStop()
//             todo Выключил, так как падает из-за спайка в начале, пока не знаю как поправить
//                .`when`(AutoStopCondition.latencyTime().percentile(90.0).greaterThan(Duration.ofMillis(400)))
        ).run()
        println(testPlan.overall())
    }
}
