includeTargets << grailsScript("_GrailsBootstrap")

def usage = """
run-job <job-name> [options]

the job must be a service name that is accessible through the
ApplicationContext, the service must also have an execute
method.  Any options will be injected into the service
according to matching field names.  If you want to avoid
possible side effects while using run-app, make sure the
service uses the 'prototype' scope.

"""

parseArguments()

if (argsMap.params.contains("help")) {
    grailsConsole.info usage
}
else {
    grailsConsole.info "I was imported, yay"
}

