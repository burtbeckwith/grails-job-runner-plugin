grails-job-runner-plugin
========================

Intended to be a simple replacement for the batch-runner plugin that can run any service which has an 'execute' method.  This plugin was built to deal with my frustrations over running command line jobs in grails.  The batch plugin used to do this, but no longer works in Grails 2.2.

Download
========

(Note: this will not work yet until it is accepted by grails.org)

add `compile(":job-runner:0.1")` to BuildConfig.groovy

Basic Usage
===========

This plugins allows you to run any bean that is accessible in the ApplicationContext which has a no argument `execute()` method.  To use it, simply give the name of stored bean in the ApplicationContext.  Assuming you have the service foo:

```groovy
class FooService {
  def execute() {
    log.error "hello from foo"
  }
}
```

simply call `grails run-job foo`, and you will get the following output:

```
$ grails run-job foo
| Error 2013-05-20 21:34:10,980 [main] ERROR FooService  - hello from foo
```

(Note: we are logging at the error level to make sure the log message is not ignored)

The plugin also detects if the application is running locally (aka `run-app`), and will use that ApplicationContext instead of bootstrapping a headless application.

Advanced Usage
==============
Coming soon!
