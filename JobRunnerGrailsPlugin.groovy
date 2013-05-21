class JobRunnerGrailsPlugin {
    def version = "0.1"
    def grailsVersion = "2.0 > *"
    def pluginExcludes = [
            "grails-app/services/job/runner/tests/*",
    ]

    def title = "Job Runner Plugin" // Headline display name of the plugin
    def author = "Tommy Barker"
    def authorEmail = "mingus.karate@gmail.com"
    def description = "Allows running any bean with an execute method on the command line while maintaining transactions and service injection"
    def organization = [ name: "University of Pennsylvania Libraries", url: "https://github.com/upenn-libraries" ]
    def developers = [ [ name: "Tommy Barker", email: "mingus.karate@gmail.com" ] ]
    def issueManagement = [ system: "GitHub", url: "https://github.com/upenn-libraries/grails-job-runner-plugin/issues" ]
    def documentation = "https://github.com/upenn-libraries/grails-job-runner-plugin"
    def scm = [ url: "https://github.com/upenn-libraries/grails-job-runner-plugin" ]
}
