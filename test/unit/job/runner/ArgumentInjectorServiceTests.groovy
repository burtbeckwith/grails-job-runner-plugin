package job.runner

import grails.test.mixin.TestFor
import spock.lang.Specification

@TestFor(ArgumentInjectorService)
class ArgumentInjectorServiceTests extends Specification {

    void "if the service has argsMap, it is injected into the job"() {
        given:
        def fooJob = new FooJob()

        when:
        service.injectArguments(fooJob, [foo: "bar"])

        then:
        fooJob.argsMap
    }

    void "if a class has argsMap, but it can't be set due to type, it is ignored"() {
        given:
        def barJob = new BarJob()

        when:
        service.injectArguments(barJob, [foo: "bar"])

        then:
        !barJob.argsMap
    }

    void "arguments are injected by name"() {
        given:
        def fooJob = new FooJob()

        when:
        service.injectArguments(fooJob, [bigNum: "123", someText: "blah", num: "not a number"])

        then:
        "blah" == fooJob.someText
        123L == fooJob.bigNum
        0 == fooJob.num
    }
}

class FooJob {
    int num = 0
    Long bigNum
    String someText
    Map argsMap
}

class BarJob {
    Long argsMap
}