import grails.util.Holders

includeTargets << grailsScript("_GrailsBootstrap")

def usage = """

////////////////////////////////
// RUN-JOB USAGE              //
////////////////////////////////

run-job <job-name> [options]

the job must be a service name that is accessible through the
ApplicationContext, the service must also have an execute
method.  Any options will be injected into the service
according to matching field names.  If you want to avoid
possible side effects while using run-app with options, make
sure the service uses the 'prototype' scope.

Options:
  -h: returns this message
  -u or -usage: prints usage of a job

"""
target(runJob: "Runs a job from the passed name") {
    //noinspection GroovyAssignabilityCheck
    depends(
            parseArguments,
            validateArguments,
            initializeAppCtx,
            findAndRunJob
    )
}

target(validateArguments: "makes sure the arguments are correct, prints help if requested") {
    if (argsMap.containsKey("h")) {
        grailsConsole.info usage
    } else if (argsMap.params.size != 1) {
        grailsConsole.error "1 job must be specified, run grails run-job -h for more info"
        exit(1)
    }

    //noinspection GroovyAssignabilityCheck
    jobName = argsMap.params[0]
}


target(initializeAppCtx: "either grabs the ApplicationContext from the currently running application, or bootstraps one") {
    try {
        appCtx = Holders.applicationContext
        grailsConsole.info "running $jobName from already running application"
    } catch (Exception) {
        grailsConsole.info "running $jobName as a commandline job with a bootstrapped headless application"
        //appCtx will be set by depending on bootstrap
        depends(bootstrap)
    }
}

target(findAndRunJob: "finds and validates the job to run") {
    //noinspection GroovyAssignabilityCheck
    depends(validateArguments, initializeAppCtx)
    def job = findJob(jobName)
    if (job == null) {
        grailsConsole.error "job $jobName does not exist in the application"
        exit(1)
    }

    try {
        def injector = appCtx.argumentInjectorService
        //TODO: should check that execute exists before calling this
        injector.injectArguments(job, argsMap)
        job.execute()
    } catch (MissingMethodException) {
        grailsConsole.error "job $jobName exists, but it does not have an [execute()] method"
        exit(1)
    }
}

def findJob(String jobName) {
    def job = appCtx."$jobName"
    if (job == null) {
        job = appCtx."${jobName}Service"
    }

    return job
}


