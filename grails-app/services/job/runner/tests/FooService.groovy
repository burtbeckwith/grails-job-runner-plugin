package job.runner.tests

class FooService {

    static transactional = false
    boolean fooBoolean = false
    String fooText = "not changed"

    def execute() {
        log.error "foo ran"
        log.error "fooText is $fooText"
        log.error "fooBoolean is $fooBoolean"
    }
}
