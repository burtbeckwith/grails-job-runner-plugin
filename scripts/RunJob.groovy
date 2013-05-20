includeTargets << new File("$jobRunnerPluginDir/scripts/_RunJob.groovy")

target(main: "Runs a job from the passed name") {
    grailsConsole.info "I ran"
}

setDefaultTarget(main)
